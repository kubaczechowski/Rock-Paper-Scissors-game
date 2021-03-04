package rps.gui.controller;

// Java imports
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    public ImageView playerImage;
    public ImageView botImage;
    public Label playerScore;
    public Label botScore;
    public Label resultLabel;

    private int botPoints;
    private int playerPoints;
    GameManager gameManager;
    IPlayer human;
    IPlayer bot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        botPoints = 0;
        playerPoints = 0;
        human = new Player("player", PlayerType.Human);
        bot = new Player("Game Bot", PlayerType.AI);
        gameManager = new GameManager(human, bot);
    }

    public void rockPlayed(ActionEvent actionEvent) throws InterruptedException {
        gameManager.playRound(Move.Rock);
        checkTheMove();
    }


    public void scissorsPlayed(ActionEvent actionEvent) throws InterruptedException {
        gameManager.playRound(Move.Scissor);
        checkTheMove();
    }

    public void paperPlayed(ActionEvent actionEvent) throws InterruptedException {
        gameManager.playRound(Move.Paper);
        checkTheMove();
    }

    private void checkTheMove() throws InterruptedException {
        Object[] results = gameManager.getGameState().getHistoricResults().toArray();
        int lastIndex = gameManager.getGameState().getHistoricResults().size() - 1;
        Result result = (Result) results[lastIndex];
        if(result.getType().equals(ResultType.Win)) {
            if (result.getWinnerPlayer().equals(human)) {
                playerPoints++;
                playerScore.setText(String.valueOf(playerPoints));
                showTheMove(result.getWinnerMove(), result.getLoserMove(), "YOU WIN");
            } else if (result.getWinnerPlayer().equals(bot)) {
                botPoints++;
                botScore.setText(String.valueOf(botPoints));
                showTheMove(result.getLoserMove(), result.getWinnerMove(), "YOU LOOSE");
            }
        } else {
            showTheMove(result.getWinnerMove(), result.getLoserMove(), "IT'S A TIE");
        }
    }

    public void showTheMove(Move playerMove, Move botMove, String resultMessage) throws InterruptedException {

        if(playerMove.equals(Move.Rock)) playerImage.setImage(new Image("/images/rock.png"));
        if(playerMove.equals(Move.Paper)) playerImage.setImage(new Image("/images/paper.png"));
        if(playerMove.equals(Move.Scissor)) playerImage.setImage(new Image("/images/scissors.png"));
        if(botMove.equals(Move.Rock)) botImage.setImage(new Image("/images/rock.png"));
        if(botMove.equals(Move.Paper)) botImage.setImage(new Image("/images/paper.png"));
        if(botMove.equals(Move.Scissor)) botImage.setImage(new Image("/images/scissors.png"));

        FadeTransition ft = new FadeTransition();
        ft.setNode(playerImage);
        ft.setDuration(new Duration(2000));
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);

        FadeTransition ft1 = new FadeTransition();
        ft1.setNode(botImage);
        ft1.setDuration(new Duration(2000));
        ft1.setFromValue(0.0);
        ft1.setToValue(1.0);
        ft1.setCycleCount(1);
        ft1.setAutoReverse(true);

        ft.play();
        ft1.play();

        //Thread.sleep(2000);
        resultLabel.setText(resultMessage);
        //Thread.sleep(5000);
        //resultLabel.setText("");
        //playerImage.setImage(null);
        //botImage.setImage(null);

    }

}
