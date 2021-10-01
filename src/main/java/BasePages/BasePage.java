package BasePages;


import Enums.TextComparison;
import PageObjects.HomePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import Utils.DriverSetup;

import static Utils.ColourCodes.BLACK;


@Slf4j
public class BasePage extends DriverSetup implements IPageIdentity<BasePage> {

  private static final String HOMEPAGE_TITLE = "Swag Labs";

  public static final String HOME_PAGE_TITLE = "Swag Labs";

  // New header elements
  private static final String LOGO_CLASS = "login_logo";

  private static final String CREDENTIAL_WRAPPER_CLASS = "login_credentials_wrap";


  public BasePage () {

    log.info(BLACK + "Loading BasePage...");
  }


  @Override
  public boolean checkPageIdentity () {

    log.info("Checking base page identity...");
    return driver.getTitle().equals(HOMEPAGE_TITLE) && driver.getCurrentUrl().contains(properties.getProperty("URL"));
  }


  @Override
  public boolean checkPageElements () {

    log.info("Checking base page elements...");

    return verifyHeaderElements();
  }


  public BasePage navigateToBasePage () {

    log.info("Navigating to base page...");
    driver.get(properties.getProperty("URL"));
    assertOnPage();
    return new BasePage();
  }


  public HomePage login () {

    log.info("Performing login.");
    elementActions.clickOn(By.id("user-name"));
    elementActions.clearAndTypeText(By.id("user-name"), properties.getProperty("saucelabUser"));
    elementActions.typePassword(By.id("password"), properties.getProperty("saucelabPass"));
    elementActions.clickOn(By.id("login-button"));
    return new HomePage();
  }


  public boolean verifyHeaderElements () {

    log.info("Checking header elements...");
//    wait.until(visibilityOfElementLocated(By.className(LOGO_CLASS)));
    return elementChecks.checkIfElementDisplayed(By.className(LOGO_CLASS))
        && elementChecks.checkIfElementDisplayed(By.className(CREDENTIAL_WRAPPER_CLASS));
  }


  public boolean checkErrorMessage (By locator, String message) {

    log.info("Checking error message from " + locator.toString() + " with message : " + message);
    return elementChecks.checkElementText(locator, message, TextComparison.CONTAINS);
  }

}
