package com.healthcare.management.dao;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.management.dto.Enrollee;
import com.healthcare.management.dto.EnrolleeUpdate;
import com.healthcare.management.dto.EnrolleeUpdate.UPDATEDEPENDENTS;
import com.healthcare.management.exception.HealthCareEnrolleeException;
import com.healthcare.management.exception.ResourceNotFoundException;

@Service
public class EnrolleeServiceImpl implements EnrolleeService {

	@Autowired
	private EnrolleeRepository enrolleRepository;

	@Override
	public Enrollee getEnrolleById(String id) {
		if (enrolleRepository.findById(id).isPresent()) {
			return enrolleRepository.findById(id).get();
		} else {
			throw new ResourceNotFoundException("Enrollee not found for provided enrollee Id");
		}
	}

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

	private void updateDependents(EnrolleeUpdate enrolleUpdate, Enrollee enrollee) {
		if (enrolleUpdate.getUpdateDependents().equals(UPDATEDEPENDENTS.ADD)) {
			enrollee.setDependents(enrolleUpdate.getDependents());
		}
		if (enrolleUpdate.getUpdateDependents().equals(UPDATEDEPENDENTS.REMOVE)) {
			enrollee.setDependents(enrolleUpdate.getDependents());
		}
	}

	private void modifyEmployee(EnrolleeUpdate enrolleUpdate, Enrollee enrollee) {

		updateEnrolleeName(enrolleUpdate, enrollee);
		updateEnrolleeBirthDate(enrolleUpdate, enrollee);
		updateEnrollePhoneNumber(enrolleUpdate, enrollee);
		updateActivationStatus(enrolleUpdate, enrollee);
	}

	private void updateEnrollePhoneNumber(EnrolleeUpdate enrolleUpdate, Enrollee enrollee) {
		if (Objects.nonNull(enrolleUpdate.getPhoneNumber())) {
			enrollee.setPhoneNumber(enrolleUpdate.getPhoneNumber());
		}
	}

	private void updateEnrolleeBirthDate(EnrolleeUpdate enrolleUpdate, Enrollee enrollee) {
		if (Objects.nonNull(enrolleUpdate.getBirthDate())) {
			enrollee.setBirthDate(enrolleUpdate.getBirthDate());
		}
	}

	private void updateEnrolleeName(EnrolleeUpdate enrolleUpdate, Enrollee enrollee) {
		if (StringUtils.isNotEmpty(enrolleUpdate.getName())) {
			enrollee.setName(enrolleUpdate.getName());
		}
	}

	private void updateActivationStatus(EnrolleeUpdate enrolleUpdate, Enrollee enrollee) {
		if (enrolleUpdate.isActivationStatus()) {
			enrollee.setActivationStatus(true);
		} else {
			enrollee.setActivationStatus(false);
		}
	}

}
