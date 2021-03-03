package rps.bll.util;

import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.player.PlayerType;
import java.util.List;
import java.util.Random;

/**
 * @author kuba
 */
public class MarkovChain implements IMarkovChain {
    private int[][] matrix;
    private int nbRounds;
    private static final Random RANDOM = new Random();
    private static MarkovChain markovChain;

    public static MarkovChain getController(){
        if(markovChain ==null)
            markovChain = new MarkovChain();
        return markovChain;
    }

    public MarkovChain() {
        initialize();
    }

    /**
     * after users move the corresponding value in the matrix is
     * incremented
     * @param prev
     * @param next
     */
    @Override
    public void updateMarkovChain(Move prev, Move next) {
        matrix[prev.ordinal()][next.ordinal()]++;
    }

    /**
     * method initializes class for the first actions
     */
    @Override
    public void initialize() {
        int length = Move.values().length;
        matrix = new int[length][length];
        fillMatrixWithInitialValues(length);
        setNbRounds(0);
    }



    private void fillMatrixWithInitialValues(int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    /**
     * method returns the next move predicted for the user
     * (we assume that it will be a move that they will make)
     * @param previous
     * @return
     */
    @Override
    public Move getNextUsersMove(Move previous) {
        //no records in the matrix
        if (nbRounds < 1) {
            System.out.println("get random move (less than one round in the records)");
            return Move.values()[RANDOM.nextInt(Move.values().length)];
        }
        Move predictedNext = getPredictedMove(previous);
        return predictedNext;
    }
    private Move getPredictedMove(Move previous){
        int nextIndex = 0;
        for (int i = 0; i < Move.values().length; i++) {
            int prevIndex = previous.ordinal();

            if (matrix[prevIndex][i] > matrix[prevIndex][nextIndex]) {
                nextIndex = i;
            }
        }
        Move predictedNext = Move.values()[nextIndex];
        System.out.println("Next predicted move: "+ predictedNext);
        return predictedNext;
    }
    /**
     * method is called whenever the ai is about to make a move
     *
     * @param results
     */
    @Override
    public Move action(List<Result> results) {
        if(!results.isEmpty()) {
            Move previousMove = MarkovChain.getPreviousMove(results);
            Move next =  markovChain.getNextUsersMove(previousMove);
            //how should i update it??
            markovChain.updateMarkovChain(previousMove, next);
            markovChain.incrementNbRounds();
            return next.getLosesTo().getLosesTo(); // I have no idea but it works
        }
        else {
            return Move.values()[RANDOM.nextInt(Move.values().length)];
        }
    }


    public static Move getPreviousMove(List<Result> resultList){
        Result lastResult = resultList.get(resultList.size()-1);

        if(lastResult.getWinnerPlayer().getPlayerType() == PlayerType.Human)
            return lastResult.getWinnerMove();
        else
            return lastResult.getLoserMove();
    }

    public int getNbRounds() {
        return nbRounds;
    }

    public void setNbRounds(int nbRounds) {
        this.nbRounds = nbRounds;
    }
    public void incrementNbRounds(){
        nbRounds++;
    }


}
