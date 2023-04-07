Feature: This is build Project

  Scenario: Verify login user and password
    Given The Campaign URL is hit

    Scenario: Verify User can go to campaign strategy
      Given The Campaign Strategy URL is hit
      And Get Price of Course "Mobile Automation Testing from Scratch" on Table