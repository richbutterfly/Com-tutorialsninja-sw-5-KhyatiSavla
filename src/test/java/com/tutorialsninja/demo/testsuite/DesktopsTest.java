package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.customlisteners.CustomListeners;
import com.tutorialsninja.demo.pages.*;
import com.tutorialsninja.demo.testbase.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.testdata.TestData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1. Create class “DesktopsTest”
 * Write the following Test:
 * 1.Test name verifyProductArrangeInAlphaBaticalOrder()
 * 1.1 Mouse hover on Desktops Tab. and click
 * 1.2 Click on “Show All Desktops”
 * 1.3 Select Sort By position "Name: Z to A"
 * 1.4 Verify the Product will arrange in Descending order.
 * 2. Test name verifyProductAddedToShoppingCartSuccessFully(String product, String qty,
 * String successMessage, String productName, String model, String total )
 * 2.1 Mouse hover on Currency Dropdown and click
 * 2.2 Mouse hover on £Pound Sterling and click
 * 2.3 Mouse hover on Desktops Tab.
 * 2.4 Click on “Show All Desktops”
 * 2.5 Select Sort By position "Name: A to Z"
 * 2.6 Select product <product>
 * 2.7 Enter Qty <qty> using Select class.
 * 2.8 Click on “Add to Cart” button
 * 2.9 Verify the Message <successMessage>
 * 2.10 Click on link “shopping cart” display into success message
 * 2.11 Verify the text "Shopping Cart"
 * 2.12 Verify the Product name <productName>
 * 2.13 Verify the Model <model>
 * 2.14 Verify the Total <total>
 */
@Listeners(CustomListeners.class)
public class DesktopsTest extends BaseTest {

    Homepage homepage = new Homepage();
    DesktopPage desktopPage = new DesktopPage();
    ProductPage productPage = new ProductPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homepage = new Homepage();
        desktopPage = new DesktopPage();
        productPage = new ProductPage();
        shoppingCartPage = new ShoppingCartPage();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyProductArrangeInAlphabeticalOrder() {
        //1.1 Mouse hover on Desktops Tab. and click
        homepage.mouseHooverOnDesktops();
        homepage.clickOnDesktopsTab();
        //1.2 Click on “Show All Desktops”
        homepage.clickOnShowAllDesktops();

        List<WebElement> products = driver.findElements(By.xpath("//h4/a"));
        ArrayList<String> originalProductsName = new ArrayList<>();
        for (WebElement e : products) {
            originalProductsName.add(e.getText());
        }
        System.out.println(originalProductsName);
        // Sort By Reverse order
        Collections.reverse(originalProductsName);
        System.out.println(originalProductsName);

        //1.3 Select Sort By position "Name: Z to A"
        desktopPage.clickOnSortBy("Name (Z - A)");

        // After filter Z -A Get all the products name and stored into array list
        products = driver.findElements(By.xpath("//h4/a"));
        ArrayList<String> afterSortByZToAProductsName = new ArrayList<>();
        for (WebElement e : products) {
            afterSortByZToAProductsName.add(e.getText());
        }
        System.out.println(afterSortByZToAProductsName);

        //1.4 Verify the Product will arrange in Descending order.
        Assert.assertEquals(originalProductsName, afterSortByZToAProductsName);
    }

    @Test(groups = {"smoke", "regression"},dataProvider = "DataSet",dataProviderClass = TestData.class)
    public void verifyProductAddedToShoppingCartSuccessFully(String product, String qty, String successMessage, String productName, String model, String total) {
        // 2.1 Mouse hover on Currency Dropdown and click
        homepage.clickOnCurrencyDropDown();
        // 2.2 Mouse hover on £Pound Sterling and click
        homepage.clickOnPoundSterling();
        // 2.3 Mouse hover on Desktops Tab.
        homepage.mouseHooverOnDesktops();
        homepage.clickOnDesktopsTab();
        //2.4 Click on “Show All Desktops”
        homepage.clickOnShowAllDesktops();
        //2.5 Select Sort By position "Name: A to Z"
        desktopPage.clickOnSortBy("Name (A - Z)");
        //2.6 Select product <product>
        desktopPage.selectProduct(product);
        //2.7 Enter Qty <qty> using Select class.
        productPage.enterQuantity(qty);
        //2.8 Click on “Add to Cart” button
        productPage.clickOnAddToCart();
        //2.9 Verify the Message <successMessage>
        Assert.assertEquals(productPage.verifySuccessText(), successMessage);
        // 2.10 Click on link “shopping cart” display into success message
        productPage.clickOnShoppingCart();
        //2.11 Verify the text "Shopping Cart"
        Assert.assertEquals(shoppingCartPage.verifyTextShoppingCart(), "Shopping Cart");
        //2.12 Verify the Product name <productName>
        Assert.assertEquals(shoppingCartPage.verifyTextHPLP3065(), productName);
        //2.13 Verify the Model <model>
        Assert.assertEquals(shoppingCartPage.verifyModelName(), model);
        //2.14 Verify the Total <total>
        Assert.assertEquals(shoppingCartPage.verifyTotalAmount(), total);
    }

}
