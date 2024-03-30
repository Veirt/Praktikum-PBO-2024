package posttest4;

import java.util.ArrayList;

import de.vandermeer.asciitable.AsciiTable;

public class AnimeMovie extends Anime {
    String airingDate;

    AnimeMovie(String title, String studio, ArrayList<Genre> genres, String airingDate) {
        super(title, studio, genres);
        this.airingDate = airingDate;
    }

    @Override
    public void displayRow(AsciiTable at, int num) {
        at.addRow(num, title, studio, 1, airingDate, Genre.join(", ", genres));
    }

}
