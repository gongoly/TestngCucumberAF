package pagesObjects;

import java.io.IOException;
import java.util.List;

import basePage.BaseTest1;
import driverFactory.CreateDriver;
import driverFactory.Global_VARS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import basePage.BasePage;
import cucumber.api.DataTable;
import launcher.Launch;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ContactUs_Page extends BasePage implements BaseTest1 {

	public By cookiebar_Agree_ContentBy = By.xpath("//button[contains(text(),'Accepter')]");
	public @FindBy(xpath = "//button[contains(text(),'Accepter')]") WebElement cookiebar_Agree_Content;

	public By contact_LinkBy = By.xpath("//a[@id='idContactLink']");
	public @FindBy(xpath = "//a[@id='idContactLink']") WebElement contact_Link;

	public By join_Our_Internet_SupportBy = By.xpath("//a[@id='0_0_3_tbc_2']//span[contains(text(),'Joindre notre')]");
	public @FindBy(xpath = "//a[@id='0_0_3_tbc_2']//span[contains(text(),'Joindre notre')]") WebElement join_Our_Internet_Support;

	public By select_a_formBy = By.xpath("//h2[contains(text(),'Sélectionnez un formulaire')]");
	public @FindBy(xpath = "//h2[contains(text(),'Sélectionnez un formulaire')]") WebElement select_a_form;

	public By select_A_FormBy = By.xpath("//select[@id='simpleList']");
	public @FindBy(xpath = "//select[@id='simpleList']") WebElement select_A_Form;

	public By invoice_RequestBy = By.xpath("//a[contains(text(),'Demande de facture')]");
	public @FindBy(xpath = "//select[@id='simpleList']") WebElement invoice_Request;

	public By civility = By.xpath("//select[@id='idCivility0']");
	public @FindBy(xpath = "//select[@id='idCivility0") WebElement select_a_civility;

	public By textfield_FirstName1 = By.xpath("//input[@id='idFirstName1']");
	public @FindBy(xpath = "//input[@id='idFirstName1']") WebElement textfield_FirstName;

	public By textfield_LastName1 = By.xpath("//input[@id='idLastName2']");
	public @FindBy(xpath = "//input[@id='idLastName2']") WebElement textfield_LastName;

	public By textfield_EmailAddress1 = By.xpath("//input[@id='email_sender']");
	public @FindBy(xpath = "//input[@id='email_sender']") WebElement textfield_EmailAddress;

	public By phone_NumberBy = By.xpath("//input[@id='home3Phone']");
	public @FindBy(xpath = "//input[@id='home3Phone']") WebElement phone_Number;

	public By adresse_PersonnelleBy = By.xpath("//label[contains(text(),'Adresse personnelle')]");
	public @FindBy(xpath = "//label[contains(text(),'Adresse personnelle')]") WebElement adresse_Personnelle;

	public By street_NumberBy = By.xpath("//input[@id='idStreet10']");
	public @FindBy(xpath = "//input[@id='idStreet10']") WebElement street_Number;

	public By postal_CodeBy = By.xpath("//input[@id='idZipCode13']");
	public @FindBy(xpath = "//input[@id='idZipCode13']") WebElement postal_Code;

	public By cityBy = By.xpath("//input[@id='idCity14']");
	public @FindBy(xpath = "//input[@id='idCity14']") WebElement city;

	public By payors_NameBy = By.xpath("//input[@id='response_18']");
	public @FindBy(xpath = "//input[@id='response_18']") WebElement payors_Name;

	public By reference_Of_ReservationBy = By.xpath("//input[@id='response_19']");
	public @FindBy(xpath = "//input[@id='response_19']") WebElement reference_Of_Reservation;

	public By passenger_NameBy = By.xpath("//input[@id='response_20']");
	public @FindBy(xpath = "//input[@id='response_20']") WebElement passenger_Name;

	public By Passengers_First_NameBy = By.xpath("//input[@id='response_21']");
	public @FindBy(xpath = "//input[@id='response_21']") WebElement Passengers_First_Name;

	public By textfield_MessageBy = By.xpath("//textarea[@id='freetext_22']");
	public @FindBy(xpath = "//textarea[@id='freetext_22']") WebElement textfield_Message;

	public By add_A_FileBy = By.xpath("//button[@id='button_22']");
	public @FindBy(xpath = "//button[@id='button_22']") WebElement add_A_File;

	public By button_SubmitBy = By.xpath("//button[@id='id_btn_send']");
	public @FindBy(xpath = "//button[@id='id_btn_send']") WebElement button_Submit;

	protected String pageTitle = "";

	public ContactUs_Page() throws IOException {
		super();
		PageFactory.initElements(CreateDriver.getInstance().getDriver(),this);
	}


	public void getContactUsPage() throws Exception {
		BasePage.loadPage(Global_VARS.TARGET_URL,10);
	}

	/**
	 * setTitle method to set page title
	 *
	 * @param //pageTitle
	 */
	public void setTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	/**
	 * getTitle method to get page title
	 *
	 * @return String
	 */
	public String getTitle() {
		return this.pageTitle;
	}

	public void closePopUpAirFrance() throws Exception {
		if (estPresent(cookiebar_Agree_ContentBy)) {

			clickObj.click(cookiebar_Agree_Content);
		}
	}

	public void goToContactLink() throws Exception {
		clickObj.waitAndClickElement(contact_LinkBy);

	}

	public void join_Our_Internet_Support() throws Exception {
		//we will switch to child window
		TabHandles();
		scrollToElement(join_Our_Internet_SupportBy);
		clickObj.waitAndClickElement(join_Our_Internet_SupportBy);
	}

	public void select_A_Request_From_Form() throws Exception {

		scrollToElement(select_a_formBy);
		clickOnTextFromDropdownList(select_A_FormBy, "Demande de facture");
		// waitToBeClickableWithByOrWebElement(select_A_Form);
	}

	public void click_On_Invoice_Request() throws Exception {
		clickObj.waitAndClickElement(invoice_RequestBy);
	}

	public void civility() throws Exception {
		scrollToElement(civility);
		clickOnTextFromDropdownListWithByOrWebElement(civility, "M.");
		// waitToBeClickableWithByOrWebElement(select_A_Form);

		// waitToBeClickable(civility);
	}

	public void enterFirstName(String firstName) throws Exception {
		sendKeysToWebElement(textfield_FirstName, firstName);
	}

	public void enterLasttName(DataTable dataTable, int row, int column) throws Exception {
		List<List<String>> data = dataTable.raw();
		sendKeysToWebElement(textfield_LastName, data.get(row).get(column));
	}

	public void enterEmailAddress(String emailAddress) throws Exception {
		sendKeysToWebElement(textfield_EmailAddress, emailAddress);
	}

	public void street_Number(String streetNumber) throws Exception {
		sendKeysToWebElement(street_Number, streetNumber);
	}

	public void city(String cityName) throws Exception {
		sendKeysToWebElement(city, cityName);
	}

	public void postal_Code(String postalCode) throws Exception {
		sendKeysToWebElement(postal_Code, postalCode);
	}

	public void payors_Name(String payorsName) throws Exception {
		sendKeysToWebElement(payors_Name, payorsName);
	}

	public void reference_Of_Reservation(String referenceOfReservation) throws Exception {
		sendKeysToWebElement(reference_Of_Reservation, referenceOfReservation);
	}

	public void passenger_Name(String passengerName) throws Exception {
		sendKeysToWebElement(passenger_Name, passengerName);
	}

	public void Passengers_First_Name(String PassengersFirstName) throws Exception {
		sendKeysToWebElement(Passengers_First_Name, PassengersFirstName);
	}

	public void enterComments(DataTable dataTable, int row, int column) throws Exception {
		List<List<String>> data = dataTable.raw();
		sendKeysToWebElement(textfield_Message, data.get(row).get(column));
	}

	public void upload_A_File() throws Exception {

		clickObj.waitAndClickElement(add_A_FileBy);

		robotObj.uploadFile("C:\\Users\\mbemdram\\Documents\\katalon_utilitaire_mbem\\katalon_lien_utils.txt");
	}

	public void clickOnSubmiButton() throws Exception {
		scrollToElement(button_SubmitBy);
	//	waitAndClickElement(button_SubmitBy);
	}

	public void confirmContactUsFormSubmissionWasSuccessful() throws Exception {
		System.out.println("Demande envoyée avec succès");
	}

}
