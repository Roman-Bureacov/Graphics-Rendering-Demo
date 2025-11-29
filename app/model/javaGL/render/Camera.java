package model.javaGL.render;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.MatrixMath;

/**
 * A representation of the camera in a world
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class Camera {
    private Matrix<Double> iOriginTransform = DoubleMatrix.identity(4);

    /**
     * Returns the transformed origin
     * @return the resulting coordinate system
     */
    public Matrix<Double> transformedOrigin() {
        return this.iOriginTransform;
    }

    /**
     * Transforms this camera by the argument matrix
     * @param pMatrix the transformation matrix
     */
    public void transform(final Matrix<Double> pMatrix) {
        this.iOriginTransform = MatrixMath.matrixMultiply(pMatrix, this.iOriginTransform);
    }
}
