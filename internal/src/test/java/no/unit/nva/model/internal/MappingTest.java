package no.unit.nva.model.internal;

import no.unit.nva.model.external.ResourceDto;
import org.javers.core.JaversBuilder;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MappingTest {
    @Test
    void mainTitleIsRoundTrippedBetweenExternalAndInternalModel() {
        var external = new ResourceDto(URI.create("https://example.org/resource/123"));
        var internal = new ResourceDao(external);
        var actual = internal.asDto();
        var javers = JaversBuilder.javers().build();
        var diff = javers.compare(external, actual);
        assertThat((diff.getChanges().size()), is(equalTo(0)));
    }
}
