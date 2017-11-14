/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXSOURCE.menu;

import DataStructures.BinaryTree;
import FXSOURCE.draw.Draw;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author forsakenMystery
 */
public class FXMLController implements Initializable {

    @FXML
    private Button chooseSymptomFile;
    @FXML
    private Button defaultSymptomFile;
    @FXML
    private Button chooseDiseaseFile;
    @FXML
    private Button defaultDiseaseFile;
    @FXML
    private Button about;
    @FXML
    private Button start;
    @FXML
    private Button exit;
    @FXML
    private VBox stage;
    private String addressSymptom;
    private String addressDisease;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addressSymptom = "E:\\forsakenmystery\\Net beans\\DecisionTree\\src\\Fetch\\symptoms.soz";
        addressDisease = "E:\\forsakenmystery\\Net beans\\DecisionTree\\src\\Fetch\\disease.doz";
        
    }

    @FXML
    private void chooseSymptomFileClicked(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Symptom File", "*.soz"));
        File file = fileChooser.showOpenDialog(DecisionTree.DecisionTree.stage);
        addressSymptom=file.getAbsolutePath();
        System.out.println("addressSymptom = " + addressSymptom);
        defaultSymptomFile.setDisable(false);
    }

    @FXML
    private void defaultSymptomFileClicked(MouseEvent event) {
        defaultSymptomFile.setDisable(true);
        addressSymptom = "E:\\forsakenmystery\\Net beans\\DecisionTree\\src\\Fetch\\symptoms.soz";
    }

    @FXML
    private void chooseDiseaseFileClicked(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Disease File", "*.doz"));
        File file = fileChooser.showOpenDialog(DecisionTree.DecisionTree.stage);
        addressDisease=file.getAbsolutePath();
        defaultDiseaseFile.setDisable(false);
    }

    @FXML
    private void defaultDiseaseFileClicked(MouseEvent event) {
        defaultDiseaseFile.setDisable(true);
        addressSymptom = "E:\\forsakenmystery\\Net beans\\DecisionTree\\src\\Fetch\\disease.doz";
    }

    @FXML
    private void aboutClicked(MouseEvent event) {
        Alert a = new Alert(Alert.AlertType.NONE, "Using ID3 to find Decision tree \n By Hamed Khashechi 9318953", ButtonType.OK);
        a.setTitle("About");
        Optional<ButtonType> showAndWait = a.showAndWait();
    }

    @FXML
    private void startClicked(MouseEvent event) throws IOException {
        Task<Integer> task = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        DecisionTree.DecisionTree.stage.close();
                        Draw draw = null;
                        try {
                            draw = new Draw(new BinaryTree());
                            draw=new Draw(new BinaryTree(addressDisease,addressSymptom));
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        draw.start(new Stage());
                    }
                });
                return null;
            }

        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    @FXML
    private void exitClicked(MouseEvent event) {
        System.exit(0);
    }

}
