import java.util.ArrayList;

public class Database {
    private ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    private FileHelper fileHelper;

    public Database() {
        fileHelper = new FileHelper();
        daftarMahasiswa = fileHelper.bacaDariFile();
    }

    public void create(Mahasiswa mahasiswa) {
        daftarMahasiswa.add(mahasiswa);
        fileHelper.simpanKeFile(daftarMahasiswa);
        System.out.println("* Data berhasil ditambahkan *");
    }

    public ArrayList<Mahasiswa> read() {
        return daftarMahasiswa;
    }

    public Mahasiswa read(String nim) {
        Mahasiswa mahasiswa = null;
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            if (daftarMahasiswa.get(i).getNim().equals(nim)) {
                mahasiswa = daftarMahasiswa.get(i);
                break;
            }
        }
        return mahasiswa;
    }

    public void update(Mahasiswa mahasiswa, String nimLama) {
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            if (daftarMahasiswa.get(i).getNim().equals(nimLama)) {
                daftarMahasiswa.set(i, mahasiswa);
                fileHelper.simpanKeFile(daftarMahasiswa);
                System.out.println("* Data berhasil diubah *");
                return;
            }
        }
        System.out.println("* Data tidak ditemukan *");
    }
    public void delete(String nim) {
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            if (daftarMahasiswa.get(i).getNim().equals(nim)) {
                daftarMahasiswa.remove(i);
                fileHelper.simpanKeFile(daftarMahasiswa);
                System.out.println("* Data berhasil dihapus *");
                return;
            }
        }
        System.out.println("* Data tidak ditemukan *");
    }
}

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHelper {
    private String namaFileDataMahasiswa = "data_mahasiswa.txt";

    public void simpanKeFile(ArrayList<Mahasiswa>daftarMahasiswa) {
        try (PrintWriter writer = new PrintWriter(new File(namaFileDataMahasiswa))) {
            for (Mahasiswa m : daftarMahasiswa) {
                writer.println(m.getNim() + "," + m.getNama());
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan ke file: " + e.getMessage());
        }
    }

    public ArrayList<Mahasiswa> bacaDariFile() {
        ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(namaFileDataMahasiswa))) {
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Mahasiswa m = new Mahasiswa(parts[0],parts[1]);
                    daftarMahasiswa.add(m);
                }
            }
        } catch (IOException e) {

        }
        return daftarMahasiswa;
    }


}

public class Mahasiswa {
    private String nim;
    private String nama;

    public Mahasiswa(String nim, String nama) {
        this.nama = nama;
        this.nim = nim;
    }
    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }
}

import java.util.Scanner;

public class KampusApp {
    public static void main(String[] args) {
        KampusApp kampusApp = new KampusApp();
        kampusApp.showMenu();
    }

    private Scanner scanner;
    private Database database;

    public KampusApp() {
        scanner = new Scanner(System.in);
        database = new Database();
    }

    public void showMenu() {
        System.out.println("---------------------------------------");
        System.out.println("Selamat Datang di Aplikasi Kampus");
        System.out.println("Pilihan Menu:");
        System.out.println("1 -> Tambah Data Mahasiswa");
        System.out.println("2 -> Ubah Data Mahasiswa");
        System.out.println("3 -> Hapus Data Mahasiswa");
        System.out.println("4 -> Cari Data Mahasiswa");
        System.out.println("0 -> Keluar Aplikasi");
        System.out.print("Silahkan masukkan menu yang dipilih: ");
        int menuYangDipilih = scanner.nextInt();
        scanner.nextLine();
        switch (menuYangDipilih) {
            case 1:
                menuTambah();
            case 2:
                menuUbah();
            case 3:
                menuHapus();
            case 4:
                menuCari();
            case 0:{
                System.out.print("* Terima kasih sudah menggunakan Aplikasi Kampus *");
                System.exit(0);
            }
        }
        scanner.close();
    }
    public void menuTambah() {
        System.out.println("----- Menu Tambah Mahasiswa -----");
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        Mahasiswa mahasiswa = new Mahasiswa(nim, nama);
        database.create(mahasiswa);
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }

    public void menuUbah() {
        System.out.println("----- Menu Ubah Data Mahasiswa -----");
        System.out.print("Masukkan NIM Sebelumnya: ");
        String nimLama = scanner.nextLine();
        System.out.print("Masukkan NIM Baru: ");
        String nimBaru = scanner.nextLine();
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        Mahasiswa mahasiswa = new Mahasiswa(nimBaru, nama);
        database.update(mahasiswa, nimLama);
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }
    public void menuCari() {
        System.out.println("----- Menu Cari Data Mahasiswa -----");
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        Mahasiswa mahasiswa = database.read(nim);
        if (mahasiswa == null) {
            System.out.println("* NIM tidak ditemukan *");
        } else {
            System.out.println("* NIM: " + mahasiswa.getNim() + " *");
            System.out.println("* Nama: " + mahasiswa.getNama() + " *");
        }
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }
    public void menuHapus() {
        System.out.println("----- Menu Hapus Data Mahasiswa -----");
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        database.delete(nim);
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }
}