package no.unit.nva.model.internal;

import no.unit.nva.model.external.ResourceDto;
import org.javers.core.JaversBuilder;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MappingTest {

    public static final String ANY_HTTPS_URI = "https://example.org/resource/123";
    public static final int EMPTY = 0;

    @Test
    void dtoIsRoundTrippedBetweenExternalAndInternalModel() {
        var external = new ResourceDto(URI.create(ANY_HTTPS_URI));
        var internal = new ResourceDao(external);
        var actual = internal.asDto();
        var javers = JaversBuilder.javers().build();
        var diff = javers.compare(external, actual);
        assertThat((diff.getChanges().size()), is(equalTo(EMPTY)));
    }
}
