import java.util.Scanner;

public class Nilaimhsw {
    public static void main(String[] args){
        // membuat objek input untuk user 
        Scanner input = new Scanner(System.in);
        // meminta memasukkan nama dan NIM
        System.out.print("Input Nama: ");
        String nama= input.nextLine();

        System.out.print("Input NIM: ");
        Integer nim = input.nextInt();

        // meminta memasukkan nilai - nilai 
        System.out.print("Input Nilai Tubes: ");
        double nialiTubes = input.nextDouble();

        System.out.print("Input Nilai Quiz: ");
        double nilaiQuiz  = input.nextDouble();

        System.out.print("Input Nilai Tugas: ");
        double nilaiTugas = input.nextDouble();

        System.out.print("Input Nilai UTS: ");
        double nilaiUTS = input.nextDouble();

        System.out.print("Input Nilai UAS: ");
        double nilaiUAS = input.nextDouble();

        // menghitung nilai akhir
        Nilai Nilai = new Nilai();
        double nilaiAkhir = Nilai.nilaiAkhir(nialiTubes, nilaiQuiz, nilaiTugas, nilaiUTS, nilaiUAS);
        
        // menampilkan nama, nim dan nilai akhir
        System.out.println("\nNama : " + nama);
        System.out.println("NIM : " + nim);
        System.out.println("Nilai Akhir Matakuliah Pemograman Berorientasi Objek :" + nilaiAkhir);

        // menutup scanner
        input.close();
    }
}
