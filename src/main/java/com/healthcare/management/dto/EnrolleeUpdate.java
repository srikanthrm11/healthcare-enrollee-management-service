package com.healthcare.management.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

public class EnrolleeUpdate {

	@NotBlank
	@Id
	private String id;

	private boolean modifyEmployee;

	private UPDATEDEPENDENTS updateDependents;

	public UPDATEDEPENDENTS getUpdateDependents() {
		return updateDependents;
	}

	public void setUpdateDependents(UPDATEDEPENDENTS updateDependents) {
		this.updateDependents = updateDependents;
	}

	private String name;
	private boolean activationStatus;
	private String phoneNumber;

	public boolean isActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public enum UPDATEDEPENDENTS {
		ADD, REMOVE
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isModifyEmployee() {
		return modifyEmployee;
	}

	public void setModifyEmployee(boolean modifyEmployee) {
		this.modifyEmployee = modifyEmployee;
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

	public List<Dependent> getDependents() {
		return dependents;
	}

	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}

	private String birthDate;

	private List<Dependent> dependents;

}
