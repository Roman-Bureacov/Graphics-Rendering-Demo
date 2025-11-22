package model.javaGL.mesh.primitives;

import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.MatrixMath;

/**
 * The representation of the triangle primitive.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public final class Triangle extends Primitive {
    private Matrix iMatrix;

    {
        this.iMatrix = new Matrix(3, 3);
    }

    @Override
    public Matrix points() {
        return this.iMatrix;
    }

    @Override
    public void transform(final Matrix pMatrix) {
        this.iMatrix = MatrixMath.matrixMultiply(pMatrix, this.points());
    }

    @Override
    public Primitive copy() {
        final Triangle lDupe = new Triangle();
        lDupe.iMatrix = this.iMatrix.copy();
        return lDupe;
    }
}
