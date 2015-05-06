package tubespbogui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.swing.JOptionPane;

public class Kompetisi extends db_kompetisi {

    database db;
    private String nama;
    private Scanner input = new Scanner(System.in);
    private db_tim dbTim;
    private int nPeserta = 0, nTim = 0, maxTim = 0;
    private final String[] daftarPosisi = {"Striker", "Bek", "Penjaga Gawang", "Gelandang"};
    private static Pattern validator = Pattern.compile("[;%#=\\+0123456789]");
    //private List<Pemain> daftarPemain = new ArrayList<Pemain>();
    //private Tim[] daftarTim;
    //private Klasemen klas;

    /**
     * Konstruktor
     *
     * 0 * @param nama
     *
     * @param max
     * @param group
     */
    public Kompetisi() {
        db = new database();
        db.connect();
    }

    public Kompetisi(String nama, int max) {
        this.nama = nama;
        maxTim = max;
        db = new database();
        db.connect();
    }

    public boolean isNameValid(String name) {
        return validator.matcher(name).find();
    }

    /*public boolean validasiNoPunggung(int nomer, int pointerTim) {
     int i = 0;
     boolean local_cek = false, valid = false;
     try {
     do {
     if (daftarTim[pointerTim].getMemberByIndex(i).getNoPunggung() == nomer) {
     local_cek = true;
     throw new IllegalArgumentException("Nomor sudah digunakan");
     } else {
     i++;
     valid = true;
     }
     } while (local_cek != true);
     } catch (NullPointerException e) {
     valid = true;
     } catch (IllegalArgumentException e) {
     System.out.println(e.getMessage());
     valid = false;
     }
     return valid;
     }
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setMaxTim(int max) {
        this.maxTim = max;
    }

    public String getNama() {
        return nama;
    }

    public int getMaxTim() {
        return maxTim;
    }

    /*public Tim[] getTim() {
     return daftarTim;
     }

     public List<Pemain> getPemain() {
     return daftarPemain;
     }*/
    public void addTim() {

    }

    public void addPemain() {

    }

    //DATABASE ALGORITM
    public void saveKompetisi() {
        super.addKompetisi(getNama(), getMaxTim());
    }

    public void removeKompetisi() {
        super.deleteKompetisi(this.nama);
    }

    public void updateKompetisi(String temp) {
        super.editKompetisi(this.nama, this.maxTim, temp);
    }

    public void selectKompetisi(String nama) {
        try {
            String query = "select namaKompetisi, maxTim from kompetisi where namaKompetisi='" + nama + "' LIMIT 1";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                this.maxTim = rs.getInt("maxTim");
                this.nama = rs.getString("namaKompetisi");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean getStatusTutupPendaftaran() {
        return super.getStatus(this.nama);
    }

    public void updateStatusTutupPendaftaran(boolean newStatus) {
        super.editStatus(this.nama, newStatus);
    }

    public int getJumlahTimKompetisi() {
        return super.getJumlahTim(this.nama);
    }
}
