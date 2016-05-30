package sofascore.enums;

/**
 * Created by gfox on 27/05/2016.
 */
public enum Tournaments {
    INTERNATIONAL("International"),
    Zambia("Zambia"),
    RUSSIA("Russia");
    private String tournamentName;

    Tournaments(String tournament) {
        this.tournamentName = tournament;
    }

    public String getTournament() {
        return this.tournamentName;
    }
}
