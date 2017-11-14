/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DecisionTree;


import DataStructures.BinaryTree;
import DataStructures.Draw;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author forsakenMystery
 */
public class StartRunner extends Application{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
//        Draw draw = new Draw(new BinaryTree());
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXSOURCE/draw/FXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("ID3 Algorithm");
        stage.setScene(scene);
        stage.show();
    }
    
}