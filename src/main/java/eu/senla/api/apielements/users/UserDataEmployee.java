package eu.senla.api.apielements.users;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDataEmployee {
    private int empNumber;
    private String employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Object terminationId;
}
