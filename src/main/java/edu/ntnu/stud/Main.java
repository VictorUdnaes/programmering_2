package edu.ntnu.stud;

import edu.ntnu.stud.views.views.applicationPanes.Router;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The Main class is the entry point of the application.
 */
public class Main extends Application {

  public static int PANE_WIDTH = 1400; // width of the main pane
  public static int PANE_HEIGHT = 1200; // height of the main pane

  public static void main(String[] args) {
    launch(args);
  }
  /**
   * Starts the application.
   *
   * @param primaryStage The primary stage of the application.
   * @throws Exception If an error occurs.
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Router.getInstance().init(primaryStage, PANE_WIDTH, PANE_HEIGHT);
    Router.getInstance().displayMainMenu();
    Image icon = new Image("edu/ntnu/stud/views/programicon/Wizardhat.png");
    primaryStage.getIcons().add(icon);
    primaryStage.setTitle("Fractal Wizard");
  }
}
