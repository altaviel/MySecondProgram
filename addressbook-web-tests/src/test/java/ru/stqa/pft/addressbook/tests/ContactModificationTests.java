package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        Groups groups = app.db().groups();
        GroupData groupData;

        if (groups.isEmpty()) {
            app.goTo().groupPage();
            groupData = new GroupData()
                    .withName("test")
                    .withHeader("header")
                    .withFooter("footer");
            app.group().create(groupData);
        } else {
            groupData = groups.iterator().next();
        }
        if (app.db().contacts().size() == 0){
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov")
                    .withHomePhone("111").withMobilePhone("89033883323")
                    .withWorkPhone("333").withEmail("ivanov@gmail.com").withAddress("Mira str 3").inGroup(groupData), true);
        }
     }

    @Test
    public void testContactModification(){
        app.goTo().mainPage();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Ivan").withLastname("Ivanov")
                .withHomePhone("111").withMobilePhone("89033883323")
                .withWorkPhone("333").withEmail("ivanov@gmail.com").withAddress("Mira str 3");
        app.contact().modify(contact);
        app.goTo().mainPage();
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.db().contacts();

       assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUi();
    }

    @Test
    public void testContactModificationTV (){
        app.goTo().mainPage();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Ivan").withLastname("Ivanov")
                .withHomePhone("111").withMobilePhone("89033883323")
                .withWorkPhone("333").withEmail("ivanov@gmail.com").withAddress("Mira str 3");
        app.contact().viewContactById(contact.getId());
        app.contact().modifyInit();
        app.contact().modifyByView(contact);
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUi();

    }

}
