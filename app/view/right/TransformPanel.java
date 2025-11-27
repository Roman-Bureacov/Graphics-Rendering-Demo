package view.right;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.javaGL.matrix.Matrix;
import model.javaGL.space.Space;

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
        for (final String name : this.iWorld.meshes()) {
            this.iSceneObjects.addItem(name);
        }
        this.iSceneObjects.setSelectedIndex(0);
        lTransformHeader.add(this.iSceneObjects);

        // transformation tabbed pane
        final TransformationControls lTransformBody = new TransformationControls();
        lTransformBody.addPropertyChangeListener(this);

        // transform button
        final JPanel lTransformFooter = new JPanel();
        lTransformFooter.add(new JLabel("Footer"));

        this.add(lTransformHeader);
        this.add(lTransformBody);
        this.add(lTransformFooter);
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
            case TransformationControls.ADD_EVENT ->
                this.iWorld.addTransformExisting(
                        (String) this.iSceneObjects.getSelectedItem(),
                        (Matrix<Double>) pEvent.getNewValue()
                );
            default -> { }
        }
    }
}
