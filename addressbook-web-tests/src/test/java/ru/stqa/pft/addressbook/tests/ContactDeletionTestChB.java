package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTestChB extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        app.getNavigationHelper().returnToMainPage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov", "IvIva", "89033883323", "ivanov@gmail.com", "Mira str. 3","test3"));
        }
    }

    @Test
    public void testContactDeletionChB() {
        app.getNavigationHelper().returnToMainPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size()-1;
        app.getContactHelper().checkContact();
        app.getContactHelper().deletebyChb();
        app.getContactHelper().closeDialog();
        app.getNavigationHelper().returnToMainPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),index);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
