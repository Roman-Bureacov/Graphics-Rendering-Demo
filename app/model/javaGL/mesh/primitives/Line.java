package model.javaGL.mesh.primitives;

import model.javaGL.matrix.Matrix;

/**
 * The representation of the line primitive
 */
public class Line extends Primitive {
    private final Matrix iMatrix;

    {
        this.iMatrix = new Matrix(3, 2);
    }

    @Override
    public Matrix points() {
        return this.iMatrix;
    }
}
