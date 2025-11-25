package view;

import javax.swing.JPanel;

import model.javaGL.render.Renderer;

/**
 * Creates a new panel that will house the rendering information.
 */
public class RenderPanel extends JPanel {
    final Renderer iRenderer;

    /**
     * Constructs a new rendering panel.
     * @param pRenderer the renderer for this panel to use
     */
    public RenderPanel(final Renderer pRenderer) {
        super();
        this.iRenderer = pRenderer;
    }


}
