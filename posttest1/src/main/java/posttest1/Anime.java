package posttest1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

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

    public static Season inputSeasonInteractive() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        SeasonEnum name = null;
        int year;

        while (true) {
            System.out.println("Pilihan Season");
            System.out.println("[1] Winter");
            System.out.println("[2] Spring");
            System.out.println("[3] Summer");
            System.out.println("[4] Fall");

            System.out.print("Season: ");
            String inputSeason = reader.readLine();

            // by names
            for (SeasonEnum se : SeasonEnum.values()) {
                if (se.toString().toLowerCase().equals(inputSeason.toLowerCase())) {
                    name = se;
                }
            }

            // by choices
            switch (inputSeason) {
                case "1":
                    name = SeasonEnum.WINTER;
                    break;
                case "2":
                    name = SeasonEnum.SPRING;
                case "3":
                    name = SeasonEnum.SUMMER;
                case "4":
                    name = SeasonEnum.FALL;
            }

            if (name == null) {
                Utils.enterToContinue("Season yang diinputkan tidak valid");
                continue;
            }

            break;
        }

        // year.
        while (true) {
            System.out.print("Masukkan tahun anime: ");
            try {
                year = Integer.parseInt(reader.readLine());

                // first anime was in 1917
                if (year < 1917) {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                Utils.enterToContinue("Tahun tidak valid.");
                continue;
            }

            break;
        }

        Season s = new Season(name, year);
        return s;
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
            System.out.println("[0] Selesai Memilih");
            for (int i = 0; i < genres.length; i++) {
                System.out.println(String.format("[%d] %s", i + 1, genres[i]));
            }

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
                if (g.name.toLowerCase().equals(genreInput.toLowerCase())) {
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
