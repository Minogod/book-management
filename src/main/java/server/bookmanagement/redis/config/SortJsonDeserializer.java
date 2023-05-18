package server.bookmanagement.redis.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public class SortJsonDeserializer extends JsonDeserializer<Sort> {
    @Override
    public Sort deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode rootNode = mapper.readTree(jsonParser);

        boolean empty = rootNode.get("empty").asBoolean();
        boolean unsorted = rootNode.get("unsorted").asBoolean();
        boolean sorted = rootNode.get("sorted").asBoolean();

        if (empty) {
            return Sort.unsorted();
        } else if (unsorted) {
            return Sort.unsorted();
        } else if (sorted) {
            // Handle sorted case if needed
            // You can extract additional information from the JSON if necessary
            // and use Sort.by() method to create the appropriate Sort object
            // For example:
             String property = rootNode.get("property").asText();
             Sort.Direction direction = rootNode.get("direction").asText().equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
             return Sort.by(direction, property);
        } else {
            // Handle other cases or throw an exception if needed
            throw new IllegalArgumentException("Invalid Sort JSON representation");
        }
    }
}
