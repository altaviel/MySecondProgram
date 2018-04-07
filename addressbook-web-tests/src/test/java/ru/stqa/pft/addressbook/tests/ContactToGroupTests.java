package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
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
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov")
                    .withHomePhone("111").withMobilePhone("89033883323")
                    .withWorkPhone("333").withEmail("ivanov@gmail.com").withAddress("Mira str 3").inGroup(groupData), true);
        }
    }


    @Test
    public void addToGroupContact(){
        Contacts before = app.db().contacts();
        ContactData addedContact = before.iterator().next();
        app.contact().add(addedContact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
        verifyContactListInUi();
    }
 }



