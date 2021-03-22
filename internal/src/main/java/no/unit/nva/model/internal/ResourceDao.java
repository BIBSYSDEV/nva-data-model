package no.unit.nva.model.internal;

import no.unit.nva.model.external.ResourceDto;

import static java.util.Objects.nonNull;

public class ResourceDao {

    public ResourceDao(ResourceDto dto) {
        this();
        if (nonNull(dto)) {
            System.out.println("Converting DTO to DAO");
        }
    }

    public ResourceDao() {

    }

    public ResourceDto asDto() {
        return new ResourceDto();
    }
}
