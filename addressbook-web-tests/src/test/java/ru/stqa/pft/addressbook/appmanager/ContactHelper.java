package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void createNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactInf(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("middlename"),contactData.getMidname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getNickname());
        type(By.name("mobile"),contactData.getMobphone());
        type(By.name("email"),contactData.getEmail());
        type(By.name("address2"),contactData.getAddress());
        if(creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else{
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void initContactModification() {

        click(By.name("update"));
    }

    public void modifyInit() {

        click(By.name("modifiy"));
    }

    public void checkContactById(int id) {

        click(By.cssSelector("input[value='" + id + "']"));
    }

    public void deletebyChb() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void closeDialog() {
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        addNewContact();
        fillContactInf(contact,true);
        createNewContact();
    }

    public void modify(ContactData contact) {
        fillContactInf(contact,false);
        initContactModification();
        returnToMainPage();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

    public void returnToMainPage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));

    }

    public void deleteSelected(ContactData contact) {
        checkContactById(contact.getId());
        deletebyChb();
        closeDialog();
    }

    public void deleteByEdit(ContactData contact) {
        editById(contact.getId());
        deleteContact();
    }

    public void editById(int Id) {
        wd.findElement(By.cssSelector("input[value='" + Id + "']"));
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));

    }

    public void deleteByView(ContactData contact) {
        viewContactById(contact.getId());
        modifyInit();
        deleteContact();
    }

    public void viewContactById(int Id) {
        wd.findElement(By.cssSelector("input[value='" + Id + "']"));
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
    }
}