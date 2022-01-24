package myCucumberTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {
	private static WebDriver driver;

    @Given("I have opened the home page")
    public void i_have_opened_the_home_page() {
        String pageTitle = driver.getTitle();
        System.out.println("********************Page Title : " + pageTitle);
    }
    @Given("I have agreed to the cookie policy")
    public void i_have_agreed_to_the_cookie_policy() {
        WebElement cookiesAccept = driver.findElement(By.id("btnAccept"));
        cookiesAccept.click();
    }
    @When("I search for {string}")
    public void i_search_for(String locationText) {
        WebElement searchLogo = driver.findElement(By.className("fa-search"));
        searchLogo.click();
        WebElement searchTestBox = driver.findElement(By.name("searchTerm"));
        searchTestBox.sendKeys(locationText);
        WebElement searchBtn = driver.findElement(By.className("btn-outline-default"));
        searchBtn.click();
    }
    @Then("the search results are displayed for {string}")
    public void the_search_results_are_displayed_for(String locationText1) {
        WebElement searchResText = driver.findElement(By.xpath("//*[@id='SearchResults']/h3/em"));
        assertTrue(searchResText.getText().contains(locationText1));
        driver.quit();
    }

    @When("I open the home page")
    public void i_open_the_home_page() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        String webURL = "https://www.linkgroup.eu/";
        driver.get(webURL);
    }
    @Then("the page is displayed")
    public void the_page_is_displayed() {
        assertEquals(driver.getTitle(), "Home");
    }

    @Given("I have opened the Fund Solutions page")
    public void i_have_opened_the_fund_solutions_page() {
        driver = new ChromeDriver();
        String webURL = "https://www.linkfundsolutions.co.uk/";
        driver.get(webURL);
    }
    @When("I view Funds")
    public void i_view_funds() {
        WebElement fundsOption = driver.findElement(By.id("navItem-funds"));
        Actions action = new Actions(driver);
        action.moveToElement(fundsOption).click().build().perform();
    }
    @Then("I can select the investment managers for {string} investors")
    public void i_can_select_the_investment_managers_for_uk_investors(String jurisdiction) {
        List<WebElement> fundsOptionList = driver.findElements(By.xpath("//*[@id='navItem-funds']/div/div/div[2]/div[1]/ul/li"));
        System.out.println("*******************"+jurisdiction);
        for(WebElement abc : fundsOptionList){
            if(abc.getText().contains(jurisdiction)){
                //assertEquals(abc.getText(), jurisdiction);
                assertTrue(abc.getText().contains(jurisdiction));
                System.out.println("***********************" + abc.getText() + " --> Enabled : " + abc.isEnabled());
            }
        }
        driver.quit();
    }
}
