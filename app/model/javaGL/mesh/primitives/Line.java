package model.javaGL.mesh.primitives;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.MatrixMath;

/**
 * The representation of the line primitive
 */
public class Line extends Primitive {
    private DoubleMatrix iDoubleMatrix;

    {
        this.iDoubleMatrix = new DoubleMatrix(3, 2);
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
        final Line lDupe = new Line();
        lDupe.iDoubleMatrix = this.iDoubleMatrix.copy();
        return lDupe;
    }
}
