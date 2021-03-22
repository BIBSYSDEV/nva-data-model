package no.unit.nva.model.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.external.ResourceDto;
import nva.commons.core.JsonUtils;
import org.javers.core.JaversBuilder;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ResourceDaoMappingTest {
    public static final ObjectMapper OBJECT_MAPPER = JsonUtils.objectMapper;

    public static final String ANY_HTTPS_URI = "https://example.org/resource/123";
    public static final int EMPTY = 0;
    public static final String ANY_IDENTIFIER = "anyIdentifier";


    @Test
    void dtoIsRoundTrippedBetweenExternalAndInternalModel() {
        var external = new ResourceDto(URI.create(ANY_HTTPS_URI));
        var internal = new ResourceDao(external);
        var actual = internal.asDto();
        var javers = JaversBuilder.javers().build();
        var diff = javers.compare(external, actual);
        assertThat((diff.getChanges().size()), is(equalTo(EMPTY)));
    }

    @Test
    void resourceDaoIsRoundtrippableFromJson() throws JsonProcessingException {
        var external = new ResourceDao(ANY_IDENTIFIER);
        var json = OBJECT_MAPPER.writeValueAsString(external);
        var actual = OBJECT_MAPPER.readValue(json, ResourceDao.class);
        assertThat(actual, is(equalTo(external)));
    }
}
