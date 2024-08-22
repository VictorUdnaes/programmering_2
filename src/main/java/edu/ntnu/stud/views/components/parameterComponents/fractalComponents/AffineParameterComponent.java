package edu.ntnu.stud.views.components.parameterComponents.fractalComponents;

import edu.ntnu.stud.views.components.parameterComponents.ParameterComponent;
import edu.ntnu.stud.views.components.parameterComponents.ParameterPane;

/**
 * The AffineParameterComponent class is a subclass of the ParameterComponent
 * interface. It is used to represent the parameters of the affine fractal.
 */
public class AffineParameterComponent implements ParameterComponent {
    private final ParameterPane parameterPane;

    /**
     * Creates a new AffineParameterComponent object.
     */
    public AffineParameterComponent() {
        this.parameterPane = new ParameterPane();
        this.parameterPane.addParameter("WIDTH", "1000");
        this.parameterPane.addParameter("HEIGHT", "1000");
        this.parameterPane.addParameter("ITERATIONS", "1000000");
        this.parameterPane.addParameter("STARTING POINT X", "0");
        this.parameterPane.addParameter("STARTING POINT Y", "0");


    }

    /**
     * Returns the parameter pane of the affine fractal.
     *
     * @return the parameter pane of the affine fractal
     */
    public ParameterPane getParameterPane() {
        return this.parameterPane;
    }

    /**
     * Returns the value of the parameter with the given name.
     *
     * @param name the name of the parameter
     * @return the value of the parameter with the given name
     */
    public String getParameterValue(String name) {
        return this.parameterPane.getParameterValue(name);
    }
}
