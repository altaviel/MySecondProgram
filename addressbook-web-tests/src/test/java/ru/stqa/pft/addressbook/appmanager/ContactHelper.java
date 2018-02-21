package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {

        super(wd);
    }

    public void createNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactInf(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("middlename"),contactData.getMidname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getNickname());
        type(By.name("mobile"),contactData.getMobphone());
        type(By.name("email"),contactData.getEmail());
        type(By.name("address2"),contactData.getAddress());

    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void editContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void initContactModification() {

        click(By.name("update"));
    }

    public void viewContact() {

        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
    }

    public void modifyContact() {

        click(By.name("modifiy"));
    }

    public void checkContact() {

        click(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }

    public void deletebyChb() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void closeDialog() {
        wd.switchTo().alert().accept();
    }
}
