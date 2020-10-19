package com.healthcare.management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.management.dao.EnrolleeService;
import com.healthcare.management.dto.Enrollee;
import com.healthcare.management.dto.EnrolleeUpdate;

@RestController
@RequestMapping(path = "/api")
public class EnrolleeController {

	@Autowired
	private EnrolleeService enrolleService;

	@GetMapping(path = "/health", produces = "application/json")
	public String gethealth() {
		return "Application is up and running";

	}

	@PostMapping(path = "/add/enrollee",consumes = "application/json", produces = "application/json")
	public ResponseEntity<Enrollee> addEnrolle(@RequestBody @Valid Enrollee enrollee) {
		return new ResponseEntity<>(enrolleService.addEnrolle(enrollee), HttpStatus.CREATED);

	}

	@GetMapping(path = "/get/enrollee/{id}", produces = "application/json")
	public ResponseEntity<Enrollee> getEnrollebyId(@PathVariable String id) {
		return new ResponseEntity<>(enrolleService.getEnrolleById(id), HttpStatus.OK);

	}

	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public void deleteEnrollebyId(@PathVariable String id) {
		enrolleService.deleteEnrolle(id);
	}

	@PutMapping(value = "/update/enrollee", consumes = "application/json", produces = "application/json")
	public Enrollee updateEnrolle(@RequestBody EnrolleeUpdate enrollee) {
		return enrolleService.updateEnrolle(enrollee);

	}

}
