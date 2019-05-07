$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("contactus.feature");
formatter.feature({
  "line": 1,
  "name": "Submit data to airfrance.fr using contact us form",
  "description": "",
  "id": "submit-data-to-airfrance.fr-using-contact-us-form",
  "keyword": "Feature"
});
formatter.before({
  "duration": 3232409205,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Submit valid data via contact us form",
  "description": "",
  "id": "submit-data-to-airfrance.fr-using-contact-us-form;submit-valid-data-via-contact-us-form",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "I access air france contact us link",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "i click on join our internet support link",
  "keyword": "And "
});
formatter.step({
  "line": 6,
  "name": "i click on select form link and i select the invoice request",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "i click on the invoice request link",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "i select a civility",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "i enter a valid firstname",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "i enter a valid last name",
  "rows": [
    {
      "cells": [
        "drame",
        "martin",
        "dupont"
      ],
      "line": 11
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "i enter a valid email address",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "i enter a street number",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "i enter a postal code",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "i enter a city",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "i enter payor\u0027s name",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "i enter reference of reservation",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "i enter Passenger name",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "i enter Passenger Passenger\u0027s first name",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "i enter comments",
  "rows": [
    {
      "cells": [
        "Bonjour, j\u0027aurai besoin d\u0027une facture pour la réservation que je viens d\u0027effectuer",
        "example comment two"
      ],
      "line": 21
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 22,
  "name": "i attach a file",
  "keyword": "And "
});
formatter.step({
  "line": 23,
  "name": "i click on the submit button",
  "keyword": "When "
});
formatter.step({
  "line": 24,
  "name": "the information should successfully be submitted via the contact us form",
  "keyword": "Then "
});
formatter.match({
  "location": "ContactUsSteps.i_access_air_france_contact_us_link()"
});
formatter.result({
  "duration": 2759178090,
  "status": "passed"
});
formatter.match({
  "location": "ContactUsSteps.i_click_on_join_our_internet_support_link()"
});
formatter.result({
  "duration": 3123420910,
  "status": "passed"
});
formatter.match({
  "location": "ContactUsSteps.i_click_on_select_form_link_and_i_select_the_invoice_request()"
});
formatter.result({
  "duration": 992830669,
  "status": "passed"
});
formatter.match({
  "location": "ContactUsSteps.i_click_on_the_invoice_request_link()"
});
formatter.result({
  "duration": 2134020994,
  "status": "passed"
});
formatter.match({
  "location": "ContactUsSteps.i_select_a_civility()"
});
formatter.result({
  "duration": 2782987318,
  "status": "passed"
});
formatter.match({
  "location": "ContactUsSteps.i_enter_a_valid_firstname()"
});
formatter.result({
  "duration": 20995306,
  "error_message": "java.lang.AssertionError: WebElement is NOT visible, Exception: null\n\tat org.junit.Assert.fail(Assert.java:88)\n\tat basePage.BasePage.WaitUntilWebElementIsVisible(BasePage.java:758)\n\tat basePage.BasePage.sendKeysToWebElement(BasePage.java:359)\n\tat pagesObjects.ContactUs_Page.enterFirstName(ContactUs_Page.java:156)\n\tat stepDefinitions.ContactUsSteps.i_enter_a_valid_firstname(ContactUsSteps.java:52)\n\tat ✽.When i enter a valid firstname(contactus.feature:9)\n",
  "status": "failed"
});
formatter.match({
  "location": "ContactUsSteps.i_enter_a_valid_last_name(DataTable)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ContactUsSteps.i_enter_a_valid_email_address()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ContactUsSteps.i_enter_a_street_number()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ContactUsSteps.i_enter_a_postal_code()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ContactUsSteps.i_enter_a_city()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ContactUsSteps.i_enter_payor_s_name()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ContactUsSteps.i_enter_reference_of_reservation()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ContactUsSteps.i_enter_Passenger_name()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ContactUsSteps.i_enter_Passenger_Passenger_s_first_name()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ContactUsSteps.i_enter_comments(DataTable)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ContactUsSteps.i_attach_a_file()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ContactUsSteps.i_click_on_the_submit_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ContactUsSteps.the_information_should_successfully_be_submitted_via_the_contact_us_form()"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 32606,
  "status": "passed"
});
});