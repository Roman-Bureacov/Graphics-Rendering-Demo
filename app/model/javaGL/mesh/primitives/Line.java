package model.javaGL.mesh.primitives;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.MatrixMath;
import model.javaGL.matrix.Vertex;

/**
 * The representation of the line primitive
 */
public class Line extends Primitive {
    private Matrix<Double>[] iVertices;

    {
        this.iVertices = new Vertex[2];
        this.iVertices[0] = new Vertex();
        this.iVertices[1] = new Vertex();
    }

    @Override
    public Matrix<Double>[] points() {
        return this.iVertices;
    }

    @Override
    public Matrix<Double>[] transformCopy(final Matrix<Double> pTransform) {
        final Matrix<Double>[] lCopy = new Vertex[2];
        lCopy[0] = MatrixMath.matrixMultiply(pTransform, this.iVertices[0]);
        lCopy[1] = MatrixMath.matrixMultiply(pTransform, this.iVertices[1]);
        return lCopy;
    }

    @Override
    public void transform(final Matrix<Double> pDoubleMatrix) {
        this.iVertices = this.transformCopy(pDoubleMatrix);
    }

    @Override
    public Primitive copy() {
        final Line lDupe = new Line();
        for (int i = 0; i < 2; i++) {
            lDupe.iVertices[i] = this.iVertices[i].copy();
        }
        return lDupe;
    }
}
