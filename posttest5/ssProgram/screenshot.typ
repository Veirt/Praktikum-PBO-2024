#import "@preview/codelst:2.0.1": sourcecode

// JANGAN LUPA GANTI //
#let posttest_num = 4
#let course = "Pemrograman Berorientasi Objek"
#let class = "B1 22"
// JANGAN LUPA GANTI //

#set page(
  paper: "a4",
  margin: (1in),
  footer: [
    #set text(12pt)
    #line(length: 100%)

    #counter(page).display(
      "1"
    ) #h(1fr) Posttest #posttest_num #course

  ]
)

== Muhammad Dony Mulya
== 2209106047 | #class
= Posttest #posttest_num #course
#line(length: 100%)


#figure(
  image("../assets/main-menu.png", width: 80%),
  caption: [Menu Utama],
) <fig-main-menu>


#figure(
  image("../assets/create-anime-part1.png", width: 80%),
  caption: [Create - Pilihan Jenis Anime],
) <fig-create-anime-part1>


#figure(
  image("../assets/create-anime-part2.png", width: 80%),
  caption: [Create - Judul dan Studio],
) <fig-create-anime-part2>


#figure(
  image("../assets/create-anime-part3.png", width: 80%),
  caption: [Create - Pemilihan Genre],
) <fig-create-anime-part3>

#figure(
  image("../assets/create-anime-part4.png", width: 80%),
  caption: [Create - Pemilihan Season],
) <fig-create-anime-part5>


#figure(
  image("../assets/read.png", width: 80%),
  caption: [Read],
) <fig-read>


#figure(
  image("../assets/update.png", width: 80%),
  caption: [Update Nomor 3],
) <fig-update>


#figure(
  image("../assets/update-after.png", width: 80%),
  caption: [Update Nomor 3 (Setelah)],
) <fig-update-after>


#figure(
  image("../assets/delete.png", width: 80%),
  caption: [Delete Nomor 3],
) <fig-delete>


#figure(
  image("../assets/delete-after.png", width: 80%),
  caption: [Delete Nomor 3 (Setelah)],
) <fig-delete-after>
