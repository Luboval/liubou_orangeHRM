package eu.senla.tests.admin;

import eu.senla.elements.UserManagementTable;
import eu.senla.elements.api.users.UsersRoot;
import eu.senla.management.common.Constants;
import eu.senla.management.common.RequestManager;
import eu.senla.management.utils.table.Table;
import eu.senla.management.utils.table.TableContents;
import eu.senla.pages.adminpages.UserManagementAdminPage;
import eu.senla.tests.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static eu.senla.management.common.SpecConfig.requestSpecification;
import static eu.senla.management.common.SpecConfig.responseSpecification;
import static eu.senla.management.utils.ReadFromJsonResponse.getList;

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

    @Test
    public void getByUserRoleAndStatusJsonParserTest() throws IOException {
        log.info("Start get json parser with parameters Validation");

        ValidatableResponse response = RequestManager.getRequest(
                requestSpecification(),
                Constants.GET_USERS_API_POINT,
                Map.of("limit",50,
                        "offset", 0,
                        "sortField", "u.userName",
                        "sortOrder", "ASC")
        );

        JsonPath parsedResponse = new JsonPath(response.extract().asString());

//        UserManagementAdminPage adminPage = new UserManagementAdminPage()
//                .switchToUserManagementPage()
//                .executeSearchByUserRoleAndStatus();

        List<Integer> empNumber = parsedResponse.getList("data.employee.empNumber");



        Assert.assertTrue(empNumber.contains(109));

        List<Integer> empNum = (List<Integer>) getList(response, "data.employee.empNumber");



        Assert.assertListContains(empNum, n -> n.equals(109), "Содержит 109");

    }

    @Test
    public void checkTableTest() {

        UserManagementAdminPage adminPage = new UserManagementAdminPage()
                .switchToUserManagementPage();

        TableContents<UserManagementTable> expected = new TableContents<>(
              Arrays.asList("", "Username", "User Role", "Employee Name", "Status", "Actions"),
              Arrays.asList(
                      new UserManagementTable(
                      "",
                      "Admin",
                      "Admin",
                      "Karthik Pandrangi",
                      "Enabled", "")
              )
        );


        Table<UserManagementTable> actual = adminPage.userManagementAdminPageTable();

        //Assert.assertEquals(actual.getContents(), expected);

        Assert.assertEquals(actual.getContents(), expected.describeDiff(actual.getContents()));

    }

    @Test
    public void checkTableAndApiResponse() {
        ValidatableResponse response = RequestManager.getRequest(
                requestSpecification(),
                Constants.GET_USERS_API_POINT,
                Map.of("limit",50,
                        "offset", 0,
                        "sortField", "u.userName",
                        "sortOrder", "ASC")
        );


        UserManagementAdminPage adminPage = new UserManagementAdminPage()
                .switchToUserManagementPage();

        Table<UserManagementTable> actual = adminPage.userManagementAdminPageTable();

        List<Map<String, Object>> responseRows = response.extract()
                .jsonPath()
                .getList("data");

        List<UserManagementTable> tableList = responseRows.stream()
                .map(u -> {
                    String userName = (String) u.get("userName");
                    String userRole = ((Map<String, Object>) u.get("userRole")).get("displayName").toString();
                    Map<String, Object> info = (Map<String, Object>) u.get("employee");
                    String firstName = (String) info.get("firstName");
                    String lastName = (String) info.get("lastName");
                    String employeeName = firstName + " " + lastName;

                    Boolean statusBool = (Boolean) u.get("status");
                    String status = statusBool ? "Enabled" : "Disabled";

                    return new UserManagementTable("", userName, userRole, employeeName, status, "");
                })
                .collect(Collectors.toList());

        //через jakson
        //   String json = response.extract().asString();
        //   ObjectMapper mapper = new ObjectMapper();
        //   JsonNode root = mapper.readTree(json);
        //   List<UserManagementTable> users = new ArrayList<>();
        //   for (JsonNode node : root.path("data")) {
        //   String userName = node.path("userName").asText();
        //   String displayName = node.path("userRole").path("displayName").asText();
        //   String firstName = node.path("employee").path("firstName").asText();
        //   String lastName = node.path("employee").path("lastName").asText();
        //   String fullName = firstName + " " + lastName;
        //   String status = node.path("status").asBoolean() ? "Enabled" : "Disabled";
        //   UserManagementTable tableRow = new UserManagementTable(userName, displayName, fullName, status); users.add(tableRow);
        //   }


        TableContents<UserManagementTable> expectedFromApi = new TableContents<>(
                Arrays.asList("", "Username", "User Role", "Employee Name", "Status", "Actions"),
                tableList

        );

        Assert.assertEquals(actual.getContents(), expectedFromApi);


    }
 }
