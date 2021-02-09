package dev.rens.steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.rens.runners.Project1Runner;

public class Project1LoginStepImpl {

	public static WebDriver driver = Project1Runner.driver;
	
	@Given("^The user is on the login page$")
	public void the_user_is_on_the_login_page() {
	    driver.get("http://localhost:8080/Project1/");
	}

	@When("^The user types in correct email and password and clicks login$")
	public void the_user_types_in_correct_email_and_password_and_clicks_login() {
	    WebElement email = driver.findElement(By.id("email"));
	    WebElement password = driver.findElement(By.id("password"));
	    WebElement loginButton = driver.findElement(By.id("login_button"));

	    email.sendKeys("jdoe@test.net");
	    password.sendKeys("password");
	    loginButton.click();
	}
	
	@Then("^The user should be on the employee page$")
	public void the_user_should_be_on_the_employee_page()  {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals("Employee Page", driver.getTitle());
	}
	
	@When("^The user types in the incorrect email and password and cliks login$")
	public void the_user_types_in_the_incorrect_email_and_password_and_cliks_login() {
		WebElement email = driver.findElement(By.id("email"));
	    WebElement password = driver.findElement(By.id("password"));
	    WebElement loginButton = driver.findElement(By.id("login_button"));

	    email.sendKeys("jdoe@tet.net");
	    password.sendKeys("passwor");
	    loginButton.click();
	}

	@Then("^The user should be on the same page and see a message$")
	public void the_user_should_be_on_the_same_page_and_see_a_message() {
		Assert.assertEquals("TRMS Login", driver.getTitle());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement errorMessage = driver.findElement(By.id("alert"));
		Assert.assertEquals("Email or password not found please try again", errorMessage.getText());
	}
}
