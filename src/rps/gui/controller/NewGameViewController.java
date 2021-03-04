package rps.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;


public class NewGameViewController implements Initializable {
    private GameViewController gameViewController;
   private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TextField nameField;

    @FXML
    private Label incorrectLbl;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void btnNewGame(ActionEvent actionEvent) throws IOException {
        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {

            incorrectLbl.setText("Please enter a your name!");

        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/rps/gui/view/GameView.fxml"));
            stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }



    }
}
