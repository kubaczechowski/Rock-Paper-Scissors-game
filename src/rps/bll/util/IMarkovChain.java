package rps.bll.util;

import rps.bll.game.Move;
import rps.bll.game.Result;

import java.util.List;

public interface IMarkovChain {

    /**
     * after users move the corresponding value in the matrix is
     * incremented
     * @param prev
     * @param next
     */
    void updateMarkovChain(Move prev, Move next);

    /**
     * method initializes class for the first actions
     */
    void initialize();

    /**
     * method returns the move that will propably be
     * made by the user
     * @param move
     * @return
     */
    Move getNextUsersMove(Move move);

    /**
     * method is called whenever the ai is about to make a move
     * @param resultList
     */
    Move action(List<Result> resultList);
}
