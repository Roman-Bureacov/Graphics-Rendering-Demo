package model.javaGL.mesh.factory;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.Matrix;
import model.javaGL.mesh.Mesh;
import model.javaGL.mesh.primitives.Triangle;

/**
 * Static class dedicated to making pre-defined meshes.
 */
public final class MeshFactory {
    /** name for the chair object */
    public final static String CHAIR = "chair";

    /**
     * Constructs and returns the basic mesh object asked for.
     * @param name the mesh name
     * @return the constructed mesh
     */
    public static Mesh create(final String name) {
        return switch(name) {
            case CHAIR -> new Chair();
            default -> throw new IllegalArgumentException("No such mesh");
        };
    }

    /**
     * Convenience method for making 
     * @param pX1 the first vertex x
     * @param pY1 the first vertex y
     * @param pZ1 the first vertex z
     * @param pX2 the second vertex x
     * @param pY2 the second vertex y
     * @param pZ2 the second vertex z
     * @param pX3 the third vertex x
     * @param pY3 the third vertex y
     * @param pZ3 the third vertex z
     * @return the resulting triangle
     */
    static Triangle makeTriangle(
            final double pX1, final double pY1, final double pZ1,
            final double pX2, final double pY2, final double pZ2,
            final double pX3, final double pY3, final double pZ3
    ) {
        final Triangle t = new Triangle();
        final Matrix<Double>[] m = t.points();
        
        m[0].set(0, 0, pX1);
        m[0].set(1, 0, pY1);
        m[0].set(2, 0, pZ1);

        m[1].set(0, 0, pX2);
        m[1].set(1, 0, pY2);
        m[1].set(2, 0, pZ2);

        m[2].set(0, 0, pX3);
        m[2].set(1, 0, pY3);
        m[2].set(2, 0, pZ3);
        
        return t;
    }

}
