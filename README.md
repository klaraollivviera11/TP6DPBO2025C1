# Janji
Saya Klara Ollivviera Augustine Gunawan dengan NIM 2306205 mengerjakan soal Tugas Praktikum 6 dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

# Desain Program
Program ini mengimplementasikan game Flappy Bird menggunakan Java Swing. Game dimulai dengan tampilan instruksi dan akan berjalan saat tombol Spasi ditekan. Pemain harus menghindari tabrakan dengan pipa atas dan bawah, serta menjaga burung tidak jatuh ke bawah.

Terdapat 5 Class:
## 1. App
Kelas utama (main class) yang menjalankan aplikasi.
### Atribut dan Metode
- JFrame frame → Frame utama untuk menampilkan game.
- JLabel scoreLabel → Label untuk menampilkan skor.
- FlappyBird flappyBird → Panel utama tempat game berjalan.
- main() → Membuat frame, label skor, dan menambahkan panel FlappyBird.
## 2. IntroFrame
Berfungsi menampilkan intro atau petunjuk sebelum masuk ke game.
### Atribut dan Metode
- JFrame frame → Frame utama untuk tampilan intro.
- JPanel panel → Panel utama yang bisa menampilkan background (bisa di-custom untuk gambar atau warna).
- JButton startButton → Tombol untuk memulai game. Saat ditekan, frame ini akan ditutup dan game akan dijalankan.
- IntroFrame() → constructor untuk setup tampilan intro dan tombol start.
## 3. FlappyBird
Merupakan JPanel utama tempat game Flappy Bird berlangsung.
### Atribut Penting
- Timer gameLoop → Mengatur update layar dan logika game setiap frame.
- Timer pipesCooldown → Mengatur jeda munculnya pipa baru.
- Player player → Objek burung yang dikendalikan.
- ArrayList<Pipe> pipes → Menyimpan semua pipa yang muncul.
- Image backgroundImage, birdImage, upperPipeImage, lowerPipeImage → Gambar-gambar untuk game.
- JLabel scoreLabel → Untuk menampilkan skor.
### Metode
- paintComponent(Graphics g) → Menggambar ulang panel.
- draw(Graphics g) → Menampilkan elemen-elemen visual.
- move() → Mengatur logika gerakan burung dan pipa.
- placePipes() → Menambahkan sepasang pipa ke dalam game.
- collide() → Mengecek tabrakan antara burung dan pipa.
- keyPressed() → Menangani input dari pemain (Spasi untuk terbang, R untuk restart).
## 4. Pipe
Mewakili objek pipa dalam game.
### Atribut
- posX, posY, width, height → Ukuran dan posisi pipa.
- velocityX → Kecepatan gerak pipa ke kiri.
- image → Gambar pipa.
- passed → Flag untuk mendeteksi apakah pemain sudah melewati pipa.
### Metode
- Getter dan setter
## 5. Player
Mewakili burung yang dikendalikan pemain.
### Atribut
- posX, posY, width, height → Posisi dan ukuran burung.
- velocityY → Kecepatan jatuh burung.
- image → Gambar burung.
### Metode
- Getter dan setter

# Alur Program
1. Program dimulai dengan menjalankan IntroFrame.java, lalu pengguna menekan tombol "Start Game".
2. FlappyBird ditampilkan sebagai JPanel pada frame.
3. Pemain melihat instruksi “Press Space to Start”.
4. Setelah menekan Spasi, game dimulai:
- Burung mulai jatuh karena gravitasi.
- Pemain dapat menekan Spasi untuk membuat burung terbang ke atas.
- Pipa akan muncul setiap 3 detik dan bergerak dari kanan ke kiri.
- Jika burung menyentuh pipa atau jatuh ke bawah, game berakhir.
5. Pemain dapat menekan R untuk me-restart permainan.
6. Skor ditampilkan di atas layar dan bertambah setiap kali burung melewati sepasang pipa.

# Dokumentasi
https://github.com/user-attachments/assets/008c59af-9fe3-46be-a685-4f0e85fa106f


