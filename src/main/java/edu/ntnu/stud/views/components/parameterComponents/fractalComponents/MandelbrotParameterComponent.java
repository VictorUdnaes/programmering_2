package edu.ntnu.stud.views.components.parameterComponents.fractalComponents;

import edu.ntnu.stud.views.components.parameterComponents.ParameterComponent;
import edu.ntnu.stud.views.components.parameterComponents.ParameterPane;

public class MandelbrotParameterComponent implements ParameterComponent {
    private final ParameterPane parameterPane;

    public MandelbrotParameterComponent() {
        this.parameterPane = new ParameterPane();
        this.parameterPane.addParameter("WIDTH", "1000");
        this.parameterPane.addParameter("HEIGHT", "700");
        this.parameterPane.addParameter("MAX ITERATIONS", "200");
    }

    public ParameterPane getParameterPane() {
        return this.parameterPane;
    }
    public String getParameterValue(String name) {
        return this.parameterPane.getParameterValue(name);
    }
}
