package edu.ntnu.stud.views.views.applicationPanes;

import edu.ntnu.stud.model.fractals.mandelbulb.SmartGroup;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * The MandelbulbDisplay class is a view class that displays the Mandelbulb fractal in 3D.
 * The class extends the Pane class to be able to display the fractal in 3D.
 */
public class MandelbulbDisplay {
    private Stage stage;
    private int width;
    private int height;

    /**
     * The constructor of the MandelbulbDisplay class.
     * The constructor takes a Stage as a parameter and sets the width and height of the MandelbulbDisplay.
     *
     * @param stage The stage to display the view on.
     * @param width The width of the MandelbulbDisplay.
     * @param height The height of the MandelbulbDisplay.
     */
    public MandelbulbDisplay(Stage stage, int width, int height) {
        this.stage = stage;
        this.width = width;
        this.height = height;
    }

    /**
     * The setMandelbulb method sets the Mandelbulb fractal on the SmartGroup.
     * The method takes a SmartGroup as a parameter and sets the scene with the SmartGroup and the camera.
     *
     * @param root The SmartGroup to set the Mandelbulb fractal on.
     */
    public void setMandelbulb(SmartGroup root) {
        Scene scene = new Scene(root, width, height, true);
        scene.setFill(javafx.scene.paint.Color.BLACK);
        Camera camera = new PerspectiveCamera();
        camera.setTranslateX((double) -width /2);  // Move camera on X-axis
        camera.setTranslateY((double) -height /2);   // Move camera on Y-axis
        camera.setTranslateZ(width); // Move camera on Z-axis (negative for backward view)
        camera.setNearClip(0.01);
        scene.setCamera(camera);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case UP:
                    camera.translateZProperty().set(camera.getTranslateZ() + 20);
                    break;
                case DOWN:
                    camera.translateZProperty().set(camera.getTranslateZ() - 20);
                    break;
                case LEFT:
                    root.rotateByY(3);
                    break;
                case RIGHT:
                    root.rotateByY(-3);
                    break;
                case N:
                    root.rotateByX(3);
                    break;
                case M:
                    root.rotateByX(-3);
                    break;
            }
        });
        stage.setScene(scene);
    }

    public void display() {
        stage.show();
    }

}
