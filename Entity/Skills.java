package com.pal.portal.Jobportal.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "skills")
public class Skills {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String expirenceLevel;
	
	private String yearsOfExpirence;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "job_seeker_profile")
	private JobSeekerProfile jobSeekerProfile;

	public Skills() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Skills(int id, String name, String expirenceLevel, String yearsOfExpirence,
			JobSeekerProfile jobSeekerProfile) {
		super();
		this.id = id;
		this.name = name;
		this.expirenceLevel = expirenceLevel;
		this.yearsOfExpirence = yearsOfExpirence;
		this.jobSeekerProfile = jobSeekerProfile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpirenceLevel() {
		return expirenceLevel;
	}

	public void setExpirenceLevel(String expirenceLevel) {
		this.expirenceLevel = expirenceLevel;
	}

	public String getYearsOfExpirence() {
		return yearsOfExpirence;
	}

	public void setYearsOfExpirence(String yearsOfExpirence) {
		this.yearsOfExpirence = yearsOfExpirence;
	}

	public JobSeekerProfile getJobSeekerProfile() {
		return jobSeekerProfile;
	}

	public void setJobSeekerProfile(JobSeekerProfile jobSeekerProfile) {
		this.jobSeekerProfile = jobSeekerProfile;
	}

	@Override
	public String toString() {
		return "Skills [id=" + id + ", name=" + name + ", expirenceLevel=" + expirenceLevel + ", yearsOfExpirence="
				+ yearsOfExpirence + ", jobSeekerProfile=" + jobSeekerProfile + "]";
	}
	
	
}
