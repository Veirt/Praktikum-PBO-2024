package posttest3;

import java.util.ArrayList;

public class AnimeMovie extends Anime {
    String airingDate;

    AnimeMovie(String title, String studio, ArrayList<Genre> genres, String airingDate) {
        super(title, studio, genres);
        this.airingDate = airingDate;
    }
}
