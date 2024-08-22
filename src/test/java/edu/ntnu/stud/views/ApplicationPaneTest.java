package edu.ntnu.stud.views;

import edu.ntnu.stud.views.views.applicationPanes.FractalDisplay;
import edu.ntnu.stud.views.views.applicationPanes.MainMenu;
import edu.ntnu.stud.views.views.applicationPanes.MandelbulbDisplay;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

/**
 * Test class for the ApplicationPane class.
 * The ApplicationPane class is responsible for displaying the main menu, fractal menu, and fractal display.
 */
@ExtendWith(ApplicationExtension.class)
public class ApplicationPaneTest {
    Stage primaryStage = mock(Stage.class);

    @Start
    public void start(Stage stage) {
    }

    /**
     * Test that the MainMenu class can be instantiated without throwing an exception.
     */
    @Test
    void MainMenuTest() {
        assertDoesNotThrow(() -> new MainMenu(primaryStage, 1600, 1600).display());
    }

    /**
     * Test that the FractalMenu class can be instantiated without throwing an exception.
     */
    @Test
    void FractalDisplayTest() {
        assertDoesNotThrow(() -> new FractalDisplay(primaryStage, 1600, 1600).display());
    }

    /**
     * Test that the MandelbulbDisplay class can be instantiated without throwing an exception.
     */
    @Test
    void MandelbulbDisplayTest() {
        assertDoesNotThrow(() -> new MandelbulbDisplay(primaryStage, 1600, 1600).display());
    }
}


