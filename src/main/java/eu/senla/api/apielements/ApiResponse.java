package eu.senla.api.apielements;

import java.util.List;

public record ApiResponse<T>(
        List<T> data,
        Meta meta,
        List<Rels> rels
) {

}
