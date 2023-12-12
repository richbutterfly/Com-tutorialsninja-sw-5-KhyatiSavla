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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Create the class LaptopsAndNotebooksTest
 * Write the following test
 * 1. Test name verifyProductsPriceDisplayHighToLowSuccessfully()
 * 1.1 Mouse hover on Laptops & Notebooks Tab.and click
 * 1.2 Click on “Show All Laptops & Notebooks”
 * 1.3 Select Sort By "Price (High > Low)"
 * 1.4 Verify the Product price will arrange in High to Low order.
 * 2. Test name verifyThatUserPlaceOrderSuccessfully()
 * 2.1 Mouse hover on Laptops & Notebooks Tab and click
 * 2.2 Click on “Show All Laptops & Notebooks”
 * 2.3 Select Sort By "Price (High > Low)"
 * 2.4 Select Product “MacBook”
 * 2.5 Verify the text “MacBook”
 * 2.6 Click on ‘Add To Cart’ button
 * 2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
 * 2.8 Click on link “shopping cart” display into success message
 * 2.9 Verify the text "Shopping Cart"
 * 2.10 Verify the Product name "MacBook"
 * 2.11 Change Quantity "2"
 * 2.12 Click on “Update” Tab
 * 2.13 Verify the message “Success: You have modified your shopping cart!”
 * 2.14 Verify the Total £737.45
 * 2.15 Click on “Checkout” button
 * 2.16 Verify the text “Checkout”
 * 2.17 Verify the Text “New Customer”
 * 2.18 Click on “Guest Checkout” radio button
 * 2.19 Click on “Continue” tab
 * 2.20 Fill the mandatory fields
 * 2.21 Click on “Continue” Button
 * 2.22 Add Comments About your order into text area
 * 2.23 Check the Terms & Conditions check box
 * 2.24 Click on “Continue” button
 * 2.25 Verify the message “Warning: Payment method required!”
 */
@Listeners(CustomListeners.class)
public class LaptopsAndNotebooksTest extends BaseTest {

    Homepage homepage = new Homepage();
    LaptopsAndNotebookPage laptopsAndNotebookPage = new LaptopsAndNotebookPage();
    ProductPage productPage = new ProductPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    CheckOutPage checkOutPage = new CheckOutPage();

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homepage = new Homepage();
        laptopsAndNotebookPage = new LaptopsAndNotebookPage();
        productPage = new ProductPage();
        shoppingCartPage = new ShoppingCartPage();
        checkOutPage = new CheckOutPage();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        // 1.1 Mouse hover on Laptops & Notebooks Tab.and click
        homepage.mouseHooverOnLaptopsAndNotebooks();
        homepage.clickOnLaptopsAndNoteBooksTab();
        //1.2 Click on “Show All Laptops & Notebooks”
        homepage.clickOnShowAllLaptopsAndNotebooks();
        //1.3 Select Sort By "Price (High > Low)"
        laptopsAndNotebookPage.clickOnSortBy("Price (High > Low)");

        // Get all the products price and stored into array list
        List<WebElement> products = driver.findElements(By.xpath("//p[@class ='price']"));
        List<Double> originalProductsPrice = new ArrayList<>();
        for (WebElement e : products) {
            System.out.println(e.getText());
            String[] arr = e.getText().split("Ex Vat:");
            originalProductsPrice.add(Double.valueOf(arr[0].substring(1).replaceAll(",", "")));
        }
        System.out.println(originalProductsPrice);
        // Sort By Reverse order
        Collections.sort(originalProductsPrice, Collections.reverseOrder());
        System.out.println(originalProductsPrice);
        // After filter Price (High > Low) Get all the products price and stored into array list
        products = driver.findElements(By.xpath("//p[@class ='price']"));
        ArrayList<Double> afterSortByPrice = new ArrayList<>();
        for (WebElement e : products) {
            String[] arr = e.getText().split("Ex Vat:");
            afterSortByPrice.add(Double.valueOf(arr[0].substring(1).replaceAll(",", "")));
        }
        System.out.println(afterSortByPrice);

