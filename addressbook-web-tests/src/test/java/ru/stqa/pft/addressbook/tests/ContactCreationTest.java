package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().mainPage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Ivanov")
                .withHomePhone("111").withMobilePhone("89033883323")
                .withWorkPhone("333").withEmail("ivanov@gmail.com").withAddress("Mira str 3").withGroup("test3");
        app.contact().create(contact, true);
        app.goTo().mainPage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
