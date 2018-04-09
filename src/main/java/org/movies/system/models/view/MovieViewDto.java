package org.movies.system.models.view;

public class MovieViewDto {

    private String posterUrl;

    private String title;

    private String description;

    private Long duration;

    private String embedCode;

    public MovieViewDto() {
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

    public String getEmbedCode() {
        return this.embedCode;
    }

    public void setEmbedCode(String embedCode) {
        this.embedCode = embedCode;
    }
}
