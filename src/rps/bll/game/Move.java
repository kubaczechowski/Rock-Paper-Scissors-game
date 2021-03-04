package rps.bll.game;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

/**
 * The various move options in the game
 *
 * @author smsj
 */
public enum Move {
    Rock, Paper, Scissor,

   // public List<Move> losesTo;

    /*
    public boolean losesTo(Move other) {
        return losesTo.contains(other);
    }

    static {
        Move.Scissor.losesTo = Arrays.asList(Rock, Paper);
        Move.Rock.losesTo = Arrays.asList(Paper, Scissor);
        Move.Paper.losesTo = Arrays.asList(Scissor, Rock);

    }

     */




}
