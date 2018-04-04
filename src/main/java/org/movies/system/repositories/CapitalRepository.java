package org.movies.system.repositories;

import org.movies.system.models.entities.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, String> {

    Capital findFirstByName(String name);

    @Query(value = "SELECT c.name FROM capitals AS c",
            nativeQuery = true)
    List<String> capitalsNames();
}
