package eu.senla.api.apielements.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersRoot {
    private ArrayList<UserData> data;
    private UserMeta meta;
    private ArrayList<Object> rels;
}
