package eu.senla.tests.admin;

import eu.senla.elements.api.users.UsersRoot;
import eu.senla.management.common.Constants;
import eu.senla.management.common.RequestManager;
import eu.senla.pages.adminpages.UserManagementAdminPage;
import eu.senla.tests.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static eu.senla.management.common.SpecConfig.requestSpecification;
import static eu.senla.management.common.SpecConfig.responseSpecification;

@Slf4j
public class UserManagementTest extends BaseTest {

    @Test
    public void getByUserRoleAndStatusTest() {
        log.info("Start get with parameters Validation");

        UsersRoot response = RequestManager.getRequestWithQueryParameters(
                requestSpecification(),
                responseSpecification(),
                Constants.GET_USERS_API_POINT,
                Map.of("limit",50,
                        "offset", 0,
                        "sortField", "u.userName",
                        "sortOrder", "ASC",
                        "userRoleId", 1,
                        "status", 1),
                UsersRoot.class
        );

        UserManagementAdminPage adminPage = new UserManagementAdminPage()
                .switchToUserManagementPage()
                .executeSearchByUserRoleAndStatus();

        Assert.assertEquals(response.getMeta().getTotal(), adminPage.getRecordFound());

    }
 }
