package rps.bll.game;


/**
 * The various move options in the game
 *
 * @author smsj
 */
public enum Move {
    Rock("/rps/gui/images/rock6.jpg"),
    Paper("/rps/gui/images/paper.png"),
    Scissor("/rps/gui/images/scissors.png");

    String path;

    public String getPath(){
        return path;
    }

    private Move(final String imagePath){
        path = imagePath;
    }


    public Move getLosesTo() {
        return losesTo;
    }

    Move losesTo;

   public boolean losesTo(Move other) {
        return losesTo.equals(other);
    }

    static {
        Rock.losesTo = Paper;
        Paper.losesTo = Scissor;
        Scissor.losesTo = Rock;
    }
}
