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
    private final Matrix<Integer> iPixelColors;

    /**
     * Constructs a raster, with a pixel matrix of 0-color pixels.
     * @param pWidth the width of the raster
     * @param pHeight the height of the raster
     */
    public Raster(final int pWidth, final int pHeight) {
        super();
        this.iPixelColors = new IntMatrix(pWidth, pHeight);
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
     * Gets the width of this raster.
     * @return the width in pixels
     */
    public int width() {
        return this.iPixelColors.columnCount();
    }

    /**
     * Gets the height of this raster.
     * @return the height in pixels
     */
    public int height() {
        return this.iPixelColors.rowCount();
    }
}
