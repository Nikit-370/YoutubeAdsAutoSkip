package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Youtube {

	WebDriver driver;

	public Youtube(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='search']")
	private WebElement searchBox;

	public WebElement getVideoToBePlayed(WebDriver driver, int index) {
		String xpath = "(//a[@class='yt-simple-endpoint style-scope ytd-video-renderer'])[" + index + "]";
		return driver.findElement(By.xpath(xpath));
	}

	@FindBy(xpath = "//button[@class='ytp-ad-skip-button ytp-button']")
	private WebElement skipAdBtn;

	@FindBy(xpath = "//div[@class='ytp-time-display notranslate']")
	private WebElement timeStamp;

	@FindBy(xpath = "//span[@class='ytp-time-duration']")
	private WebElement timeDurartion;

	@FindBy(xpath = "//span[@class='ytp-time-current']")
	private WebElement currentTime;

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSkipAdBtn() {
		return skipAdBtn;
	}

	public WebElement getTimeStamp() {
		return timeStamp;
	}

	public WebElement getTimeDurartion() {
		return timeDurartion;
	}

	public WebElement getCurrentTime() {
		return currentTime;
	}

	public WebElement getVideoToBePlayed() {
		return getVideoToBePlayed();
	}

}
