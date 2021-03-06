@googleSearch
Feature: Google Searches
  
  In order to learn the sikuli slides interface to cucumber JVM, I want to
  use Google to search for different subjects and view different results

  Scenario Outline: Search for a few things at www.google.com
    Given I have opened google as my search engine
    When I enter "<searchString>"
    Then I should see "<powerPointFile>" "<helloBlurb>"

    Examples: 
      | searchString  | powerPointFile    | helloBlurb           |
      | amazon.com    | amazon.pptx       | Hello Amazon!        |
      | sikuli slides | sikuliSlides.pptx | Hello Sikuli Slides! |
      | cucumber jvm  | cucumberJvm.pptx  | Hello Cucumber JVM!  |
