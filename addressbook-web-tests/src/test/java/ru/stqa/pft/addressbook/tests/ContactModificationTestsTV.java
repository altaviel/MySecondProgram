package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTestsTV extends TestBase {

    @Test
    public void testContactModificationTV (){
        app.getNavigationHelper().returnToMainPage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov", "IvIva", "89033883323", "ivanov@gmail.com", "Mira str. 3","test3"));
        }
        app.getNavigationHelper().returnToMainPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().viewContact();
        app.getContactHelper().modifyContact();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Ivan", "Ivanovich", "Ivanov", "IvIva", "89033883323", "ivanov@gmail.com", "Mira str. 3",null);
        app.getContactHelper().fillContactInf(contact,false);
        app.getContactHelper().initContactModification();
        app.getNavigationHelper().returnToMainPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size());

        before.remove (before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        Assert.assertEquals(before, after);

    }
}
