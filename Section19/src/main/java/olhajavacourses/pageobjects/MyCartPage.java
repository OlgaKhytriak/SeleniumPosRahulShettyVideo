package olhajavacourses.pageobjects;

import olhajavacourses.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyCartPage extends AbstractComponent {
    WebDriver driver;

    public MyCartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Page Factory
    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    //Page Factory
    @FindBy(css = ".totalRow button")
    WebElement checkoutButton;


    public Boolean isProductPresent(String productName) {
        Boolean match = cartProducts.stream().anyMatch(cartProduct ->
                cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public PaymentPage clickOnCheckoutButton(){
        checkoutButton.click();
        return new PaymentPage(driver);
    }
}
