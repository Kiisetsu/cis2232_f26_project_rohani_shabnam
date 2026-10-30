package ca.hccis.adoption.controllers;

import ca.hccis.adoption.dao.AnimalDAO;
import ca.hccis.adoption.dao.AnimalDAO.AnimalRow;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {

    private final AnimalDAO dao;

    public ReportController(AnimalDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/reports/animals")
    public String input() {
        return "reports/animalInput";
    }

    @PostMapping("/reports/animals")
    public String run(@RequestParam String species,
                      @RequestParam double minAge,
                      @RequestParam double maxAge,
                      Model model) {
        try {
            List<AnimalRow> rows = dao.findBySpeciesAndAge(species, minAge, maxAge);
            String fileName = writeReportFile(species, minAge, maxAge, rows);

            model.addAttribute("rows", rows);
            model.addAttribute("names", rows.stream().map(AnimalRow::name).toList());
            model.addAttribute("ages", rows.stream().map(AnimalRow::age).toList());
            model.addAttribute("species", species);
            model.addAttribute("minAge", minAge);
            model.addAttribute("maxAge", maxAge);
            model.addAttribute("fileName", fileName);
        } catch (SQLException | IOException e) {
            model.addAttribute("error", "Report failed: " + e.getMessage());
        }
        return "reports/animalResults";
    }

    private String writeReportFile(String species, double minAge, double maxAge,
                                   List<AnimalRow> rows) throws IOException {
        Path folder = Paths.get("c:\\cis2232\\");
        Files.createDirectories(folder);

        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        Path file = folder.resolve("AnimalsBySpeciesAndAge" + stamp + ".txt");

        List<String> lines = new ArrayList<>();
        lines.add("Animals by species and age report");
        lines.add("Species: " + species + "   Age: " + minAge + " to " + maxAge);
        lines.add("");
        lines.add(String.format("%-5s %-15s %-8s %-8s %-12s %-8s %s",
                "ID", "Name", "Sex", "Species", "Colour", "Coat", "Age"));
        for (AnimalRow r : rows) {
            lines.add(String.format("%-5d %-15s %-8s %-8s %-12s %-8s %.1f",
                    r.id(), r.name(), r.sex(), r.species(), r.colour(), r.coat(), r.age()));
        }
        lines.add("");
        lines.add("Total animals: " + rows.size());

        Files.write(file, lines);
        return file.toString();
    }
}
