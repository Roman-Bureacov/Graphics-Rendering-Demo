package model.javaGL.render;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.IntMatrix;
import model.javaGL.matrix.Matrix;

/**
 * Representation of the raster, simply a non-resizable
 * grid of pixels with a depth buffer.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class DepthBuffer {
    Matrix<Double> iDepthBuffer;

    /**
     * Constructs a depth buffer, with a depth buffer matrix of maximum depth for each pixel.
     * @param pWidth the width of the raster
     * @param pHeight the height of the raster
     */
    public DepthBuffer(final int pWidth, final int pHeight) {
        super();
        this.iDepthBuffer = new DoubleMatrix(pWidth, pHeight);
        for (int row = 0; row < pHeight; row++) {
            for (int col = 0; col < pWidth; col++) {
                this.iDepthBuffer.set(row, col, Double.MAX_VALUE);
            }
        }
    }

    /**
     * Sets the depth value at the pixel
     * @param pRow the row
     * @param pCol the column
     * @param pDepth the depth value
     */
    public void setDepth(final int pRow, final int pCol, final double pDepth) {
        this.iDepthBuffer.set(pRow, pCol, pDepth);
    }

    /**
     * Gets the depth value at the pixel
     * @param pRow the row
     * @param pCol the column
     * @return the depth value in the buffer at the specified pixel
     */
    public double getDepth(final int pRow, final int pCol) {
        return this.iDepthBuffer.get(pRow, pCol);
    }

    /**
     * Gets the width of this raster.
     * @return the width in pixels
     */
    public int width() {
        return this.iDepthBuffer.columnCount();
    }

    /**
     * Gets the height of this raster.
     * @return the height in pixels
     */
    public int height() {
        return this.iDepthBuffer.rowCount();
    }
}
