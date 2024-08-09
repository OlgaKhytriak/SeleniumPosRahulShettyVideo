package olhajavacourses.pageobjects;

import olhajavacourses.abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends AbstractComponent {
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement selectCountryInput;

    @FindBy(css = ".action__submit")
    WebElement placeOrderButton;

    By selectCountryDropdownItemBy = By.xpath("(//button[contains(@class,'ta-item')])[2]");

    public void selectCountry(String country) {
        Actions a = new Actions(driver);
        a.sendKeys(selectCountryInput, country).build().perform();
        waitForElementToAppear(By.cssSelector(".ta-results"));
        driver.findElement(selectCountryDropdownItemBy).click();

    }

    public ConfirmationPage clickOnPlaceOrderButton(){
        placeOrderButton.click();
        return new ConfirmationPage(driver);
    }
}
