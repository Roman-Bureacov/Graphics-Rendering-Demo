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
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.Matrix;
import view.Base;

/**
 * A panel depicting the transformation controls for the scene.
 * <br>
 * The matrix is column-major order. The columns themselves depict the axis that
 * are manipulated.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class TransformationControls extends JPanel {
    /** the name for when a transformation matrix is input and intended to be sent out */
    public static final String TRANSFORM_EVENT = "matrix transformation";
    /** the name for when a rotation matrix is input and intended to be sent out */
    public static final String ROTATION_EVENT = "rotation transformation";

    /**
     * denotes the number of rows and columns as a product, for use with the JTextField
     * array that denotes the matrix input.
     * <br>
     * The product 4*4-1, the minus 1 being for array traversal.
     */
    private static final int ROWCOLS = 4*4 - 1;

    private final JPanel iMatrixTransformation = new JPanel();
    private final JPanel iRotationTransformation = new JPanel();
    private final JButton iMatrixButton = new JButton("Transform");
    private final JButton iRotationButton = new JButton("Rotate");


    // matrix components
    /** a 3x3 matrix for transformation input */
    private final JTextField[] iMatrixInput = new JTextField[4*4];

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
        
        this.makeButtonActions();

        this.add(lTabs);

        Base.centerAllComponents(this.iMatrixTransformation);
        Base.centerAllComponents(this.iRotationTransformation);
    }
    
    private void makeButtonActions() {
        this.iMatrixButton.addActionListener(
                e -> this.firePropertyChange(TRANSFORM_EVENT, null, this.getTransformationMatrix())
        );
        this.iRotationButton.addActionListener(
                e -> this.firePropertyChange(ROTATION_EVENT, null, this.getRotationMatrix())
        );
    }
    
    private void makeMatrixTab() {
        this.iMatrixTransformation.setLayout(new BoxLayout(this.iMatrixTransformation, BoxLayout.Y_AXIS));

        final JPanel lPanel = new JPanel(new GridLayout(4, 4));
        for (final JTextField textField : this.iMatrixInput) {
            textField.setText("0");
            lPanel.add(textField);
        }
        // disable last column
        this.iMatrixInput[ROWCOLS].setText("1");
        for (int i = 0; i < 4; i++) {
            this.iMatrixInput[4*3 + i].setEnabled(false);
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

        final JPanel lInputPanel = new JPanel();
        final int lWidth = 100;
        final int lHeight = 25;
        this.iRotationInput.setMaximumSize(new Dimension(lWidth, lHeight));
        this.iRotationInput.setText("0");
        lInputPanel.add(this.iRotationInput);
        lInputPanel.add(new JLabel("Degrees"));

        this.iRotationTransformation.add(lInputPanel);
        this.iRotationTransformation.add(this.iRotationButton);
    }

    private Matrix<Double> getTransformationMatrix() {
        for (final JTextField field : this.iMatrixInput) {
            if (field.getText().isBlank()) field.setText("0");
        }

        final Matrix<Double> lInput = new DoubleMatrix(4, 4);
        for (int i = 0, row = 0, col = 0;
             i < ROWCOLS;
             i++, row = i / 4, col = i % 4
        ) {
            lInput.set(row, col, Double.parseDouble(this.iMatrixInput[row * 4 + col].getText()));
        }

        return lInput;
    }

    /**
     * Computes the rotation transformation matrix about an axis
     * @return the transformation matrix
     */
    private Matrix<Double> getRotationMatrix() {
        if (this.iRotationInput.getText().isBlank()) this.iRotationInput.setText("0");

        final Matrix<Double> lInput = DoubleMatrix.identity(4);
        final double lTheta = Double.parseDouble(this.iRotationInput.getText()) * Math.PI/180d;

        // see: https://en.wikipedia.org/wiki/Rotation_matrix
        if (this.iXAxis.isSelected()) {
            lInput.set(1, 1, Math.cos(lTheta));
            lInput.set(1, 2, -Math.sin(lTheta));
            lInput.set(2, 1, Math.sin(lTheta));
            lInput.set(2, 2, Math.cos(lTheta));
        } else if (this.iYAxis.isSelected()) {
            lInput.set(0, 0, Math.cos(lTheta));
            lInput.set(0, 2, Math.sin(lTheta));
            lInput.set(2, 0, -Math.sin(lTheta));
            lInput.set(2, 2, Math.cos(lTheta));
        } else if (this.iZAxis.isSelected()) {
            lInput.set(0, 0, Math.cos(lTheta));
            lInput.set(0, 1, -Math.sin(lTheta));
            lInput.set(1, 0, Math.sin(lTheta));
            lInput.set(1, 1, Math.cos(lTheta));
        }

        return lInput;
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
