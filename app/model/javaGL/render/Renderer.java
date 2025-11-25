package model.javaGL.render;

import model.javaGL.space.Space;

/**
 * Class that handles rendering of a three-dimensional
 * space into a two-dimensional screen.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class Renderer {
    final Space iWorld;
    final Raster iScreen;

    /**
     * Constructs a renderer with the world space provided
     * @param pSpace the world space to render
     */
    public Renderer(final Space pSpace, final int pWidth, final int pHeight) {
        super();
        this.iWorld = pSpace;
        this.iScreen = new Raster(pWidth, pHeight);
    }

    /**
     * Initiates and conducts rendering.
     */
    public void render() {
        // TODO: ???
    }

}
