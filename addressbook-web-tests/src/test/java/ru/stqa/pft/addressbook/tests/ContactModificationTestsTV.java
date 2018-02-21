package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTestsTV extends TestBase {

    @Test
    public void testContactModificationTV (){

        app.getContactHelper().viewContact();
        app.getContactHelper().modifyContact();
        app.getContactHelper().fillContactInf(new ContactData("Ivan", "Ivanovich", "Ivanov", "IvIva", "89033883323", "ivanov@gmail.com", "Mira str. 3"));
        app.getContactHelper().initContactModification();
        app.getNavigationHelper().returnToMainPage();

    }
}
