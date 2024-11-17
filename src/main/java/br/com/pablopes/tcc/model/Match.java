package br.com.pablopes.tcc.model;

import lombok.Data;

@Data
public class Match {
    private Team home;
    private Team away;

    public Match(Team home, Team away) {
        this.home = home;
        this.away = away;
    }
}
