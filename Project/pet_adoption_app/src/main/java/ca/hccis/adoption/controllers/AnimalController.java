package ca.hccis.adoption.controllers;

import ca.hccis.adoption.entity.Animal;
import ca.hccis.adoption.repositories.AnimalRepository;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class AnimalController {

    private final AnimalRepository repository;

    public AnimalController(AnimalRepository repository) {
        this.repository = repository;
    }

    //Dropdown choices available to the form
    @ModelAttribute("speciesOptions")
    public List<String> speciesOptions() { return List.of("Cat", "Dog"); }

    @ModelAttribute("coatOptions")
    public List<String> coatOptions() { return List.of("Short", "Medium", "Long", "Bald"); }

    @ModelAttribute("sexOptions")
    public List<String> sexOptions() { return List.of("Male", "Female"); }

    //List
    @GetMapping("/animals")
    public String list(Model model) {
        List<Animal> animals = repository.findAll();

        Map<Integer, Double> fees = new HashMap<>();
        double total = 0;
        for (Animal a : animals) {
            try {
                double fee = a.calculate();
                fees.put(a.getId(), fee);
                total += fee;
            } catch (IllegalArgumentException e) {
                //leave this animal without a fee
            }
        }

        model.addAttribute("animals", animals);
        model.addAttribute("fees", fees);
        model.addAttribute("totalFees", total);
        return "animals/animalList";
    }

    //Add (show blank form)
    @GetMapping("/animals/add")
    public String add(Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("formTitle", "Add Animal");
        return "animals/animalForm";
    }

    //Update (show form filled in)
    @GetMapping("/animals/edit/{id}")
    public String edit(@PathVariable int id, Model model, RedirectAttributes redirect) {
        Optional<Animal> animal = repository.findById(id);
        if (animal.isEmpty()) {
            redirect.addFlashAttribute("error", "Animal " + id + " was not found.");
            return "redirect:/animals";
        }
        model.addAttribute("animal", animal.get());
        model.addAttribute("formTitle", "Update Animal");
        return "animals/animalForm";
    }

    //Save (used by both add and update)
    @PostMapping("/animals/save")
    public String save(@Valid @ModelAttribute("animal") Animal animal,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirect) {

        //Age limits depend on species (cats 0-20, dogs 0-15)
        if (!result.hasFieldErrors("age") && !result.hasFieldErrors("species")) {
            boolean dog = "dog".equalsIgnoreCase(animal.getSpecies());
            double max = dog ? 15 : 20;
            if (animal.getAge() > max) {
                result.rejectValue("age", "age.range",
                        (dog ? "A dog" : "A cat") + " must be between 0 and " + (int) max + " years");
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("formTitle", animal.getId() == 0 ? "Add Animal" : "Update Animal");
            return "animals/animalForm";
        }

        //Normalize M/F and capitalization before saving
        animal.setSex(normalizeSex(animal.getSex()));
        animal.setSpecies(capitalize(animal.getSpecies()));
        animal.setCoat(capitalize(animal.getCoat()));

        boolean isNew = animal.getId() == 0;
        repository.save(animal);
        redirect.addFlashAttribute("message",
                "Animal " + animal.getName() + (isNew ? " added." : " updated."));
        return "redirect:/animals";
    }

    //Delete
    @PostMapping("/animals/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            redirect.addFlashAttribute("message", "Animal deleted.");
        } else {
            redirect.addFlashAttribute("error", "Animal " + id + " was not found.");
        }
        return "redirect:/animals";
    }

    private String normalizeSex(String sex) {
        return sex.trim().toLowerCase().startsWith("m") ? "Male" : "Female";
    }

    private String capitalize(String text) {
        String t = text.trim().toLowerCase();
        return t.substring(0, 1).toUpperCase() + t.substring(1);
    }
}
