package posttest3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

public class Main {
    static ArrayList<Anime> animeList = new ArrayList<>();

    public static String getHeader() throws IOException {
        return new String(Files.readAllBytes(Paths.get("./header.txt")));
    }

    public static void main(String[] args) throws IOException {
        AnimeShow onimai = new AnimeShow("Onimai", "Studio Bind", Genre.getGenreObjects("Comedy"), 12,
                new Season("Winter", 2023));
        AnimeMovie suzume = new AnimeMovie("Suzume no Tojimari", " CoMix Wave Films",
                Genre.getGenreObjects("Adventure", "Fantasy"), "11 November 2022");

        animeList.add(onimai);
        animeList.add(suzume);

        Utils.clearConsole();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            menu();

            System.out.print("Masukkan pilihan: ");
            try {
                int choice = Integer.parseInt(reader.readLine());
                Utils.clearConsole();

                if (choice == 0) {
                    break;
                } else if (choice == 1) {
                    Anime newAnime = Anime.createInteractive();
                    createAnime(newAnime);
                    Utils.enterToContinue("Data anime berhasil disimpan.");
                } else if (choice == 2) {
                    readAnime();
                    Utils.enterToContinue("");
                } else if (choice == 3) {
                    readAnime();
                    if (animeList.size() == 0) {
                        Utils.enterToContinue("Silakan isi data terlebih dahulu.");
                        continue;
                    }

                    int idx = Utils.inputIndexInteractive(animeList);
                    if (idx == -1) {
                        Utils.enterToContinue("Nomor tidak valid!");
                        continue;
                    }

                    updateAnime(idx, Anime.createInteractive());
                    Utils.enterToContinue("Data anime berhasil diperbarui.");
                } else if (choice == 4) {
                    readAnime();
                    if (animeList.size() == 0) {
                        Utils.enterToContinue("Silakan isi data terlebih dahulu.");
                        continue;
                    }

                    int idx = Utils.inputIndexInteractive(animeList);
                    if (idx == -1) {
                        Utils.enterToContinue("Nomor tidak valid!");
                        continue;
                    }

                    deleteAnime(idx);
                    Utils.enterToContinue("Data anime berhasil dihapus.");
                } else {
                    throw new IllegalArgumentException();
                }

            } catch (IllegalArgumentException e) {
                Utils.enterToContinue("Pilihan menu tidak valid.");
            }
        }

        reader.close();
    }

    public static void menu() {
        System.out.println("============");
        System.out.println("AnimeArchive");
        System.out.println("============");
        System.out.println("[0] Exit");
        System.out.println("[1] Create");
        System.out.println("[2] Read");
        System.out.println("[3] Update");
        System.out.println("[4] Delete");
    }

    public static void createAnime(Anime anime) {
        animeList.add(anime);
    }

    public static void readAnime() {
        AsciiTable at = new AsciiTable();
        at.getRenderer().setCWC(new CWC_LongestLine());

        at.addRule();
        at.addRow("No", "Title", "Studio", "Episode", "Season / Air Date", "Genre");
        at.addRule();

        int num = 1;
        for (Anime anime : animeList) {
            if (anime instanceof AnimeShow) {
                AnimeShow show = (AnimeShow) anime;
                at.addRow(num, show.title, show.studio, show.episode,
                        show.season.toString(), Genre.join(", ", show.genres));
            } else {
                AnimeMovie movie = (AnimeMovie) anime;
                at.addRow(num, movie.title, movie.studio, 1,
                        movie.airingDate, Genre.join(", ", movie.genres));

            }

            at.setPaddingLeft(2);
            at.setPaddingRight(2);
            at.addRule();

            num++;
        }

        at.setTextAlignment(TextAlignment.CENTER);
        System.out.println(at.render());

    }

    public static void updateAnime(int idx, Anime newAnime) {
        animeList.set(idx, newAnime);
    }

    public static void deleteAnime(int idx) {
        animeList.remove(idx);
    }
}
