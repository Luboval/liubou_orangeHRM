package eu.senla.api.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class EmptyArrayAsNullDeserializer extends JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        // Если это пустой массив - возвращаем null
        if (node.isArray() && node.isEmpty()) {
            return null;
        }

        // Иначе десериализуем как обычный объект
        // Нам нужно знать целевой тип, чтобы десериализовать
        Class<?> targetType = ctxt.getContextualType().getRawClass();
        return p.getCodec().treeToValue(node, targetType);
    }
}
