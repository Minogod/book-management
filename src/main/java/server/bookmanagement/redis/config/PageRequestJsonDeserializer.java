package server.bookmanagement.redis.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.IOException;

public class PageRequestJsonDeserializer extends JsonDeserializer<PageRequest> {
    @Override
    public PageRequest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode rootNode = mapper.readTree(jsonParser);

        // JSON 데이터에서 필요한 정보를 추출합니다.
        JsonNode pageNode = rootNode.get("pageNumber");
        JsonNode sizeNode = rootNode.get("pageSize");
        JsonNode sortNode = rootNode.get("sort");

        // 필요한 정보를 사용하여 PageRequest 인스턴스를 생성합니다.
        int page = pageNode.asInt();
        int size = sizeNode.asInt();
        Sort sort = mapper.treeToValue(sortNode, Sort.class);

        return PageRequest.of(page, size, sort);

    }
}
