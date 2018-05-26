package org.spring.sparql.constants;

public class Queries {
    public static final String GET_ARTIST_HOMETOWN_QUERY ="PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX dbp: <http://dbpedia.org/resource/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "\n" +
            "select ?band ?hometown\n" +
            "where {\n" +
            "\t?band foaf:name \"%s\"@en.\n" +
            "        ?band dbo:hometown ?hometown\n" +
            "}\n" +
            "\n" +
            "\t";

    public static final String GET_ARTIST_HOMETOWN_LOCATION_QUERY = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX dbp: <http://dbpedia.org/resource/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "\n" +
            "select ?band ?hometown ?lat ?lon\n" +
            "where {\n" +
            "\t?band foaf:name \"%s\"@en.\n" +
            "        ?band dbo:hometown ?hometown.\n" +
            "        ?hometown geo:lat ?lat.\n" +
            "        ?hometown geo:long ?lon.\n" +
            "\n" +
            "\n" +
            "}";

    public static final String GET_ARTISTS_HOMETOWNS_QUERY = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX dbp: <http://dbpedia.org/resource/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "\n" +
            "select  ?band\n" +
            "where {\n" +
            "       values (?value) { ?s }\n" +
            "\t?band foaf:name ?value.\n" +
            "        ?band dbo:hometown ?hometown.\n" +
            "}";
}
