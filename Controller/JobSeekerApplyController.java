package com.pal.portal.Jobportal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pal.portal.Jobportal.Entity.JobPostActivity;
import com.pal.portal.Jobportal.Service.JobPostActivityService;
import com.pal.portal.Jobportal.Service.UserService;

@Controller
public class JobSeekerApplyController {

	private final JobPostActivityService jobPostActivityService;
	private final UserService userService;
	
	@Autowired
	public JobSeekerApplyController(JobPostActivityService jobPostActivityService, UserService userService) {
		super();
		this.jobPostActivityService = jobPostActivityService;
		this.userService = userService;
	}
	
	@GetMapping("job-details-apply/{id}")
	public String display(@PathVariable("id") int id, Model model) {
		JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);
		
		model.addAttribute("jobDetails", jobPostActivity);
		model.addAttribute("user", userService.getCurrentUserProfile());
		
		return "job-details";
	}
	
	
}
