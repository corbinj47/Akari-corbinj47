package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI

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

    ModelImpl model = new ModelImpl(library);
    ControllerImpl controller = new ControllerImpl(model);
    View view = new View(controller);
    Parent meow = view.render();

    ModelObserver obs =
        new ModelObserver() {
          @Override
          public void update(Model model) {
            Scene scene = new Scene(view.render(), 700, 575, Color.BLACK);
            scene.getStylesheets().add("main.css");
            stage.setScene(scene);
          }
        };
    model.addObserver(obs);

    stage.setTitle("Akari");

    Scene scene = new Scene(meow, 700, 575, Color.BLACK);

    scene.getStylesheets().add("main.css");
    stage.setScene(scene);
    stage.show();
  }
}
