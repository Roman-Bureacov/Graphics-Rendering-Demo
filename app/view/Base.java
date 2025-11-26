package view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
    public final static int BASE_WIDTH = 500;
    /** the height of the base panel */
    public final static int BASE_HEIGHT = 500;

    private final JPanel iMainPanel = new JPanel();
    private final RenderPanel iRender = new RenderPanel(null);
    private final TransformPanel iTransform = new TransformPanel(null);

    /**
     * Creates a new base for the application to sit upon.
     */
    public Base() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.iMainPanel.setLayout(new BorderLayout());
        this.iMainPanel.add(this.iRender, BorderLayout.WEST);
        this.iMainPanel.add(this.iTransform, BorderLayout.EAST);
        this.setContentPane(this.iMainPanel);

        this.setSize(500, 500);
        this.setVisible(true);
    }


    @Override
    public void propertyChange(final PropertyChangeEvent evt) {

    }
}
