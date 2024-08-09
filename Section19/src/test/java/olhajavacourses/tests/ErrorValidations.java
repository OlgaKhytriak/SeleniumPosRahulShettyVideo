package olhajavacourses.tests;

import olhajavacourses.pageobjects.MyCartPage;
import olhajavacourses.pageobjects.ProductCatalog;
import olhajavacourses.testComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {

    @Test
    public void loginErrorValidation() throws IOException {

        landingPage.loginApplication("khytriolga@gmail.com", "gAH!gBZsWG2X");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test(groups={"ErrorHandling"})
    public void ProductErrorvalidation() throws IOException {
        String productName = "ZARA COAT 3";
        ProductCatalog productCatalog = landingPage.loginApplication("khytriakolga@gmail.com", "gAH!W2gBZsWG2X");

        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
        MyCartPage myCartPage = productCatalog.clickonCartbutton();

        Assert.assertFalse(myCartPage.isProductPresent("ZARA COAT 333"));
    }

}
