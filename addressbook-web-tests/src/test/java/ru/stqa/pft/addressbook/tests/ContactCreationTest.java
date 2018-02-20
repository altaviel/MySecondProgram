package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.addNewContact();
        app.fillContactInf(new ContactData("Ivan", "Ivanovich", "Ivanov", "IvIva", "89033883323", "ivanov@gmail.com", "Mira str. 3"));
        app.createNewContact();
    }

}
