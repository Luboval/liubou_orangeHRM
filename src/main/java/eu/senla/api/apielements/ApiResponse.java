package eu.senla.api.apielements;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import eu.senla.api.utils.EmptyArrayAsNullDeserializer;

import java.util.List;


public record ApiResponse<T, V>(
        @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY) //to allow using single value without []
        List<T> data,
        @JsonDeserialize(using = EmptyArrayAsNullDeserializer.class)
        @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        List<V> meta,
        List<Rels> rels
) {

}
