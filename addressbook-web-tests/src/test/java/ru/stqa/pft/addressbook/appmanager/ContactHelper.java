package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
      //  contactCache = null;
    }

    public void modify(ContactData contact) {
        fillContactInf(contact,false);
        initContactModification();
        returnToMainPage();
    }

//  public Set<ContactData> all() {
//       Set<ContactData> contacts = new HashSet<ContactData>();
//       List<WebElement> rows = wd.findElements(By.name("entry"));
  //     for (WebElement row : rows) {
   //        List<WebElement> cells = row.findElements((By.tagName("id")));
   //        int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
    //       String lastname = cells.get(1).getText();
     //      String firstname = cells.get(2).getText();
     //      String address = cells.get(3).getText();
     //      String email = cells.get(4).getText();
      //     String[] phones = cells.get(5).getText().split("\n");

      //     contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withHomePhone(phones[0])
      //             .withMobphone(phones[1]).withWorkPhone(phones[2]).withAddress(address).withEmail(email));
    //   }
  //    return contacts;
 //   }

    private Contacts contactCache = null;

    public Contacts all() {
              if (contactCache != null){
                return new Contacts(contactCache);
            }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
           List<WebElement> cells = row.findElements((By.tagName("id")));
           int id = Integer.parseInt(By.tagName("input").findElement(cells.get(0)).getAttribute("value"));
           String lastname = cells.get(1).getText();
           String firstname = cells.get(2).getText();
           String address = cells.get(3).getText();
           String email = cells.get(4).getText();
           String[] phones = cells.get(5).getText().split("\n");

            assert contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withHomePhone(phones[0])
                    .withMobphone(phones[1]).withWorkPhone(phones[2]).withAddress(address).withEmail(email));
        }
      return new Contacts(contactCache);
  }

    public ContactData infoFromEditForm(ContactData contact) {
        editById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname)
                .withLastname(lastname).withHomePhone(home).withMobphone(mobile).withWorkPhone(work)
                .withEmail(email).withAddress(address);

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
     //   contactCache = null;
    }

    public void deleteByEdit(ContactData contact) {
        editById(contact.getId());
        deleteContact();
      //  contactCache = null;
    }

    public void editById(int Id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", Id))).click();

    }

    public void deleteByView(ContactData contact) {
        viewContactById(contact.getId());
        modifyInit();
        deleteContact();
     //   contactCache = null;
    }

    public void viewContactById(int Id) {
        wd.findElement(By.cssSelector("input[value='" + Id + "']"));
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

}