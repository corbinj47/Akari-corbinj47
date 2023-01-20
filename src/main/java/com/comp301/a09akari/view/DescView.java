package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DescView implements FXComponent {
  private ControllerImpl controller;

  public DescView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox box = new VBox();
    Label label =
        new Label(
            "Instructions: \nTo solve the puzzle place lamps in empty corridors until the "
                + "whole puzzle is lit up. Clues indicate how many lamps should be next to them. Two lamps"
                + "placed next to eachother are illegal and will light up red.");
    label.getStyleClass().add("desc");

    Label solved = new Label("The Puzzle isn't solved");
    if (controller.getModel().isSolved()) {
      solved.setText("You solved the Puzzle!");
      solved.getStyleClass().add("solved");
    } else {
      solved.setText("The Puzzle isn't solved");
      solved.getStyleClass().add("notsolved");
    }

    box.getChildren().addAll(label, solved);
    return box;
  }
}
