package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTV extends TestBase {

    @Test
    public void contactDeletionTV() {

        app.getContactHelper().viewContact();
        app.getContactHelper().modifyContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().returnToMainPage();
    }
}
