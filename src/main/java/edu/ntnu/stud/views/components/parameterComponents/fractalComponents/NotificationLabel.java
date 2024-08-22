package edu.ntnu.stud.views.components.parameterComponents.fractalComponents;

import javafx.scene.control.Label;

/**
 * The NotificationLabel class is a subclass of the Label class.
 * It is used to display notifications to the user.
 */
public class NotificationLabel extends Label {
    public NotificationLabel() {
        super();
    }

    /**
     * Shows an error message to the user.
     *
     * @param message the error message to show
     */
    public void showError(String message) {
        this.setText(message);
        this.setStyle("-fx-text-fill: red;");
    }

    /**
     * Shows an info message to the user.
     *
     * @param message the info message to show
     */
    public void showInfo(String message) {
        this.setText(message);
        this.setStyle("-fx-text-fill: blue;");
    }

    /**
     * Shows a success message to the user.
     *
     * @param message the success message to show
     */
    public void showSuccess(String message) {
        this.setText(message);
        this.setStyle("-fx-text-fill: green;");
    }

    /**
     * Clears the notification label.
     */
    public void clear() {
        this.setText("");
    }
}
