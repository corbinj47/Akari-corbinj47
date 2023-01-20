package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.ModelImpl;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class PuzzleView implements FXComponent {

  private ControllerImpl controller;
  private GridPane gridPane;

  public PuzzleView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    ModelImpl model = (ModelImpl) controller.getModel();
    GridPane gridPane = new GridPane();

    for (int i = 0; i < model.getActivePuzzle().getHeight(); i++) {
      for (int j = 0; j < model.getActivePuzzle().getWidth(); j++) {
        Button btn = new Button();
        btn.getStyleClass().add("empty");
        if (model.getActivePuzzle().getCellType(i, j) == CellType.WALL) {
          btn.getStyleClass().add("wall");
        }
        if (model.getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR) {
          if (model.isLamp(i, j)) {
            if (model.isLampIllegal(i, j)) btn.getStyleClass().add("illegallamp");
            else btn.getStyleClass().add("lamp");
          } else if (model.isLit(i, j)) btn.getStyleClass().add("lit");
        }
        if (model.getActivePuzzle().getCellType(i, j) == CellType.CLUE) {
          btn.setText("" + model.getActivePuzzle().getClue(i, j));
          btn.getStyleClass().add("clue");
          if (model.isClueSatisfied(i, j)) {
            btn.getStyleClass().add("Sclue");
          } else {
            btn.getStyleClass().add("Uclue");
          }
        }
        int finalI = i;
        int finalJ = j;
        btn.setOnMouseClicked(
            (MouseEvent event) -> {
              controller.clickCell(finalI, finalJ);
            });
        gridPane.add(btn, j, i);
      }
    }

    return gridPane;
  }
}
