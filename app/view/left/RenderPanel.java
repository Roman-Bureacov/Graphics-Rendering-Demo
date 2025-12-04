package view.left;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import model.javaGL.render.Camera;
import model.javaGL.render.Raster;
import model.javaGL.render.Renderer;
import model.javaGL.space.Space;
import view.Base;
import view.util.TextFieldFilter;

/**
 * Creates a new panel that will house the rendering information.
 */
public class RenderPanel extends JPanel {
    private final JLabel iRenderStatsLabel = new JLabel();
    private final JTextField iFocalLengthProp = new JTextField();
    private final JTextField iApertureWidthProp = new JTextField();
    private final JTextField iApertureHeightProp = new JTextField();
    private final JTextField iImageWidthProp = new JTextField();
    private final JTextField iImageHeightProp = new JTextField();
    private final RasterPanel iRasterPanel = new RasterPanel();
    private final JButton iRenderButton = new JButton("Render");

    private final Space iWorkingSpace;
    private final Camera iWorkingCamera;
    private final String iRenderStats =
"""
<html>
<body>
<p> Render Time: %d ms </p>
</body>
</html>
""";

    /**
     * Constructs a new rendering panel.
     * @param pSpace the space to render
     * @param pCamera the camera that will be performing renders
     */
    public RenderPanel(final Space pSpace, final Camera pCamera) {
        super();
        this.iWorkingSpace = pSpace;
        this.iWorkingCamera = pCamera;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // the property config panels on top
        this.add(this.makeConfigPanel());

        // the rendered image in center
        this.add(this.iRasterPanel);

        // the stats on the bottom
        this.add(this.iRenderButton);
        this.add(this.iRenderStatsLabel);

        this.setupActions();

        Base.centerAllComponents(this);
    }

    private void setupActions() {
        this.iRenderButton.addActionListener(e -> {
            final long lStartTime = System.currentTimeMillis();
            
            // set the parameters
            this.iWorkingCamera.setFocalLength(Double.parseDouble(this.iFocalLengthProp.getText()));
            this.iWorkingCamera.setApertureWidth(Double.parseDouble(this.iApertureWidthProp.getText()));
            this.iWorkingCamera.setApertureHeight(Double.parseDouble(this.iApertureHeightProp.getText()));
            this.iWorkingCamera.setImageWidth(Integer.parseInt(this.iImageWidthProp.getText()));
            this.iWorkingCamera.setImageHeight(Integer.parseInt(this.iImageHeightProp.getText()));
            
            final Renderer lRender = new Renderer(this.iWorkingSpace, this.iWorkingCamera);
            this.iRasterPanel.drawRaster(lRender.render());

            final long lEndTime = System.currentTimeMillis();
            this.iRenderStatsLabel.setText(this.iRenderStats.formatted(lEndTime - lStartTime));
        });
    }

    private JPanel makeConfigPanel() {
        final JPanel lConfigs = new JPanel();
        lConfigs.setLayout(new BorderLayout());

        // camera options, left
        lConfigs.add(this.makeCameraConfigs(), BorderLayout.WEST);

        // image configs, right
        lConfigs.add(this.makeImageConfigs(), BorderLayout.EAST);

        // defaults
        this.iFocalLengthProp.setText("10");
        this.iApertureWidthProp.setText("22");
        this.iApertureHeightProp.setText("22");
        this.iImageWidthProp.setText("480");
        this.iImageHeightProp.setText("480");

        return lConfigs;
    }

