package model.javaGL.mesh.primitives;

import model.javaGL.matrix.DoubleMatrix;

/**
 * Representation of basic primitives.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public abstract class Primitive {

    private int iColor = 0x000000;

    /**
     * Returns the matrix representing this primitive.
     * @return the points as column vectors. No sorting is guaranteed.
     */
    public abstract DoubleMatrix points();

    /**
     * Applies a matrix transformation to the set of points
     * defining this primitive.
     * @param pDoubleMatrix the matrix representing the linear transformation
     * @return the resulting transformation
     */
    public abstract void transform(final DoubleMatrix pDoubleMatrix);

    /**
     * Gets the color value from this primitive
     * @return the RGB value
     */
    int color() {
        return this.iColor;
    }

    /**
     * Sets the color for this primitive.
     * @param pColor the (ideally hex) RGB value
     */
    void setColor(final int pColor) {
        this.iColor = pColor;
    }

    /**
     * Constructs a copy of this primitive.
     * @return the duplicate of this primitive
     */
    public abstract Primitive copy();
}
