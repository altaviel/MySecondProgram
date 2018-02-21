package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTestChB extends TestBase {

    @Test
    public void testContactDeletionChB() {

        app.getContactHelper().checkContact();
        app.getContactHelper().deletebyChb();
        app.getContactHelper().closeDialog();
        app.getNavigationHelper().returnToMainPage();

    }
}
