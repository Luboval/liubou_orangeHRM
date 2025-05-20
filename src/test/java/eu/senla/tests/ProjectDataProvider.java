package eu.senla.tests;

import net.bytebuddy.utility.RandomString;
import org.testng.annotations.DataProvider;

public class ProjectDataProvider {

    @DataProvider(name = "getIncorrectCredentials")
    public static Object[][] getCredentials() {
        return new Object[][] {
                {"Admin", RandomString.make()},
                {RandomString.make(), "admin123"},
                {RandomString.make(), RandomString.make()}
        };
    }

}
