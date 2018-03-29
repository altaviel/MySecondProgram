package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        app.goTo().mainPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov")
                    .withHomePhone("111").withMobilePhone("89033883323")
                    .withWorkPhone("333").withEmail("ivanov@gmail.com").withAddress("Mira str 3").withGroup("test3"), true);
        }
    }

    @Test
    public void testContactModification(){
        app.goTo().mainPage();
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Ivan").withLastname("Ivanov")
                .withHomePhone("111").withMobilePhone("89033883323")
                .withWorkPhone("333").withEmail("ivanov@gmail.com").withAddress("Mira str 3").withGroup("test3");
        app.contact().modify(contact);
        app.goTo().mainPage();
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

    @Test
    public void testContactModificationTV (){
        app.goTo().mainPage();
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Ivan").withLastname("Ivanov")
                .withHomePhone("111").withMobilePhone("89033883323")
                .withWorkPhone("333").withEmail("123@123.qw").withAddress("Mira str 3").withGroup("test3");
        app.contact().viewContactById(contact.getId());
        app.contact().modifyInit();
        app.contact().modifyByView(contact);
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }


}
