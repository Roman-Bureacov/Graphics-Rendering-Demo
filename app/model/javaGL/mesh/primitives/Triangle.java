package model.javaGL.mesh.primitives;

import model.javaGL.matrix.Matrix;

/**
 * The representation of the triangle primitive.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public final class Triangle extends Primitive {
    private final Matrix iMatrix;

    {
        this.iMatrix = new Matrix(3, 3);
    }

    @Override
    public Matrix points() {
        return this.iMatrix;
    }
}
