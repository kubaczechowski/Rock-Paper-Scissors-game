package rps.gui.controller;

// Java imports
import com.jfoenix.controls.JFXButton;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import rps.bll.game.Move;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {


    private static final String PAPER = "paper";
    private static final String STONE = "stone";
    private static final String SCISSORS = "scissors";
    private Image image;



    @FXML
    private ImageView humanView;

    @FXML
    private ImageView botView;

    @FXML
    private Label WhoIsTheWinner;

    @FXML
    private JFXButton stoneBtn;

    @FXML
    private JFXButton paperBtn;

    @FXML
    private JFXButton scissorsBtn;

    @FXML
    private Label humanlbl;

    @FXML
    private Label humanScoreLbl;

    @FXML
    private Label botScoreLbl;




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    @FXML
    private void humanTurn(ActionEvent event){
        String Choice = null;
        switch (((JFXButton) event.getSource()).getId()) {
            case "paperBtn":
                image = new Image("/rps/images/Paper.png");
                Choice = PAPER;
                break;
            case "stoneBtn":
                image = new Image("/rps/images/Rock.png");
                Choice = STONE;
                break;
            case "scissorsBtn":
                image = new Image("/rps/images/Scissors.png");
                Choice = SCISSORS;
                break;
        }
        humanView.setImage(image);

    }





}
