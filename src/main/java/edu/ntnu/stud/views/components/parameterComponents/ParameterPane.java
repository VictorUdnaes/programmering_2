package edu.ntnu.stud.views.components.parameterComponents;

import javafx.scene.Node;
import javafx.scene.layout.TilePane;

/**
 * The ParameterPane class is a subclass of the Tile
 * Pane class. It is used to represent a pane that
 * contains parameters.
 */
public class ParameterPane extends TilePane {

    /**
     * Creates a new ParameterPane object.
     */
    public ParameterPane() {
        super();
        this.setPrefColumns(3);
        this.setPrefRows(3);
        this.setHgap(50);
    }

    /**
     * Adds a parameter to the parameter pane.
     *
     * @param name         the name of the parameter
     * @param defaultValue the default value of the parameter
     */
    public void addParameter(String name, String defaultValue) {
        Parameter parameter = new Parameter(name, defaultValue, this);
        this.getChildren().add(parameter);
    }

    /**
     * Returns the parameter with the given name.
     *
     * @param name the name of the parameter
     * @return the parameter with the given name
     */
    public Parameter getParameter(String name) {
        for (Node node : this.getChildren()) {
            if (node instanceof Parameter && ((Parameter) node).getParameterName().equals(name)) {
                return ((Parameter) node).getParameter();
            }
        }
        return null;
    }

    /**
     * Returns the value of the parameter with the given name.
     *
     * @param name the name of the parameter
     * @return the value of the parameter with the given name
     */
    public String getParameterValue(String name) {
        for (Node node : this.getChildren()) {
            if (node instanceof Parameter && ((Parameter) node).getParameterName().equals(name)) {
                return ((Parameter) node).getTextField().getText();
            }
        }
        return null;
    }

    /**
     * Sets the observer of the parameter pane.
     *
     * @param observer the observer to set
     */
    public void setObserver(ParameterObserver observer) {
        for (Node node : this.getChildren()) {
            if (node instanceof Parameter) {
                ((Parameter) node).addObserver(observer);
            }
        }
    }
}
