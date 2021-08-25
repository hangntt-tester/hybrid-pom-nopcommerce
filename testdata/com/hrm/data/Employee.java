package com.hrm.data;

public class Employee {
	
	public class Role {
		public static final String ADMIN_USERNAME = "Admin";
		public static final String ADMIN_PASSWORD = "admin123";
	}
	
	public class PersonalDetail {
		public static final String FIRSTNAME = "John";
		public static final String LASTNAME = "Wick";
		public static final String FULLNAME = FIRSTNAME + " " + LASTNAME;
		public static final String USERNAME = "johnwick";
		public static final String PASSWORD = "john154$$$";
		public static final String GENDER = "Male";
		public static final String MARITAL_STATUS = "Single";
		public static final String NATIONALITY = "Vietnamese";
		public static final String STATUS = "Enabled";
		
		public static final String EDIT_FIRSTNAME = "Therry";
		public static final String EDIT_LASTNAME = "Herry";
		public static final String EDIT_FULLNAME = EDIT_FIRSTNAME + " " + EDIT_LASTNAME;
	}
	
	public class ContactDetail {
		public static final String ADDRESS_STREET = "25 Le Van Luong";
		public static final String CITY = "Ha Noi";
		public static final String COUNTRY = "Viet Nam";
		public static final String MOBILE = "0986254213";
		public static final String WORK_EMAIL = "autofc1256@gmail.com";
	}
	
	public class EmergencyContact {
		public static final String NAME = "John Smith";
		public static final String RELATIONSHIP = "Wife";
		public static final String HOMEPHONE = "0915246525";
	}
	
	public class AssignedDependent {
		public static final String NAME = "John Henry";
		public static final String RELATIONSHIP = "Child";
		public static final String DOP = "2015-05-25";
	}
	
	public class Job {
		public static final String JOB_TITLE = "QA Engineer";
		public static final String JOB_STATUS = "Full-Time Contract";
		public static final String JOB_CATEGORY = "Technicians";
		public static final String JOINED_DATE = "2020-04-01";
		public static final String SUB_UNIT = "Engineering";
		public static final String JOB_LOCATION = "HQ - CA, USA";
		public static final String CONTRACT_START_DATE = "2020-04-01";
		public static final String CONTRACT_END_DATE = "2023-03-31";
	}
	
}
