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

    /**
     * Constructs a renderer with the world space provided
     * @param pSpace the world space to render
     */
    public Renderer(final Space pSpace) {
        super();
        this.iWorld = pSpace;
    }

    /**
     * Initiates and conducts rendering.
     */
    public void render() {
        // TODO: ???
    }

}
