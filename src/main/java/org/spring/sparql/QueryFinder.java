package org.spring.sparql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class QueryFinder {
    public static String find(String name){
        if (name == null || name.length() == 0)
            throw new IllegalArgumentException("Query name can't be null or empty string");
        String path = "src/main/java/org/spring/sparql/constants/queries/" + name + ".rdf";
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
