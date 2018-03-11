package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTestsTV extends TestBase {

    @Test
    public void testContactModificationTV (){
        app.getNavigationHelper().returnToMainPage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov", "IvIva", "89033883323", "ivanov@gmail.com", "Mira str. 3","test3"));
        }
        app.getNavigationHelper().returnToMainPage();
        app.getContactHelper().viewContact();
        app.getContactHelper().modifyContact();
        app.getContactHelper().fillContactInf(new ContactData("Ivan", "Ivanovich", "Ivanov", "IvIva", "89033883323", "ivanov@gmail.com", "Mira str. 3",null),false);
        app.getContactHelper().initContactModification();
        app.getNavigationHelper().returnToMainPage();

    }
}
