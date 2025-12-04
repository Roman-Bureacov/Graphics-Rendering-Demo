package model.javaGL.render;

import java.util.Arrays;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.MatrixMath;

/**
 * A representation of the camera in a world.
 * <br>
 * Does not implement resolution gating.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class Camera {
    private Matrix<Double> iOriginTransform = DoubleMatrix.identity(4);
    private Matrix<Double> iInverse = DoubleMatrix.identity(4);
    private boolean iChanged = true; // if we need to calculate the inverse matrix
    private boolean iApertureChanged = true;

    // camera properties
    private double iNearPlane = 1;
    private double iFarPlane = 10;
    private double iFocalLength = this.iNearPlane;
    private double iApertureWidth;
    private double iApertureHeight;
    private int iImageWidth;
    private int iImageHeight;

    private double iAngleOfViewHorizontal;
    private double iAngleOfViewVertical;
    /** depicts the canvas coordinates, ordered [l, r, t, b] */
    private double[] iCanvas;
    private double iFilmGateAspectRatio;
    private double iResolutionGateAspectRatio;

    /**
     * Creates a basic camera at the origin with a near plane of 1 and far plane of 10
     * @param pFocalLength the focal length in mm
     * @param pApertureWidth the aperture width in mm
     * @param pApertureHeight the aperture height in mm
     * @param pImageWidth the image width in pixels
     * @param pImageHeight the image height in pixels
     *
     */
    public Camera(final double pFocalLength,
                  final double pApertureWidth,
                  final double pApertureHeight,
                  final int pImageWidth,
                  final int pImageHeight) {
        super();
        this.iFocalLength = pFocalLength;
        this.iApertureWidth = pApertureWidth;
        this.iApertureHeight = pApertureHeight;
        this.iImageWidth = pImageWidth;
        this.iImageHeight = pImageHeight;

        this.iResolutionGateAspectRatio = (double) this.iImageWidth / this.iImageHeight;
        this.iFilmGateAspectRatio = this.iApertureWidth / this.iApertureHeight;
    }

    /**
     * Retrieves the inverse of this camera's transform matrix,
     * effectively producing a world-to-camera transformation.
     * <br>
     * Computes the inverse matrix if necessary.
     * @return the world-to-camera transformation matrix
     */
    public Matrix<Double> inverseMatrix() {
        if (this.iChanged) {
            this.iChanged = false;
            this.iInverse = MatrixMath.inverse(this.iOriginTransform);
        }
        return this.iInverse;
    }

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
        this.iChanged = true;
        this.iOriginTransform = MatrixMath.matrixMultiply(pMatrix, this.iOriginTransform);
    }

    /**
     * Sets the distance of the near plane from the eye of the camera
     * @param pNear the distance
     */
    public void setNearPlane(final double pNear) {
        this.iNearPlane = pNear;
    }

    /**
     * Sets the distance of the far plane from the eye of the camera
     * @param pFar the distance
     */
    public void setFarPlane(final double pFar) {
        this.iFarPlane = pFar;
    }

    /**
     * Gets this camera's focal length
     * @return the focal length in mm
     */
    public double getFocalLength() {
        return this.iFocalLength;
    }

    /**
     * Sets this camera's focal length
     * @param pFocalLength the focal length in mm
     */
    public void setFocalLength(final double pFocalLength) {
        this.iFocalLength = pFocalLength;
    }

    /**
     * Gets this camera's aperture width
      * @return the aperture width in mm
     */
    public double getApertureWidth() {
        return this.iApertureWidth;
    }

    /**
     * Sets this camera's aperture width
     * @param pWidth the width in mm
     */
    public void setApertureWidth(final double pWidth) {
        this.iApertureWidth = pWidth;
        this.iFilmGateAspectRatio = this.iApertureWidth / this.iApertureHeight;
        this.iApertureChanged = true;
    }

    /**
     * Gets this camera's aperture height
     * @return the aperture height in mm
     */
    public double getApertureHeight() {
        return this.iApertureHeight;
    }

    /**
     * Sets this camera's aperture height
     * @param pHeight the aperture height in mm
     */
    public void setApertureHeight(final double pHeight) {
        this.iApertureHeight = pHeight;
        this.iFilmGateAspectRatio = this.iApertureWidth / this.iApertureHeight;
        this.iApertureChanged = true;
    }

    /**
     * Gets this camera's image width
     * @return the width in pixels
     */
    public int getImageWidth() {
        return this.iImageWidth;
    }

    /**
     * Sets this camera's image width
     * @param pWidth the width in pixels
     */
    public void setImageWidth(final int pWidth) {
        this.iImageWidth = pWidth;
        this.iResolutionGateAspectRatio = (double) this.iImageWidth / this.iImageHeight;
    }

    /**
     * Gets this camera's image height
     * @return the height in pixels
     */
    public int getImageHeight() {
        return this.iImageHeight;
    }

    /**
     * Sets this camera's image height
     * @param pHeight the height in pixels
     */
    public void setImageHeight(final int pHeight) {
        this.iImageHeight = pHeight;
        this.iResolutionGateAspectRatio = (double) this.iImageWidth / this.iImageHeight;
    }

    /**
     * Retrieves the distance of the near plane
     * @return the distance from the eye of the camera to the near plane
     */
    public double getNearPlane() {
        return this.iNearPlane;
    }

    /**
     * Retrieves the distance of the far plane
     * @return the distance from the eye of the camera ot the far plane
     */
    public double getFarPlane() {
        return this.iFarPlane;
    }

    /**
     * Retrieves the canvas coordinates of this camera, organized as [l, r, t, b]
     * @return a copy of the internal canvas coordinates
     */
    public double[] getCanvasCoordinates() {
        if (this.iApertureChanged) {
            final double lCanvasWidth = (this.iApertureWidth) / this.iFocalLength * this.iNearPlane;
            // l, r, t, b
            this.iCanvas[0] = -lCanvasWidth / 2;
            this.iCanvas[1] = -this.iCanvas[0];
            this.iCanvas[2] = lCanvasWidth / 2 * this.iFilmGateAspectRatio;
            this.iCanvas[3] = -this.iCanvas[2];
            this.iApertureChanged = false;
        }

        return Arrays.copyOf(this.iCanvas, this.iCanvas.length);
    }
}
