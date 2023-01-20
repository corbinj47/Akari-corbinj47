package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {

  private int[][] board;

  public PuzzleImpl(int[][] board) {
    if (board == null) throw new IllegalArgumentException("Cannot create a puzzle without a board");
    this.board = board;
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r < 0 || c < 0) throw new IndexOutOfBoundsException("Cannot search out of bounds");
    if (r > getHeight() - 1 || c > getWidth() - 1)
      throw new IndexOutOfBoundsException("Cannot search out of bounds");
    int cell = board[r][c];
    if (cell == 5) return CellType.WALL;
    else if (cell == 6) return CellType.CORRIDOR;
    else return CellType.CLUE;
  }

  @Override
  public int getClue(int r, int c) {
    if (r < 0 || c < 0) throw new IndexOutOfBoundsException("Cannot search out of bounds");
    if (r > getHeight() - 1 || c > getWidth() - 1)
      throw new IndexOutOfBoundsException("Cannot search out of bounds");
    int cell = board[r][c];
    if (cell > 4) throw new IllegalArgumentException("Cell is not a clue");
    else return cell;
  }
}
