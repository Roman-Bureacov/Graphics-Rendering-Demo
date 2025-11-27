package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.Vertex;
import model.javaGL.mesh.Mesh;
import model.javaGL.mesh.factory.MeshFactory;
import model.javaGL.mesh.primitives.Primitive;
import model.javaGL.mesh.primitives.Triangle;
import model.javaGL.space.Space;
import view.left.RenderPanel;
import view.right.TransformPanel;

/**
 * Creates the base upon which the application sites upon.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class Base extends JFrame implements PropertyChangeListener {

    /** the width of the base panel */
    public final static int BASE_WIDTH = 750;
    /** the height of the base panel */
    public final static int BASE_HEIGHT = 500;

    public final Space iWorld = new Space();

    private final JPanel iMainPanel = new JPanel();
    private final RenderPanel iRender = new RenderPanel(null);
    private final TransformPanel iTransform;

    /**
     * Creates a new base for the application to sit upon.
     */
    public Base() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(BASE_WIDTH, BASE_HEIGHT);

        // specifically to make the world meshes visible
        this.setupSpace(); // set up the world
        this.iTransform = new TransformPanel(this.iWorld); // then this can properly populate combo box

        this.iMainPanel.setLayout(new BorderLayout());
        this.iMainPanel.add(this.iRender, BorderLayout.WEST);
        this.iMainPanel.add(this.iTransform, BorderLayout.EAST);
        this.setContentPane(this.iMainPanel);

        this.setVisible(true);
    }

    private void setupSpace() {
        final int lRed = 0xff0000;
        final int lBlue = 0x0000ff;

        Mesh lMesh = MeshFactory.create(MeshFactory.CHAIR);
        lMesh.setName("Chair 1");
        for (final Primitive p : lMesh.primitives()) {
            p.setColor(lRed);
        }
        this.iWorld.addMesh(lMesh);

        lMesh = MeshFactory.create(MeshFactory.CHAIR);
        lMesh.setName("Chair 2");
        for (final Primitive p : lMesh.primitives()) {
            p.setColor(lBlue);
        }
        this.iWorld.addMesh(lMesh);

        // flat triangle
        lMesh = new Mesh();
        lMesh.setName("Triangle");
        final Triangle lTriangle = new Triangle();
        final Vertex[] lVerts = lTriangle.points();
        lVerts[1].set(1, 1d);
        lVerts[2].set(2, 1d);
        lMesh.add(lTriangle);
        this.iWorld.addMesh(lMesh);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent pEvent) {
        switch (pEvent.getPropertyName()) {

            default -> { }
        }
    }

    /**
     * Utility method to center-align all components of a panel by x alignment
     * @param pPanel the panel in which to center all JComponents
     */
    public static void centerAllComponents(final JPanel pPanel) {
        for (final Component comp : pPanel.getComponents()) {
            if (comp instanceof JComponent) {
                ((JComponent) comp).setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        }
    }
}
