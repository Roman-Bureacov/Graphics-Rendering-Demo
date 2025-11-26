package view;

import static javax.swing.SwingUtilities.invokeLater;

import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;

/**
 * Entry point for the swing application.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public final class Application {

    private Application() {
        super();
    }

    public static void main(String[] args) {
        FlatSolarizedLightIJTheme.setup();

        invokeLater(Base::new);

    }
}
