package com.healthcare.management.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

/**
 * @author srikanthreddy
 *
 */
public class Person {

	@NotBlank
	@Id
	private String id;

	@NotBlank
	private String name;

	@NotBlank
	private String birthDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
