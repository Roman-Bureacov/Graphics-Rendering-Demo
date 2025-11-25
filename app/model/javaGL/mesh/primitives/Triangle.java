package model.javaGL.mesh.primitives;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.MatrixMath;

/**
 * The representation of the triangle primitive.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public final class Triangle extends Primitive {
    private DoubleMatrix iDoubleMatrix;

    {
        this.iDoubleMatrix = new DoubleMatrix(3, 3);
    }

    @Override
    public DoubleMatrix points() {
        return this.iDoubleMatrix;
    }

    @Override
    public void transform(final DoubleMatrix pDoubleMatrix) {
        this.iDoubleMatrix = MatrixMath.matrixMultiply(pDoubleMatrix, this.points());
    }

    @Override
    public Primitive copy() {
        final Triangle lDupe = new Triangle();
        lDupe.iDoubleMatrix = this.iDoubleMatrix.copy();
        return lDupe;
    }
}
