package com.healthcare.management.dto;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

// Mongo DB annotation and entity
/**
 * @author srikanthreddy
 *
 */
@Document(collection = "Enrollees")
public class Enrollee extends Person {

	private boolean activationStatus;

	private String phoneNumber;

	private List<Dependent> dependents;

	@Override
	public String toString() {
		return "Enrollee [activationStatus=" + activationStatus + ", phoneNumber=" + phoneNumber + ", dependents="
				+ dependents + "]";
	}

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

	public List<Dependent> getDependents() {
		return dependents;
	}

	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}

}
