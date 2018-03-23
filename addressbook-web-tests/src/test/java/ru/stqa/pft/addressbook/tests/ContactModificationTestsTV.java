package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTestsTV extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        app.goTo().mainPage();
        if (app.contact().list().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withMidname("Ivanovich").withLastname("Ivanov"). withNickname("IvIva").withMobphone("89033883323").withEmail("ivanov@gmail.com").withAddress("Mira str. 3").withGroup("test3"));
        }
    }

    @Test
    public void testContactModificationTV (){
        app.goTo().mainPage();
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("Ivan").withMidname("Ivanovich").withLastname("Ivanov"). withNickname("IvIva").withMobphone("89033883323").withEmail("ivanov@gmail.com").withAddress("Mira str. 3").withGroup("test3");
        app.contact().viewContact();
        app.contact().modifyInit();
        app.contact().modify(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size());

        before.remove (index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        Assert.assertEquals(before, after);

    }
}
