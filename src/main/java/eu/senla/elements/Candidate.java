package eu.senla.elements;

import eu.senla.management.utils.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    @Column("firstName")
    private String firstName;
    @Column("middleName")
    private String middleName;
    @Column("lastName")
    private String lastName;
    @Column("email")
    private String email;
    @Column("contactNumber")
    private String contactNumber;
    @Column("filePath")
    private String filePath;

    @Override
    public String toString() {
        return "Candidate { "
                + firstName
                + " mn " + middleName
                + " ln " + lastName
                + " email " + email
                + " contact " + contactNumber
                + " file " + filePath
                + " }";
    }


}
