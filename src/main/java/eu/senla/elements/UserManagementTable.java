package eu.senla.elements;


import eu.senla.management.utils.table.DomainBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserManagementTable extends DomainBase {
    private String checkbox;
    private String userName;
    private String userRole;
    private String employeeName;
    private String status;
    private String actions;


    @Override
    public String toString() {
        return "new UserManagementTable("+checkbox + ", \"" + userName + ", \"" + userRole + ", \"" +employeeName + ", \"" +status + ", \"" + actions + "\")\n";
    }

}
