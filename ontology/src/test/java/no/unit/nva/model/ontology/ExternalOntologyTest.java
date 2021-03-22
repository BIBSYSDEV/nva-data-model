package no.unit.nva.model.ontology;

import no.unit.nva.model.external.ResourceDto;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ExternalOntologyTest extends OntologyTest {
    private static final String ONTOLOGY_FILE = "external_ontology.ttl";
    public static final String ID = "id";
    private Model model;

    @BeforeEach
    void ontologyExists() {
        model = ModelFactory.createDefaultModel();
        RDFDataMgr.read(model, getOntologyFromFile(ONTOLOGY_FILE), Lang.TURTLE);
    }

    @Test
    void everyClassInOntologyIsInExternalModel() {
        var modelClasses = getClassTypeAnnotation(ResourceDto.class);
        var ontologyClasses = getAllClassesInOntology(model);
        Collections.sort(modelClasses);
        Collections.sort(ontologyClasses);
        assertThat(modelClasses, is(equalTo(ontologyClasses)));
    }

    @Test
    void everyPropertyInOntologyIsInExternalModel() {
        var exclusions = List.of(ID);
        var modelFields = extractClassFields(ResourceDto.class, exclusions);
        var ontologyProperties = getAllPropertiesInOntology(model);
        Collections.sort(modelFields);
        Collections.sort(ontologyProperties);
        assertThat(modelFields, is(equalTo(ontologyProperties)));
    }
}
