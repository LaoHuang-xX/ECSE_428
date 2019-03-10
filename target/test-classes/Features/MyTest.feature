#Author: your.email@your.domain.com
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
Scenario Outline: Verification of sending an email with an attachment with numbers of credential
Given I am logged in

When I select 'Compose'

And I enter the recipient's "<recipient>" email address

And I enter the subject

And I attach a file "<number>" to the email

And I select 'Send'

#Then Reset the credential
   	
Examples:  
   	                    		
|recipient  									|number           |		
|williamin18@gmail.com    	  |1   					    |	
|xu.hai@mail.mcgill.ca				|2								|	
#|User2     |password2        |		
#|User3     |password3        |

