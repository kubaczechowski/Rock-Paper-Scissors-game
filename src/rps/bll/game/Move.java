package rps.bll.game;

import java.util.Arrays;
import java.util.List;

/**
 * The various move options in the game
 *
 * @author smsj
 */
public enum Move {
    Rock,
    Paper,
    Scissor;

    public Move getLosesTo() {
        return losesTo;
    }

    Move losesTo;

   /*
   public boolean losesTo(Move other) {
        return losesTo.equals(other);
    }

    */

    static {
        Rock.losesTo = Paper;
        Paper.losesTo = Scissor;
        Scissor.losesTo = Rock;
    }
}
