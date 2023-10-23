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