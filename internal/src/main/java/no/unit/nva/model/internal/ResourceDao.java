package no.unit.nva.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import no.unit.nva.model.external.ResourceDto;

import java.net.URI;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonTypeName("Resource")
public class ResourceDao {

    public static final String PATH_SEPARATOR = "/";
    public static final int BEGIN_INDEX = 0;
    @JsonProperty
    private final String identifier;
    private final URI base;

    public ResourceDao(ResourceDto dto) {
        this(extractBaseUri(dto.getId()), extractIdentifier(dto.getId()));
    }

    public ResourceDao(URI base, String identifier) {
        this.base = base;
        this.identifier = identifier;
    }

    public ResourceDto asDto() {
        var id = URI.create(base.toString() + identifier);
        return new ResourceDto(id);
    }

    private static URI extractBaseUri(URI id) {
        var uri = id.toString();
        return URI.create(uri.substring(BEGIN_INDEX, getLastPathSeparatorPosition(uri)));
    }

    private static String extractIdentifier(URI id) {
        var path = id.getPath();
        return path.substring(getLastPathSeparatorPosition(path));
    }

    private static int getLastPathSeparatorPosition(String uri) {
        return uri.lastIndexOf(PATH_SEPARATOR) + 1;
    }
}
