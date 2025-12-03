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
    private final Camera iCamera = null;
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
        this.iScreen = new Raster(pWidth, pHeight);
    }

    /**
     * Initiates and conducts rendering.
     */
    public void render() {
        this.iToRender = new HashSet<>();

        // start by assuming the primitive is contained in the view volume
        for (final Mesh mesh : this.iWorld.meshes()) {
            final Matrix<Double> lWorldToCamera = this.iCamera.inverseMatrix();
            for (final Primitive p : mesh.primitives()) {

            }
        }
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
