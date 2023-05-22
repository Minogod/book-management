package server.bookmanagement.config.redis.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public class PageImplJsonDeserializer extends JsonDeserializer<PageImpl<?>> {
    @Override
    public PageImpl<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode rootNode = mapper.readTree(jsonParser);

        // JSON 데이터에서 필요한 정보를 추출합니다.
        JsonNode contentNode = rootNode.get("content");
        JsonNode pageableNode = rootNode.get("pageable");
//        JsonNode totalPagesNode = rootNode.get("totalPages");
//        JsonNode totalElementsNode = rootNode.get("totalElements");
//        JsonNode lastNode = rootNode.get("last");
//        JsonNode sizeNode = rootNode.get("size");
//        JsonNode numberNode = rootNode.get("number");
        JsonNode sortNode = rootNode.get("sort");
        JsonNode numberOfElementsNode = rootNode.get("numberOfElements");
//        JsonNode firstNode = rootNode.get("first");
//        JsonNode emptyNode = rootNode.get("empty");

        // 필요한 정보를 사용하여 Pageable 인스턴스를 생성합니다.
        Pageable pageable = mapper.treeToValue(pageableNode, Pageable.class);

        // contentNode를 사용하여 원하는 타입의 List를 생성합니다.
        List<?> content = mapper.readValue(contentNode.traverse(mapper), new TypeReference<List<?>>(){});
        Sort sort = mapper.treeToValue(sortNode, Sort.class);
        // 필요한 정보를 사용하여 PageImpl 인스턴스를 생성합니다.
        return new PageImpl<>(content, pageable, numberOfElementsNode.asLong());
    }
}
