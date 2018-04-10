package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class AdminHelper extends HelperBase {

    public AdminHelper(ApplicationManager app) {
        super(app);
    }

    public void login() {
        app.getDriver();
        wd.findElement(name("username")).sendKeys("administrator");
        click(By.cssSelector("input[type='submit']"));
        wd.findElement(name("password")).sendKeys("root");
        click(By.cssSelector("input[type='submit']"));
    }

    public void userPassChange() {
        click(By.cssSelector("a[href='/mantisbt-2.13.1/manage_overview_page.php']"));
        click(By.cssSelector("a[href='/mantisbt-2.13.1/manage_user_page.php']"));
        click(By.linkText("user1"));
        click(By.cssSelector("input[type='submit']"));
    }

    public void finish(String confirmationLink) {
        wd.get(confirmationLink);
        wd.findElement(name("password")).sendKeys("password1");
        wd.findElement(name("password_confirm")).sendKeys("password1");
        click(xpath("/html/body/div[2]/form/table/tbody/tr[10]/td[2]/input"));
    }
}
