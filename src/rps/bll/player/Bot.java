package rps.bll.player;

import rps.bll.game.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import rps.bll.game.GameManager;

public class Bot {
    private static final Random RANDOM = new Random();
    private int[][] chain;
    private int nbRounds= 0;
    private HashMap<Move,Move> losesTo = new HashMap<>();




    public Bot() {

        losesTo.put(Move.Rock, Move.Paper);
        losesTo.put(Move.Paper, Move.Scissor);
        losesTo.put(Move.Scissor, Move.Rock);




        int length = Move.values().length;
        chain = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                chain[i][j] = 0;
            }
        }
    }




    public void updateChain(Move prev, Move next) {

        chain[prev.ordinal()][next.ordinal()]++;
    }



    public Move getNextMove(Move prev) {

        if (nbRounds < 1) {
            return Move.values()[RANDOM.nextInt(Move.values().length)];
        }
        int nextIndex = 0;

        for (int i = 0; i < Move.values().length; i++) {
            int prevIndex = prev.ordinal();

            if (chain[prevIndex][i] > chain[prevIndex][nextIndex]) {
                nextIndex = i;
            }
        }

        Move predictedNext = Move.values()[nextIndex];

        return losesTo.get(nextIndex);
    }
}
