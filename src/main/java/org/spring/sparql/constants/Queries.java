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
}
