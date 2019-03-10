#Author: xu.hai@mail.mcgill.ca & ziyuan.wang@mail.mcgill.ca
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)                                          
#""
## (Comments)
#Sample Feature Definition Template

Feature: Send an emial with an attachment 

	# Nornal Flow
	Scenario Outline: Verification of sending an email which contains both the subject and an attachment with numbers of credential
		Given I am logged in
		When I select 'Compose'
		And I enter the recipient's "<recipient>" email address
		And I enter the subject "<subject>"
		And I attach a file "<number>" to the email
		And I select 'Send'
		Then there should be a window saying the email has been sent and I should be able to share my files with others
  
  # Data for normal flow 	
	Examples:  
   	                    		
	|recipient										|subject								|number						|		
	|williamin18@gmail.com				|ecse 428 a2 test 1			|1								|	
	|xu.hai@mail.mcgill.ca				|ecse 428 a2 test 2			|2								|
	|ziyuan.wang@mail.mcgill.ca		|ecse 428 a2 test 3			|3								|
	|609437851@qq.com							|ecse 428 a2 test 4			|4								|
	|tieshan.lyu@gmail.com				|ecse 428 a2 test 5			|5								|
	
	# Alternative Flow
	Scenario Outline: Verification of sending an email which contains both the subject and an attachment with numbers of credential
		Given I am logged in
		When I select 'Compose'
		And I enter the recipient's "<recipient>" email address
		And I do not enter the subject
		And I attach a file "<number>" to the email
		And I select 'Send'
		And I confirm to send the email without a subject or text
		Then there should be a window saying the email has been sent and I should be able to share my files with others
  
  # Data for alternative flow 	
	Examples:  
   	                    		
	|recipient										|number						|		
	|williamin18@gmail.com				|1								|	
	|xu.hai@mail.mcgill.ca				|2								|
	|ziyuan.wang@mail.mcgill.ca		|3								|
	|609437851@qq.com							|4								|
	|tieshan.lyu@gmail.com				|5								|
	
	# Error Flow
	Scenario Outline: Verification of sending an email which contains both the subject and an attachment with numbers of credential
		Given I am logged in
		When I select 'Compose'
		And I do not enter the recipient's email address
		And I enter the subject "<subject>"
		And I attach a file "<number>" to the email
		And I select 'Send'
		Then there should be a an error saying I can not send an email without a recipient
  
  # Data for error flow 	
	Examples:  
   	                    		
	|subject								|number						|		
	|ecse 428 a2 test 1			|1								|	
	|ecse 428 a2 test 2			|2								|
	|ecse 428 a2 test 3			|3								|
	|ecse 428 a2 test 4			|4								|
	|ecse 428 a2 test 5			|5								|
	

