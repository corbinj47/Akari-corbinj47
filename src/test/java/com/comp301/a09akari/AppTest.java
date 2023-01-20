package com.comp301.a09akari;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/** Unit test for simple App. */
public class AppTest {
  /** Rigorous Test :-) */
  @Test
  public void puzzle1() {
    PuzzleLibrary library = new PuzzleLibraryImpl();
    PuzzleImpl puzzle1 = new PuzzleImpl(SamplePuzzles.PUZZLE_01);

    library.addPuzzle(puzzle1);

    ModelImpl test = new ModelImpl(library);
    test.addLamp(0, 3);
    test.addLamp(1, 1);
    test.addLamp(2, 5);
    test.addLamp(3, 4);
    test.addLamp(3, 6);
    test.addLamp(4, 5);
    test.addLamp(5, 2);
    test.addLamp(5, 6);
    test.addLamp(6, 0);
    test.addLamp(6, 3);

    assertTrue(test.isSolved());
  }

  @Test
  public void puzzle3() {
    PuzzleLibrary library = new PuzzleLibraryImpl();
    PuzzleImpl puzzle3 = new PuzzleImpl(SamplePuzzles.PUZZLE_03);

    library.addPuzzle(puzzle3);

    ModelImpl test = new ModelImpl(library);

    test.setActivePuzzleIndex(0);

    test.addLamp(0, 1);
    test.addLamp(0, 5);
    test.addLamp(1, 4);
    test.addLamp(1, 6);
    test.addLamp(3, 0);
    test.addLamp(2, 5);
    test.addLamp(4, 1);
    test.addLamp(5, 0);
    test.addLamp(5, 3);
    test.addLamp(6, 2);
    test.addLamp(6, 6);

    assertTrue(test.isSolved());
  }

  @Test
  public void puzzle4() {
    PuzzleLibrary library = new PuzzleLibraryImpl();
    PuzzleImpl puzzle4 = new PuzzleImpl(SamplePuzzles.PUZZLE_04);

    library.addPuzzle(puzzle4);

    ModelImpl test = new ModelImpl(library);

    test.addLamp(0, 2);
    test.addLamp(0, 9);
    test.addLamp(1, 7);
    test.addLamp(2, 0);
    test.addLamp(2, 6);
    test.addLamp(3, 1);
    test.addLamp(4, 7);
    test.addLamp(5, 9);
    test.addLamp(6, 4);
    test.addLamp(7, 0);
    test.addLamp(7, 3);
    test.addLamp(7, 8);
    test.addLamp(8, 2);
    test.addLamp(9, 0);
    test.addLamp(9, 5);
    test.addLamp(9, 9);

    assertTrue(test.isSolved());
  }

  @Test
  public void puzzle2() {
    PuzzleLibrary library = new PuzzleLibraryImpl();
    PuzzleImpl puzzle2 = new PuzzleImpl(SamplePuzzles.PUZZLE_02);

    library.addPuzzle(puzzle2);

    ModelImpl test = new ModelImpl(library);

    test.addLamp(0, 1);
    test.addLamp(0, 4);
    test.addLamp(1, 3);
    test.addLamp(2, 0);
    test.addLamp(2, 2);
    test.addLamp(2, 9);
    test.addLamp(3, 1);
    test.addLamp(4, 6);
    test.addLamp(5, 3);
    test.addLamp(5, 7);
    test.addLamp(6, 2);
    test.addLamp(6, 5);
    test.addLamp(6, 8);
    test.addLamp(7, 0);
    test.addLamp(7, 4);
    test.addLamp(7, 9);
    test.addLamp(8, 8);
    test.addLamp(9, 2);
    test.addLamp(9, 7);

    assertTrue(test.isSolved());
  }

  @Test
  public void puzzle1x1() {
    PuzzleLibrary library = new PuzzleLibraryImpl();
    PuzzleImpl puzzle1x1 = new PuzzleImpl(SamplePuzzles.PUZZLE_1x1);

    library.addPuzzle(puzzle1x1);

    ModelImpl test = new ModelImpl(library);

    test.addLamp(0, 0);

    assertTrue(test.isSolved());
  }

  @Test
  public void puzzle5() {
    PuzzleLibrary library = new PuzzleLibraryImpl();
    PuzzleImpl puzzle5 = new PuzzleImpl(SamplePuzzles.PUZZLE_05);

    library.addPuzzle(puzzle5);

    ModelImpl test = new ModelImpl(library);

    test.addLamp(0, 1);
    test.addLamp(0, 5);
    test.addLamp(1, 4);
    test.addLamp(2, 5);
    test.addLamp(3, 0);
    test.addLamp(4, 1);
    test.addLamp(5, 0);
    test.addLamp(5, 3);
    test.addLamp(6, 2);

    assertTrue(test.isSolved());
  }

  @Test
  public void indexTest() {
    PuzzleLibrary library = new PuzzleLibraryImpl();
    PuzzleImpl puzzle1 = new PuzzleImpl(SamplePuzzles.PUZZLE_01);
    PuzzleImpl puzzle2 = new PuzzleImpl(SamplePuzzles.PUZZLE_02);
    PuzzleImpl puzzle3 = new PuzzleImpl(SamplePuzzles.PUZZLE_03);
    PuzzleImpl puzzle4 = new PuzzleImpl(SamplePuzzles.PUZZLE_04);
    PuzzleImpl puzzle5 = new PuzzleImpl(SamplePuzzles.PUZZLE_05);
    library.addPuzzle(puzzle1);
    library.addPuzzle(puzzle2);
    library.addPuzzle(puzzle3);
    library.addPuzzle(puzzle4);
    library.addPuzzle(puzzle5);

    int size = library.size();

    ModelImpl model = new ModelImpl(library);
    ControllerImpl control = new ControllerImpl(model);

    model.setActivePuzzleIndex(4);
    CellType cell = model.getActivePuzzle().getCellType(6,0);

    assertTrue(cell.equals(CellType.CORRIDOR));
  }
}
