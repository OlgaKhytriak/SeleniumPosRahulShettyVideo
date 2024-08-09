package olhajavacourses.tests;

import olhajavacourses.pageobjects.*;
import olhajavacourses.testComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class StandAloneTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test
    public void submitOrder() throws IOException {

        String country = "india";

        ProductCatalog productCatalog = landingPage.loginApplication("khytriakolga@gmail.com", "gAH!W2gBZsWG2X");

        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
        MyCartPage myCartPage = productCatalog.clickonCartbutton();

        Assert.assertTrue(myCartPage.isProductPresent(productName));

        PaymentPage paymentPage = myCartPage.clickOnCheckoutButton();
        paymentPage.selectCountry(country);
        ConfirmationPage confirmationPage = paymentPage.clickOnPlaceOrderButton();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderhistoryTest() {
        ProductCatalog productCatalog = landingPage.loginApplication("khytriakolga@gmail.com", "gAH!W2gBZsWG2X");
        OrderPage orderPage = productCatalog.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }
}
