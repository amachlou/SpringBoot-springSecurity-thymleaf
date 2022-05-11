package com.machlou.patientmanager.controllers;

import com.machlou.patientmanager.entities.Patient;
import com.machlou.patientmanager.repositories.PatientRepository;
import javafx.scene.shape.PathElement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping
    public String getAll(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size){
        Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("patients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "patients";
    }

    @GetMapping("/{id}")
    public Optional<Patient> getOne(@PathVariable Long id){
        return patientRepository.findById(id);
    }

    @GetMapping("/search")
    public String search(Model model,
                                    @RequestParam(defaultValue = "") String keyword,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "5") int size){
        if(keyword.isEmpty())
            return  "redirect:/patients";
        Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("patients", patientRepository.findByFirstNameContainsOrLastNameContains(keyword, PageRequest.of(page,size)));
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/edit")
    public String edit(Model model){
        model.addAttribute("patient", new Patient());
        return "patient_edit";
    }

    @PostMapping("/save")
    public Patient addPatient(@RequestBody Patient patient){
        return patientRepository.save(patient);
    }

    @PatchMapping
    public Patient updatePatient(@RequestBody Patient patient){
        return patientRepository.save(patient);
    }

    @GetMapping("/delete/{id}")
    public String deleteOne(@PathVariable("id") long id, String keyword, int page, @RequestParam(name = "size", defaultValue = "5") int size){
        patientRepository.deleteById(id);
        return "redirect:/patients?"+"keyword="+keyword+"&page="+page+"&size="+size;
    }
}
