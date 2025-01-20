package com.pal.portal.Jobportal.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pal.portal.Jobportal.Entity.JobPostActivity;
import com.pal.portal.Jobportal.Entity.RecruiterJobsDto;
import com.pal.portal.Jobportal.Entity.RecruiterProfile;
import com.pal.portal.Jobportal.Entity.Users;
import com.pal.portal.Jobportal.Service.JobPostActivityService;
import com.pal.portal.Jobportal.Service.UserService;

@Controller
public class JobPostActivityController {

    private final UserService usersService;
    
    private final JobPostActivityService jobPostActivityService;

    @Autowired
    public JobPostActivityController(UserService usersService,
    		JobPostActivityService jobPostActivityService) {
        this.usersService = usersService;
        this.jobPostActivityService = jobPostActivityService;
    }

    @GetMapping("/dashboard/")
    public String searchJobs(Model model) {

        Object currentUserProfile = usersService.getCurrentUserProfile();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            model.addAttribute("username", currentUsername);
            
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
                List<RecruiterJobsDto> recruiterJobs = jobPostActivityService.getrecruitersJob(((RecruiterProfile) currentUserProfile).getUserAccountId());
                model.addAttribute("jobPost", recruiterJobs);
            }
            
        }

        model.addAttribute("user", currentUserProfile);

        return "dashboard";
    }
    
    @GetMapping("/dashboard/add")
    public String addJobs(Model model) {
    	
    	model.addAttribute("jobPostActivity", new JobPostActivity());
    	model.addAttribute("user", usersService.getCurrentUserProfile());
    	return "add-jobs";
    }
    
    @PostMapping("/dashboard/addNew")
    public String addNew(Model model, JobPostActivity jobPostActivity) {
    	
    	Users users = usersService.getCurrentUser();
    	
    	if(users!=null) {
    		jobPostActivity.setPostedById(users);
    	}
    	jobPostActivity.setPostedDate(new Date());
    	model.addAttribute("jobPostActivity", jobPostActivity);
    	JobPostActivity savedUser = jobPostActivityService.addNew(jobPostActivity);
    	return "redirect:/dashboard/";
    }
    
    @PostMapping("dashboard/edit/{id}")
    public String editJob(@PathVariable("id") int id, Model model) {
    	
    	JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);
    	model.addAttribute("jobPostActivity", jobPostActivity);
        model.addAttribute("user", usersService.getCurrentUserProfile());
    	return "add-jobs";
    }
}
