package org.spring.sparql.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class QueryFinder {

    @Value("${app.queries.path}")
    private String path;

    public String find(String name){
        if (name == null || name.length() == 0)
            throw new IllegalArgumentException("Query name can't be null or empty string");
        try {
            return new String(Files.readAllBytes(Paths.get(path+name)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
