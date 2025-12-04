package view.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import org.jetbrains.annotations.Nullable;

/**
 * Specialized document filter intended to be used by JTextFields
 * <br>
 * Inspired by: <a href="https://www.tutorialspoint.com/how-can-we-make-jtextfield-accept-only-numbers-in-java">this</a>
 */
public class TextFieldFilter extends DocumentFilter {
    /** regex filter for integers */
    private static final String INT_REGEX = "\\d+";
    /** regex filter for doubles */
    private static final String DOUBLE_REGEX = "(\\d+)?\\.\\d+|\\d+\\.(\\d+)?|\\d+";
    
    private final String iFilter;

    /**
     * Constructs a new specialized document.
     * @param pFilter the filter to enforce
     */
    public TextFieldFilter(final String pFilter) {
        super();
        this.iFilter = pFilter;
    } 
    
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

        if (lCandidate.toString().matches(this.iFilter)) {
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

        if (lCandidate.toString().matches(this.iFilter)) {
            super.replace(pFB, pOffset, pLength, pStr, pAttr);
        }
    }

    /**
     * Constructs an appropriate document filter
     * @param pName the name of the desired filter
     * @return the corresponding filter, null if it does not exist
     */
    public static @Nullable TextFieldFilter getFilter(final String pName) {
        return switch (pName) {
            case "integer" -> new TextFieldFilter(INT_REGEX);
            case "double" -> new TextFieldFilter(DOUBLE_REGEX);
            default -> null;
        };
    }
}