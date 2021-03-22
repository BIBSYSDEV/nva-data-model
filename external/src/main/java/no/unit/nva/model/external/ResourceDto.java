package no.unit.nva.model.external;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import nva.commons.core.JacocoGenerated;

import java.net.URI;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonTypeName("Resource")
public class ResourceDto {
    @JsonProperty
    private final URI id;

    @JsonCreator
    public ResourceDto(@JsonProperty("id") URI id) {
        this.id = id;
    }

    public URI getId() {
        return id;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceDto)) {
            return false;
        }
        ResourceDto that = (ResourceDto) o;
        return Objects.equals(getId(), that.getId());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
