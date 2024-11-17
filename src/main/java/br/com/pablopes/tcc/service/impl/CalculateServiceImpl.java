package br.com.pablopes.tcc.service.impl;

import br.com.pablopes.tcc.model.Championship;
import br.com.pablopes.tcc.model.Team;
import br.com.pablopes.tcc.model.dto.CalculateRequest;
import br.com.pablopes.tcc.model.dto.CalculateResponse;
import br.com.pablopes.tcc.service.CalculateService;
import br.com.pablopes.tcc.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.DoubleStream;

@Service
@Slf4j
public class CalculateServiceImpl implements CalculateService {
    private final ExcelService excelService;

    public CalculateServiceImpl(ExcelService excelService) {
        this.excelService = excelService;
    }

    @Override
    public CalculateResponse execute(CalculateRequest request) {
        log.info("Starting Calculator");

        log.info("Read Teams: Start");
        var teams = excelService.readTeams(request.getYear());
        log.info("Read Teams: End");

        log.info("Read Championship Table: Start");
        var championship = excelService.readChampionhsip(request.getYear(), teams);
        log.info("Read Championship Table: End");

        log.info("Calculate Distances: Start");
        var response = this.calculate(request.getYear(), championship, teams);
        log.info("Calculate Distances: End");

        log.info("Ending Calculator");
        return response;
    }

    private CalculateResponse calculate(String year, Championship championship, List<Team> teams) {
        var response = new CalculateResponse();

        championship.getRounds().stream().forEach(round ->
            round.getMatch().stream().forEach(
                match -> {
                    //ajustando mandante
                    match.getHome().setDistance(
                        match.getHome().getDistance() +
                        excelService.findDistance(year, match.getHome().getCurrentPlace(), match.getHome().getPlace())
                    );
                    match.getHome().setCurrentPlace(match.getHome().getPlace());

                    //ajudatar visitante
                    match.getAway().setDistance(
                         match.getAway().getDistance() +
                         excelService.findDistance(year, match.getAway().getCurrentPlace(), match.getHome().getPlace())
                    );
                    match.getAway().setCurrentPlace(match.getHome().getPlace());
                }
            ));


        response.setTeams(teams);
        response.setTotalDistance(teams.parallelStream().flatMapToDouble(team -> DoubleStream.of(team.getDistance())).sum());





        return response;
    }


}
