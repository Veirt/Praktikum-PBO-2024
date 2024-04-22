package posttest5;

import java.util.ArrayList;

public class Genre {
    private String name;
    private String descrpition;

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

    public static ArrayList<Genre> getGenreObjects(String... genreNames) {
        ArrayList<Genre> genres = new ArrayList<>();
        for (String genreName : genreNames) {
            for (Genre genre : Genre.genres) {
                if (genre.getName().equalsIgnoreCase(genreName)) {
                    genres.add(genre);
                    break;
                }
            }
        }

        return genres;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.descrpition;
    }

    public void setDescription(String description) {
        this.descrpition = description;
    }

    public static String join(String delimiter, ArrayList<Genre> genres) {
        return String.join(
                delimiter,
                genres.stream().map(Genre::getName).toArray(String[]::new));
    }
}
