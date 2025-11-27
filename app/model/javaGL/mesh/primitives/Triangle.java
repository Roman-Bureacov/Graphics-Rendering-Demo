package model.javaGL.mesh.primitives;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.MatrixMath;
import model.javaGL.matrix.Vertex;

/**
 * The representation of the triangle primitive.
 *
 * @author Roman Bureacov
 * @version 2025-22
 */
public final class Triangle extends Primitive {
    private Matrix<Double>[] iVertices;

    {
        this.iVertices = new Vertex[3];
        this.iVertices[0] = new Vertex();
        this.iVertices[1] = new Vertex();
        this.iVertices[2] = new Vertex();
    }

    @Override
    public Matrix<Double>[] points() {
        return this.iVertices;
    }

    @Override
    public Matrix<Double>[] transformCopy(final Matrix<Double> pTransform) {
        final Matrix<Double>[] lCopy = new Vertex[3];
        lCopy[0] = MatrixMath.matrixMultiply(pTransform, this.iVertices[0]);
        lCopy[1] = MatrixMath.matrixMultiply(pTransform, this.iVertices[1]);
        lCopy[2] = MatrixMath.matrixMultiply(pTransform, this.iVertices[2]);
        return lCopy;
    }

    @Override
    public void transform(final Matrix<Double> pDoubleMatrix) {
        this.iVertices = this.transformCopy(pDoubleMatrix);
    }

    @Override
    public Primitive copy() {
        final Triangle lDupe = new Triangle();
        for (int i = 0; i < 2; i++) {
            lDupe.iVertices[i] = this.iVertices[i].copy();
        }
        return lDupe;
    }
}
