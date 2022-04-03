package com.machlou.patientmanager.controllers;

import com.machlou.patientmanager.entities.Patient;
import com.machlou.patientmanager.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private PatientRepository patientRepository;

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient){
        return patientRepository.save(patient);
    }

    @GetMapping
    public Page<Patient> getAll(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size){
        return patientRepository.findAll(PageRequest.of(page,size));
    }

    @GetMapping("/search")
    public List<Patient> searchByFirstName(@RequestParam String key, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size){
//        return patientRepository.getByKeyword(key, PageRequest.of(page,size));
        return patientRepository.getByKeyword(key);
    }

    @GetMapping("/{id}")
    public Optional<Patient> getOne(@PathVariable Long id){
        return patientRepository.findById(id);
    }

    @PatchMapping
    public Patient updatePatient(@RequestBody Patient patient){
        return patientRepository.save(patient);
    }

    @DeleteMapping("/{id}")
    public void deleteBOne(@PathVariable Long id){
        patientRepository.deleteById(id);
    }
}
