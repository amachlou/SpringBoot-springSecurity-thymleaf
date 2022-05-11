package com.machlou.patientmanager;

import com.machlou.patientmanager.entities.Patient;
import com.machlou.patientmanager.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.io.Serializable;
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
		patientRepository.save(new Patient(null, "Jihane","Arfi", "jihane@machlou.com", false, 10, new Date()));
		patientRepository.save(new Patient(null, "Abderrahim","Machlou", "jihane@machlou.com",true, 10, new Date()));
		patientRepository.save(new Patient(null, "Ayoub","Berached", "ayoub@machlou.com",false, 2, new Date()));
		patientRepository.save(new Patient(null, "Aziz","Daaif", "aziz@machlou.com",true, 154, new Date()));
		patientRepository.save(new Patient(null, "Mohamed","Youssfi", "med@machlou.com",false, 10, new Date()));
	}


	@Entity
	@Data @NoArgsConstructor @AllArgsConstructor @ToString
	public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String civilite;
		private String nom;
		private String prenom;
		private String email;
		private String password;
		private String photo;
		private String ville;
		private String adresse;
	}


	@Entity
	@Table(name = "PATIENTS")
	@Data @NoArgsConstructor @AllArgsConstructor
	public class Patient2 implements Serializable {
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@Column(name = "first_name")
		private String firstName;
		@Column(name = "last_name")
		private String lastName;
		private String email;
		private boolean sick;
		private int score;
		@Column(name = "birth_date")
		private Date birthDate;
	}

	@RepositoryRestResource
	interface CustomerRepository extends JpaRepository<User, Long> {}

	@RestController
	class UserController {

		@GetMapping("/api/add")
		public Patient2 addGet(@RequestBody Patient2 user){
			System.out.println(user);
			return user;
		}

		@PostMapping("/api/add")
		public User addPost(@RequestBody User user){
			System.out.println(user);
			return new User();
		}

	}

}
