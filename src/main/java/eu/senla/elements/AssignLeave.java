package eu.senla.elements;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AssignLeave {
    private String employeeName;
    private String fromDate;
    private String toDate;
}
