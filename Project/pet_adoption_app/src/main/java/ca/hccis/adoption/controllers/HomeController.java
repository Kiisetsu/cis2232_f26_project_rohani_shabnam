package ca.hccis.adoption.controllers;

import ca.hccis.adoption.dao.AnimalDAO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.Map;

@org.springframework.stereotype.Controller
public class HomeController {

    private final AnimalDAO dao;

    public HomeController(AnimalDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/")
    public String home(Model model) {
        try {
            Map<String, Integer> counts = dao.countBySpecies();
            model.addAttribute("speciesNames", counts.keySet());
            model.addAttribute("speciesCounts", counts.values());
        } catch (SQLException e) {
            model.addAttribute("error", "Could not load animals: " + e.getMessage());
        }
        return "index";
    }

    @RequestMapping("/add")
    public String add() {
        return "/animals/add";
    }
}
