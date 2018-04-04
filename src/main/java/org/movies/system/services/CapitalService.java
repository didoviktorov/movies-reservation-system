package org.movies.system.services;

import org.movies.system.models.entities.Capital;

public interface CapitalService {

    Long capitalCount();

    void save(Capital capital);

    void seedCapitals();
}
