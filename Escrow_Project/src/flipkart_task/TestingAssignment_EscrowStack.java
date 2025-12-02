package flipkart_task;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestingAssignment_EscrowStack {

	public static void main(String[] args) throws InterruptedException, IOException {

		// To Launch Browser
		WebDriver driver = new ChromeDriver();

		// To Maximize
		driver.manage().window().maximize();

		// To Open Flipkart
		driver.get("https://www.flipkart.com/");

		// Applying Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// To Entering Bluetooth Speakers In Search Bar
		driver.findElement(By.name("q")).sendKeys("Bluetooth Speakers");

		// To Click On Search Icon
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// To Select Brand
		driver.findElement(By.xpath("//div[text()='Brand']")).click();

		// To Select boAt Brand
		driver.findElement(By.xpath("//div[text()='boAt']/preceding-sibling::div")).click();

		Actions action = new Actions(driver);
		action.pause(Duration.ofSeconds(2)).perform();

		// To Select 4★ & above
		WebElement elementOf4star = driver.findElement(By.xpath("//div[text()='4★ & above']/preceding-sibling::div"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", elementOf4star);

		//To click 
		action.pause(Duration.ofSeconds(2)).perform();
		driver.findElement(By.xpath("//div[text()='Price -- Low to High']")).click();

		action.pause(Duration.ofSeconds(2)).perform();
		driver.findElement(By.xpath(
				"(//a[@title='boAt Stone 190 Pro w/ 12 HRS Playback, TWS Feature & IPX6 Splash Resistance 5 W Bluetooth Speaker'])[1]"))
				.click();

		// Switch to new tab
		String parent = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String w : windows) {
			if (!w.equals(parent)) {
				driver.switchTo().window(w);
				break;
			}
		}

		//To click view all offer
		action.pause(Duration.ofSeconds(2)).perform();
		driver.findElement(By.xpath("//span[contains(text(),'View 8 more offers')]")).click();

		// Check for available offers
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			WebElement availability = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Available offers']")));
			List<WebElement> offerList = driver
					.findElements(By.xpath("//div[text()='Available offers']/following::li"));
			System.out.println("Available Offers:");

			int count = 0;

			for (WebElement offer : offerList) {
				if (count == 12) {
					break;
				}
				System.out.println("- " + offer.getText());
				count++;
			}
		} catch (Exception e) {
			System.out.println("No Available Offers section found.");
		}
		
		TakesScreenshot ts= (TakesScreenshot)driver;
		
	    // Ensure screenshots folder exists
        File folder = new File("./screenshots");
        if (!folder.exists()) folder.mkdir();

		// Check Add to Cart
        try {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement addToCartBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[contains(text(),'Add to cart') or contains(text(),'ADD TO CART')]")));
            if (addToCartBtn.isEnabled()) {
                js.executeScript("arguments[0].click();", addToCartBtn);
                System.out.println("Product added to cart.");
                Thread.sleep(2000);

                // Navigate to cart page
                driver.get("https://www.flipkart.com/viewcart?otracker=cart");

                // Take screenshot
                File temp = ts.getScreenshotAs(OutputType.FILE);
                File src = new File("./screenshots/cart_result.png");
                FileHandler.copy(temp, src);
                System.out.println("Screenshot saved as cart_result.png");

            } else {
                System.out.println("Add to Cart button is disabled. Product unavailable.");
                File temp = ts.getScreenshotAs(OutputType.FILE);
                File src = new File("./screenshots/result.png");
                FileHandler.copy(temp, src);
            }
        } catch (Exception e) {
            System.out.println("Add to Cart button not found. Product unavailable.");
            File temp = ts.getScreenshotAs(OutputType.FILE);
            File src = new File("./screenshots/result.png");
            FileHandler.copy(temp, src);
        }

        Thread.sleep(2000);
        driver.quit();
	}

}
