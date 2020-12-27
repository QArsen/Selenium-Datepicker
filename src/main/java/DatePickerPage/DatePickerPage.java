package DatePickerPage;

import BaseClass.BaseClass;
import Elements.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class DatePickerPage extends BaseClass {


    public void clickOnDatePickerButton() {
        Elements.pick_date_button.click();
    }

    private static boolean flag = true;

    public void findDate(int date) {
        List<WebElement> list = driver.findElements(By.cssSelector("div.datepicker-panel li"));
        List<WebElement> days = new ArrayList<>();
        String currentDate = waitForElementIsPresent(Elements.current_date).getText();
        int currentDateToInt = Integer.parseInt(currentDate);
        int res = currentDateToInt + date;
        if (date == 0) {
            waitForElementIsPresent(Elements.current_date).click();
            flag = false;
            printSelectedDate();
            return;
        } else if (flag) {
            for (WebElement element : list) {
                if (element.getAttribute("class").equalsIgnoreCase("highlighted picked") || element.getAttribute("data-view").equalsIgnoreCase("day")) {
                    days.add(element);
                }
            }
            if (res <= days.size()) {
                driver.findElement(By.xpath("//ul//li[@data-view='day' and text()='" + res + "']")).click();
            } else {
                while (res > 0) {
                    res = countDays(res);
                }
            }
        }
        printSelectedDate();
    }

    public int countDays(int res) {
        List<WebElement> list = driver.findElements(By.cssSelector("div.datepicker-panel li"));
        List<WebElement> day = new ArrayList<>();
;        for (WebElement s : list) {
            if (!s.getAttribute("class").equalsIgnoreCase("muted")
                    && s.getAttribute("data-view").equalsIgnoreCase("day")) {
                day.add(s);
            } else if (s.getAttribute("data-view").equalsIgnoreCase("day picked")) {
                day.add(s);
            }
        }
        if (res > day.size()) {
            driver.findElement(By.xpath("//ul//li[@data-view='month next']")).click();
            res = res - day.size();
            if (res == 0) {
                res = 1;
                driver.findElement(By.xpath("//ul//li[@data-view='day' and text()='" + res + "']")).click();
            }
        } else if (res <= day.size()) {
            driver.findElement(By.xpath("//ul//li[@data-view='day' and text()='" + res + "']")).click();
            res = 0;
        }
        return res;
    }

    public void printSelectedDate() {

        if (!flag) {
            System.out.println("Selected date is : " + Elements.current_date.getText() + " " +
                    waitForElementIsPresent(Elements.current_month).getText() + " year");
        } else
            System.out.println("Selected date is : " + waitForElementIsPresent(Elements.selected_date).getText() + " " +
                    waitForElementIsPresent(Elements.current_month).getText() + " year");
    }
}














