package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTestChB extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        app.goTo().mainPage();
        if (app.contact().list().size() == 0){
            app.contact().create(new ContactData("Ivan", "Ivanovich", "Ivanov", "IvIva", "89033883323", "ivanov@gmail.com", "Mira str. 3","test3"));
        }
    }

    @Test
    public void testContactDeletionChB() {
        app.goTo().mainPage();
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        app.contact().checkContact();
        app.contact().deletebyChb();
        app.contact().closeDialog();
        app.goTo().mainPage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
