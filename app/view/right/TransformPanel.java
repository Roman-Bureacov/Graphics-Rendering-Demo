package view.right;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        /* TODO: refactor meshes object
        for (final String name : this.iWorld.meshes()) {
            this.iSceneObjects.addItem(name);
        }
         */

        // transformation tabbed pane
        final TransformationControls lTransformBody = new TransformationControls();

        // transform button
        final JPanel lTransformFooter = new JPanel();
        lTransformFooter.add(new JLabel("Footer"));

        this.add(lTransformHeader);
        this.add(lTransformBody);
        this.add(lTransformFooter);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent pEvent) {
        switch (pEvent.getPropertyName()) {
            case TransformationControls.MATRIX_EVENT -> {} // TODO
            case TransformationControls.ROTATION_EVENT -> {} // TODO
            default -> { }
        }
    }
}
