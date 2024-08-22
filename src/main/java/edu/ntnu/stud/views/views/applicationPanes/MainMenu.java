package edu.ntnu.stud.views.views.applicationPanes;

import edu.ntnu.stud.views.components.buttons.BackgroundButton;
import edu.ntnu.stud.views.components.parameterComponents.fractalComponents.AffineParameterComponent;
import edu.ntnu.stud.views.components.parameterComponents.fractalComponents.JuliaSetParameterComponent;
import edu.ntnu.stud.views.components.parameterComponents.fractalComponents.MandelbrotParameterComponent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The MainMenu class is a view that displays the main menu of the application. The main menu
 * contains buttons that lead to the different fractals that can be displayed.
 */
public class MainMenu {

    private final Stage stage;
    private final int width;
    private final int height;

    /**
     * The constructor of the MainMenu class. The constructor sets the width and height of the
     * MainMenu and the stage to display the view on.
     *
     * @param stage  The stage to display the view on.
     * @param width  The width of the MainMenu.
     * @param height The height of the MainMenu.
     */
    public MainMenu(Stage stage, int width, int height) {
        this.width = width;
        this.height = height;
        this.stage = stage;
    }

    /**
     * <p>Displays the main menu.</p>
     * The main menu displays buttons for selecting different fractals.
     */
    public void display() {
        // Create an AnchorPane to hold the main layout
        AnchorPane mainLayout = new AnchorPane();

        // Create a VBox to hold the logo, application name, title, and the buttons
        VBox vbox = new VBox(20); // 20 is the spacing between elements
        vbox.setAlignment(Pos.CENTER);

        // Create an ImageView for the logo
        ImageView logoView =
            new ImageView(new Image("edu/ntnu/stud/views/programicon/Wizardhat.png"));
        logoView.setFitWidth(100); // Set desired width
        logoView.setFitHeight(100); // Set desired height

        // Create a label for the application name
        Label appNameLabel = new Label("Fractal Wizard");
        appNameLabel.setFont(new Font("Arial", 30));
        appNameLabel.setTextFill(Color.BLACK);

        // Create a label for the title of the main menu
        Label titleLabel = new Label("Select Fractal Type");
        titleLabel.setFont(new Font("Arial", 20));
        titleLabel.setTextFill(Color.BLACK);

        // Create a rectangle to contain the title
        Rectangle box = new Rectangle(200, 50);
        box.setFill(Color.rgb(249, 253, 246));
        box.setArcWidth(30);
        box.setArcHeight(30);

        // Add the label to the box
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(box, titleLabel);

        // Create gradient for the background
        Stop[] stops = new Stop[] {
            new Stop(0, Color.rgb(4, 21, 55)),
            new Stop(1, Color.rgb(75, 131, 149))
        };
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

        // Create a rectangle to contain the gradient
        Rectangle background = new Rectangle(width, height);
        background.setFill(gradient);
        mainLayout.getChildren().add(background);

        // Bind the width and height of the rectangle to the width and height of the scene
        background.widthProperty().bind(mainLayout.widthProperty());
        background.heightProperty().bind(mainLayout.heightProperty());

        // Anchor the sides of the background to the sides of the window
        AnchorPane.setTopAnchor(background, 0.0);
        AnchorPane.setBottomAnchor(background, 0.0);
        AnchorPane.setLeftAnchor(background, 0.0);
        AnchorPane.setRightAnchor(background, 0.0);

        // Array of fractal properties needed to create the buttons
        String[][] fractalProperties = {
            {"Mandelbrot Fractal", "edu/ntnu/stud/views/buttonpictures/mandelbrot.jpg"},
            {"Julia Set", "edu/ntnu/stud/views/buttonpictures/juliaSet.png"},
            {"Sierpinski Triangle", "edu/ntnu/stud/views/buttonpictures/sierpinski.png"},
            {"Barnsley-Fern Fractal", "edu/ntnu/stud/views/buttonpictures/barnsleyFern.jpeg"},
            {"Mandelbulb Fractal", "edu/ntnu/stud/views/buttonpictures/mandelbulb.png"}
        };

        // Array of EventHandlers
        EventHandler<ActionEvent>[] eventHandlers = new EventHandler[] {
            e -> Router.getInstance().displayFractalMenu(
                fractalProperties[0][1],
                "Mandelbrot Fractal",
                new MandelbrotParameterComponent()
            ),
            e -> Router.getInstance().displayFractalMenu(
                fractalProperties[1][1],
                "Julia Set",
                new JuliaSetParameterComponent()
            ),
            e -> Router.getInstance().displayFractalMenu(
                fractalProperties[2][1],
                "Sierpinski Triangle",
                new AffineParameterComponent()
            ),
            e -> Router.getInstance().displayFractalMenu(
                fractalProperties[3][1],
                "Barnsley-Fern Fractal",
                new AffineParameterComponent()
            ),
            e -> Router.getInstance().displayMandelbulb()
        };

        // Create a GridPane to hold the buttons
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER); // Center the GridPane in the scene
        layout.setHgap(20); // Horizontal gap between buttons
        layout.setVgap(10); // Vertical gap between buttons

        // Create the fractal buttons
        for (int i = 0; i < fractalProperties.length; i++) {
            BackgroundButton button = new BackgroundButton(
                fractalProperties[i][1], 150, 100, fractalProperties[i][0]);
            button.setOnAction(eventHandlers[i]); // Set the action of the button

            // Add the button to the GridPane at the appropriate row and column
            layout.add(button, i % 2, i / 2); // Adjusting to 2 columns layout for centering
        }

        // Add the logo, application name, stackPane (title), and layout (buttons) to the vbox
        vbox.getChildren().addAll(logoView, appNameLabel, stackPane, layout);

        // Center the VBox in the main layout
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);
        mainLayout.getChildren().add(vbox);

        Scene scene = new Scene(mainLayout, width, height);
        stage.setScene(scene);
        stage.show();
    }
}
