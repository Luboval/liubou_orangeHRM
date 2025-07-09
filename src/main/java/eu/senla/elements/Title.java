package eu.senla.elements;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Title {
    private String jobTitle;
    private String jobDescription;
    private String note;

}
