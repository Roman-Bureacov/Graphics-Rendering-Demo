package model.javaGL.render;

import java.util.HashSet;
import java.util.Set;

import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.MatrixMath;
import model.javaGL.matrix.Vertex;
import model.javaGL.mesh.Mesh;
import model.javaGL.mesh.primitives.Line;
import model.javaGL.mesh.primitives.Point;
import model.javaGL.mesh.primitives.Primitive;
import model.javaGL.mesh.primitives.Triangle;
import model.javaGL.space.Space;

/**
 * Class that handles rendering of a three-dimensional
 * space into a two-dimensional screen.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class Renderer {
    private final Space iWorld;
    private final Raster iScreen;
    private final DepthBuffer iDepthBuffer;
    private final Camera iCamera;
    final double[] iCanvasCoordinates;

    private Set<Primitive> iToRender;

    /**
     * Constructs a renderer with the world space provided
     * @param pSpace the world space to render
     * @param pWidth the width of the raster
     * @param pHeight the height of the raster
     */
    public Renderer(final Space pSpace, final int pWidth, final int pHeight) {
        super();
        this.iWorld = pSpace;
        this.iDepthBuffer = new DepthBuffer(pWidth, pHeight);
        this.iScreen = new Raster(pWidth, pHeight);
        this.iCamera = new Camera(0.1, 22, 35, pWidth, pHeight);
        this.iCanvasCoordinates = this.iCamera.getCanvasCoordinates();
    }

    /**
     * Initiates and conducts rendering.
     */
    public void render() {
        this.iToRender = new HashSet<>();

        // start by assuming the primitive is contained in the view volume
        for (final Mesh mesh : this.iWorld.meshes()) {
            final Matrix<Double> lWorldToCamera = this.iCamera.inverseMatrix();
            final Matrix<Double> lObjToCamera = MatrixMath.matrixMultiply(lWorldToCamera, mesh.getTransform());
            
            for (final Primitive p : mesh.primitives()) {

                final Vertex[] lRasterVerts = new Vertex[3];
                for (int i = 0; i < lRasterVerts.length; i++) {
                    lRasterVerts[i] = p.points()[i].clone();

                    // transform into camera coordinates
                    lRasterVerts[i] = MatrixMath.vertexMultiply(lObjToCamera, lRasterVerts[i]);
                    final Vertex lVert = lRasterVerts[i];

                    this.toScreen(lVert); // project onto screen space
                    this.toNDC(lVert); // project onto NDC
                    this.toRaster(lVert); // convert into raster space
                }

                // bounding box
                double lBoxMinX = this.minimum(
                        lRasterVerts[0].x(),
                        lRasterVerts[1].x(),
                        lRasterVerts[2].x()
                );
                double lBoxMinY = this.minimum(
                        lRasterVerts[0].y(),
                        lRasterVerts[1].y(),
                        lRasterVerts[2].y()
                );
                double lBoxMaxX = this.maximum(
                        lRasterVerts[0].x(),
                        lRasterVerts[1].x(),
                        lRasterVerts[2].x()
                );
                double lBoxMaxY = this.maximum(
                        lRasterVerts[0].y(),
                        lRasterVerts[1].y(),
                        lRasterVerts[2].y()
                );

                // reject if outside
                if (lBoxMaxX < 0 || this.iCamera.getImageWidth() - 1 < lBoxMinX) continue;
                else if (lBoxMaxY < 0 || this.iCamera.getImageHeight() - 1 < lBoxMinY) continue;

                // refit bounding box
                lBoxMinX = Math.max(0, lBoxMinX);
                lBoxMinY = Math.max(0, lBoxMinY);
                lBoxMaxX = Math.min(this.iCamera.getImageWidth() - 1, lBoxMaxX);
                lBoxMaxY = Math.min(this.iCamera.getImageWidth() - 1, lBoxMaxY);

                // rasterize
                for (int x = (int) lBoxMinX; x < lBoxMaxX; x++) {
                    for (int y = (int) lBoxMinY; y < lBoxMaxY; y++) {
                        // is this pixel contained in the triangle?
                        final double lEF0 = this.edgeFunction(
                                lRasterVerts[0], lRasterVerts[1],
                                x + 0.5, y + 0.5
                        );
                        final double lEF1 = this.edgeFunction(
                                lRasterVerts[1], lRasterVerts[2],
                                x + 0.5, y + 0.5
                        );
                        final double lEF2 = this.edgeFunction(
                                lRasterVerts[2], lRasterVerts[0],
                                x + 0.5, y + 0.5
                        );

                        // only test if the point lies in the triangle.
                        // do not perform face culling (too complex for demo).
                        // this can be achieved by simply examining that the sign
                        // is the same for all edge function tests
                        final boolean lCase1 = lEF0 <= 0 && lEF1 <= 0 && lEF2 <= 0;
                        final boolean lCase2 = lEF0 >= 0 && lEF1 >= 0 && lEF2 >= 0;
                        if (lCase1 || lCase2) {
                            // convert to barycentric coordinates
                            final double lArea = this.edgeFunction(
                                    lRasterVerts[0], lRasterVerts[1],
                                    lRasterVerts[2].x(), lRasterVerts[2].y()
                            );

                            // assume all values are positive (all triangles to be rendered have "clockwise" vertices)
                            final double lDepthZ = 1 / (
                                    lRasterVerts[0].z() * Math.abs(lEF0 / lArea)
                                    + lRasterVerts[1].z() * Math.abs(lEF1 / lArea)
                                    + lRasterVerts[2].z() * Math.abs(lEF2 / lArea)
                            );

                            // depth test
                            if (lDepthZ < this.iDepthBuffer.getDepth(y, x)) { // row (y), column (x)
                                this.iScreen.setPixel(y, x, (int) (p.color() / lDepthZ));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * The edge function for a set of vertices defined clockwise.
     * @param pV1 the first vertex
     * @param pV2 the second vertex
     * @param pTestX the sample x
     * @param pTestY the sample y
     * @return positive if the sample is on the right of the line formed by the vertices, negative otherwise
     */
    private double edgeFunction(final Vertex pV1, final Vertex pV2, final double pTestX, final double pTestY) {
        // https://www.scratchapixel.com/lessons/3d-basic-rendering/rasterization-practical-implementation/rasterization-stage.html
        return (pTestX - pV1.x()) * (pV2.y() - pV1.y()) - (pTestY - pV1.y()) * (pV2.x() - pV1.x());
    }

    private double minimum(final double pA, final double pB, final double pC) {
        return Math.min(pA, Math.min(pB, pC));
    }

    private double maximum(final double pA, final double pB, final double pC) {
        return Math.max(pA, Math.max(pB, pC));
    }

    /**
     * Converts the vertex x and y camera into Normalized Device Coordinates
     * @param pVert the vertex to convert
     */
    private void toNDC(final Vertex pVert) {
        // x = 2 * screen.x / ( r - l) - ( r + l ) / ( r - l )
        pVert.set(0,
                2 * pVert.get(0) / (this.iCanvasCoordinates[0] - this.iCanvasCoordinates[1]) - (
                        (this.iCanvasCoordinates[0] + this.iCanvasCoordinates[1])
                                / (this.iCanvasCoordinates[0] - this.iCanvasCoordinates[1])
                )
        );
        // y = 2 * screen.x / ( t - b) - ( t + b ) / ( t - b )
        pVert.set(1,
                2 * pVert.get(1) / (this.iCanvasCoordinates[2] - this.iCanvasCoordinates[3]) - (
                        (this.iCanvasCoordinates[2] + this.iCanvasCoordinates[3])
                                / (this.iCanvasCoordinates[2] - this.iCanvasCoordinates[3])
                )
        );
    }

    /**
     * Converts the vertex x and y NDC to raster coordinates
     * @param pVert the vertex
     */
    private void toRaster(final Vertex pVert) {
        pVert.set(0, (pVert.get(0) + 1) / 2 * this.iCamera.getImageWidth());
        pVert.set(1, (1 - pVert.get(1)) / 2 * this.iCamera.getImageHeight());
    }

    /**
     * Converts the vertex x and y camera coordinates to screen coordinates
     * @param pVert the vertex
     */
    private void toScreen(final Vertex pVert) {
        // perspective divide
        final double lX = pVert.get(0);
        final double lY = pVert.get(1);
        final double lZ = pVert.get(2);
        pVert.set(0, this.iCamera.getNearPlane() * lX / -lZ);
        pVert.set(1, this.iCamera.getNearPlane() * lY / -lZ);
        pVert.set(1, -lZ);
    }

    /**
     * Goes through every primitive provided and colors a pixel on the raster
     * while performing depth test
     * @param pPrimitives the set of primitives the render
     */
    private void renderScene(final Set<Primitive> pPrimitives) {

    }

    /**
     * Tests if the primitive is contained in the view frustrum as determined by the camera.
     * Note that partially contained primitives are also considered to be a contained in the
     * scene.
     * @param pPrimitive the primitive to test
     */
    private void isContained(final Primitive pPrimitive) {
        final int lCount = pPrimitive.points().length;
        final Vertex[] lPoints = new Vertex[lCount];
        for (int i = 0; i < lCount; i++) {
            final Vertex lVert = pPrimitive.points()[i];
            lPoints[i] = (MatrixMath.vertexMultiply(this.iCamera.inverseMatrix(), lVert));
        }
    }

    /**
     * Transforms the camera coordinates for this renderer
     * @param pTransform the transformation matrix
     */
    public void transformCamera(final Matrix<Double> pTransform) {
        this.iCamera.transform(pTransform);
    }

}
