package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        app.getNavigationHelper().returnToMainPage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov", "IvIva", "89033883323", "ivanov@gmail.com", "Mira str. 3","test3"));
        }
    }

    @Test
    public void contactDelete() {
        app.getNavigationHelper().returnToMainPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size()-1;
        app.getContactHelper().editContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().returnToMainPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),index);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
