package org.movies.system.areas.movies.models.binding;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MovieBinding {

    @NotEmpty(message = "Add Poster Url")
    private String posterUrl;

    @NotEmpty(message = "You need to add movie title")
    private String title;

    @NotEmpty(message = "You need to add some description")
    private String description;

    @NotNull(message = "You must add duration")
    @Min(value = 60, message = "Min duration is about 60 minutes")
    @Max(value = 180, message = "Max duration is about 180 minutes")
    private Long duration;

    private String trailerUrl;

    public MovieBinding() {
    }

    public String getPosterUrl() {
        return this.posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getTrailerUrl() {
        return this.trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
}
