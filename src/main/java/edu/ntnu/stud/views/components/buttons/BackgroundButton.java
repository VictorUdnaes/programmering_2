package edu.ntnu.stud.views.components.buttons;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class BackgroundButton extends StackPane {

  private final Text text;

  public BackgroundButton(String imageUrl, double width, double height, String buttonText) {
    super();

    // Set the size of the button
    this.setPrefSize(width, height);

    // Load the image
    Image image = new Image(imageUrl);
    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(width);
    imageView.setFitHeight(height);

    // Initial ColorAdjust effect with no brightness adjustment
    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setBrightness(0);
    imageView.setEffect(colorAdjust);

    // Timeline for animating brightness change on hover
    Timeline fadeInTimeline = new Timeline(
        new KeyFrame(Duration.seconds(0.07), new KeyValue(colorAdjust.brightnessProperty(), 0.3)));
    Timeline fadeOutTimeline = new Timeline(
        new KeyFrame(Duration.seconds(0.07), new KeyValue(colorAdjust.brightnessProperty(), 0)));

    // Listener to trigger animation on hover
    imageView.hoverProperty().addListener((obs, wasHovered, isNowHovered) -> {
      if (isNowHovered) {
        fadeInTimeline.play();
      } else {
        fadeOutTimeline.play();
      }
    });

    // Create the text
    this.text = new Text(buttonText);
    this.text.setFont(Font.font("Arial", 14));
    this.text.setFill(Color.WHITE);
    this.text.setTextAlignment(TextAlignment.CENTER); // Center the text

    // Create VBox for image and text
    VBox contentBox = new VBox(imageView, this.text);
    contentBox.setAlignment(Pos.CENTER); // Center the content in the VBox

    // Add the VBox to the stack
    getChildren().add(contentBox);
  }

  public void setText(String text) {
    this.text.setText(text);
  }

  public void setOnAction(EventHandler<ActionEvent> eventHandler) {
    this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> eventHandler.handle(new ActionEvent()));
  }
}