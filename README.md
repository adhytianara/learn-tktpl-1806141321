# Lab 3 - Android App Life Cycle
## Deskripsi tugas :  
Implementasi:
1. Override tombol back  
2. Membuat proses tetap berjalan walaupun lost focus.  
   Cth: stopwatch  
   Jangan sampai stop watch nya berenti ketika kita ganti ke apps lain.  
     
Catatan:
* Tombol back yg biasanya mengeluarkan kita dari apps, mhs diharapkan dapat meng override agar kalau mau keluar dari aplikasi harus klik tombol EXIT pada aplikasi.  
* Membuat suatu proses yg apabila aplikasinya lost focus, maka proses tetep jalan. Dan ketika kita masuk lagi ke aplikasi, prosesnya masih keliatan jalan. Ketika stopwatch sdh distart, apabila kita pindh ke watsapp di detik ke 10, lalu balik lagi ke aplikasi stopwatch nya di detik ke 20, maka tulisan stopwatch nya harusnya muncul di detik 20 (bukan resume dari detik 10)  
* Mahasiswa dapat mengimplementasi menggunaka salah salah satu dari 4 cara yang sudah dijelaskan di kelas agar tidak terjadi blocking UI async atau view model.  
* Boleh mempergunakan contoh code dari Github  
