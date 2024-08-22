package edu.ntnu.stud.views.components.parameterComponents.fractalComponents;

import edu.ntnu.stud.views.components.parameterComponents.ParameterComponent;
import edu.ntnu.stud.views.components.parameterComponents.ParameterPane;

public class JuliaSetParameterComponent implements ParameterComponent {

    private final ParameterPane parameterPane;
    public JuliaSetParameterComponent() {
        this.parameterPane = new ParameterPane();
        this.parameterPane.addParameter("WIDTH", "1000");
        this.parameterPane.addParameter("HEIGHT", "700");
        this.parameterPane.addParameter("REAL PART OF C", "-0.7269");
        this.parameterPane.addParameter("IMAGINARY PART OF C", "0.1889");
        this.parameterPane.addParameter("MAX ITERATIONS", "400");
    }

    public ParameterPane getParameterPane() {
        return this.parameterPane;
    }

    public String getParameterValue(String name) {
        return this.parameterPane.getParameterValue(name);
    }
}
