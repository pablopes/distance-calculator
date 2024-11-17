package br.com.pablopes.tcc.service.impl;

import br.com.pablopes.tcc.model.Championship;
import br.com.pablopes.tcc.model.Match;
import br.com.pablopes.tcc.model.Round;
import br.com.pablopes.tcc.model.Team;
import br.com.pablopes.tcc.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {
    @Override
    public List<Team> readTeams(String year) {
        try {
            var teams = new ArrayList<Team>();
            var resource = new ClassPathResource("planilhas/teams.xlsx");
            var file = new FileInputStream(resource.getFile());
            var workbook = new XSSFWorkbook(file);
            var sheet = workbook.getSheet(year);
            Team team;
            for (var row : sheet) {
                team = Team.builder()
                        .name(row.getCell(0).getStringCellValue())
                        .place(row.getCell(1).getStringCellValue())
                        .currentPlace(row.getCell(1).getStringCellValue())
                        .build();
                this.findDistance(year, team.getCurrentPlace(), team.getPlace());
                teams.add(team);

                if (teams.size() == 20) {
                    break;
                }
            }
            return teams;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Championship readChampionhsip(String year, List<Team> teams) {
        try {
            var resource = new ClassPathResource("planilhas/championship.xlsx");
            var file = new FileInputStream(resource.getFile());
            var workbook = new XSSFWorkbook(file);
            var sheet = workbook.getSheet(year);

            var championship = new Championship();
            var round = new Round();
            for (var row : sheet) {
                var home = teams.parallelStream().filter(team -> team.getName().equals(row.getCell(0).getStringCellValue())).findFirst();
                var away = teams.parallelStream().filter(team -> team.getName().equals(row.getCell(1).getStringCellValue())).findFirst();

                if (home.isPresent() && away.isPresent()) {
                    var match = new Match(home.get(), away.get());

                    if (round.getMatch().size() == 10) {
                        championship.getRounds().add(round);
                        round = new Round();
                    }
                    round.getMatch().add(match);
                }
            }
            championship.getRounds().add(round);
            return championship;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Double findDistance(String year, String from, String to) {
        try {
            var resource = new ClassPathResource("planilhas/roadmap.xlsx");
            var file = new FileInputStream(resource.getFile());
            var workbook = new XSSFWorkbook(file);
            var sheet = workbook.getSheet(year);

            var line = 0;
            var column = 0;
            //preenchendo indice linha
            for (var row : sheet) {
                if (this.normalizeText(from).equals(this.normalizeText(row.getCell(0).getStringCellValue()))) {
                   line = row.getRowNum();
                    break;
                }
            }
            //preencher indice coluna
            for (var col : sheet.getRow(0)) {
                if (this.normalizeText(to).equals(this.normalizeText(col.getStringCellValue()))) {
                    column = col.getColumnIndex();
                    break;
                }
            }

            return sheet.getRow(line).getCell(column).getNumericCellValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String normalizeText(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase();
    }

}
