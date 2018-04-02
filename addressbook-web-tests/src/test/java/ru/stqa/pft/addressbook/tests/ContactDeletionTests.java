package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        app.goTo().mainPage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withMidname("Ivanovich").withLastname("Ivanov"). withNickname("IvIva").withMobphone("89033883323").withEmail("ivanov@gmail.com").withAddress("Mira str. 3").withGroup("test3"));
        }
    }

    @Test
    public void contactDelete() {
        app.goTo().mainPage();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteByEdit(deletedContact);
        app.goTo().mainPage();
        assertThat(app.contact().count(),equalTo(before.size() - 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.without(deletedContact)));
    }

    @Test
    public void testContactDeletionChB() {
        app.goTo().mainPage();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteSelected(deletedContact);
        app.goTo().mainPage();
        assertThat(app.contact().count(),equalTo(before.size() - 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.without(deletedContact)));
    }

    @Test
    public void contactDeletionTV() {
        app.goTo().mainPage();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteByView(deletedContact);
        app.goTo().mainPage();
        assertThat(app.contact().count(),equalTo(before.size() - 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
