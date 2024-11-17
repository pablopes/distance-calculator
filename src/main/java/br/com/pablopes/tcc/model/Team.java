package br.com.pablopes.tcc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {
    private  String name;
    private String place;
    private String currentPlace;
    private double distance = 0;
    private int lineIndex;
    private int columnIndex;
}
