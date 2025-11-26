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

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.Matrix;
import model.javaGL.mesh.Mesh;
import view.Base;

/**
 * A panel depicting the transformation controls for the scene
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class TransformationControls extends JPanel {
    /** the name for when a transformation matrix is input and intended to be sent out */
    public static final String MATRIX_EVENT = "matrix transformation";
    /** the name for when a rotation matrix is input and intended to be sent out */
    public static final String ROTATION_EVENT = "rotation transformation";
    
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
        
        this.makeButtonActions();

        this.add(lTabs);
    }
    
    private void makeButtonActions() {
        this.iMatrixButton.addActionListener(
                e -> this.firePropertyChange(MATRIX_EVENT, null, this.createMatrixRecord())
        );
        this.iRotationButton.addActionListener(
                e -> this.firePropertyChange(ROTATION_EVENT, null, this.createRotationRecord())
        );
    }
    
    private matrixData createMatrixRecord() {
        for (final JTextField field : this.iMatrixInput) {
            if (field.getText().isEmpty()) field.setText("0");
        }

        return new matrixData(
                Double.parseDouble(this.iMatrixInput[0].getText()),
                Double.parseDouble(this.iMatrixInput[1].getText()),
                Double.parseDouble(this.iMatrixInput[2].getText()),
                Double.parseDouble(this.iMatrixInput[3].getText()),
                Double.parseDouble(this.iMatrixInput[4].getText()),
                Double.parseDouble(this.iMatrixInput[5].getText()),
                Double.parseDouble(this.iMatrixInput[6].getText()),
                Double.parseDouble(this.iMatrixInput[7].getText()),
                Double.parseDouble(this.iMatrixInput[8].getText())
        );
    }
    
    private rotationData createRotationRecord() {
        final rotationData.AXIS lAxis;
        if (this.iXAxis.isSelected()) lAxis = rotationData.AXIS.X;
        else if (this.iYAxis.isSelected()) lAxis = rotationData.AXIS.Y;
        else lAxis = rotationData.AXIS.Z;
            
        return new rotationData(lAxis, Double.parseDouble(this.iRotationInput.getText()));
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
     * Record for matrix transformations intended by the transformation controls
     */
    public record matrixData(
            double x1, double x2, double x3,
            double y1, double y2, double y3,
            double z1, double z2, double z3
    ) {
        /**
         * Converts this record into a matrix
         * @return this data as a matrix
         */
        public Matrix<Double> toMatrix() {
            final Matrix<Double> lMatrix = new DoubleMatrix(3, 3);
            lMatrix.set(0, 0, this.x1);
            lMatrix.set(0, 1, this.x2);
            lMatrix.set(0, 2, this.x3);
            lMatrix.set(1, 0, this.y1);
            lMatrix.set(1, 1, this.y2);
            lMatrix.set(1, 2, this.y3);
            lMatrix.set(2, 0, this.z1);
            lMatrix.set(2, 1, this.z2);
            lMatrix.set(2, 2, this.z3);
            
            return lMatrix;
        }
    }

    /**
     * Record for rotation transformations intended by the transformation controls
     */
    public record rotationData(
            AXIS axis,
            double amount
    ) {
        enum AXIS {
            X, Y, Z
        }
        
        /**
         * Calculates the matrix for this transformation
         * @return the resulting transformation matrix
         */
        public Matrix calculate() {
            // TODO: math
            return null;
        }
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
