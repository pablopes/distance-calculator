package br.com.pablopes.tcc.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Championship {
    private List<Round> rounds  = new ArrayList<>();
    private Integer distance = 0;
    private Double rating = 0.0;
    private Integer distanceBase = 0;
    private Integer recurrence = 0;

}
