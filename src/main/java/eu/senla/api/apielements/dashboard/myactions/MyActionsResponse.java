package eu.senla.api.apielements.dashboard.myactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyActionsResponse {
    private int id;
    private String group;
    private int pendingActionCount;

}
