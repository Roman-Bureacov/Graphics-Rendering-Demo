package model.javaGL.render;

import model.javaGL.matrix.IntMatrix;
import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.DoubleMatrix;

/**
 * Representation of the raster, simply a non-resizable
 * grid of pixels with a depth buffer.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class Raster {
    Matrix<Integer> iPixelColors;
    Matrix<Double> iDepthBuffer;

    /**
     * Constructs a raster, with a pixel and depth buffer matrices.
     * @param pWidth the width of the raster
     * @param pHeight the height of the raster
     */
    public Raster(final int pWidth, final int pHeight) {
        super();
        this.iPixelColors = new IntMatrix(pWidth, pHeight);
        this.iDepthBuffer = new DoubleMatrix(pWidth, pHeight);
    }

    /**
     * Sets the color at the specified pixel.
     * @param pRow the row
     * @param pCol the column
     * @param pColor the (ideally hex) color
     */
    public void setPixel(final int pRow, final int pCol, final int pColor) {
        this.iPixelColors.set(pRow, pCol, pColor);
    }

    /**
     * Gets the specified pixel color value.
     * @param pRow the row
     * @param pCol the column
     * @return the RGB value of the pixel
     */
    public int getPixel(final int pRow, final int pCol) {
        return this.iPixelColors.get(pRow, pCol);
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
     * @return the width
     */
    public int width() {
        return this.iPixelColors.columnCount();
    }

    /**
     * Gets the height of this raster.
     * @return
     */
    public int height() {
        return this.iPixelColors.rowCount();
    }
}
