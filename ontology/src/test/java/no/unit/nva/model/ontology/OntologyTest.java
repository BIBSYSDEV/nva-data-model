package no.unit.nva.model.ontology;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OntologyTest {

    protected List<String> getClassTypeAnnotation(Class<?> type) {
        return Arrays.stream(type.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType().equals(JsonTypeName.class))
                .map(annotation -> (JsonTypeName) annotation)
                .map(JsonTypeName::value)
                .collect(Collectors.toList());
    }

    protected List<String> getAllClassesInOntology(Model model) {
        return model.listSubjects().toList().stream()
                .map(Resource::getURI)
                .map(URI::create)
                .map(URI::getFragment)
                .filter(this::isClass)
                .collect(Collectors.toList());
    }

    protected boolean isProperty(String candidate) {
        var initial = candidate.toCharArray()[0];
        return Character.isLowerCase(initial);
    }

    protected boolean isClass(String candidate) {
        return !isProperty(candidate);
    }

    protected List<String> extractClassFields(Class<?> type) {
        return extractClassFields(type, Collections.emptyList());
    }

    protected List<String> extractClassFields(Class<?> type, List<String> exclusions) {
        return Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(JsonProperty.class))
                .map(Field::getName)
                .filter(string -> !exclusions.contains(string))
                .collect(Collectors.toList());
    }

    protected List<String> getAllPropertiesInOntology(Model model) {
        return model.listSubjects().toList().stream()
                .map(Resource::getURI)
                .map(URI::create)
                .map(URI::getFragment)
                .filter(this::isProperty)
                .collect(Collectors.toList());
    }

    protected InputStream getOntologyFromFile(String file) {
        return InternalOntologyTest.class.getClassLoader().getResourceAsStream(file);
    }
}