        //1.4 Verify the Product price will arrange in High to Low order.
        Assert.assertEquals(originalProductsPrice, afterSortByPrice);

    }

    @Test(groups = {"smoke", "regression"})
    public void verifyThatUserPlaceOrderSuccessfully() {
        //2.1 Mouse hover on Laptops & Notebooks Tab and click
        homepage.mouseHooverOnLaptopsAndNotebooks();
        homepage.clickOnLaptopsAndNoteBooksTab();
        //2.2 Click on “Show All Laptops & Notebooks”
        homepage.clickOnShowAllLaptopsAndNotebooks();
        //2.3 Select Sort By "Price (High > Low)"
        laptopsAndNotebookPage.clickOnSortBy("Price (High > Low)");
        //2.4 Select Product “MacBook”
        laptopsAndNotebookPage.clickOnMacBook();
        //2.5 Verify the text “MacBook”
        Assert.assertEquals(productPage.verifyTextMacBook(), "MacBook");
        //2.6 Click on ‘Add To Cart’ button
        productPage.clickOnAddToCart();
        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        String expectedText = "Success: You have added MacBook to your shopping cart!";
        String actualText = productPage.verifySuccessMsgForMacBook();
        Assert.assertEquals(actualText, expectedText);
        //2.8 Click on link “shopping cart” display into success message
        productPage.clickOnShoppingCart();
        //2.9 Verify the text "Shopping Cart"
        Assert.assertEquals(shoppingCartPage.verifyTextShoppingCart(), "Shopping Cart");
        //2.10 Verify the Product name "MacBook"
        Assert.assertEquals(shoppingCartPage.verifyTextMacBook(), "MacBook");
        //2.11 Change Quantity "2"
        shoppingCartPage.changeQty();
        //2.12 Click on “Update” Tab
        shoppingCartPage.clickOnUpdateSymbol();
        //2.13 Verify the message “Success: You have modified your shopping cart!”
        String expectedText1 = "Success: You have modified your shopping cart!";
        String actualText1 = shoppingCartPage.verifyTheModifyCartText();
        Assert.assertEquals(actualText1, expectedText1);
        //2.14 Verify the Total £737.45
        Assert.assertEquals(shoppingCartPage.verifyTotalAmount(), "£737.45");
        //2.15 Click on “Checkout” button
        shoppingCartPage.clickOnCheckOutButton();
        //2.16 Verify the text “Checkout”
        Assert.assertEquals(checkOutPage.verifyCheckOutText(), "Checkout");
        //2.17 Verify the Text “New Customer”
        Assert.assertEquals(checkOutPage.verifyNewCustomerText(), "New Customer");
        //2.18 Click on “Guest Checkout” radio button
        checkOutPage.clickOnGuestCheckOut();
        //2.19 Click on “Continue” tab
        checkOutPage.clickOnContinueButton();
        //2.20 Fill the mandatory fields
        checkOutPage.enterFirstName("Khyati");
        checkOutPage.enterLastName("Savla");
        checkOutPage.enterEmail("khyati123@gmail.com");
        checkOutPage.enterPhoneNumber("07474747474");
        checkOutPage.enterAddress1("11 Abc Road");
        checkOutPage.enterCity("London");
        checkOutPage.enterPostCode("AB1 CD2");
        checkOutPage.selectCountry("United Kingdom");
        checkOutPage.selectRegion("Greater London");
        //2.21 Click on “Continue” Button
        checkOutPage.clickOnLastContinueCheckOutButton();
        //2.22 Add Comments About your order into text area
        checkOutPage.enterComments("Handle it carefully");
        //2.23 Check the Terms & Conditions check box
        checkOutPage.clickOnAgree();
        //2.24 Click on “Continue” button
        checkOutPage.clickOnGuestContinue();
        //2.25 Verify the message “Warning: Payment method required!”
        String expectedMsg = "Warning: Payment method required!";
        String actualMsg = checkOutPage.verifyPaymentWarningText();
        Assert.assertEquals(actualMsg, expectedMsg);

    }
}
