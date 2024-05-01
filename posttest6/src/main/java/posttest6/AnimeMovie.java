package posttest6;

import java.util.ArrayList;

import de.vandermeer.asciitable.AsciiTable;

// Final pada Class
public final class AnimeMovie extends Anime implements Displayable {
    String airingDate;

    AnimeMovie(String title, String studio, ArrayList<Genre> genres, String airingDate) {
        super(title, studio, genres);
        this.airingDate = airingDate;
    }

    public void display(int num) {
        System.out.println(num + ". " + title);
        System.out.println("Studio: " + studio);
        System.out.println("Airing Date: " + airingDate);
        System.out.println("Genres: " + Genre.join(", ", genres));
        System.out.println("===================================");
    }

    @Override
    public void displayRow(AsciiTable at, int num) {
        at.addRow(num, title, studio, 1, airingDate, Genre.join(", ", genres));
    }

}
