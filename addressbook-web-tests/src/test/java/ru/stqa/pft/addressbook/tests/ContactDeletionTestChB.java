package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTestChB extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        app.goTo().mainPage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withMidname("Ivanovich").withLastname("Ivanov"). withNickname("IvIva").withMobphone("89033883323").withEmail("ivanov@gmail.com").withAddress("Mira str. 3").withGroup("test3"));
        }
    }

    @Test
    public void testContactDeletionChB() {
        app.goTo().mainPage();
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteSelected(deletedContact);
        app.goTo().mainPage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }

}
