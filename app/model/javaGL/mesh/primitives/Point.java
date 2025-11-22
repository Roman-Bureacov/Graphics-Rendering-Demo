package model.javaGL.mesh.primitives;

import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.MatrixMath;

/**
 * The representation of a point in space. Points in space
 * are represented by a 1x3 matrix.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class Point extends Primitive {
    private Matrix iMatrix;

    {
        this.iMatrix = new Matrix(1, 3);
    }

    /**
     * Creates a new point (1x3 matrix) from an existing matrix
     * @param p1 the first entry
     * @param p2 the second entry
     * @param p3 the third entry
     */
    public Point(final double p1, final double p2, final double p3) {
        super();
        this.iMatrix.set(0, 0, p1);
        this.iMatrix.set(1, 0, p2);
        this.iMatrix.set(2, 0, p3);
    }

    /**
     * Creates a new point with 0 values.
     */
    public Point() {
        super();
    }

    @Override
    public void transform(final Matrix pMatrix) {
        this.iMatrix = MatrixMath.matrixMultiply(pMatrix, this.points());
    }

    @Override
    public Primitive copy() {
        final Point lDupe = new Point();
        lDupe.iMatrix = this.iMatrix.copy();
        return lDupe;
    }

    @Override
    public Matrix points() {
        return this.iMatrix;
    }
}
