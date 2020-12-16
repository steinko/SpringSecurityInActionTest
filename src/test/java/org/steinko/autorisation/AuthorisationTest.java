package org.steinko.autorisation;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class AuthorisationTest {
	
	 String url =  "http://localhost:8080/hello";	
	 
	
	
	 @Test
     public void shouldbeUnautorised()  {
    	 
    	 given()
        .when()
           .get(url)
        .then()
          .statusCode(401); 
     
     }
	 
	 @Test
     public void shouldAutorise()  {
		 
		 System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");

		    WebDriver driver = new ChromeDriver();
		    driver.get("http://localhost:8080");
		    WebElement userName = driver.findElement(By.id("username"));
		    userName.sendKeys("Stein");
		    WebElement password = driver.findElement(By.id("password"));
		    password.sendKeys("12345");
		    WebElement button = driver.findElement(By.tagName("button"));
		    button.submit();
		    driver.quit();
     
     }
	 
	 
	 @Test
	 public void sholdDiplayHello() {
		 given()
		   .auth().basic("Stein", "12345")
		 .when()
		   .get("http://localhost:8080/hello")
	     .then()
	        .statusCode(200);
	 }

}

