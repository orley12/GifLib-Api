package com.teamtreehouse.category;


import com.teamtreehouse.gif.Gif;
import com.teamtreehouse.core.BaseEntity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity {

    @NotNull
    @Size(min = 3, max = 12)
    private String name;

    @NotNull
    @Pattern(regexp = "#[0-9a-fA-F]{6}")
    private String colorCode;

    @OneToMany(mappedBy = "category")
    private List<Gif> gifs = new ArrayList<>();

    public Category() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public List<Gif> getGifs() {
        return gifs;
    }

    public void setGifs(List<Gif> gifs) {
        this.gifs = gifs;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", gifs=" + gifs +
                '}';
    }

}
