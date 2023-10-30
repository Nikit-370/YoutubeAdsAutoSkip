package YTAutomate;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import POM.Youtube;
import genericUtils.Utils;

public class YoutubeVideoSearchAndPlayTest {

	@Test
	public void playVideo() throws InterruptedException, IOException {

		// Create a Utils object for using user defined methods
		Utils ut = new Utils();

		// Launching Browser and implementing implicit wait
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Navigate to the URL
		driver.get(ut.ToFetchDataFromPropertyFile("url"));

		// Wait for the title to be "YouTube"
		WebDriverWait waitTitle = new WebDriverWait(driver, Duration.ofSeconds(20));
		waitTitle.until(ExpectedConditions.titleIs(ut.ToFetchDataFromPropertyFile("title")));
		Assert.assertTrue(driver.getTitle().contains(ut.ToFetchDataFromPropertyFile("title")));

		// Create a YouTube object for interacting with the YouTube page
		Youtube yt = new Youtube(driver);

		// Find and interact with the search box
		WebElement box = yt.getSearchBox();
		WebDriverWait waitSearch = new WebDriverWait(driver, Duration.ofSeconds(20));
		waitSearch.until(ExpectedConditions.elementToBeClickable(box));
		if (box.isDisplayed()) {
			box.sendKeys(ut.ToFetchDataFromPropertyFile("search"));
			box.sendKeys(Keys.ENTER);
		}

		// Find and click on the video to be played
		WebElement video = yt.getVideoToBePlayed(driver, Integer.parseInt(ut.ToFetchDataFromPropertyFile("videoIndex")));
		WebDriverWait waitVideo = new WebDriverWait(driver, Duration.ofSeconds(20));
		waitVideo.until(ExpectedConditions.elementToBeClickable(video));
		if (video.isDisplayed()) {
			video.click();
		}

		// Loop to continuously check for the "Skip Ad" button with a timeout
		WebDriverWait waitSkipAd = new WebDriverWait(driver, Duration.ofSeconds(20));
		boolean adSkipped = false;
		while (!adSkipped) {
			try {
				WebElement skipAd = yt.getSkipAdBtn();
				waitSkipAd.until(ExpectedConditions.elementToBeClickable(skipAd));
				if (skipAd.isDisplayed()) {
					skipAd.click();
					System.out.println(ut.ToFetchDataFromPropertyFile("addSkipped"));
					adSkipped = true; // Set the flag to exit the loop
				}
			} catch (Exception e) {
				System.out.println(ut.ToFetchDataFromPropertyFile("noAddMessage"));
				break; // Exit the loop if no "Skip Ad" button is found
			}
		}

		// Find the time display element
		WebElement timeStamp = yt.getTimeStamp();
		WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(20));
		waitTime.until(ExpectedConditions.elementToBeClickable(timeStamp));

		if (timeStamp.isDisplayed()) {
			// Find and display the expected video duration
			WebElement expectedTime = yt.getTimeDurartion();
			if (expectedTime.isDisplayed())
				System.out.println("Expected Duration: " + expectedTime.getText());
		}

		// Calculate the expected video duration
		String expectedDuration = "";
		WebElement expectedTime = yt.getTimeDurartion();
		if (expectedTime.isDisplayed()) {
			expectedDuration = expectedTime.getText();
		}

		// Wait for the video to finish
		while (true) {
			WebElement currentTime = yt.getCurrentTime();
			if (currentTime.isDisplayed() && currentTime.getText().equals(expectedDuration)) {
				// Video has reached the end
				System.out.println(ut.ToFetchDataFromPropertyFile("videoEndMessage"));
				driver.close(); // Close the browser
				break;
			}
		}

	}

}
