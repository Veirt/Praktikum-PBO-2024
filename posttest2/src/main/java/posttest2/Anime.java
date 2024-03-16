package posttest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;

public class Anime {
    String title;
    String studio;
    int episode;
    Season season;
    ArrayList<Genre> genres;

    Anime(String title, String studio, int episode, Season season,
            ArrayList<Genre> genres) {
        this.title = title;
        this.studio = studio;
        this.episode = episode;
        this.season = season;
        this.genres = genres;
    }

    public static Anime createInteractive() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Masukkan judul anime: ");
        String title = reader.readLine();

        System.out.print("Masukkan studio anime: ");
        String studio = reader.readLine();

        int episode;
        while (true) {
            System.out.print("Masukkan jumlah episode: ");
            try {
                episode = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                Utils.enterToContinue("Masukkan episode berupa angka.");
                continue;
            }

            break;
        }

        Season season = inputSeasonInteractive();

        ArrayList<Genre> genres = inputGenresInteractive();

        Anime anime = new Anime(title, studio, episode, season, genres);
        return anime;
    }

    public static void seasonMenu() {
        AsciiTable at = new AsciiTable();
        at.getRenderer().setCWC(new CWC_LongestLine());

        at.addRule();
        at.addRow("No", "Season");
        at.addRule();
        at.addRow("1", "Winter");
        at.addRow("2", "Spring");
        at.addRow("3", "Summer");
        at.addRow("4", "Fall");
        at.addRule();

        System.out.println(at.render());
    }

    public static Season inputSeasonInteractive() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Season season = new Season();

        while (true) {
            seasonMenu();
            System.out.print("Season: ");
            String inputSeason = reader.readLine();

            try {
                season.setSeason(inputSeason);
            } catch (IllegalArgumentException e) {
                Utils.enterToContinue(e.getMessage());
                continue;
            }

            break;
        }

        // year.
        while (true) {
            System.out.print("Masukkan tahun anime: ");
            try {
                season.setYear(Integer.parseInt(reader.readLine()));
            } catch (IllegalArgumentException e) {
                Utils.enterToContinue("Season tidak valid: " + e.getMessage());
                continue;
            }

            break;
        }

        return season;
    }

    public static ArrayList<Genre> inputGenresInteractive() throws IOException {
        Genre[] genres = Genre.genres;

        ArrayList<Genre> selected = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        outer: while (true) {
            Utils.clearConsole();

            // This will filter genres that are already selected
            genres = Arrays.stream(genres)
                    .filter(genre -> selected.indexOf(genre) == -1)
                    .toArray(Genre[]::new);

            if (selected.size() > 0) {
                System.out.print("Genre yang telah dipilih: ");
                System.out.println(Genre.join(", ", selected));
            }

            System.out.println("Pilihan Genre");

            AsciiTable at = new AsciiTable();
            at.addRule();
            at.addRow(0, "Selesai Memilih");
            at.addRule();

            for (int i = 0; i < genres.length; i++) {
                at.addRow(i + 1, genres[i].getName());
                at.addRule();
            }

            System.out.println(at.render());

            System.out.print("Input: ");
            String genreInput = reader.readLine();

            if (genreInput.equals("0")) {
                if (selected.size() == 0) {
                    Utils.enterToContinue("Masukkan genre terlebih dahulu.");
                    continue;
                }

                break;
            }

            // by names
            for (Genre g : genres) {
                if (g.getName().toLowerCase().equals(genreInput.toLowerCase())) {
                    selected.add(g);
                    continue outer;
                }
            }

            // by numbers
            try {
                int idx = Integer.parseInt(genreInput);
                selected.add(genres[idx - 1]);
            } catch (Exception e) {
                Utils.enterToContinue(
                        String.format("'%s' bukan pilihan yang valid.", genreInput));
                continue;
            }
        }

        return selected;
    }
}
