package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ControlView implements FXComponent {

  private ControllerImpl controller;

  public ControlView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox top = new HBox();
    Label index =
        new Label(
            "Puzzle "
                + (controller.getModel().getActivePuzzleIndex() + 1)
                + " of "
                + controller.getModel().getPuzzleLibrarySize());
    index.getStyleClass().add("index");
    Button reset = new Button("Reset Puzzle");
    reset.setOnAction(
        (ActionEvent event) -> {
          controller.clickResetPuzzle();
        });
    reset.getStyleClass().add("control");
    Button next = new Button("Next Puzzle");
    next.setOnAction(
        (ActionEvent event) -> {
          controller.clickNextPuzzle();
          index.setText(
              "Puzzle "
                  + (controller.getModel().getActivePuzzleIndex() + 1)
                  + " of "
                  + controller.getModel().getPuzzleLibrarySize());
        });
    next.getStyleClass().add("control");
    Button prev = new Button("Prev. Puzzle");
    prev.setOnAction(
        (ActionEvent event) -> {
          controller.clickPrevPuzzle();
          index.setText(
              "Puzzle "
                  + (controller.getModel().getActivePuzzleIndex() + 1)
                  + " of "
                  + controller.getModel().getPuzzleLibrarySize());
        });
    prev.getStyleClass().add("control");
    Button rand = new Button("Random Puzzle");
    rand.setOnAction(
        (ActionEvent event) -> {
          controller.clickRandPuzzle();
          index.setText(
              "Puzzle "
                  + (controller.getModel().getActivePuzzleIndex() + 1)
                  + " of "
                  + controller.getModel().getPuzzleLibrarySize());
        });
    rand.getStyleClass().add("control");

    top.getChildren().addAll(reset, prev, next, rand, index);
    top.getStyleClass().add("controlbox");
    return top;
  }
}
