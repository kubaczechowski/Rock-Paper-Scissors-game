package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.util.MarkovChain;

//Java imports
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;
    private MarkovChain markovChain  = MarkovChain.getController();
    private static final Random RANDOM = new Random();

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

        //Implement better AI here...
        //return Move.Rock;
        if(!results.isEmpty()) {
            Move previousMove = MarkovChain.getPreviousMove(results).getLosesTo();
           Move next =  markovChain.getNextMove(previousMove);
           markovChain.updateMarkovChain(previousMove, next);
           markovChain.incrementNbRounds();
           return next;
        }
        else
            return Move.values()[RANDOM.nextInt(Move.values().length)];
    }

}
