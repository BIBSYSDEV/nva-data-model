package no.unit.nva.model.client;

import no.unit.nva.model.external.ResourceDto;

import java.net.URI;

public final class ResourceDtoBuilder {
    private URI id;

    public ResourceDtoBuilder() {
    }

    public static ResourceDtoBuilder resourceDto() {
        return new ResourceDtoBuilder();
    }

    public ResourceDtoBuilder withId(URI id) {
        this.id = id;
        return this;
    }

    public ResourceDto build() {
        return new ResourceDto(id);
    }
}
