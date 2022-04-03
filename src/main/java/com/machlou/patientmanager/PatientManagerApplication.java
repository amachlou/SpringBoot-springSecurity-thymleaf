package com.machlou.patientmanager;

import com.machlou.patientmanager.entities.Patient;
import com.machlou.patientmanager.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientManagerApplication implements CommandLineRunner {

	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(PatientManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null, "Jihane","Arfi", "jihane@machlou.com",new Date()));
		patientRepository.save(new Patient(null, "Abderrahim","Machlou", "jihane@machlou.com",new Date()));
		patientRepository.save(new Patient(null, "Ayoub","Berached", "ayoub@machlou.com",new Date()));
		patientRepository.save(new Patient(null, "Aziz","Daaif", "aziz@machlou.com",new Date()));
		patientRepository.save(new Patient(null, "Mohamed","Youssfi", "med@machlou.com",new Date()));
	}

}
