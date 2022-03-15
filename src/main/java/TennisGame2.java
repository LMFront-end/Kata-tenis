import java.util.HashMap;
import java.util.Map;

public class TennisGame2 implements TennisGame
{
    private int p1point;
    private int p2point;


    private final String player1Name;
    private final String player2Name;
    private final String winStatement;
    private final String[] scoreNames = new String[]{"Love", "Fifteen", "Thirty", "Forty", "Win for "};

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        p1point = 0;
        p2point = 0;
        winStatement = "Win for ";
    }

    // definimos metodo para señalar los puntajes en caso de deuce
    public Map<Integer, String> deuceScores(String playerName1, String playerName2){
        Map<Integer, String> scores = new HashMap<>();
        scores.put(-2, winStatement + playerName2);
        scores.put(-1, "Advantage " + playerName2);
        scores.put(0, "Deuce");
        scores.put(1, "Advantage " + playerName1);
        scores.put(2, winStatement + playerName1);
        return scores;
    }

    public void p1Score(){
        p1point++;
    }

    public void p2Score(){
        p2point++;
    }

    // se elimina el if-else
    public void wonPoint(String player) {
        if (player.equals(player1Name)) {
            p1Score();
            return;
        }
        p2Score();
    }

    private String scoreStatement(){
        return p1point == p2point ? scoreNames[p1point] + "-All": scoreNames[p1point] + "-" + scoreNames[p2point];
    }

    // metodo que gestiona el puntaje durante un deuce
    public String scoreLineDeuce(){
        return deuceScores(player1Name, player2Name).get(p1point - p2point);
    }

    private String checkPlayer2Wins(){
        return p2point > 3 ? winStatement + player2Name : scoreStatement();
    }

    private String completeScoreStatement(){
        Map<Integer, String> statements = deuceScores(player1Name, player2Name);
        return p1point > 3 ? statements.get(2) : checkPlayer2Wins();
    }

    public String getScore(){
        return p1point >=3 && p2point >=3 ? scoreLineDeuce(): completeScoreStatement();
    }
}