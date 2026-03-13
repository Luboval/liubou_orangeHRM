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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmptyArrayAsNullDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {
    private JavaType type;
    private JavaType elementType;

    public EmptyArrayAsNullDeserializer() {
        // Пустой конструктор для Jackson
    }

    public EmptyArrayAsNullDeserializer(JavaType type) {
        this.type = type;
        if (type != null && type.isCollectionLikeType()) {
            this.elementType = type.getContentType();
        }
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
    @SuppressWarnings("unchecked")
    public List<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        // Случай 1: Пустой массив -> пустой список
        if (node.isArray() && node.isEmpty()) {
            return Collections.emptyList();
        }

        // Случай 2: Объект -> список с одним элементом
        if (node.isObject()) {
            return Collections.singletonList(convertElement(p.getCodec(), node));
        }

        // Случай 3: Массив с элементами
        if (node.isArray()) {
            List<Object> result = new ArrayList<>(node.size());
            for (JsonNode element : node) {
                result.add(convertElement(p.getCodec(), element));
            }
            return result;
        }

        // Неожиданный тип
        return Collections.emptyList();
    }
    private Object convertElement(ObjectCodec codec, JsonNode node) throws IOException {
        if (elementType == null) {
            return codec.treeToValue(node, Object.class);
        }

        Class<?> targetClass = elementType.getRawClass();

        // Для record'ов используем readValue
        if (targetClass.isRecord()) {
            return ((ObjectMapper) codec).readValue(
                    ((ObjectMapper) codec).treeAsTokens(node),
                    targetClass
            );
        }

        // Для обычных классов
        return codec.treeToValue(node, targetClass);
    }
}
