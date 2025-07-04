package eu.senla.management.loginstrategy;

import eu.senla.pages.login.LoginPage;

public class UiLoginStrategy implements LoginStrategy {

    @Override
    public void login() {
        new LoginPage()
                .visitLoginPage()
                .loginWithValidCredentials();
    }
}
