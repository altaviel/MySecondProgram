package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void contactDelete() {
        app.getContactHelper().editContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().returnToMainPage();
    }

}
