package model.javaGL.mesh.primitives;

import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.MatrixMath;
import model.javaGL.matrix.Vertex;

/**
 * The representation of the line primitive
 */
public class Line extends Primitive {
    private Vertex[] iVertices;

    {
        this.iVertices = new Vertex[2];
        this.iVertices[0] = new Vertex();
        this.iVertices[1] = new Vertex();
    }

    @Override
    public Vertex[] points() {
        return this.iVertices;
    }

    @Override
    public Vertex[] transformCopy(final Matrix<Double> pTransform) {
        final Vertex[] lCopy = new Vertex[2];
        lCopy[0] = MatrixMath.vertexMultiply(pTransform, this.iVertices[0]);
        lCopy[1] = MatrixMath.vertexMultiply(pTransform, this.iVertices[1]);
        return lCopy;
    }

    @Override
    public void transform(final Matrix<Double> pDoubleMatrix) {
        this.iVertices = this.transformCopy(pDoubleMatrix);
    }

    @Override
    public Line clone() {
        final Line lDupe;
        lDupe = (Line) super.clone();
        for (int i = 0; i < this.iVertices.length; i++) lDupe.iVertices[i] = this.iVertices[i].clone();
        return lDupe;
    }
}
