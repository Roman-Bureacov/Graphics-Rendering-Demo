package view.right;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.Matrix;
import view.Base;
import view.util.TextFieldFilter;

/**
 * A panel depicting the transformation controls for the scene.
 * <br>
 * The matrix is column-major order. The columns themselves depict the axis that
 * are manipulated. Rotations follow the right-hand rule.
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
    private static final int ROWCOLS = 4*4;

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
            ((AbstractDocument) lField.getDocument())
                    .setDocumentFilter(TextFieldFilter.getFilter("double"));
            this.iMatrixInput[i] = lField;
        }

        this.iAxisGroup.add(this.iXAxis);
        this.iAxisGroup.add(this.iYAxis);
        this.iAxisGroup.add(this.iZAxis);
        this.iXAxis.setSelected(true);
        ((AbstractDocument) this.iRotationInput.getDocument())
                .setDocumentFilter(TextFieldFilter.getFilter("double"));

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

        final JPanel lMatrixPanel = new JPanel(new GridLayout(5, 4));
        lMatrixPanel.add(new JLabel("x"));
        lMatrixPanel.add(new JLabel("y"));
        lMatrixPanel.add(new JLabel("z"));
        lMatrixPanel.add(new JLabel("w"));
        for (final Component comp : lMatrixPanel.getComponents()) {
            if (comp instanceof final JLabel pComp) {
                final int lFontSize = 18;
                pComp.setHorizontalAlignment(SwingConstants.CENTER);
                pComp.setFont(new Font("Cambria", Font.PLAIN, lFontSize ));
            }
        }

        for (final JTextField textField : this.iMatrixInput) {
            textField.setText("0");
            lMatrixPanel.add(textField);
        }
        // disable last row
        this.iMatrixInput[ROWCOLS - 1].setText("1");
        for (int i = 0; i < 4; i++) {
            this.iMatrixInput[4*3 + i].setEnabled(false);
        }


        this.iMatrixTransformation.add(lMatrixPanel);
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

}
