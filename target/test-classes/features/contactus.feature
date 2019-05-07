Feature: Submit data to airfrance.fr using contact us form

	Scenario: Submit valid data via contact us form
		Given I access air france contact us link
		And i click on join our internet support link
		And i click on select form link and i select the invoice request
		And i click on the invoice request link
		When i select a civility
		When i enter a valid firstname
		When i enter a valid last name
			| drame | martin| dupont |
		When i enter a valid email address
		And i enter a street number
		And i enter a postal code
		And i enter a city
		And i enter payor's name
		And i enter reference of reservation
		And i enter Passenger name
		And i enter Passenger Passenger's first name
		And i enter comments
			|Bonjour, j'aurai besoin d'une facture pour la réservation que je viens d'effectuer | example comment two |
		And i attach a file
		When i click on the submit button
		Then the information should successfully be submitted via the contact us form