package eu.senla.api.apielements.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private int id;
    private String userName;
    private boolean deleted;
    private boolean status;
    private UserDataEmployee employee;
    private UserDataRole userRole;
}
