package dev.rens.runners;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "dev.rens.steps")
public class Project1Runner {

	public static WebDriver driver;
	
	@BeforeClass
	public static void setUp() {
		File file = new File("src/test/resources/geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
		
		driver = new FirefoxDriver();
	}
	
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
}
