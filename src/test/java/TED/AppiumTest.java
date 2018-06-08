package TED;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

import static java.lang.System.in;


public class AppiumTest {

    AppiumLoader loader = new AppiumLoader();

    private static AndroidDriver<MobileElement> driver;

    static String login = "m0riturus@ukr.net";
    static String password = "zaqwsxcde";

    static String xpathLogin = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[4]/android.widget.Button";

    static  String xpathFourElements = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[6]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ImageView";

    @BeforeMethod
    public void start() throws MalformedURLException, InterruptedException {
        loader.setUp();
    }


    @Test(priority = 1)
    public void loginTest() throws InterruptedException {

        myElement(By.id("Избранное")).click();
        myElement(By.id("com.ted.android:id/myTalksLoggedOutActionLogin")).click();
        myElement(By.id("user_email")).sendKeys(login);
        myElement(By.id("user_password")).sendKeys(password);
        Tools.setDriver(loader.getDriver());
        Tools.swipeByCoords(500, 600, 500, 200);
        myElement(By.xpath(xpathLogin)).click();
        Assert.assertEquals(myElement(By.id("com.ted.android:id/alertTitle")).getText(), "Готово!");
        Thread.sleep(3000);
    }


    @Test(priority = 2)
    public void serchElement() throws InterruptedException {

        myElement(By.id("Плейлисты")).click();
        Thread.sleep(10000);
        Tools.finedAndClick(xpathFourElements, loader.getDriver());
        Thread.sleep(5000);
        MobileElement ted = loader.getDriver().findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.TextView[1]"));
        Assert.assertEquals(ted.getText(), "Thrifty TED Talks");
    }


    @AfterMethod
    public void close() throws InterruptedException {
        Thread.sleep(3000);
        loader.tearDown();
    }

    public MobileElement myElement(By by) {
        MobileElement element = loader.getDriver().findElement(by);
        return element;
    }

}
