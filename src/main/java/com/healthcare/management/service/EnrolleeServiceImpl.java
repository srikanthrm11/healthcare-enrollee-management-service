package com.healthcare.management.service;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.management.dao.EnrolleeRepository;
import com.healthcare.management.dto.Enrollee;
import com.healthcare.management.dto.EnrolleeUpdate;
import com.healthcare.management.dto.EnrolleeUpdate.UPDATEDEPENDENTS;
import com.healthcare.management.exception.HealthCareEnrolleeException;
import com.healthcare.management.exception.ResourceNotFoundException;

/**
 * @author srikanthreddy
 *
 */
@Service
public class EnrolleeServiceImpl implements EnrolleeService {

	/**
	 * EnrolleeRepository will use the Mongodb-Repository to perform the database
	 * operations.
	 */
	@Autowired
	private EnrolleeRepository enrolleRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.healthcare.management.dao.EnrolleeService#getEnrolleById(java.lang.
	 * String)
	 */
	@Override
	public Enrollee getEnrolleById(String id) {
		try {
			return enrolleRepository.findById(id).get();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Enrollee not found for provided enrollee Id" + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.healthcare.management.dao.EnrolleeService#addEnrolle(com.healthcare.
	 * management.dto.Enrollee)
	 */
	@Override
	public Enrollee addEnrolle(Enrollee enrolle) {
		if (enrolleRepository.findById(enrolle.getId()).isPresent()) {
			throw new HealthCareEnrolleeException("Enrollee already present with specified Id");

		} else {
			try {
				return enrolleRepository.save(enrolle);
			} catch (Exception e) {

				throw new HealthCareEnrolleeException("Unable to add enrollee" + e.getMessage());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.healthcare.management.dao.EnrolleeService#updateEnrolle(com.healthcare.
	 * management.dto.EnrolleeUpdate)
	 */
	@Override
	public Enrollee updateEnrolle(EnrolleeUpdate enrolleUpdate) {
		if (Objects.nonNull(enrolleUpdate) && StringUtils.isNotEmpty(enrolleUpdate.getId())) {
			Enrollee enrolle1 = null;
			try {
				enrolle1 = enrolleRepository.findById(enrolleUpdate.getId()).get();
			} catch (Exception ex) {
				throw new ResourceNotFoundException("Enrollee not found for provided enrollee Id");
			}
			if (Objects.nonNull(enrolle1)) {
				if (enrolleUpdate.isModifyEmployee()) {
					modifyEmployee(enrolleUpdate, enrolle1);
				}
				if (Objects.nonNull(enrolleUpdate.getUpdateDependents())) {
					updateDependents(enrolleUpdate, enrolle1);
				}
				try {
					enrolleRepository.save(enrolle1);
				} catch (Exception e) {
					throw new HealthCareEnrolleeException("Unable to add dependents Successfully" + e.getMessage());
				}
			}
			return enrolle1;
		} else {
			throw new HealthCareEnrolleeException("Unable to add dependents Successfully");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.healthcare.management.dao.EnrolleeService#deleteEnrolle(java.lang.String)
	 */
	@Override
	public void deleteEnrolle(String id) {
		if (enrolleRepository.findById(id).isPresent()) {
			Enrollee enrolle = enrolleRepository.findById(id).get();
			try {
				enrolleRepository.delete(enrolle);
			} catch (Exception e) {
				throw new HealthCareEnrolleeException("unable to delete enrolle");
			}

		} else {
			throw new ResourceNotFoundException("Enrollee not found for provided enrollee Id");
		}
	}

	/**
	 * This method handles processing either adding or removing dependents
	 * 
	 * @param enrolleUpdate
	 * @param enrollee
	 */
	private void updateDependents(EnrolleeUpdate enrolleUpdate, Enrollee enrollee) {
		if (enrolleUpdate.getUpdateDependents().equals(UPDATEDEPENDENTS.ADD)) {
			enrollee.setDependents(enrolleUpdate.getDependents());
		}
		if (enrolleUpdate.getUpdateDependents().equals(UPDATEDEPENDENTS.REMOVE)) {
			enrollee.setDependents(enrolleUpdate.getDependents());
		}
	}

	/**
	 * This method delegates modifying phoneNumber, Name, BirthDate,ActivationStatus
	 * 
	 * @param enrolleUpdate
	 * @param enrollee
	 */
	private void modifyEmployee(EnrolleeUpdate enrolleUpdate, Enrollee enrollee) {

		updateEnrolleeName(enrolleUpdate, enrollee);
		updateEnrolleeBirthDate(enrolleUpdate, enrollee);
		updateEnrollePhoneNumber(enrolleUpdate, enrollee);
		updateActivationStatus(enrolleUpdate, enrollee);
	}

	/**
	 * This method handles modifying phoneNumber
	 * 
	 * @param enrolleUpdate
	 * @param enrollee
	 */
	private void updateEnrollePhoneNumber(EnrolleeUpdate enrolleUpdate, Enrollee enrollee) {
		if (Objects.nonNull(enrolleUpdate.getPhoneNumber())) {
			enrollee.setPhoneNumber(enrolleUpdate.getPhoneNumber());
		}
	}

	/**
	 * This method handles modifying BirthDate
	 * 
	 * @param enrolleUpdate
	 * @param enrollee
	 */
	private void updateEnrolleeBirthDate(EnrolleeUpdate enrolleUpdate, Enrollee enrollee) {
		if (Objects.nonNull(enrolleUpdate.getBirthDate())) {
			enrollee.setBirthDate(enrolleUpdate.getBirthDate());
		}
	}

	/**
	 * This method handles modifying EnrolleeName
	 * 
	 * @param enrolleUpdate
	 * @param enrollee
	 */
	private void updateEnrolleeName(EnrolleeUpdate enrolleUpdate, Enrollee enrollee) {
		if (StringUtils.isNotEmpty(enrolleUpdate.getName())) {
			enrollee.setName(enrolleUpdate.getName());
		}
	}

	/**
	 * This method handles modifying ActivationStatus
	 * 
	 * @param enrolleUpdate
	 * @param enrollee
	 */
	private void updateActivationStatus(EnrolleeUpdate enrolleUpdate, Enrollee enrollee) {
		enrollee.setActivationStatus(enrolleUpdate.isActivationStatus());
	}

}
