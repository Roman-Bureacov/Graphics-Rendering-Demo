package model.javaGL.mesh.primitives;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.MatrixMath;

/**
 * The representation of a point in space. Points in space
 * are represented by a 1x3 matrix.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class Point extends Primitive {
    private DoubleMatrix iDoubleMatrix;

    {
        this.iDoubleMatrix = new DoubleMatrix(1, 3);
    }

    /**
     * Creates a new point (1x3 matrix) from an existing matrix
     * @param p1 the first entry
     * @param p2 the second entry
     * @param p3 the third entry
     */
    public Point(final double p1, final double p2, final double p3) {
        super();
        this.iDoubleMatrix.set(0, 0, p1);
        this.iDoubleMatrix.set(1, 0, p2);
        this.iDoubleMatrix.set(2, 0, p3);
    }

    /**
     * Creates a new point with 0 values.
     */
    public Point() {
        super();
    }

    @Override
    public void transform(final DoubleMatrix pDoubleMatrix) {
        this.iDoubleMatrix = MatrixMath.matrixMultiply(pDoubleMatrix, this.points());
    }

    @Override
    public Primitive copy() {
        final Point lDupe = new Point();
        lDupe.iDoubleMatrix = this.iDoubleMatrix.copy();
        return lDupe;
    }

    @Override
    public DoubleMatrix points() {
        return this.iDoubleMatrix;
    }
}
