package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        app.goTo().mainPage();
        if (app.contact().list().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withMidname("Ivanovich").withLastname("Ivanov"). withNickname("IvIva").withMobphone("89033883323").withEmail("ivanov@gmail.com").withAddress("Mira str. 3").withGroup("test3"));
        }
    }

    @Test
    public void contactDelete() {
        app.goTo().mainPage();
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        app.contact().edit();
        app.contact().deleteContact();
        app.goTo().mainPage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
