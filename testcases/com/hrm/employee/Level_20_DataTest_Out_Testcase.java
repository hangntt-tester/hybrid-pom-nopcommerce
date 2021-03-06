package com.hrm.employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hrm.data.Employee;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObject.employee.hrm.AddEmployeePO;
import pageObject.employee.hrm.DashboardPO;
import pageObject.employee.hrm.EmployeeListPO;
import pageObject.employee.hrm.EmployeeSearchPO;
import pageObject.employee.hrm.LoginPO;
import pageObject.employee.hrm.MyInfoPO;
import pageObject.employee.hrm.PageGenerator;

public class Level_20_DataTest_Out_Testcase extends BaseTest {
	String employeeID;
	String avatarFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "Avatar.jpg";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);

		log.info("Pre-condition - Step 02: Login with Account Admin");
		dashboardPage = loginPage.loginToSystem(driver, Employee.Role.ADMIN_USERNAME, Employee.Role.ADMIN_PASSWORD);
	}

	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Add_New_01 - Step 01: Open 'Employee List' Page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Add_New_01 - Step 02: Click to 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);

		log.info("Add_New_01 - Step 03: Enter valid info to First Name textbox");
		addEmployeePage.enterToTextboxByID(driver, "firstName", Employee.PersonalDetail.FIRSTNAME);

		log.info("Add_New_01 - Step 04: Enter valid info to Last Name textbox");
		addEmployeePage.enterToTextboxByID(driver, "lastName", Employee.PersonalDetail.LASTNAME);

		log.info("Add_New_01 - Step 05: Get value of Employee ID");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");

		log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

		log.info("Add_New_01 - Step 07: Enter valid info to User Name textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_name", Employee.PersonalDetail.USERNAME);

		log.info("Add_New_01 - Step 08: Enter valid info to Password textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_password", Employee.PersonalDetail.PASSWORD);

		log.info("Add_New_01 - Step 09: Enter valid info to Confirm Password textbox");
		addEmployeePage.enterToTextboxByID(driver, "re_password", Employee.PersonalDetail.PASSWORD);

		log.info("Add_New_01 - Step 10: Select '" + Employee.PersonalDetail.STATUS + "' value in 'Status' dropdown");
		addEmployeePage.selectToDropdownByID(driver, "status", Employee.PersonalDetail.STATUS);

		log.info("Add_New_01 - Step 11: Click to 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage = PageGenerator.getMyInforPage(driver);

		log.info("Add_New_01 - Step 12: Open 'Employee List' Page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Add_New_01 - Step 13: Enter valid info to Employee Name textbox");
		verifyTrue(employeeListPage.isJQueryAJAXCallsHaveCompleted(driver));
		employeeListPage.enterToTextboxByID(driver, "empsearch_employee_name_empName", Employee.PersonalDetail.FULLNAME);
		verifyTrue(employeeListPage.isJQueryAJAXCallsHaveCompleted(driver));

		log.info("Add_New_01 - Step 14: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAJAXCallsHaveCompleted(driver));

		log.info("Add_New_01 - Step 15: Verify Employee Info displayed with ID, FristName, LastName at 'Result Table' ");
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), Employee.PersonalDetail.FIRSTNAME);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), Employee.PersonalDetail.LASTNAME);
	}

	@Test
	public void Employee_02_Upload_Avatar() {
		log.info("UPload_Avatar_02 - Step 01: Logout to System");
		loginPage = employeeListPage.logoutToSystem(driver);

		log.info("UPload_Avatar_02 - Step 02: Login to System with account Employee");
		dashboardPage = loginPage.loginToSystem(driver, Employee.PersonalDetail.USERNAME, Employee.PersonalDetail.PASSWORD);
		log.info("UPload_Avatar_02 - Step 03: Open Personal Details page");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = PageGenerator.getMyInforPage(driver);

		log.info("UPload_Avatar_02 - Step 04: Click to change photo image");
		myInfoPage.clickToChangePhotoImage();

		log.info("UPload_Avatar_02 - Step 05: Upload new Avatar image");
		myInfoPage.uploadImage(driver, avatarFilePath);

		log.info("UPload_Avatar_02 - Step 06: Click to Upload button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("UPload_Avatar_02 - Step 07: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));

		log.info("UPload_Avatar_02 - Step 08: Verify new Avatar image is displayed");
		verifyTrue(myInfoPage.isNewAvatarImageDisplayed());
	}

	@Test
	public void Employee_03_Personal_Details() {
		log.info("Personal_Details_03 - Step 01: Open 'PersonalDetails' at Side bar");
		myInfoPage.openTabAtSideBarByName("Personal Details");

		log.info("Personal_Details_03 - Step 02: Verify all fields at 'Personal Details' tab are disable");
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtEmpFirstName"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtEmpLastName"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtEmployeeId"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtLicenNo"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtOtherID"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtLicExpDate"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtNICNo"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtSINNo"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_optGender_1"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_optGender_2"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_cmbMarital"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_cmbNation"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_DOB"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_chkSmokeFlag"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtEmpNickName"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtMilitarySer"));

		log.info("Personal_Details_03 - Step 03: Click to Edit button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Personal_Details_03 - Step 04: Verify 'Employee Id' textbox is disable");
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtEmployeeId"));

		log.info("Personal_Details_03 - Step 05: Verify 'Driver's License Number' textbox is disable");
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtLicenNo"));

		log.info("Personal_Details_03 - Step 06: Verify 'SSN Number' textbox is disable");
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtNICNo"));

		log.info("Personal_Details_03 - Step 07: Verify 'SIN Number' textbox is disable");
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_txtSINNo"));

		log.info("Personal_Details_03 - Step 08: Verify 'Date of Birth' textbox is disable");
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "personal_DOB"));

		log.info("Personal_Details_03 - Step 09: Enter new value to 'First Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "personal_txtEmpFirstName", Employee.PersonalDetail.EDIT_FIRSTNAME);

		log.info("Personal_Details_03 - Step 10: Enter new value to 'Last Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "personal_txtEmpLastName", Employee.PersonalDetail.EDIT_LASTNAME);

		log.info("Personal_Details_03 - Step 11: Select new value to 'Gender' radio button");
		myInfoPage.clickToRadioButtonByLabel(driver, Employee.PersonalDetail.GENDER);

		log.info("Personal_Details_03 - Step 12: Select new value to 'Marital Status' dropdown");
		myInfoPage.selectToDropdownByID(driver, "personal_cmbMarital", Employee.PersonalDetail.MARITAL_STATUS);

		log.info("Personal_Details_03 - Step 13: Select new value to 'Nationality' dropdown");
		myInfoPage.selectToDropdownByID(driver, "personal_cmbNation", Employee.PersonalDetail.NATIONALITY);

		log.info("Personal_Details_03 - Step 14: Click to Save button at 'Personal Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Personal_Details_03 - Step 15: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Personal_Details_03 - Step 16: Verify 'First Name' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpFirstName"), Employee.PersonalDetail.EDIT_FIRSTNAME);

		log.info("Personal_Details_03 - Step 17: Verify 'Last Name' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpLastName"), Employee.PersonalDetail.EDIT_LASTNAME);

		log.info("Personal_Details_03 - Step 18: Verify 'Gender' radio button is updated success");
		verifyTrue(myInfoPage.isRadioButtonSelectedByLabel(driver, Employee.PersonalDetail.GENDER));

		log.info("Personal_Details_03 - Step 19: Verify 'Marital Status' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbMarital"), Employee.PersonalDetail.MARITAL_STATUS);

		log.info("Personal_Details_03 - Step 20: Verify 'Nationality' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbNation"), Employee.PersonalDetail.NATIONALITY);

		log.info("Personal_Details_03 - Step 21: Verify Employee ID textbox is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmployeeId"), employeeID);
	}

	@Test
	public void Employee_04_Contact_Details() {
		log.info("Contact_Details_04 - Step 01: Open 'Contact Details' at Side bar");
		myInfoPage.openTabAtSideBarByName("Contact Details");

		log.info("Contact_Details_04 - Step 02: Verify all fields at 'Contact Details' tab are disable");
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "contact_street1"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "contact_street2"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "contact_city"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "contact_province"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "contact_emp_zipcode"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "contact_country"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "contact_emp_hm_telephone"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "contact_emp_mobile"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "contact_emp_work_telephone"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "contact_emp_work_email"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "contact_emp_oth_email"));

		log.info("Contact_Details_04 - Step 03: Click 'Edit' button at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Contact_Details_04 - Step 04: Enter value to 'Address Street 1' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_street1", Employee.ContactDetail.ADDRESS_STREET);

		log.info("Contact_Details_04 - Step 05: Enter value to 'City' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_city", Employee.ContactDetail.CITY);

		log.info("Contact_Details_04 - Step 06: Select value to 'Country' dropdown");
		myInfoPage.selectToDropdownByID(driver, "contact_country", Employee.ContactDetail.COUNTRY);

		log.info("Contact_Details_04 - Step 07: Enter value to 'Mobile' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_mobile", Employee.ContactDetail.MOBILE);

		log.info("Contact_Details_04 - Step 08: Enter value to 'Work Email' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_work_email", Employee.ContactDetail.WORK_EMAIL);

		log.info("Contact_Details_04 - Step 09: Click 'Save' button at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Contact_Details_04 - Step 10: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Contact_Details_04 - Step 11: Verify 'Address Street 1' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street1"), Employee.ContactDetail.ADDRESS_STREET);

		log.info("Contact_Details_04 - Step 12: Verify 'City' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_city"), Employee.ContactDetail.CITY);

		log.info("Contact_Details_04 - Step 13: Verify 'Country' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "contact_country"), Employee.ContactDetail.COUNTRY);

		log.info("Contact_Details_04 - Step 14: Verify 'Mobile' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_mobile"), Employee.ContactDetail.MOBILE);

		log.info("Contact_Details_04 - Step 15: Verify 'Work Email' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_email"), Employee.ContactDetail.WORK_EMAIL);
	}

	@Test
	public void Employee_05_Emergency_Contacts() {
		log.info("Emergency_Contacts_05 - Step 01: Open 'Emergency Contacts' at Side bar");
		myInfoPage.openTabAtSideBarByName("Emergency Contacts");

		log.info("Emergency_Contacts_05 - Step 02: Click 'Add' button at 'Assigned Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnAddContact");

		log.info("Emergency_Contacts_05 - Step 03: Click 'Save' button at 'Add Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");

		log.info("Emergency_Contacts_05 - Step 04: Verify message 'Required' is displayed with empty Name textbox");
		verifyTrue(myInfoPage.isMessageRequiredDisplayed("emgcontacts_name"));

		log.info("Emergency_Contacts_05 - Step 05: Verify message 'Required' is displayed with empty Relationship textbox");
		verifyTrue(myInfoPage.isMessageRequiredDisplayed("emgcontacts_relationship"));

		log.info("Emergency_Contacts_05 - Step 06: Verify message 'At least one phone number is required' is displayed with empty phone textbox");
		verifyTrue(myInfoPage.isMessagePhoneDisplayed("emgcontacts_homePhone"));

		log.info("Emergency_Contacts_05 - Step 07: Enter value to Name textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_name", Employee.EmergencyContact.NAME);

		log.info("Emergency_Contacts_05 - Step 08: Enter value to Relationship textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_relationship", Employee.EmergencyContact.RELATIONSHIP);

		log.info("Emergency_Contacts_05 - Step 09: Enter value to Home Telephone textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_homePhone", Employee.EmergencyContact.HOMEPHONE);

		log.info("Emergency_Contacts_05 - Step 10: Click 'Save' button at 'Add Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");

		log.info("Emergency_Contacts_05 - Step 11: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Emergency_Contacts_05 - Step 12: Verify Emergency Contacts displayed with Name, Relationship, LastHome Telephone at 'Emgcontact List' table ");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "1"), Employee.EmergencyContact.NAME);
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Relationship", "1"), Employee.EmergencyContact.RELATIONSHIP);
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Home Telephone", "1"), Employee.EmergencyContact.HOMEPHONE);
	}

	@Test
	public void Employee_06_Assigned_Dependents() {
		log.info("Assigned_Dependents_06 - Step 01: Open 'Dependents' at Side bar");
		myInfoPage.openTabAtSideBarByName("Dependents");

		log.info("Assigned_Dependents_06 - Step 02: Click 'Add' button at 'Assigned_Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnAddDependent");

		log.info("Assigned_Dependents_06 - Step 03: Click 'Save' button at 'Assigned_Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveDependent");

		log.info("Assigned_Dependents_06 - Step 04: Verify message 'Required' is displayed with empty Name textbox");
		verifyTrue(myInfoPage.isMessageRequiredDisplayed("dependent_name"));

		log.info(
				"Assigned_Dependents_06 - Step 05: Verify message 'Required' is displayed with empty Relationship dropdown");
		verifyTrue(myInfoPage.isMessageRequiredDisplayed("dependent_relationshipType"));

		log.info("Assigned_Dependents_06 - Step 06: Enter value to Name textbox");
		myInfoPage.enterToTextboxByID(driver, "dependent_name", Employee.AssignedDependent.NAME);

		log.info("Assigned_Dependents_06 - Step 07: Select value to Relationship dropdown");
		myInfoPage.selectToDropdownByID(driver, "dependent_relationshipType", Employee.AssignedDependent.RELATIONSHIP);

		log.info("Assigned_Dependents_06 - Step 08: Enter value to Date of Birth textbox");
		myInfoPage.enterToDatePickerByID(driver, "dependent_dateOfBirth", Employee.AssignedDependent.DOP);

		log.info("Assigned_Dependents_06 - Step 09: Click 'Save' button at 'Assigned_Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveDependent");

		log.info("Assigned_Dependents_06 - Step 10: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Assigned_Dependents_06 - Step 11: Verify Assigned Dependents Infor is displayed with Name, Relationship, DOB at 'Dependents List' table ");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "1"), Employee.AssignedDependent.NAME);
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "1"), Employee.AssignedDependent.RELATIONSHIP.toLowerCase());
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Date of Birth", "1"), Employee.AssignedDependent.DOP);
	}

	@Test
	public void Employee_07_Edit_View_Job() {
		log.info("Edit_View_Job_07 - Step 01: Logout to System");
		loginPage = myInfoPage.logoutToSystem(driver);

		log.info("Edit_View_Job_07 - Step 02: Login to System with account Admin");
		dashboardPage = loginPage.loginToSystem(driver, Employee.Role.ADMIN_USERNAME, Employee.Role.ADMIN_PASSWORD);

		log.info("Edit_View_Job_07 - Step 03: Open 'Employee List' Page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Edit_View_Job_07 - Step 04: Enter valid info to Employee Name textbox");
		verifyTrue(employeeListPage.isJQueryAJAXCallsHaveCompleted(driver));
		employeeListPage.enterToTextboxByID(driver, "empsearch_employee_name_empName", Employee.PersonalDetail.EDIT_FULLNAME);
		verifyTrue(employeeListPage.isJQueryAJAXCallsHaveCompleted(driver));

		log.info("Edit_View_Job_07 - Step 05: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAJAXCallsHaveCompleted(driver));

		log.info("Edit_View_Job_07 - Step 06: Click to ID employee at 'Result Table'");
		employeeListPage.ClickToEmployeeID();

		log.info("Edit_View_Job_07 - Step 07: Open 'Job' at Side bar");
		myInfoPage.openTabAtSideBarByName("Job");

		log.info("Edit_View_Job_07 - Step 08: Verify all fields at 'Job' form are disable");
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "job_job_title"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "job_emp_status"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "job_eeo_category"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "job_joined_date"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "job_sub_unit"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "job_location"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "job_contract_start_date"));
		verifyFalse(myInfoPage.isFieldEnableByName(driver, "job_contract_end_date"));

		log.info("Edit_View_Job_07 - Step 09: Click to 'Edit' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Edit_View_Job_07 - Step 10: Select value to 'Job Title' dropdown");
		myInfoPage.selectToDropdownByID(driver, "job_job_title", Employee.Job.JOB_TITLE);

		log.info("Edit_View_Job_07 - Step 11: Select value to 'Employment Status' dropdown");
		myInfoPage.selectToDropdownByID(driver, "job_emp_status", Employee.Job.JOB_STATUS);

		log.info("Edit_View_Job_07 - Step 12: Select value to 'Job Category' dropdown");
		myInfoPage.selectToDropdownByID(driver, "job_eeo_category", Employee.Job.JOB_CATEGORY);

		log.info("Edit_View_Job_07 - Step 13: Enter value to 'Joined Date' textbox");
		myInfoPage.enterToDatePickerByID(driver, "job_joined_date", Employee.Job.JOINED_DATE);

		log.info("Edit_View_Job_07 - Step 14: Select value to 'Sub Unit' dropdown");
		myInfoPage.selectToDropdownByID(driver, "job_sub_unit", Employee.Job.SUB_UNIT);

		log.info("Edit_View_Job_07 - Step 15: Select value to 'Location' dropdown");
		myInfoPage.selectToDropdownByID(driver, "job_location", Employee.Job.JOB_LOCATION);

		log.info("Edit_View_Job_07 - Step 16: Enter value to 'Start Date' textbox");
		myInfoPage.enterToDatePickerByID(driver, "job_contract_start_date", Employee.Job.CONTRACT_START_DATE);

		log.info("Edit_View_Job_07 - Step 17: Enter value to 'End Date' textbox");
		myInfoPage.enterToDatePickerByID(driver, "job_contract_end_date", Employee.Job.CONTRACT_END_DATE);

		log.info("Edit_View_Job_07 - Step 18: Click to 'Save' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Edit_View_Job_07 - Step 19: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Updated"));

		log.info("Edit_View_Job_07 - Step 20: Verify 'Job Title' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "job_job_title"), Employee.Job.JOB_TITLE);

		log.info("Edit_View_Job_07 - Step 20: Verify 'Employment Status' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "job_emp_status"), Employee.Job.JOB_STATUS);

		log.info("Edit_View_Job_07 - Step 21: Verify 'Job Category' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "job_eeo_category"), Employee.Job.JOB_CATEGORY);

		log.info("Edit_View_Job_07 - Step 22: Verify 'Joined Date' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_joined_date"), Employee.Job.JOINED_DATE);

		log.info("Edit_View_Job_07 - Step 23: Verify 'Sub Unit' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "job_sub_unit"), Employee.Job.SUB_UNIT);

		log.info("Edit_View_Job_07 - Step 24: Verify 'Location' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "job_location"), Employee.Job.JOB_LOCATION);

		log.info("Edit_View_Job_07 - Step 25: Verify 'Start Date' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_contract_start_date"), Employee.Job.CONTRACT_START_DATE);

		log.info("Edit_View_Job_07 - Step 26: Verify 'End Date' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_contract_end_date"), Employee.Job.CONTRACT_END_DATE);
	}

	@Test
	public void Employee_08_Edit_View_Salary() {
		log.info("Edit_View_Salary_08 - Step 01: Open 'Salary' at Side bar");
		myInfoPage.openTabAtSideBarByName("Salary");

		log.info("Edit_View_Salary_08 - Step 02: Click 'Add' button at Assigned Salary Components form");
		myInfoPage.clickToButtonByID(driver, "addSalary");
	}

	@Test
	public void Employee_09_Edit_View_Tax() {

	}

	@Test
	public void Employee_10_Qualifications() {

	}

	@Test
	public void Employee_11_Search_Employee() {

	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanClass(String browserName) {
		log.info("Post-Condition: Close browser'" + browserName + "'");
		cleanDriverInstance();
	}

	WebDriver driver;
	LoginPO loginPage;
	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage;
	EmployeeListPO employeeListPage;
	EmployeeSearchPO employeeSearchPage;
	MyInfoPO myInfoPage;
}
