package org.movies.system.services;

import org.movies.system.models.entities.City;

public interface CityService {

    Long cityCount();

    void save(City city);

    void seedCities();
}
