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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author forsakenMystery
 */
public class DecisionTree extends Application{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static Stage stage;
    public static String arg[];
    public static void main(String[] args) throws IOException {
//        Draw draw = new Draw(new BinaryTree());
        arg=args;
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXSOURCE/menu/FXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        this.stage=stage;
        stage.show();
    }
    
}

