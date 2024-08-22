package edu.ntnu.stud.views.views.applicationPanes;

import edu.ntnu.stud.model.interfaces.ChaosGameObserver;
import edu.ntnu.stud.views.components.buttons.NavigationButton;
import edu.ntnu.stud.views.views.FractalFactory;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The FractalDisplay class is a view class that displays the fractal on a canvas.
 * The class implements the ChaosGameObserver interface to be able to observe changes in the model.
 * The class extends the Pane class to be able to display the canvas and a button to return to the main menu.
 */
public class FractalDisplay extends Pane {
    List<ChaosGameObserver> observers = new ArrayList<>();
    private String fractalName;
    private FractalFactory fractalFactory;
    private final int width;
    private final int height;
    private final Stage stage;
    private Canvas canvas = new Canvas();

    /**
     * The constructor of the FractalDisplay class.
     * The constructor takes a Stage as a parameter and creates a canvas and a button to return to the main menu.
     * The constructor adds the canvas and the button to the Pane.
     *
     * @param stage The stage to display the view on.
     */
    public FractalDisplay(Stage stage, int width, int height) {
        this.stage = stage;
        this.width = width;
        this.height = height;
    }

    /**
     * The setFractal method sets the fractal on the DisplayCanvas.
     * The method takes a Canvas as a parameter and aligns the dimensions
     * of the canvas to the DisplayCanvas, then the fractal pixels are drawn on the canvas.
     *
     * @param fractalCanvas The fractal canvas to set the fractal on.
     */
    public void setFractal(Canvas fractalCanvas) {
        canvas = new Canvas();
        canvas.setWidth(fractalCanvas.getWidth());
        canvas.setHeight(fractalCanvas.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(fractalCanvas.snapshot(null, null), 0, 0);
    }

    /**
     * <p>Displays the FractalDisplay view on the stage.</p>
     * The method takes a Stage as a parameter and creates a Scene with the FractalDisplay view and shows it.
     */
    public void display() {
        AnchorPane mainLayout = new AnchorPane();
        Button mainMenuButton = NavigationButton.mainMenuButton();

        PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
        pause.setOnFinished(delay -> {
            mainLayout.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                if (!pause.getStatus().equals(PauseTransition.Status.RUNNING)) {
                    if (event.getCode() == KeyCode.UP) {
                        update(0, 50, 1);
                    } else if (event.getCode() == KeyCode.DOWN) {
                        update(0, -50, 1);
                    } else if (event.getCode() == KeyCode.LEFT) {
                        update(50, 0, 1);
                    } else if (event.getCode() == KeyCode.RIGHT) {
                        update(-50, 0, 1);
                    }
                }
            });
        });
        pause.playFromStart();

        mainLayout.getChildren().addAll(canvas, mainMenuButton);
        Scene scene = new Scene(mainLayout, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public void setFractalName(String fractalName) {
        this.fractalName = fractalName;
    }

    public void setViewFactory(FractalFactory fractalFactory) {
        this.fractalFactory = fractalFactory;
    }

    /**
     * The addObserver method adds an observer to the list of observers.
     * The method takes a ChaosGameObserver as a parameter and adds it to the list of observers.
     *
     * @param observer The observer to add.
     */
    public void addObserver(ChaosGameObserver observer) {
        observers.add(observer);
    }

    /**
     * The removeObserver method removes an observer from the list of observers.
     * The method takes a ChaosGameObserver as a parameter and removes it from the list of observers.
     */
    public void removeObserver(ChaosGameObserver observer) {
        observers.remove(observer);
    }

    /**
     * The update method updates the view with the new fractal set.
     * The method takes the parameters for the fractal set and updates the view with the new fractal set.
     *
     * @param changeX The change of the x-coordinate of the fractal set.
     * @param changeY The change of y-coordinate of the fractal set.
     * @param zoom    The zoom factor for the fractal set.
     */
    public void update(int changeX, int changeY, float zoom) {
        for (ChaosGameObserver observer : observers) {
            observer.update(changeY, changeX, zoom);
            Canvas newCanvas = fractalFactory.updateViewport(fractalName);
            setFractal(newCanvas);
            display();
        }
    }
}
