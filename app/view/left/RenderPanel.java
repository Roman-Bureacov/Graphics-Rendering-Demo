package view.left;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.javaGL.render.Renderer;
import view.Base;

/**
 * Creates a new panel that will house the rendering information.
 */
public class RenderPanel extends JPanel {
    private final Renderer iRenderer;
    private final String iRenderStats =
"""
<body>

</body>
""";

    /**
     * Constructs a new rendering panel.
     * @param pRenderer the renderer for this panel to use
     */
    public RenderPanel(final Renderer pRenderer) {
        super();
        this.iRenderer = pRenderer;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // this.add(render)

        Base.centerAllComponents(this);
    }

    public String getStats() {
        return null;
    }


}
