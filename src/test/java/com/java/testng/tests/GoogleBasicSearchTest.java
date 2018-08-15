package com.java.testng.tests;

import com.java.testng.tests.Model.GoogleSearchPage;
import com.java.testng.tests.Model.PageUtils;
import com.java.testng.tests.Utils.WebdriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

public class GoogleBasicSearchTest {

    private static WebDriver driver;
    private static GoogleSearchPage gsp;
    private static PageUtils pu;

    @BeforeClass
    public static void setUpConfig() {
        gsp = new GoogleSearchPage();
        pu = new PageUtils();
    }

    @BeforeTest
    @Parameters("browser")
    public void setUpDriver(WebdriverHelper.BrowserType browser) {
        driver = WebdriverHelper.createDriver(browser);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(description = "The name of this test")
    public void search() {
        driver.get(gsp.getUrl());
        By searchBox = gsp.getSearchBox();
        driver.findElement(searchBox).sendKeys("ChromeDriver");
        driver.findElement(searchBox).submit();
        pu.waitForWebElementToBeClickable(driver, gsp.getLogo());
        assertEquals("ChromeDriver - Google Search", driver.getTitle());
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
