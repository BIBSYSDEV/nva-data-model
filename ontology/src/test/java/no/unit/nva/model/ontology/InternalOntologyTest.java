package no.unit.nva.model.ontology;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import no.unit.nva.model.external.ResourceDto;
import no.unit.nva.model.internal.ResourceDao;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class InternalOntologyTest extends OntologyTest {
    private static final String ONTOLOGY_FILE = "internal_ontology.ttl";
    private Model model;

    @BeforeEach
    void ontologyExists() {
        model = ModelFactory.createDefaultModel();
        RDFDataMgr.read(model, getOntologyFromFile(ONTOLOGY_FILE), Lang.TURTLE);
    }

    @Test
    void everyClassInOntologyIsInInternalModel() {
        var modelClasses = getClassTypeAnnotation(ResourceDao.class);
        var ontologyClasses = getAllClassesInOntology(model);
        Collections.sort(modelClasses);
        Collections.sort(ontologyClasses);
        assertThat(modelClasses, is(equalTo(ontologyClasses)));
    }

    @Test
    void everyPropertyInOntologyIsInInternalModel() {
        var modelFields = extractClassFields(ResourceDao.class);
        var ontologyProperties = getAllPropertiesInOntology(model);
        Collections.sort(modelFields);
        Collections.sort(ontologyProperties);
        assertThat(modelFields, is(equalTo(ontologyProperties)));
    }
}
