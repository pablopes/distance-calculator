package br.com.pablopes.tcc.service;

import br.com.pablopes.tcc.model.Team;
import br.com.pablopes.tcc.model.Championship;

import java.util.List;

public interface ExcelService {
    List<Team> readTeams(String year);
    Championship readChampionhsip(String year, List<Team> teams);
    Double findDistance(String year, String from, String to);

}
