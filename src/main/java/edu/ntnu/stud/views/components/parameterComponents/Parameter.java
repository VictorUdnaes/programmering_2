package edu.ntnu.stud.views.components.parameterComponents;

import edu.ntnu.stud.views.components.parameterComponents.fractalComponents.NotificationLabel;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a parameter in the GUI.
 * A parameter consists of a name, a text field and a notification label.
 * The text field is used to input the value of the parameter.
 * The notification label is used to display error messages.
 * The parameter notifies its observers when the value of the text field is changed.
 */
public class Parameter extends VBox {
    private final String parameterName;
    private final TextField textField;
    private final Label notificationLabel;
    private final ParameterPane parameterPane;
    private final List<ParameterObserver> observers = new ArrayList<>();

    /**
     * Creates a new parameter with the given name, default value and parameter pane.
     * @param name the name of the parameter
     * @param defaultValue the default value of the parameter
     * @param parameterPane the parameter pane that the parameter belongs to
     */
    public Parameter(String name, String defaultValue, ParameterPane parameterPane) {
        this.parameterName = name;
        this.textField = new TextField(defaultValue);
        this.parameterPane = parameterPane;
        Label parameterName = new Label(name);

        this.notificationLabel = new NotificationLabel();
        notificationLabel.setMaxWidth(160);
        notificationLabel.setWrapText(true);

        this.getChildren().addAll(parameterName, textField, notificationLabel);

        // Add listener to text field
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { // Check if new value is not a number
                if (parameterName.getText().equals("REAL PART OF C") || parameterName.getText().equals("IMAGINARY PART OF C")) {
                    if (newValue.contains(".") || !newValue.matches("-")) {
                        textField.setText(newValue);
                    }
                } else {
                    textField.setText(oldValue); // Revert back to old value
                }
            }
        });
        textField.textProperty().addListener((observable, oldValue, newValue) -> notifyObservers());
    }

    public Parameter getParameter() {
        return this;
    }

    public TextField getTextField() {
        return this.textField;
    }

    public String getParameterName() {
        return this.parameterName;
    }

    public ParameterPane getParameterPane() {
        return this.parameterPane;
    }

    /**
     * Sets the notification label to the given message and style.
     * @param message the message to display
     * @param style the style of the message
     */
    public void setNotificationLabel(String message, String style) {
        this.notificationLabel.setText(message);
        this.notificationLabel.setStyle(style);
    }

    public void addObserver(ParameterObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ParameterObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers that the parameter has changed.
     */
    private void notifyObservers() {
        for (ParameterObserver observer : observers) {
            observer.onParameterChanged(this);
        }
    }
}
