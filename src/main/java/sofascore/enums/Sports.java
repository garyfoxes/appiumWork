package sofascore.enums;

/**
 * Created by gfox on 27/05/2016.
 */
public enum Sports {
    FOOTBALL("Football");
    private String sport;

    Sports(String sport) {
        this.sport = sport;
    }

    public String getSport() {
        return this.sport;
    }
}
