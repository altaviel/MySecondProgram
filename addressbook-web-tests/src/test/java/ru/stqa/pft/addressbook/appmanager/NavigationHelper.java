package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void goToGroupPage() {
        wd.findElement(By.id("header")).click();
        wd.findElement(By.linkText("groups")).click();
    }

    public void returnToMainPage() {
        wd.findElement(By.id("header")).click();
        wd.findElement(By.linkText("home")).click();
    }
}
