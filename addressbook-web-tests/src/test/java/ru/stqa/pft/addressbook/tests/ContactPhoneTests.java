package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {


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
    public void testContactPhones() {
        app.goTo().mainPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

        private String mergePhones(ContactData contact) {
            return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                    .stream().filter((s) -> ! s.equals(""))
                    .map(ContactPhoneTests::clean)
                    .collect(Collectors.joining("\n"));

        }

        public static String clean(String phone) {
            return phone.replaceAll("\\s", "").replaceAll("[-()]", "");

        }

    }

