package steps;


import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.SmartBearHomePage;
import pages.SmartBearWebOrdersPage;
import utils.Driver;
import utils.DropdownHandler;
import utils.Waiter;

public class SmartBearSteps {

    WebDriver driver;
    SmartBearHomePage smartBearHomePage;
    SmartBearWebOrdersPage smartBearWebOrdersPage;

    @Before
    public void setup(){
        driver = Driver.getDriver();
        smartBearHomePage = new SmartBearHomePage();
        smartBearWebOrdersPage = new SmartBearWebOrdersPage();
    }

    @When("user enters username as {string}")
    public void user_enters_username_as(String userName) {
        smartBearHomePage.userNameInputBox.sendKeys(userName);
    }

    @When("user enters password as {string}")
    public void user_enters_password_as(String password) {
        smartBearHomePage.passwordInputBox.sendKeys(password);
    }

    @When("user clicks on Login button")
    public void user_clicks_on_Login_button() {
        smartBearHomePage.loginButton.click();
    }

    @Then("user should see {string} Message")
    public void user_should_see_Message(String errorMessage) {
        Assert.assertEquals(errorMessage, smartBearHomePage.errorMessage.getText());
    }

    @Then("user should be routed to {string}")
    public void user_should_be_routed_to(String currentUrl) {
        Assert.assertEquals(currentUrl, driver.getCurrentUrl());

    }

    @Then("validate below menu items are displayed")
    public void validateBelowMenuItemsAreDisplayed(DataTable dataTable) {
        for (int i = 0; i < dataTable.asList().size(); i++) {
            Assert.assertTrue(smartBearWebOrdersPage.webOrderMenuItems.get(i).isDisplayed());
        }
    }

        @When("user clicks on {string} button")
        public void user_clicks_on_button(String button) {
            switch (button) {
                case "Check All":
                    smartBearWebOrdersPage.checkAllButton.click();
                    break;

                case "Uncheck All":
                    smartBearWebOrdersPage.unCheckAllButton.click();
                    break;
            }
        }

        @Then("all rows should be checked")
        public void all_rows_should_be_checked() {
            for (WebElement element : smartBearWebOrdersPage.checkBoxes) {
                Assert.assertFalse(element.isSelected());
            }
        }

        @Then("all rows should be unchecked")
        public void all_rows_should_be_unchecked() {
        for (WebElement element : smartBearWebOrdersPage.checkBoxes){
            Assert.assertFalse(element.isSelected());
        }
    }

    @When("user clicks on{string} menu item")
    public void user_clicks_on_menu_item(String orderMenuItem) {
        switch (orderMenuItem) {
            case "Order":
                for (int i = 0; i < smartBearWebOrdersPage.webOrderMenuItems.size(); i++) {
                    smartBearWebOrdersPage.webOrderMenuItems.get(i).click();
                }
        }
    }

    @When("user selects{string} as product")
    public void user_selects_as_product(String product) {
        Waiter.pause(3);
        DropdownHandler.selectOptionByValue(smartBearWebOrdersPage.familyAlbumProduct, product);

    }

    @When("user enters {int} as quantity")
    public void user_enters_as_quantity(Integer quantity) {
        smartBearWebOrdersPage.quantityInputBox.sendKeys(String.valueOf(quantity));
    }

    @When("user enters all address information")
    public void user_enters_all_address_information() {
        smartBearWebOrdersPage.customerNameInputBox.sendKeys("Melda");
        smartBearWebOrdersPage.StreetInputBox.sendKeys("WhiteOak Drive");
        smartBearWebOrdersPage.cityInputBox.sendKeys("Plainfield");
        smartBearWebOrdersPage.StateInputBox.sendKeys("IL");
        smartBearWebOrdersPage.zipInputBox.sendKeys("60585");

    }

    @When("user enters all payment information")
    public void user_enters_all_payment_information() {
        smartBearWebOrdersPage.visaRadioButton.click();
        smartBearWebOrdersPage.cardNrInputBox.sendKeys("1234567890");
        smartBearWebOrdersPage.expireDateInputBox.sendKeys("08/23");
        smartBearWebOrdersPage.processBox.click();

    }

    @And("user clicks on{string} menu items")
    public void userClicksOnMenuItems(String viewAllOrderLink) {
        smartBearWebOrdersPage.viewAllOrder.click();
    }

    @Then("user should see their order displayed in the{string} table")
    public void user_should_see_their_order_displayed_in_the_table(String string) {
        for (int i = 1; i < smartBearWebOrdersPage.myOrderFirstRow.size()-1; i++) {
            Assert.assertTrue(smartBearWebOrdersPage.myOrderFirstRow.get(i).isDisplayed());
        }

    }
    @Then("validate all information entered displayed correct with the order")
    public void validate_all_information_entered_displayed_correct_with_the_order(DataTable dataTable) {
        for (int i = 1; i <12; i++) {
            Assert.assertEquals(dataTable.asList().get(i), smartBearWebOrdersPage.myOrderFirstRow.get(i).getText());
        }
    }

    @Then("validate all orders are deleted from the{string}")
    public void validate_all_orders_are_deleted_from_the(String orders) {
        Assert.assertNotNull(orders);

    }
    @Then("validate user sees{string}Message")
    public void validate_user_sees_Message(String emptyMessage) {
        Assert.assertEquals(emptyMessage,smartBearWebOrdersPage.emptyMessage.getText());
    }

}
