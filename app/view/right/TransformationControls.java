package view.right;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import view.Base;

/**
 * A panel depicting the transformation controls for the scene
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class TransformationControls extends JPanel {
    private final JPanel iMatrixTransformation = new JPanel();
    private final JPanel iRotationTransformation = new JPanel();
    private final JButton iMatrixButton = new JButton("Transform");
    private final JButton iRotationButton = new JButton("Transform");

    // matrix components
    /** a 3x3 matrix for transformation input */
    private final JTextField[] iMatrixInput = new JTextField[9];

    // rotation components
    private final ButtonGroup iAxisGroup = new ButtonGroup();
    private final JRadioButton iXAxis = new JRadioButton("X Axis");
    private final JRadioButton iYAxis = new JRadioButton("Y Axis");
    private final JRadioButton iZAxis = new JRadioButton("Z Axis");
    private final JTextField iRotationInput = new JTextField();

    /**
     * Creates this panel and its subcomponents.
     */
    public TransformationControls() {
        super();

        // instantiate everything
        final int lBoxDimension = Base.BASE_WIDTH/2;
        this.setPreferredSize(new Dimension(lBoxDimension, lBoxDimension));

        // configure the text fields
        for (int i = 0; i < this.iMatrixInput.length; i++) {
            final JTextField lField = new JTextField();
            ((AbstractDocument) lField.getDocument()).setDocumentFilter(new DoubleFilter());
            this.iMatrixInput[i] = lField;
        }

        this.iAxisGroup.add(this.iXAxis);
        this.iAxisGroup.add(this.iYAxis);
        this.iAxisGroup.add(this.iZAxis);
        this.iXAxis.setSelected(true);
        ((AbstractDocument) this.iRotationInput.getDocument()).setDocumentFilter(new DoubleFilter());

        // setup components
        final JTabbedPane lTabs = new JTabbedPane();
        lTabs.addTab("Matrix", this.iMatrixTransformation);
        this.makeMatrixTab();

        lTabs.addTab("Rotation", this.iRotationTransformation);
        this.makeRotationTab();

        this.add(lTabs);
    }

    private void makeMatrixTab() {
        this.iMatrixTransformation.setLayout(new BoxLayout(this.iMatrixTransformation, BoxLayout.Y_AXIS));
        final JPanel lPanel = new JPanel(new GridLayout(3, 3));
        for (final JTextField jTextField : this.iMatrixInput) {
            lPanel.add(jTextField);
        }

        this.iMatrixTransformation.add(lPanel);

        this.iMatrixTransformation.add(this.iMatrixButton);
    }

    private void makeRotationTab() {
        this.iRotationTransformation.setLayout(new BoxLayout(this.iRotationTransformation, BoxLayout.Y_AXIS));
        this.iRotationTransformation.add(new JLabel("Axis"));

        // button group
        final JPanel lRadioGroup = new JPanel();
        lRadioGroup.setLayout(new BoxLayout(lRadioGroup, BoxLayout.X_AXIS));
        for (final Iterator<AbstractButton> iter = this.iAxisGroup.getElements().asIterator();
             iter.hasNext();
             /* */) {
            lRadioGroup.add(iter.next());
        }

        this.iRotationTransformation.add(lRadioGroup);
        this.iRotationTransformation.add(this.iRotationInput);
        this.iRotationTransformation.add(this.iRotationButton);
    }

    /**
     * Simple document filter that allows for filtering in only double inputs.
     * <br>
     * Inspired by: <a href="https://www.tutorialspoint.com/how-can-we-make-jtextfield-accept-only-numbers-in-java">this</a>
     */
    private static class DoubleFilter extends DocumentFilter {
        private static final String DOUBLE_REGEX = "(\\d+)?\\.\\d+|\\d+\\.(\\d+)?|\\d+";

        @Override
        public void insertString(
                final FilterBypass pFB,
                final int pOffset,
                final String pStr,
                final AttributeSet pAttr) throws BadLocationException {

            // string concatenation, permissible in relatively low-activity environment
            final Document lDoc = pFB.getDocument();
            final StringBuilder lCandidate = new StringBuilder(lDoc.getText(0, lDoc.getLength()));
            lCandidate.insert(pOffset, pStr);

            if (lCandidate.toString().matches(DOUBLE_REGEX)) {
                super.insertString(pFB, pOffset, lCandidate.toString(), pAttr);
            }
        }

        @Override
        public void replace(
                final FilterBypass pFB,
                final int pOffset,
                final int pLength,
                final String pStr,
                final AttributeSet pAttr) throws BadLocationException {

            // string concatenation, permissible in relatively low-activity environment
            final Document lDoc = pFB.getDocument();
            final StringBuilder lCandidate = new StringBuilder(lDoc.getText(0, lDoc.getLength()));
            lCandidate.replace(pOffset, pOffset + pLength, pStr == null ? "" : pStr);

            if (lCandidate.toString().matches(DOUBLE_REGEX)) {
                super.replace(pFB, pOffset, pLength, pStr, pAttr);
            }
        }
    }

}
