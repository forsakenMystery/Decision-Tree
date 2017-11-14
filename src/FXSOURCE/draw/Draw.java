/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXSOURCE.draw;

import Calculation.Disease;
import DataStructures.BinaryTree;
import DataStructures.Node;
import DecisionTree.DecisionTree;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author forsakenMystery
 */
public class Draw extends Application {

    BinaryTree tree;

    public Draw() {

    }

    public Draw(BinaryTree tree) {
        this.tree = tree;
    }

    @Override
    public void start(Stage stage) {
        Group g = new Group();
        final Scene scene = new Scene(g, 800, 600);
        System.out.println("scene.getWidth() = " + scene.getWidth());
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                Platform.runLater(() -> {
                    DrawTree(g, 0, scene.getWidth(), 0, (scene.getHeight() / (tree.getheight(BinaryTree.getRoot()))), BinaryTree.getRoot());
                });
                return null;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
//        DrawTree(g, 0, scene.getWidth(), 0, (scene.getHeight() / (tree.getheight(BinaryTree.getRoot()))), BinaryTree.getRoot());

        stage.setScene(scene);
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws Exception {
                        Platform.runLater(() -> {
                            g.getChildren().remove(0, g.getChildren().size() - 1);

                            DrawTree(g, 0, scene.getWidth(), 0, (scene.getHeight() / (tree.getheight(BinaryTree.getRoot()))), BinaryTree.getRoot());
                        });
                        return null;
                    }
                };
                Thread th = new Thread(task);
                th.setDaemon(true);
                th.start();
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws Exception {
                        Platform.runLater(() -> {
                            g.getChildren().remove(0, g.getChildren().size() - 1);
                            DrawTree(g, 0, scene.getWidth(), 0, (scene.getHeight() / (tree.getheight(BinaryTree.getRoot()))), BinaryTree.getRoot());
                        });
                        return null;
                    }
                };
                Thread th = new Thread(task);
                th.setDaemon(true);
                th.start();
            }
        });
        stage.setTitle("Decision Tree");
        stage.setOnCloseRequest((WindowEvent we) -> {
            Platform.runLater(() -> {
                DecisionTree dt = new DecisionTree();
                try {
                    dt.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        });
        stage.show();
    }

    public void DrawTree(Group g, double StartWidth, double EndWidth, double StartHeight, double Level, Node node) {
        System.out.println("node = " + node);
        String data = node.String();
        if(data==null){
            data="don't Know";
        }
        Text txt = new Text(data);
        txt.setFont(new javafx.scene.text.Font("Tahoma", 10));
        double dataWidth = txt.getBoundsInLocal().getWidth();
        if (node.getYes() != null) {
            Text tt = new Text(node.getYes().String());
            tt.setFont(new javafx.scene.text.Font("Tahoma", 10));
            double dd = tt.getBoundsInLocal().getWidth();
            double x1 = (StartWidth + EndWidth) / 2 - dataWidth / 2 - 8 + dataWidth / 2;
            double y1 = StartHeight + Level / 2;
            double x2 = (StartWidth + (StartWidth + EndWidth) / 2) / 2 - dd / 2 - 8 + dd / 2;
            double y2 = StartHeight + Level + Level / 2;
            Line l = new Line(x1, y1, x2, y2);
            l.setFill(Color.BLUE);
            g.getChildren().add(l);
            Text text = new Text((x1 + x2) / 2 + 10, (y1 + y2) / 2, "Yes");
            text.setRotate(Math.atan((y2 - y1) / (x2 - x1)));
            text.setFont(new javafx.scene.text.Font("Tahoma", 10));
            g.getChildren().add(text);
        }
        if (node.getNo() != null) {
            Text tt = new Text(node.getNo().String());
            tt.setFont(new javafx.scene.text.Font("Tahoma", 10));
            double dd = tt.getBoundsInLocal().getWidth();
            double x1 = (StartWidth + EndWidth) / 2 - dataWidth / 2 - 8 + dataWidth / 2;
            double y1 = StartHeight + Level / 2;
            double x2 = ((StartWidth + EndWidth) / 2 + EndWidth) / 2;
            double y2 = StartHeight + Level + Level / 2;
            Line l = new Line(x1, y1, x2, y2);
            l.setFill(Color.GREEN);
            g.getChildren().add(l);
            Text text = new Text((x1 + x2) / 2 + 10, (y1 + y2) / 2, "No");
            text.setRotate(Math.atan((y2 - y1) / (x2 - x1)));
            text.setFont(new javafx.scene.text.Font("Tahoma", 10));
            g.getChildren().add(text);
//            g.setColor(Color.white);
//            Graphics2D g2d = (Graphics2D) g;
//            g2d.drawString("No", (x1+x2)/2, (y1+y2)/2);
//            AffineTransform at = new AffineTransform();
//            at.setToRotation(Math.atan((y2-y1)/x2-x1));
//            g2d.setTransform(at);
//            g2d.drawString("No", (x1+x2)/2, (y1+y2)/2);
        }
//        System.out.println("why");
        Ellipse el = new Ellipse((StartWidth + EndWidth) / 2 - 3, StartHeight + Level / 2 - 2, dataWidth / 2 + 9, dataWidth / 4 + 4);
        el.setFill(Color.RED);
        
        Button b = new Button(data);
        b.setShape(el);
        b.setLayoutX((StartWidth + EndWidth) / 2 - 3 - dataWidth / 2 - 13);
        b.setLayoutY(StartHeight + Level / 2 - 7);
        b.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        b.setOnMouseEntered((MouseEvent event) -> {
            b.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        });
        b.setOnMouseExited((MouseEvent event) -> {
            b.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        });
        b.setOnMouseClicked((MouseEvent event) -> {
            if (node.getCanHave() != null && node.getCanHave().size() > 1) {
                System.out.println("node.getCanHave() = " + node.getCanHave());
                Alert a = new Alert(Alert.AlertType.WARNING, "You can have these diseases :\n" + node.getCanHaveName(), ButtonType.OK);
                a.setTitle("Your Diseases");
                a.showAndWait();
            } else {
                if (node.getDisease().getName().equals("None")) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, " You are probably healty\n", ButtonType.OK);
                    a.setTitle("Good news");
                    a.showAndWait();
                } else {
                    Alert a = new Alert(Alert.AlertType.WARNING, "You Probably have this disease go Check Yourself Up :\n" + node.getDisease().getName(), ButtonType.OK);
                    a.setTitle("Your Diseases");
                    a.showAndWait();
                }
            }
        });
        g.getChildren().add(b);
//        g.getChildren().add(el);
//        System.out.println("dataWidth = " + dataWidth);
//        Text t = new Text((StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2, data);
//        t.setFont(new javafx.scene.text.Font("Tahoma", 10));
//        t.setFill(Color.BLACK);
//        g.getChildren().add(t);
        if (node.getYes() != null) {
            DrawTree(g, StartWidth, (StartWidth + EndWidth) / 2, StartHeight + Level, Level, node.getYes());
        }

        if (node.getNo() != null) {
            DrawTree(g, (StartWidth + EndWidth) / 2, EndWidth, StartHeight + Level, Level, node.getNo());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
