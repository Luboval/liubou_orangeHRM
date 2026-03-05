package eu.senla.api.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class EmptyArrayAsNullDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {
    private JavaType type;

    public EmptyArrayAsNullDeserializer() {
        // Пустой конструктор для Jackson
    }

    public EmptyArrayAsNullDeserializer(JavaType type) {
        this.type = type;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt,
                                                BeanProperty property) {
        JavaType contextualType = ctxt.getContextualType();

        // Если контекстный тип null, пробуем получить из property
        if (contextualType == null && property != null) {
            contextualType = property.getType();
        }

        // Если всё ещё null, используем тип из контекста
        if (contextualType == null) {
            contextualType = ctxt.getContextualType();
        }

        return new EmptyArrayAsNullDeserializer(contextualType);
    }


    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        // Случай 1: Если это пустой массив - возвращаем null
        if (node.isArray() && node.isEmpty()) {
            return null;
        }

        // Случай 2: Объект -> преобразуем в массив с одним элементом
        if (node.isObject()) {
            // Создаем новый массив
            ObjectCodec codec = p.getCodec();
            if (codec instanceof ObjectMapper) {
                ObjectMapper mapper = (ObjectMapper) codec;
                ArrayNode arrayNode = mapper.createArrayNode(); // Здесь используем ObjectMapper
                arrayNode.add((ObjectNode) node);

                // Десериализуем как массив
                if (type != null) {
                    return codec.treeToValue(arrayNode, type.getRawClass());
                }
            }
        }

        // Случай 3: Обычный массив
        if (node.isArray()) {
            if (type != null) {
                return p.getCodec().treeToValue(node, type.getRawClass());
            }
        }

        // Fallback
        return p.getCodec().treeToValue(node, Object.class);
    }
}
