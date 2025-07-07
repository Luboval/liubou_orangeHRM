package eu.senla.elements;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Candidate {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String contactNumber;
}
