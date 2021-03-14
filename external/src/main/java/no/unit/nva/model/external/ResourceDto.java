package no.unit.nva.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.net.URI;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonTypeName("Resource")
public class ResourceDto {
    @JsonProperty
    private final URI id;

    public ResourceDto(URI id) {
        this.id = id;
    }

    public URI getId() {
        return id;
    }
}
