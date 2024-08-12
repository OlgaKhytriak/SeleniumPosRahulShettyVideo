package olhajavacourses.tests;

import olhajavacourses.pageobjects.*;
import olhajavacourses.testComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandAloneTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException {

        String country = "india";

        ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(input.get("productName"));
        MyCartPage myCartPage = productCatalog.clickonCartbutton();

        Assert.assertTrue(myCartPage.isProductPresent(input.get("productName")));

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

//    @DataProvider
//    public Object[][] getData() {
//        HashMap<Object,Object> map = new HashMap<Object,Object>();
//        map.put("email", "khytriakolga@gmail.com");
//        map.put("password", "gAH!W2gBZsWG2X");
//        map.put("productName", "ZARA COAT 3");
//        return new String[][]{{"khytriakolga@gmail.com", "gAH!W2gBZsWG2X","ZARA COAT 3"}, {"khytriakolga@gmail.com", "gAH!W2gBZsWG2X","ADIDAS ORIGINAL"}};
//    }

//    @DataProvider
//    public Object[][] getData() {
//        HashMap<String,String> map = new HashMap<String,String>();
//
//        map.put("email", "khytriakolga@gmail.com");
//        map.put("password", "gAH!W2gBZsWG2X");
//        map.put("productName", "ZARA COAT 3");
//
//        HashMap<String,String> map1 = new HashMap<String,String>();
//
//        map.put("email", "khytriakolga@gmail.com");
//        map.put("password", "gAH!W2gBZsWG2X");
//        map.put("productName", "ADIDAS ORIGINAL");
//
//        return new Object[][]{{map}, {map1}};
//    }

}
