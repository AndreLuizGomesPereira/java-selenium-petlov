package br.com.rocketskills.petlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

class Selenium {

	WebDriver driver;

	@BeforeEach
	void start() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterEach
	void finish() {
		driver.close();
	}

	@Test
	@DisplayName("Deve cadsatrar um novo ponto de doação")
	void createPoint() {

		driver.get("https://petlov.vercel.app/signup");

		WebElement title = driver.findElement(By.cssSelector("h1"));
		WebElement name = driver.findElement(By.cssSelector("input[name=name]"));
		WebElement email = driver.findElement(By.cssSelector("input[name=email]"));
		WebElement cep = driver.findElement(By.cssSelector("input[name=cep]"));
		WebElement cepButton = driver.findElement(By.cssSelector("input[value='Buscar CEP']"));
		WebElement addressNumber = driver.findElement(By.cssSelector("input[name=addressNumber]"));
		WebElement details = driver.findElement(By.cssSelector("input[name=addressDetails]"));
		WebElement dogChosen = driver.findElement(By.xpath("//span[text()='Cachorros']"));
		WebElement registerButton = driver.findElement(By.className("button-register"));

		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(d -> title.isDisplayed());

		assertEquals("Cadastro de ponto de doação", title.getText(), "Verificando a página de cadastro");

		name.sendKeys("Andre's House");
		email.sendKeys("andre@andreshouse.com.br");
		cep.sendKeys("45990000");
		cepButton.click();
		addressNumber.sendKeys("1000");
		details.sendKeys("Bloco 5F");
		dogChosen.click();
		registerButton.click();

		WebElement titleSuccess = driver.findElement(By.cssSelector("#success-page h1"));
		assertEquals("Você fez a diferença!", titleSuccess.getText(),
				"Verificando a página de cadastro realizada com sucesso!");

	}
}
