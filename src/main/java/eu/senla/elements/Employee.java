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

}