    private JPanel makeCameraConfigs() {
        final JPanel lCameraConfigs = new JPanel();
        lCameraConfigs.setLayout(new BoxLayout(lCameraConfigs, BoxLayout.Y_AXIS));

        // focal length
        lCameraConfigs.add(new JLabel("Focal Length"));
        final JPanel lFocalLengthConfig = new JPanel();
        ((AbstractDocument) this.iFocalLengthProp.getDocument())
                .setDocumentFilter(TextFieldFilter.getFilter("double"));
        lFocalLengthConfig.add(this.iFocalLengthProp);
        lFocalLengthConfig.add(new JLabel("mm"));
        lCameraConfigs.add(lFocalLengthConfig);

        lFocalLengthConfig.setMaximumSize(lFocalLengthConfig.getPreferredSize());

        // aperture width
        lCameraConfigs.add(new JLabel("Aperture width"));
        final JPanel lAperWidthConf = new JPanel();
        ((AbstractDocument) this.iApertureWidthProp.getDocument())
                .setDocumentFilter(TextFieldFilter.getFilter("double"));
        lAperWidthConf.add(this.iApertureWidthProp);
        lAperWidthConf.add(new JLabel("mm"));
        lAperWidthConf.setMaximumSize(lAperWidthConf.getPreferredSize());
        lCameraConfigs.add(lAperWidthConf);


        // aperture height
        lCameraConfigs.add(new JLabel("Aperture height"));
        final JPanel lAperHeightConf = new JPanel();
        ((AbstractDocument) this.iApertureHeightProp.getDocument())
                .setDocumentFilter(TextFieldFilter.getFilter("double"));
        lAperHeightConf.add(this.iApertureHeightProp);
        lAperHeightConf.add(new JLabel("mm"));
        lAperHeightConf.setMaximumSize(lAperHeightConf.getPreferredSize());
        lCameraConfigs.add(lAperHeightConf);


        return lCameraConfigs;
    }

    private JPanel makeImageConfigs() {
        final JPanel lImageConfigs = new JPanel();
        lImageConfigs.setLayout(new BoxLayout(lImageConfigs, BoxLayout.Y_AXIS));

        // image width
        lImageConfigs.add(new JLabel("Image width"));
        final JPanel lImageWidthConf = new JPanel();
        ((AbstractDocument) this.iImageWidthProp.getDocument())
                .setDocumentFilter(TextFieldFilter.getFilter("integer"));
        lImageWidthConf.add(this.iImageWidthProp);
        lImageWidthConf.add(new JLabel("px"));
        lImageWidthConf.setMaximumSize(lImageWidthConf.getPreferredSize());
        lImageConfigs.add(lImageWidthConf);


        // image height
        lImageConfigs.add(new JLabel("Image height"));
        final JPanel lImageHeightConf = new JPanel();
        ((AbstractDocument) this.iImageHeightProp.getDocument())
                .setDocumentFilter(TextFieldFilter.getFilter("integer"));
        lImageHeightConf.add(this.iImageHeightProp);
        lImageHeightConf.add(new JLabel("px"));
        lImageHeightConf.setMaximumSize(lImageHeightConf.getPreferredSize());
        lImageConfigs.add(lImageHeightConf);

        return lImageConfigs;
    }

    private static class RasterPanel extends JPanel {

        private BufferedImage iBuffer;

        RasterPanel() {
            super();
            final int lDim = Base.BASE_WIDTH/3;
            this.setMaximumSize(new Dimension(lDim, lDim));
            this.setMinimumSize(new Dimension(lDim, lDim));
            this.setPreferredSize(new Dimension(lDim, lDim));
            this.iBuffer = new BufferedImage(lDim, lDim, BufferedImage.TYPE_INT_RGB);
            this.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        @Override
        protected void paintComponent(final Graphics pGraphics) {
            super.paintComponent(pGraphics);
            pGraphics.drawImage(this.iBuffer, 0, 0, null);
        }

        public void drawRaster(final Raster pRaster) {
            this.iBuffer = new BufferedImage(pRaster.width(), pRaster.height(), BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < pRaster.width(); x++) {
                for (int y = 0; y < pRaster.height(); y++) {
                    this.iBuffer.setRGB(x, y, pRaster.getPixel(y, x));
                }
            }
            repaint();
        }
    }

}
