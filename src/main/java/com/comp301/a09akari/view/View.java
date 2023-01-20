package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class View implements FXComponent {

  private ControllerImpl controller;

  public View(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    BorderPane root = new BorderPane();
    root.getStyleClass().add("border");

    ControlView controlView = new ControlView(controller);
    PuzzleView puzzleView = new PuzzleView(controller);
    DescView descView = new DescView(controller);

    root.setTop(controlView.render());
    root.setCenter(puzzleView.render());
    root.setLeft(descView.render());

    return root;
  }
}
