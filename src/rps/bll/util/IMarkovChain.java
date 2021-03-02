package rps.bll.util;

import rps.bll.game.Move;

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
     * method returns the best strategic move that will be made by the
     * AI
     * @param move
     * @return
     */
    Move getNextMove(Move move);
}
