package view.right;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.javaGL.matrix.Matrix;
import model.javaGL.space.Space;
import view.Base;

/**
 * A panel representing the transformation controls for this application
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class TransformPanel extends JPanel implements PropertyChangeListener {
    private final JComboBox<String> iSceneObjects;
    private final Space iWorld;

    /**
     * Creates a transformation panel that houses the controls for creating transformation matrices.
     * @param pSpace the space that the transformation
     */
    public TransformPanel(final Space pSpace) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.iSceneObjects = new JComboBox<>();
        this.iSceneObjects.setMaximumSize(new Dimension(
                Base.BASE_WIDTH/2, this.iSceneObjects.getPreferredSize().height
                ));
        this.iWorld = pSpace;

        this.setup();
    }

    /**
     * Helper to set up other components in this panel
     */
    private void setup() {
        // selection of the object to manipulate
        final JPanel lTransformHeader = new JPanel();
        lTransformHeader.setLayout(new BoxLayout(lTransformHeader, BoxLayout.X_AXIS));
        lTransformHeader.add(new JLabel("Scene Object:"));
        for (final String name : this.iWorld.meshesNames()) {
            this.iSceneObjects.addItem(name);
        }
        this.iSceneObjects.setSelectedIndex(0);
        lTransformHeader.add(this.iSceneObjects);

        // transformation tabbed pane
        final TransformationControls lTransformBody = new TransformationControls();
        lTransformBody.addPropertyChangeListener(this);

        this.add(lTransformHeader);
        this.add(lTransformBody);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void propertyChange(final PropertyChangeEvent pEvent) {
        switch (pEvent.getPropertyName()) {
            case TransformationControls.TRANSFORM_EVENT,
                 TransformationControls.ROTATION_EVENT ->
                    this.iWorld.transformExisting(
                            (String) this.iSceneObjects.getSelectedItem(),
                            (Matrix<Double>) pEvent.getNewValue()
                    );
            default -> {}
        }
    }
}
