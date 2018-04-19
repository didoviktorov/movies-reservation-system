package org.movies.system.areas.cinemas.entities;

import org.hibernate.annotations.GenericGenerator;
import org.movies.system.areas.halls.entities.Hall;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cinemas")
public class Cinema {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String name;

    private String imageUrl;

    private String address;

    @ManyToMany
    @JoinTable(
            name = "cinemas_halls",
            joinColumns = @JoinColumn(name = "cinema_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "hall_id", referencedColumnName = "id")
    )
    private Set<Hall> halls;

    public Cinema() {
        this.halls = new LinkedHashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Hall> getHalls() {
        return halls;
    }

    public void setHalls(Set<Hall> halls) {
        this.halls = halls;
    }
}
