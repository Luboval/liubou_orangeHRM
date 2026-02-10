package eu.senla.api.createemployee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateEmployeeResponseData {
   private int empNumber;
   private String lastName;
   private String firstName;
   private String middleName;
   private String employeeId;
}
