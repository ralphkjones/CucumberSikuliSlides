package googleSearch;

import googleSearch.GoogleSearch;

import org.apache.log4j.Logger;
import org.sikuli.slides.api.Context;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefs {

	private GoogleSearch googleSearch;
	private static final Logger logger = Logger.getLogger(Stepdefs.class);

	@Before
	public void beforeCallingScenario() {
		this.googleSearch = new GoogleSearch();
	}

	@Given("^I have opened google as my search engine")
	public void I_have_opened_Google_as_my_search_engine() throws Throwable {
		this.googleSearch
				.executePowerPointFile("src\\test\\resources\\googleSearch\\openGoogle.pptx");
	}

	@When("^I enter \"(.*?)\"$")
	public void i_enter(String searchString) throws Throwable {

		Context context = new Context();
		context.addParameter("searchString", searchString);

		this.googleSearch.executePowerPointFileWithContext(
				"src\\test\\resources\\googleSearch\\searchGoogle.pptx", context);
	}

	@Then("^I should see \"(.*?)\" \"(.*?)\"$")
	public void I_should_see(String powerPointFile, String helloBlurb)
			throws Throwable {
		Context context = new Context();
		context.addParameter("blurb", helloBlurb);
		this.googleSearch
				.executePowerPointFileWithContext("src\\test\\resources\\googleSearch\\" + powerPointFile, context);
	}

	@After
	public void afterRunningScenario(Scenario scenario) {
		if (logger.isDebugEnabled()) {
			logger.debug("*********** Just finished running scenario: "
					+ scenario.getStatus());
		}
		System.out.println("*********** Just finished running scenario: "
				+ scenario.getStatus());
	}
}
