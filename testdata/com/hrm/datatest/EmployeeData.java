package com.hrm.datatest;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class EmployeeData {
	public static EmployeeData getEmployee() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,  false);
			return mapper.readValue(new File(GlobalConstants.PROJETC_PATH + "\\testdata\\com\\hrm\\datatest\\EmployeeTest.json"), EmployeeData.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@JsonProperty("Role")
	Role role;
	
	static class Role{
		@JsonProperty("adminuser")
		private String adminuser;
		
		@JsonProperty("adminpassword")
		private String adminpassword;
	}
	
	public String getAdminuser() {
		return role.adminuser;
	}

	public String getAdminpassword() {
		return role.adminpassword;
	}

	
	@JsonProperty("PersonalDetail")
	PersonalDetail personalDetail;
	
	static class PersonalDetail{
		@JsonProperty("firstname")
		private String firstname;
	
		@JsonProperty("lastname")
		private String lastname;
	
		@JsonProperty("fullname")
		private String fullname;
	
		@JsonProperty("username")
		private String username;
	
		@JsonProperty("password")
		private String password;
		
		@JsonProperty("gender")
		private String gender;
		
		@JsonProperty("maritalstatus")
		private String maritalstatus;
		
		@JsonProperty("nationality")
		private String nationality;
		
		@JsonProperty("status")
		private String status;
		
		@JsonProperty("editfirstname")
		private String editfirstname;
		
		@JsonProperty("editlastname")
		private String editlastname;
		
		@JsonProperty("editfullname")
		private String editfullname;
	}
	
	public String getFirstname() {
		return personalDetail.firstname;
	}

	public String getLastname() {
		return personalDetail.lastname;
	}

	public String getFullname() {
		return personalDetail.fullname;
	}

	public String getUsername() {
		return personalDetail.username;
	}

	public String getPassword() {
		return personalDetail.password;
	}

	public String getGender() {
		return personalDetail.gender;
	}

	public String getMaritalstatus() {
		return personalDetail.maritalstatus;
	}

	public String getNationality() {
		return personalDetail.nationality;
	}

	public String getStatus() {
		return personalDetail.status;
	}

	public String getEditfirstname() {
		return personalDetail.editfirstname;
	}

	public String getEditlastname() {
		return personalDetail.editlastname;
	}

	public String getEditfullname() {
		return personalDetail.editfullname;
	}
	
	@JsonProperty("ContactDetail")
	ContactDetail contactDetail;
	
	static class ContactDetail{
		@JsonProperty("addressstreet")
		private String addressstreet;
		
		@JsonProperty("city")
		private String city;
		
		@JsonProperty("country")
		private String country;
		
		@JsonProperty("mobile")
		private String mobile;
		
		@JsonProperty("workemail")
		private String workemail;
	}
	
	public String getAddressstreet() {
		return contactDetail.addressstreet;
	}

	public String getCity() {
		return contactDetail.city;
	}

	public String getCountry() {
		return contactDetail.country;
	}

	public String getMobile() {
		return contactDetail.mobile;
	}

	public String getWorkemail() {
		return contactDetail.workemail;
	}
	
	@JsonProperty("EmergencyContact")
	EmergencyContact emergencyContact;
	
	static class EmergencyContact{
		@JsonProperty("emgname")
		private String emgname;
		
		@JsonProperty("emgrelationship")
		private String emgrelationship;
		
		@JsonProperty("emghomephone")
		private String emghomephone;
	}
	
	public String getEmgname() {
		return emergencyContact.emgname;
	}

	public String getEmgrelationship() {
		return emergencyContact.emgrelationship;
	}

	public String getEmghomephone() {
		return emergencyContact.emghomephone;
	}
	
	@JsonProperty("AssignedDependent")
	AssignedDependent assignedDependent;
	
	static class AssignedDependent{
		@JsonProperty("dpname")
		private String dpname;
		
		@JsonProperty("dprelationship")
		private String dprelationship;
		
		@JsonProperty("dpdop")
		private String dpdop;
	}
	
	public String getDpname() {
		return assignedDependent.dpname;
	}

	public String getDprelationship() {
		return assignedDependent.dprelationship;
	}

	public String getDpdop() {
		return assignedDependent.dpdop;
	}
	
	@JsonProperty("Job")
	Job job;
	
	static class Job{
		

		@JsonProperty("jobtitle")
		private String jobtitle;
		
		@JsonProperty("jobstatus")
		private String jobstatus;
		
		@JsonProperty("jobcategory")
		private String jobcategory;
		
		@JsonProperty("joineddate")
		private String joineddate;
		
		@JsonProperty("subunit")
		private String subunit;
		
		@JsonProperty("joblocation")
		private String joblocation;
		
		@JsonProperty("contractstartdate")
		private String contractstartdate;
		
		@JsonProperty("contractenddate")
		private String contractenddate;
	}
	
	public String getJobtitle() {
		return job.jobtitle;
	}

	public String getJobstatus() {
		return job.jobstatus;
	}

	public String getJobcategory() {
		return job.jobcategory;
	}

	public String getJoineddate() {
		return job.joineddate;
	}

	public String getSubunit() {
		return job.subunit;
	}

	public String getJoblocation() {
		return job.joblocation;
	}

	public String getContractstartdate() {
		return job.contractstartdate;
	}

	public String getContractenddate() {
		return job.contractenddate;
	}
}
