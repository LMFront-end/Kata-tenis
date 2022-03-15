import java.util.HashMap;
import java.util.Map;

public class TennisGame1 implements TennisGame {

    private int score1 = 0;
    private int score2 = 0;
    private final String player1Name;
    private final String player2Name;
    private final String winStatement;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        winStatement = "Win for ";
    }

    // definimos metodo para señalar los puntajes de los jugadores
    public Map<Integer, String> scores(String playerName){
        Map<Integer, String> scores = new HashMap<>();
        scores.put(0, "Love");
        scores.put(1, "Fifteen");
        scores.put(2, "Thirty");
        scores.put(3, "Forty");
        scores.put(4, winStatement + playerName);
        return scores;
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

    // eliminamos code smell if/else y == para Strings
    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            score1 += 1;
            return;
        }
        score2 += 1;
    }

    // metodo que gestiona el puntaje antes del deuce
    public String scoreLine(){
        return score1 == score2 ?
                scores(player1Name).get(score1) + "-All":
                scores(player1Name).get(score1) + "-" + scores(player2Name).get(score2);
    }

    // separamos la funcion que retorna
    public String whoWon(String playerName1, String playerName2){
        return scoreLine().contains(playerName1) ?
                scores(playerName1).get(4):
                scores(playerName2).get(4);
    }

    // metodo que elimina el puntaje del jugador que perdio el game
    public String checkWinner(){
        return scoreLine().contains(winStatement) ?
                whoWon(player1Name, player2Name):
                scoreLine();
    }

    // metodo que gestiona el puntaje durante un deuce
    public String scoreLineDeuce(){
        return deuceScores(player1Name, player2Name).get(score1 - score2);
    }

    // se separa la condicion de deuce mediante un metodo
    public boolean isDeuce(int score1, int score2){
        return score1 >= 3 && score2 >= 3;
    }

    // se arregla el meollo de if-else y switch del codigo original
    public String getScore(){
        return isDeuce(score1, score2)?
                scoreLineDeuce():
                checkWinner();
    }
}
