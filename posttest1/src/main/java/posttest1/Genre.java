package posttest1;

import java.util.ArrayList;

public class Genre {
    String name;
    String descrpition;

    public static Genre[] genres = {
            new Genre("Comedy", "Genre yang mengandung unsur humor."),
            new Genre("Action", "Genre pertarungan dan aksi."),
            new Genre("Adventure", "Genre petualangan."),
            new Genre("Drama", "Genre yang mengandung unsur konflik."),
            new Genre("Fantasy", "Genre yang mengandung unsur fantasi."),
            new Genre("Romance", "Genre yang mengandung unsur percintaan."),
            new Genre("Sci-Fi", "Genre yang menunjukkan fiksi sains.") };

    Genre(String name, String description) {
        this.name = name;
        this.descrpition = description;
    }

    public String toString() {
        return this.name;
    }

    public static String join(String delimiter, ArrayList<Genre> genres) {
        return String.join(
                delimiter,
                genres.stream().map(Genre::toString).toArray(String[]::new));
    }
}
