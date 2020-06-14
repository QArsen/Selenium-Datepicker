package Elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Elements {


    @FindBy(xpath = "//input[@class='form-control docs-date']")
    public static WebElement pick_date_button;

    @FindBy(xpath = "//ul//li[@class='highlighted picked']")
    public static WebElement current_date;

    @FindBy(xpath = "//li[@class='picked']")
    public static WebElement selected_date;

    @FindBy(xpath = "//li[@data-view='month current']")
    public static WebElement current_month;

}
