package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;

//Java imports
import java.util.ArrayList;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;

    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }


    @Override
    public String getPlayerName() {
        return name;
    }


    @Override
    public PlayerType getPlayerType() {
        return type;
    }


    /**
     * Decides the next move for the bot...
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();

        int rock=0, paper=0, scissors=0;

        if(!results.isEmpty()) {
            for (Result result : results) {
                if (result.getWinnerMove().equals(Move.Rock)) rock++;
                if (result.getWinnerMove().equals(Move.Paper)) paper++;
                if (result.getWinnerMove().equals(Move.Scissor)) scissors++;
            }
        }

        Move move = Move.Rock;
        int max = rock;
        if(max<paper){
            max = paper;
            move = Move.Paper;
        }
        if(max<scissors) {
            max = scissors;
            move = Move.Scissor;
        }

        return move;
    }
}
