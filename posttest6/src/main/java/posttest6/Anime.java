package posttest6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;

// Abstract class
public abstract class Anime {
    String title;
    String studio;
    Season season;
    ArrayList<Genre> genres;

    Anime(String title, String studio, ArrayList<Genre> genres) {
        this.title = title;
        this.studio = studio;
        this.genres = genres;
    }

    // Abstract method
    public abstract void displayRow(AsciiTable at, int num);

    public abstract void display(int num);

    // Final pada method
    public final static Anime createInteractive() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String type;
        while (true) {
            System.out.println("Jenis Anime");
            System.out.println("[1] Show (Seasonal)");
            System.out.println("[2] Movie");
            System.out.print("Pilih jenis anime:");
            type = reader.readLine();

            if (type.equals("1") || type.toLowerCase().startsWith("show")) {
                type = "show";
            } else if (type.equals("2") || type.toLowerCase().startsWith("movie")) {
                type = "movie";
            } else {
                System.out.println("Jenis naime tidak valid!");
                continue;
            }

            break;
        }

        System.out.print("Masukkan judul anime: ");
        String title = reader.readLine();

        System.out.print("Masukkan studio anime: ");
        String studio = reader.readLine();

        ArrayList<Genre> genres = inputGenresInteractive();

        if (type.equals("show")) {
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

            AnimeShow show = new AnimeShow(title, studio, genres, episode, season);

            return show;
        } else if (type.equals("movie")) {
            String airingDate;
            while (true) {
                Locale id = new Locale.Builder().setLanguage("id").setRegion("ID").build();

                try {
                    System.out.print("Masukkan tanggal [Ex: 1 Januari 2023]: ");
                    airingDate = reader.readLine();

                    DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
                    builder.parseCaseInsensitive();
                    builder.appendPattern("d MMMM yyyy");
                    DateTimeFormatter formatter = builder.toFormatter(id);

                    LocalDate date = LocalDate.parse(airingDate, formatter);
                    airingDate = date.format(formatter);

                } catch (DateTimeParseException e) {
                    Utils.enterToContinue("Tanggal tidak valid.");
                    continue;
                }

                break;
            }

            AnimeMovie movie = new AnimeMovie(title, studio, genres, airingDate);

            return movie;
        } else {
            // This should never happen
            return null;
        }
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
