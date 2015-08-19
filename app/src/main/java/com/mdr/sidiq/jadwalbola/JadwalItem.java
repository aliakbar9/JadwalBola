package com.mdr.sidiq.jadwalbola;

import java.io.Serializable;

/**
 * Created by Sidiq on 12/06/2015.
 */
public class JadwalItem implements Serializable {
    String kickOff, event, teamAway, teamHome, tv;

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(String teamHome) {
        this.teamHome = teamHome;
    }

    public String getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(String teamAway) {
        this.teamAway = teamAway;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getKickOff() {
        return kickOff;
    }

    public void setKickOff(String kickOff) {
        this.kickOff = kickOff;
    }
}
