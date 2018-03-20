package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTestChB extends TestBase {

    @Test
    public void testContactDeletionChB() {
        app.getNavigationHelper().returnToMainPage();

        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov", "IvIva", "89033883323", "ivanov@gmail.com", "Mira str. 3","test3"));
        }

        app.getNavigationHelper().returnToMainPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().checkContact();
        app.getContactHelper().deletebyChb();
        app.getContactHelper().closeDialog();
        app.getNavigationHelper().returnToMainPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(before.size() -  1);
        Assert.assertEquals(before, after);
    }

}
