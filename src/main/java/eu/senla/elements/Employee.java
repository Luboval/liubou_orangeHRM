package eu.senla.elements;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Employee {
    private String firstName;
    private String middleName;
    private String lastName;
    private String userName;
    private String password;


//    public String getEmployeeFirstName() {
//        return this.firstName;
//    }
//
//    public String getEmployeeMiddleName() {
//        return this.middleName;
//    }
//    public String getEmployeeLastName() {
//        return this.lastName;
//    }
//    public String getEmployeeUserName() {
//        return this.userName;
//    }
//    public String getEmployeePassword() {
//        return this.password;
//    }


}
