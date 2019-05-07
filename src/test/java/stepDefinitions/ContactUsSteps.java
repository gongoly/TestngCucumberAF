package stepDefinitions;

import com.cucumber.listener.Reporter;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driverFactory.CreateDriver;
import pagesObjects.ContactUs_Page;
import java.io.IOException;

public class ContactUsSteps extends CreateDriver {


private ContactUs_Page contactUsPage = new ContactUs_Page();

	public ContactUsSteps() throws IOException {
	}


	@Given("^I access air france contact us link$")
	public void i_access_air_france_contact_us_link() throws Exception {
		Reporter.addStepLog("Accessing air france");
		contactUsPage.getContactUsPage();
	}

	@Given("^i click on join our internet support link$")
	public void i_click_on_join_our_internet_support_link() throws Throwable {
		contactUsPage.closePopUpAirFrance();
		contactUsPage.goToContactLink();
		contactUsPage.join_Our_Internet_Support();
	}

	@Given("^i click on select form link and i select the invoice request$")
	public void i_click_on_select_form_link_and_i_select_the_invoice_request() throws Throwable {
		contactUsPage.select_A_Request_From_Form();
	}

	@Given("^i click on the invoice request link$")
	public void i_click_on_the_invoice_request_link() throws Throwable {
		contactUsPage.click_On_Invoice_Request();
	}

	@When("^i select a civility$")
	public void i_select_a_civility() throws Throwable {
		contactUsPage.civility();
	}

	@When("^i enter a valid firstname$")
	public void i_enter_a_valid_firstname() throws Throwable {
		contactUsPage.enterFirstName("Mbemba");
	}

	@When("^i enter a valid last name$")
	public void i_enter_a_valid_last_name(DataTable dataTable) throws Throwable {
		contactUsPage.enterLasttName(dataTable, 0, 1);
	}

	@And("^i enter a valid email address$")
	public void i_enter_a_valid_email_address() throws Exception  {
		contactUsPage.enterEmailAddress("mbembadrame@hotmail.fr");
	}
	
	@When("^i enter a street number$")
	public void i_enter_a_street_number() throws Throwable {
		contactUsPage.street_Number("23");
	}
	
	@When("^i enter a postal code$")
	public void i_enter_a_postal_code() throws Throwable {
		contactUsPage.postal_Code("34200");
	}
	
	@When("^i enter a city$")
	public void i_enter_a_city() throws Throwable {
		contactUsPage.city("Sète");
	}
	
	@When("^i enter payor's name$")
	public void i_enter_payor_s_name() throws Throwable {
		contactUsPage.payors_Name("Dramé");
	}
	
	@When("^i enter reference of reservation$")
	public void i_enter_reference_of_reservation() throws Throwable {
		contactUsPage.reference_Of_Reservation("ZE4J8K");
	}
	
	@When("^i enter Passenger name$")
	public void i_enter_Passenger_name() throws Throwable {
		contactUsPage.passenger_Name("Dramé");
	}
	
	@When("^i enter Passenger Passenger's first name$")
	public void i_enter_Passenger_Passenger_s_first_name() throws Throwable {
		contactUsPage.Passengers_First_Name("Mbemba");
	}

	@And("^i enter comments$")
	public void i_enter_comments(DataTable dataTable) throws Exception {
		contactUsPage.enterComments(dataTable, 0, 0);
	}
	
	@When("^i attach a file$")
	public void i_attach_a_file() throws Throwable {
		contactUsPage.upload_A_File();
	}

	@When("^i click on the submit button$")
	public void i_click_on_the_submit_button() throws Exception {
		contactUsPage.clickOnSubmiButton();
	}

	@Then("^the information should successfully be submitted via the contact us form$")
	public void the_information_should_successfully_be_submitted_via_the_contact_us_form() throws Exception  {
	//	Assert.fail();
		contactUsPage.confirmContactUsFormSubmissionWasSuccessful();
	}
}
