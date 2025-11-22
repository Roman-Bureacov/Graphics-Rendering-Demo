package model.javaGL.mesh.primitives;

import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.MatrixMath;

/**
 * The representation of the line primitive
 */
public class Line extends Primitive {
    private Matrix iMatrix;

    {
        this.iMatrix = new Matrix(3, 2);
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
        final Line lDupe = new Line();
        lDupe.iMatrix = this.iMatrix.copy();
        return lDupe;
    }
}
