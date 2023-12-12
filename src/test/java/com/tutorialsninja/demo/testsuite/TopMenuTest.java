package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.customlisteners.CustomListeners;
import com.tutorialsninja.demo.pages.ComponentsPage;
import com.tutorialsninja.demo.pages.DesktopPage;
import com.tutorialsninja.demo.pages.Homepage;
import com.tutorialsninja.demo.pages.LaptopsAndNotebookPage;
import com.tutorialsninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * 1. create class "TopMenuTest"
 * 1.1 create method with name "selectMenu" it has one parameter name "menu" of type
 * string
 * 1.2 This method should click on the menu whatever name is passed as parameter.
 * Write the following Test:
 * 1. verifyUserShouldNavigateToDesktopsPageSuccessfully()
 * 1.1 Mouse hover on “Desktops” Tab and click
 * 1.2 call selectMenu method and pass the menu = “Show All Desktops”
 * 1.3 Verify the text ‘Desktops’
 * 2. verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully()
 * 2.1 Mouse hover on “Laptops & Notebooks” Tab and click
 * 2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
 * 2.3 Verify the text ‘Laptops & Notebooks’
 * 3. verifyUserShouldNavigateToComponentsPageSuccessfully()
 * 3.1 Mouse hover on “Components” Tab and click
 * 3.2 call selectMenu method and pass the menu = “Show All Components”
 * 3.3 Verify the text ‘Component
 */
@Listeners(CustomListeners.class)
public class TopMenuTest extends BaseTest {

    Homepage homepage = new Homepage();
    DesktopPage desktopPage = new DesktopPage();
    LaptopsAndNotebookPage laptopAndNotebookPage = new LaptopsAndNotebookPage();
    ComponentsPage componentsPage = new ComponentsPage();

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homepage = new Homepage();
        desktopPage = new DesktopPage();
        laptopAndNotebookPage = new LaptopsAndNotebookPage();
        componentsPage = new ComponentsPage();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        // 1.1 Mouse hover on “Desktops” Tab and click
        homepage.mouseHooverOnDesktops();
        homepage.clickOnDesktopsTab();
        //1.2 call selectMenu method and pass the menu = “Show All Desktops”
        homepage.selectMenu("Show All Desktops");
        //1.3 Verify the text ‘Desktops'
        Assert.assertEquals(desktopPage.getDesktopsText(), "Desktops");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        // 2.1 Mouse hover on “Laptops & Notebooks” Tab and click
        homepage.mouseHooverOnLaptopsAndNotebooks();
        homepage.clickOnLaptopsAndNoteBooksTab();
        //2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        homepage.selectMenu("Show All Laptops & Notebooks");
        //2.3 Verify the text ‘Laptops & Notebooks’
        Assert.assertEquals(laptopAndNotebookPage.verifyTextLaptopsAndNotebooks(), "Laptops & Notebooks");
    }

    @Test(groups = {"regression"})
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        // 3.1 Mouse hover on “Components” Tab and click
        homepage.mouseHooverOnComponents();
        homepage.clickOnComponents();
        //3.2 call selectMenu method and pass the menu = “Show All Components”
        homepage.selectMenu("Show All Components");
        //3.3 Verify the text ‘Components’
        Assert.assertEquals(componentsPage.verifyComponentsText(), "Components");
    }


}
