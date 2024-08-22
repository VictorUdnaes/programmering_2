package edu.ntnu.stud.views.components.buttons;

import edu.ntnu.stud.views.components.parameterComponents.ParameterComponent;
import edu.ntnu.stud.views.views.applicationPanes.Router;
import javafx.scene.control.Button;

/**
 * The MainMenuButton class is a button
 * that returns to the main menu when clicked.
 */
public class NavigationButton extends Button {

    /**
     * The escapeButton method creates a button that returns to the main menu when clicked.
     * The method takes a Stage as a parameter and creates a button with an event handler that returns to the main menu.
     *
     * @return The button that returns to the main menu.
     */
    public static Button mainMenuButton() {
        Button escapeButton = new Button("Return to main menu");
        escapeButton.setOnAction(e -> Router.getInstance().displayMainMenu());
        return escapeButton;
    }

    public static Button fractalMenuButton(String fractalName, String imagePath, ParameterComponent parameterComponent) {
        Button escapeButton = new Button("Return to fractal menu");
        escapeButton.setOnAction(e -> Router.getInstance().displayFractalMenu(fractalName, imagePath, parameterComponent));
        return escapeButton;
    }
}
