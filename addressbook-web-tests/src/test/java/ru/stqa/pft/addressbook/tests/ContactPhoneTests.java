package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions(){

        app.goTo().mainPage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withMidname("Ivanovich").withLastname("Ivanov"). withNickname("IvIva").getMobphone("89033883323").withEmail("ivanov@gmail.com").withAddress("Mira str. 3").withGroup("test3"));
        }
    }

    @Test
    public void testContactPhones(){
        app.goTo().mainPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.withHomephone(), equalTo(contactInfoFromEditForm.withHomephone()));


    }
}
