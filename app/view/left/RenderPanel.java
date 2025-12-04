package view.left;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import model.javaGL.render.Raster;
import view.Base;
import view.util.TextFieldFilter;

/**
 * Creates a new panel that will house the rendering information.
 */
public class RenderPanel extends JPanel {
    private final JLabel iRenderStatsLabel = new JLabel();
    private final JTextField iFocalLengthProp = new JTextField();
    private final JTextField iApertureWidth = new JTextField();
    private final JTextField iApertureHeight = new JTextField();
    private final JTextField iImageWidth = new JTextField();
    private final JTextField iImageHeight = new JTextField();
    private final JPanel iRasterPanel = new RasterPanel();

    private final String iRenderStats =
"""
<body>

</body>
""";

    /**
     * Constructs a new rendering panel.
     */
    public RenderPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // the property config panels on top
        this.add(this.makeConfigPanel());

        // the rendered image in center
        this.add(this.iRasterPanel);

        // the stats on the bottom
        this.add(this.iRenderStatsLabel);
        this.iRenderStatsLabel.setText(this.iRenderStats);

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
        this.iApertureWidth.setText("22");
        this.iApertureHeight.setText("22");
        this.iImageWidth.setText("720");
        this.iImageHeight.setText("480");

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
        ((AbstractDocument) this.iApertureWidth.getDocument())
                .setDocumentFilter(TextFieldFilter.getFilter("double"));
        lAperWidthConf.add(this.iApertureWidth);
        lAperWidthConf.add(new JLabel("mm"));
        lAperWidthConf.setMaximumSize(lAperWidthConf.getPreferredSize());
        lCameraConfigs.add(lAperWidthConf);


        // aperture height
        lCameraConfigs.add(new JLabel("Aperture height"));
        final JPanel lAperHeightConf = new JPanel();
        ((AbstractDocument) this.iApertureHeight.getDocument())
                .setDocumentFilter(TextFieldFilter.getFilter("double"));
        lAperHeightConf.add(this.iApertureHeight);
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
        ((AbstractDocument) this.iImageWidth.getDocument())
                .setDocumentFilter(TextFieldFilter.getFilter("integer"));
        lImageWidthConf.add(this.iImageWidth);
        lImageWidthConf.add(new JLabel("px"));
        lImageWidthConf.setMaximumSize(lImageWidthConf.getPreferredSize());
        lImageConfigs.add(lImageWidthConf);


        // image height
        lImageConfigs.add(new JLabel("Image height"));
        final JPanel lImageHeightConf = new JPanel();
        ((AbstractDocument) this.iImageHeight.getDocument())
                .setDocumentFilter(TextFieldFilter.getFilter("integer"));
        lImageHeightConf.add(this.iImageHeight);
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
