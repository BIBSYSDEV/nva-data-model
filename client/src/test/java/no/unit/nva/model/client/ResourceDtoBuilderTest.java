package no.unit.nva.model.client;


import no.unit.nva.model.external.ResourceDto;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ResourceDtoBuilderTest {

    public static final URI ANY_ID = URI.create("https://example.org/resource/anyId");

    @Test
    void resourceDtoBuilderReturnsResourceDtoWhenInputIsValid() {
        var actual = ResourceDtoBuilder.resourceDto().withId(ANY_ID).build();
        var expected = new ResourceDto(ANY_ID);
        assertThat(actual, equalTo(expected));
    }
}