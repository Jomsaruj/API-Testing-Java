Feature: check github API

Scenario: fetch private repo from called demo-pyci from jomsaruj
  Given I am an authenticated user
  Given GitHub account with username markna551
  When I query the repository called demo-pyci
  Then the message is 200

#Scenario: fetch jomsaruj information
#  Given GitHub account with username jomsaruj
#  When I query the user information
#  Then the name is "Saruj Sattayanurak"

#Scenario: fetch jomsaruj information2
#  Given GitHub account with username jomsaruj
#  When I query the user information
#  Then the name is Saruj Sattayanurak
#
#Scenario: fetch jeejee information
#  Given GitHub account with username PitchapaSaelim
#  When I query the user information
#  Then the email is null
#
#Scenario Outline: An authenticated user can get email address of a Github acct.
#This is a test of the Github /users/{username} API endpoint.
#
#  Given I am an authenticated user
#  When I query the user data for <username>
#  Then the email is <email>
#  And the name is "<name>"
#  Examples:
#    | username      | email           | name                 |
#    | fatalaijon     | fatalaijon@gmail.com | Jon Fatalai          |
#    | jomsaruj       | null                 | Saruj Sattayanurak   |
#    | PitchapaSaelim | null                 | Pitchapa Sae-lim     |
#
  Scenario: test
    Given user with these info
        | username      | name                     |
        | fatalaijon    | Jon Fatalai              |
        | jomsaruj      | Saruj Sattayanurak       |
