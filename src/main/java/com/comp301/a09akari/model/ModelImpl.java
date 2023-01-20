package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private PuzzleLibrary library;
  private Puzzle activePuzzle;
  private int index = 0;
  private List<ModelObserver> observers;
  private boolean[][] lamps;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null)
      throw new IllegalArgumentException("Cannot create model without puzzle library");
    this.library = library;
    this.observers = new ArrayList<ModelObserver>();
    this.activePuzzle = library.getPuzzle(index);
    lamps = new boolean[library.getPuzzle(index).getHeight()][library.getPuzzle(index).getWidth()];
  }

  @Override
  public void addLamp(int r, int c) {
    if (r < 0 || c < 0) throw new IndexOutOfBoundsException("Cannot add out of bounds");
    if (r > lamps.length - 1 || c > lamps[0].length - 1)
      throw new IndexOutOfBoundsException("Cannot add out of bounds");
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR)
      throw new IllegalArgumentException("Cell Type is not corridor");
    boolean cell = lamps[r][c];
    if (cell == true) return;
    else {
      lamps[r][c] = true;
      notifyObservers();
      return;
    }
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r < 0 || c < 0) throw new IndexOutOfBoundsException("Cannot remove out of bounds");
    if (r > lamps.length - 1 || c > lamps[0].length - 1)
      throw new IndexOutOfBoundsException("Cannot remove out of bounds");
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR)
      throw new IllegalArgumentException("Cell Type is not corridor");
    boolean cell = lamps[r][c];
    if (cell == false) return;
    else {
      lamps[r][c] = false;
      notifyObservers();
      return;
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r < 0 || c < 0) throw new IndexOutOfBoundsException("Cannot search out of bounds");
    if (r > lamps.length - 1 || c > lamps[0].length - 1)
      throw new IndexOutOfBoundsException("Cannot search out of bounds");
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR)
      throw new IllegalArgumentException("Cell Type is not corridor");
    if (isLamp(r, c)) return true;

    int i = r;
    int j = c;
    while (i < activePuzzle.getHeight()) {
      if (activePuzzle.getCellType(i, j) == CellType.CLUE
          || activePuzzle.getCellType(i, j) == CellType.WALL) break;
      if (lamps[i][j] == true) return true;
      i++;
    }
    i = r;
    j = c;
    while (i > -1) {
      if (activePuzzle.getCellType(i, j) == CellType.CLUE
          || activePuzzle.getCellType(i, j) == CellType.WALL) break;
      if (lamps[i][j] == true) return true;
      i--;
    }
    i = r;
    j = c;
    while (j < activePuzzle.getWidth()) {
      if (activePuzzle.getCellType(i, j) == CellType.CLUE
          || activePuzzle.getCellType(i, j) == CellType.WALL) break;
      if (lamps[i][j] == true) return true;
      j++;
    }
    i = r;
    j = c;
    while (j > -1) {
      if (activePuzzle.getCellType(i, j) == CellType.CLUE
          || activePuzzle.getCellType(i, j) == CellType.WALL) break;
      if (lamps[i][j] == true) return true;
      j--;
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r < 0 || c < 0) throw new IndexOutOfBoundsException("Cannot search out of bounds");
    if (r > lamps.length - 1 || c > lamps[0].length - 1)
      throw new IndexOutOfBoundsException("Cannot search out of bounds");
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR)
      throw new IllegalArgumentException("Cell Type is not corridor");
    if (lamps[r][c] == true) return true;
    else return false;
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r < 0 || c < 0) throw new IndexOutOfBoundsException("Cannot search out of bounds");
    if (r > lamps.length - 1 || c > lamps[0].length - 1)
      throw new IndexOutOfBoundsException("Cannot search out of bounds");
    if (!isLamp(r, c)) throw new IllegalArgumentException("Cell doesn't contain a lamp");

    int i = r + 1;
    int j = c;
    while (i < activePuzzle.getHeight()) {
      if (activePuzzle.getCellType(i, j) == CellType.CLUE
          || activePuzzle.getCellType(i, j) == CellType.WALL) break;
      if (lamps[i][j] == true & isLamp(r, c)) return true;
      i++;
    }
    i = r - 1;
    j = c;
    while (i > -1) {
      if (activePuzzle.getCellType(i, j) == CellType.CLUE
          || activePuzzle.getCellType(i, j) == CellType.WALL) break;
      if (lamps[i][j] == true & isLamp(r, c)) return true;
      i--;
    }
    i = r;
    j = c + 1;
    while (j < activePuzzle.getWidth()) {
      if (activePuzzle.getCellType(i, j) == CellType.CLUE
          || activePuzzle.getCellType(i, j) == CellType.WALL) break;
      if (lamps[i][j] == true & isLamp(r, c)) return true;
      j++;
    }
    i = r;
    j = c - 1;
    while (j > -1) {
      if (activePuzzle.getCellType(i, j) == CellType.CLUE
          || activePuzzle.getCellType(i, j) == CellType.WALL) break;
      if (lamps[i][j] == true & isLamp(r, c)) return true;
      j--;
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return activePuzzle;
  }

  @Override
  public int getActivePuzzleIndex() {
    return index;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index > getPuzzleLibrarySize() - 1)
      throw new IndexOutOfBoundsException("invalid index");
    this.index = index;
    activePuzzle = library.getPuzzle(index);
    lamps = new boolean[activePuzzle.getHeight()][activePuzzle.getWidth()];
    notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() {
    for (int i = 0; i < lamps.length; i++) {
      for (int j = 0; j < lamps[0].length; j++) {
        lamps[i][j] = false;
      }
    }
    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < activePuzzle.getHeight(); i++) {
      for (int j = 0; j < activePuzzle.getWidth(); j++) {
        if (activePuzzle.getCellType(i, j) == CellType.CLUE)
          if (!isClueSatisfied(i, j)) return false;
        if (activePuzzle.getCellType(i, j) == CellType.CORRIDOR) {
          if (!isLit(i, j)) {
            return false;
          }
          if (isLamp(i, j)) {
            if (isLampIllegal(i, j)) return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    int lampCount = 0;
    if (r < 0 || c < 0) throw new IndexOutOfBoundsException("Cannot search out of bounds");
    if (r > lamps.length - 1 || c > lamps[0].length - 1)
      throw new IndexOutOfBoundsException("Cannot search out of bounds");
    if (activePuzzle.getCellType(r, c) != CellType.CLUE)
      throw new IllegalArgumentException("Cell Type is not clue");

    if (r + 1 <= activePuzzle.getHeight() - 1)
      if (activePuzzle.getCellType(r + 1, c) == CellType.CORRIDOR)
        if (lamps[r + 1][c] == true) lampCount++;
    if (r - 1 > -1) {
      if (activePuzzle.getCellType(r - 1, c) == CellType.CORRIDOR)
        if (lamps[r - 1][c] == true) lampCount++;
    }
    if (c + 1 <= activePuzzle.getWidth() - 1)
      if (activePuzzle.getCellType(r, c + 1) == CellType.CORRIDOR)
        if (lamps[r][c + 1] == true) lampCount++;
    if (c - 1 > -1) {
      if (activePuzzle.getCellType(r, c - 1) == CellType.CORRIDOR)
        if (lamps[r][c - 1] == true) lampCount++;
    }

    if (activePuzzle.getClue(r, c) == lampCount) return true;
    else return false;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  public void notifyObservers() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }
}
