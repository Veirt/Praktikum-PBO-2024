package posttest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static ArrayList<Anime> animeList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

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

            } catch (Exception e) {
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
        int num = 1;
        for (Anime anime : animeList) {
            System.out.println("No\t: " + num);
            System.out.println("Judul\t: " + anime.title);
            System.out.println("Studio\t: " + anime.studio);
            System.out.println("Episode\t: " + anime.episode);
            System.out.println("Season\t: " + anime.season.toString());
            System.out.println("Genre\t: " + Genre.join(", ", anime.genres));
            System.out.println("=================");

            num++;
        }
    }

    public static void updateAnime(int idx, Anime newAnime) {
        animeList.set(idx, newAnime);
    }

    public static void deleteAnime(int idx) {
        animeList.remove(idx);
    }
}
