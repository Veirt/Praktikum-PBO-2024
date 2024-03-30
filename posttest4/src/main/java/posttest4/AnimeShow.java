package posttest4;

import java.util.ArrayList;

import de.vandermeer.asciitable.AsciiTable;

public class AnimeShow extends Anime {
    int episode;

    AnimeShow(String title, String studio, ArrayList<Genre> genres, int episode, Season season) {
        super(title, studio, genres);
        this.episode = episode;
        this.season = season;
    }

    @Override
    public void displayRow(AsciiTable at, int num) {
        at.addRow(num, title, studio, episode, season.toString(), Genre.join(", ", genres));
    }
}
