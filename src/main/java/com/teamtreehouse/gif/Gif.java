package com.teamtreehouse.gif;


import com.teamtreehouse.category.Category;
import com.teamtreehouse.core.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class Gif extends BaseEntity {

    private LocalDateTime dateUploaded = LocalDateTime.now();

    private String description;

    private String url;

    @ManyToOne
    @NotNull
    private Category category;

    private String username = "You";

    private boolean favorite;

    private String hash;

    public Gif() {
        super();
    }

    public LocalDateTime getDateUploaded() {
        return dateUploaded;
    }

    public String getTimeSinceUploaded() {
        String unit = "";
        LocalDateTime now = LocalDateTime.now();
        long diff;
        if ((diff = ChronoUnit.SECONDS.between(dateUploaded, now)) < 60) {
            unit = "secs";
        } else if ((diff = ChronoUnit.MINUTES.between(dateUploaded, now)) < 60) {
            unit = "mins";
        } else if ((diff = ChronoUnit.HOURS.between(dateUploaded, now)) < 24) {
            unit = "hours";
        } else if ((diff = ChronoUnit.DAYS.between(dateUploaded, now)) < 30) {
            unit = "days";
        } else if ((diff = ChronoUnit.MONTHS.between(dateUploaded, now)) < 12) {
            unit = "months";
        } else {
            diff = ChronoUnit.YEARS.between(dateUploaded, now);
        }
        return String.format("%d %s", diff, unit);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDateUploaded(LocalDateTime dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
