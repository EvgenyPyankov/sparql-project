package org.spring.sparql.services;

import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.sparql.SPARQLRepository;

public class SPARQLService {
    private SPARQLRepository repo;

    public SPARQLService(String url) {
        repo = new SPARQLRepository(url);
        repo.initialize();
    }

    public TupleQueryResult select(String query) throws Exception {
        RepositoryConnection conn = repo.getConnection();

        TupleQueryResult result =
                conn.prepareTupleQuery(QueryLanguage.SPARQL, query).evaluate();

        return result;

    }


}
