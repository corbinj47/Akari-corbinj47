package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import javafx.scene.control.Button;

import java.util.Random;

public class ControllerImpl implements ClassicMvcController {

  private Model model;
  Random random = new Random();

  public ControllerImpl(Model model) {
    if (model == null) throw new IllegalArgumentException("cannot create without a model");
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    try {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
    } catch (IndexOutOfBoundsException x) {
      model.setActivePuzzleIndex(0);
    }
  }

  @Override
  public void clickPrevPuzzle() {
    try {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    } catch (IndexOutOfBoundsException x) {
      model.setActivePuzzleIndex(model.getPuzzleLibrarySize() - 1);
    }
  }

  @Override
  public void clickRandPuzzle() {
    int rand = random.nextInt(model.getPuzzleLibrarySize() - 1);
    while (rand == model.getActivePuzzleIndex())
      rand = random.nextInt(model.getPuzzleLibrarySize() - 1);
    model.setActivePuzzleIndex(rand);
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      if (!model.isLamp(r, c)) model.addLamp(r, c);
      else model.removeLamp(r, c);
    }
  }

  public void clickButton(Button btn, int i, int j) {
    if (model.getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR) {
      if (!model.isLamp(i, j)) {
        model.addLamp(i, j);
        btn.setText("L");
      } else {
        model.removeLamp(i, j);
        btn.setText("  ");
      }
    }
  }

  public Model getModel() {
    return model;
  }
}
