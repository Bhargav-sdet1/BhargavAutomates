package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
	
	@Given("^User is on net banking login page$")
	public void User_is_on_net_banking_login_page()
	{
		System.out.println("In Given");
	}
	@When("User login into app with {string} and Pwd {string}")
	public void user_login_into_app_with_and_pwd(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("Jin and John");
	}
	@When("^User login into app with Uname and Pwd$")
	public void User_login_into_app_with_Uname_and_Pwd ()
	{
		System.out.println("In When");
	}
	
	@Then("^Home page is populated$")
	public void Home_page_is_populated ()
	{
		System.out.println("In Then");
	}
	
	@And("^cards are displayed$")
	public void cards_are_displayed()
	{
		System.out.println("In And");
	}

}
