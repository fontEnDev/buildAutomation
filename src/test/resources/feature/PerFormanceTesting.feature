Feature: Performance testing with jmeter

  @ABC
  Scenario Outline: Performance testing with Jmeter file
    Given Go to Jmeter folder and load properties file
    And Remove file "<resultPathFile>" if it exits
    When Performance testing with jmeter file "<pathFileJmeter>"
    Then Verify system work correctly with file report "<resultPathFile>" and request "<requestName>"
    Examples:
      | pathFileJmeter                            | resultPathFile                                  | requestName         |
      | src\test\resources\datafile\testplan1.jmx | src\test\resources\report\createContactList.csv | Create_Contact_List |