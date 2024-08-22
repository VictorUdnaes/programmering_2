package edu.ntnu.stud.views.views.applicationPanes;

import edu.ntnu.stud.controller.FractalController;
import edu.ntnu.stud.utils.FractalGenerationException;
import edu.ntnu.stud.model.chaosGame.Viewport;
import edu.ntnu.stud.model.fractals.mandelbulb.SmartGroup;
import edu.ntnu.stud.views.components.parameterComponents.ParameterComponent;
import edu.ntnu.stud.views.views.FractalFactory;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Arrays;

/**
 * The Router class is a singleton class that handles the routing of the application.
 * The Router class is responsible for displaying the different views of the application.
 */
public class Router {
    private static Router instance = null;
    private FractalFactory fractalFactory;
    private FractalController fractalController;
    private Stage stage;
    private int paneWidth;
    private int paneHeight;

    private MainMenu mainMenu;
    private FractalMenu fractalMenu;
    private FractalDisplay fractalDisplay;
    private MandelbulbDisplay mandelbulbDisplay;

    private MediaPlayer mediaPlayer;

    private Router() {
    }

    /**
     * Returns the instance of the Router class.
     * If the instance is null, a new instance of the Router class is created.
     *
     * @return The instance of the Router class.
     */
    public static Router getInstance() {
        if (instance == null) {
            instance = new Router();
        }
        return instance;
    }

    /**
     * Initializes the Router with the stage, pane width, and pane height.
     *
     * @param stage      The stage to display the views on.
     * @param paneWidth  The width of the pane.
     * @param paneHeight The height of the pane.
     */
    public void init(Stage stage, int paneWidth, int paneHeight) {
        this.stage = stage;
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
        this.fractalController = new FractalController();
        this.fractalFactory = new FractalFactory(paneWidth, paneHeight, fractalController);
        String musicFile = "src/main/resources/sounds/fractalMusic.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.5);
        initializePanes();
    }

    /**
     * Initializes the application panes with the stage, pane width, and pane height.
     */
    private void initializePanes() {
        this.mainMenu = new MainMenu(stage, paneWidth, paneHeight);
        this.fractalMenu = new FractalMenu(stage, paneWidth, paneHeight);
        this.fractalDisplay = new FractalDisplay(stage, paneWidth, paneHeight);
        this.mandelbulbDisplay = new MandelbulbDisplay(stage, paneWidth, paneHeight);
    }

    /**
     * Displays the main menu view.
     */
    public void displayMainMenu() {
        this.mainMenu = new MainMenu(stage, paneWidth, paneHeight);
        mediaPlayer.stop();
        mainMenu.display();
    }

    /**
     * Displays the fractal menu view.
     *
     * @param imagePath           The path to the image of the fractal.
     * @param fractalName         The name of the fractal.
     * @param parameterComponent  The parameter component of the fractal.
     */
    public void displayFractalMenu(String imagePath, String fractalName, ParameterComponent parameterComponent) {
        try {
            parameterComponent.getParameterPane().setObserver(fractalMenu);
            fractalMenu.display(imagePath, fractalName, parameterComponent, fractalFactory);
        } catch (FractalGenerationException e) {
            alert(e);
        }
    }

    /**
     * Displays the fractal display view.
     *
     * @param fractalName
     * @param canvas
     * @param viewport
     */
    public void displayFractalDisplay(String fractalName, Canvas canvas, Viewport viewport) {
        try {
            fractalDisplay.addObserver(viewport);
            fractalDisplay.setFractalName(fractalName);
            fractalDisplay.setViewFactory(fractalFactory);
            fractalDisplay.setFractal(canvas);
            fractalDisplay.display();
            mediaPlayer.play();
        } catch (FractalGenerationException e) {
            alert(e);
        }
    }

    /**
     * Displays the mandelbulb view.
     */
    public void displayMandelbulb() {
        try {
            SmartGroup mandelbulb = fractalFactory.createMandelbulbSmartGroup();
            mandelbulbDisplay.setMandelbulb(mandelbulb);
            mandelbulbDisplay.display();
            mediaPlayer.play();
        } catch (FractalGenerationException e) {
            alert(e);
        }
    }

    /**
     * Displays an alert with the error message of the exception.
     *
     * @param er The exception to display the error message of.
     */
    public static void alert(FractalGenerationException er) {
        System.out.println(Arrays.toString(er.getStackTrace()));
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error generating fractal");
        alert.setContentText(
                er.getUserMessage() + "\n\n"
                + "Errorcode: " + er.getErrorCodeMessage() + "\n"
                + "Error id: " + er.getErrorCodeId()
        );
        alert.showAndWait();
    }
}
