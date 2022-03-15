
public class TennisGame3 implements TennisGame {

    private int p2;
    private int p1;
    private final String player1Name;
    private final String player2Name;
    private final String[] pointNames;
    private final String advantage;
    private final String winPhrase;
    private final String deuce;

    public TennisGame3(String p1N, String p2N) {
        this.player1Name = p1N;
        this.player2Name = p2N;
        winPhrase = "Win for ";
        pointNames = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
        advantage = "Advantage ";
        deuce = "Deuce";

    }

    // creamos una logica en sentido upstream, manejamos los casos desde el mas interior al mas superior para
    // simular un manejo de las condiciones similar al original

    private String gameStatement(){return pointNames[p1].concat("-").concat(pointNames[p2]);}

    private String tieStatement(){return p1 >= 3 ? deuce: pointNames[p1].concat("-All");}

    private String advantageStatement(){return p1 > p2 ? advantage.concat(player1Name) : advantage.concat(player2Name);}

    private String gameLine(){return p1 != p2 ? gameStatement(): tieStatement();}

    private String deuceLine(){return p1 != p2 ? advantageStatement() : deuce;}

    private String checkPlayer1Win(){return p1 > 3 ? winPhrase.concat(player1Name) : gameLine();}

    private String checkPlayer2Win(){return p2 > 3 ? winPhrase.concat(player2Name) : gameLine();}

    private String checkPlayer1WinDeuce(){return p1 - p2 >= 2 ? winPhrase.concat(player1Name) : deuceLine();}

    private String checkPlayer2WinDeuce(){return p1 - p2 <= -2 ? winPhrase.concat(player2Name) : deuceLine();}

    private String gameHandler(){return p1 > p2 ? checkPlayer1Win(): checkPlayer2Win();}

    private String deuceHandler(){return p1 > p2 ? checkPlayer1WinDeuce(): checkPlayer2WinDeuce();}

    public String getScore() {
        return p1 + p2 > 6 ? deuceHandler(): gameHandler();
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            this.p1 += 1;
            return;
        }
        this.p2 += 1;
    }
}
