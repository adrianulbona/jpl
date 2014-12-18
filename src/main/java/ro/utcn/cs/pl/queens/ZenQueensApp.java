package ro.utcn.cs.pl.queens;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.Double.POSITIVE_INFINITY;
import static javafx.scene.layout.Priority.ALWAYS;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ZenQueensApp extends Application {

    private int solutionCount = 1;

    @Override
    public void start(Stage primaryStage) {
        final ZenQueensSolver zenQueensSolver = new ZenQueensSolver();
        final List<Map<String, int[]>> solutions = zenQueensSolver.solve();
        final Iterator<Map<String, int[]>> solutionsIterator = solutions.iterator();
        showBoard(primaryStage, solutionCount, solutionsIterator.next().get("ZenBoard"));
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.N) {
                    if (solutionsIterator.hasNext()) {
                        showBoard(primaryStage, ++solutionCount, solutionsIterator.next().get("ZenBoard"));
                    }
                }
            }
        });
    }

    private void showBoard(Stage primaryStage, int count, int[] queens) {
        final GridPane board = buildBoard(queens);
        primaryStage.setTitle("Solution " + count);
        primaryStage.setScene(new Scene(board, 400, 400));
        primaryStage.show();
    }

    private GridPane buildBoard(int[] queens) {
        final GridPane board = new GridPane();
        final int size = 8;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                final StackPane square = new StackPane();
                String color;
                if (queens[row] == col) {
                    color = "red";
                } else if ((row + col) % 2 == 0) {
                    color = "white";
                } else {
                    color = "black";
                }
                square.setStyle("-fx-background-color: " + color + ";");
                board.add(square, col, row);
            }
        }
        for (int i = 0; i < size; i++) {
            board.getColumnConstraints().add(
                    new ColumnConstraints(5, USE_COMPUTED_SIZE, POSITIVE_INFINITY, ALWAYS, HPos.CENTER, true));
            board.getRowConstraints().add(
                    new RowConstraints(5, USE_COMPUTED_SIZE, POSITIVE_INFINITY, ALWAYS, VPos.CENTER, true));
        }
        return board;
    }

    public static void main(String[] args) {
        launch(args);
    }
}