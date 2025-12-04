package model.javaGL.mesh.primitives;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.MatrixMath;
import model.javaGL.matrix.Vertex;

/**
 * The representation of a point in space. Points in space
 * are represented by a 1x3 matrix.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class Point extends Primitive {
    private Vertex[] iVertices;

    {
        this.iVertices = new Vertex[1];
        this.iVertices[0] = new Vertex();
    }

    /**
     * Creates a new point (1x3 matrix) from an existing matrix
     * @param p1 the first entry
     * @param p2 the second entry
     * @param p3 the third entry
     */
    public Point(final double p1, final double p2, final double p3) {
        super();
        final Matrix<Double> lMatrix = this.iVertices[0];
        lMatrix.set(0, 0, p1);
        lMatrix.set(1, 0, p2);
        lMatrix.set(2, 0, p3);
    }

    /**
     * Creates a new point with 0 values.
     */
    public Point() {
        super();
    }

    @Override
    public void transform(final Matrix<Double> pTransform) {
        this.iVertices = this.transformCopy(pTransform);
    }

    @Override
    public Vertex[] transformCopy(final Matrix<Double> pTransform) {
        return new Vertex[] {MatrixMath.vertexMultiply(pTransform, this.iVertices[0])};
    }

    @Override
    public Point clone() {
        final Point lDupe;
        lDupe = (Point) super.clone();
        lDupe.iVertices[0] = this.iVertices[0].clone();
        lDupe.setColor(this.color());
        return lDupe;
    }

    @Override
    public Vertex[] points() {
        return this.iVertices;
    }
}
