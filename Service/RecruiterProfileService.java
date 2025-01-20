package com.pal.portal.Jobportal.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pal.portal.Jobportal.Entity.RecruiterProfile;
import com.pal.portal.Jobportal.Repository.RecruiterProfileRepository;

@Service
public class RecruiterProfileService {

	private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository) {
        this.recruiterProfileRepository = recruiterProfileRepository;
    }

    public Optional<RecruiterProfile> getOne(Integer id) {
        return recruiterProfileRepository.findById(id);
    }

	public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
		// 
		return recruiterProfileRepository.save(recruiterProfile);
	}
}
