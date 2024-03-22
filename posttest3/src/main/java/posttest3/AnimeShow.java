package posttest3;

import java.util.ArrayList;

public class AnimeShow extends Anime {
    int episode;

    AnimeShow(String title, String studio, ArrayList<Genre> genres, int episode, Season season) {
        super(title, studio, genres);
        this.episode = episode;
        this.season = season;
    }
}
