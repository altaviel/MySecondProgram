package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address"), contactData.getAddress());
 //       attach(By.name("photo"), contactData.getPhoto());
 //       if (creation) {
 //           new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
  //      } else {
   //         Assert.assertFalse(isElementPresent(By.name("new_group")));
     //   }
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);

    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void submitContact() {
        click(By.name("submit"));
    }

    public void initContactModificationById(int id)  {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void selectContactById(int id) {
        click(By.cssSelector("input[value='" + id + "']"));
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm((contact), false);
        submitModification();
        contactCache = null;
        goToHomePage();
    }

    public void submitModification() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void deleteSelectedContact() {
        click(By.cssSelector("input[value='Delete']"));
    }

    public void goToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void create(ContactData contactData, boolean creation) {
        initContactCreation();
        fillContactForm(contactData, creation);
        submitContact();
        contactCache = null;

    }


    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allphones = cells.get(5).getText();
            String allemails = cells.get(4).getText();
            String address = cells.get(3).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAllPhones(allphones).withAllemails(allemails).withAddress(address));
        }
        return new Contacts(contactCache);
    }

    public void deleteByEdit(ContactData contact) {
        editById(contact.getId());
        deleteContact();
        contactCache = null;
    }

    public void editById(int Id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", Id))).click();

    }

    public void deleteSelected(ContactData contact) {
        checkContactById(contact.getId());
        deletebyChb();
        closeDialog();
        contactCache = null;
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

    public void deleteByView(ContactData contact) {
        viewContactById(contact.getId());
        modifyInit();
        deleteContact();
        contactCache = null;
    }

    public void viewContactById(int Id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", Id))).click();
    }


    public void modifyInit() {

        click(By.name("modifiy"));
    }

    public void modifyByView(ContactData contact) {
        fillContactForm((contact), false);
        submitModification();
        contactCache = null;
        goToHomePage();
    }


    public void add(ContactData contact) {
            selectContactById(contact.getId());
            submitContactAddInGroup();
            goToHomePage();
            contactCache = null;
        }


    public void remove(ContactData contact, GroupData group) {
        viewContactById(contact.getId());
        goToContactsInGroupsPage(group.getId());
        selectContactById(contact.getId());
        submitContactRemoveFromGroup();
        goToHomePage();
        contactCache = null;
    }


    public void submitContactAddInGroup() {
        click(By.cssSelector("input[name='add']"));
    }

    public void submitContactRemoveFromGroup() {
        click(By.cssSelector("input[name='remove']"));
    }

    public void goToContactsInGroupsPage(int group) {
        wd.findElement(By.cssSelector(String.format("a[href='./index.php?group=%s']", group))).click();
    }
}