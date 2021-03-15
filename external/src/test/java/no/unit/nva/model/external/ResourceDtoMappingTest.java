package no.unit.nva.model.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nva.commons.core.JsonUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.UUID;

public class ResourceDtoMappingTest {
    public static final String BASE_PATH = "https://example.org/resource/";
    public static final ObjectMapper OBJECT_MAPPER = JsonUtils.objectMapper;

    @Test
    void externalResourceIsMappableFromJson() throws JsonProcessingException {
        var identifier = UUID.randomUUID().toString();
        var id = URI.create(BASE_PATH + identifier);
        var resourceDto = new ResourceDto(id);
        var json = OBJECT_MAPPER.writeValueAsString(resourceDto);
        var actual = OBJECT_MAPPER.readValue(json, ResourceDto.class);
        MatcherAssert.assertThat(actual, Matchers.equalTo(resourceDto));
    }
}
