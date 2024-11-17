package br.com.pablopes.tcc.model.dto;

import br.com.pablopes.tcc.model.Team;
import lombok.Data;

import java.util.List;

@Data
public class CalculateResponse {
    private List<Team> teams;
    private double totalDistance;
}
