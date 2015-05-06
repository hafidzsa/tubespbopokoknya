package tubespbogui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pemain extends db_pemain implements Comparable<Pemain> {

    private String nama, posisi, tim;
    private int noPunggung, jumlahGoal, tmpGoal;

    public Pemain() {
    }

    public Pemain(String nama, int noPunggung, String posisi) {
        this.nama = nama;
        this.noPunggung = noPunggung;
        this.posisi = posisi;
    }

    //getter
    public String getNama() {
        return nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public int getNoPunggung() {
        return noPunggung;
    }

    public int getTmpGoal() {
        return tmpGoal;
    }

    public int getJumlahGoal() {
        return jumlahGoal;
    }
    public String getTim(){
        return tim;
    }

    //setter
    public void setGoal() {
        this.jumlahGoal += tmpGoal;
        this.tmpGoal = 0;
    }
    public void setTim(String tim) {
        this.tim = tim;
    }

    public void setTmpGoal() {
        this.tmpGoal++;
    }

    @Override
    public String toString() {
        return "nullExceptionLoh!!!";
    }
//a

    @Override
    public int compareTo(Pemain comparePemain) {
        int compareJumlahGoal = ((Pemain) comparePemain).getJumlahGoal();
        return compareJumlahGoal - this.jumlahGoal;
    }

    public void savePemain(String namaKompetisi, String namaTim, String namaPemain, int noPunggung, String posisi) {
        super.addPemain(namaKompetisi, namaTim, namaPemain, noPunggung, posisi);
    }

    public void updatePemain(String namaKompetisi, String namaTim, String namaPemain, int noPunggung,int oldNoPunggung, String posisi) {
        super.editPemain(namaKompetisi, namaTim, namaPemain, noPunggung, oldNoPunggung, posisi);
    }

    public void removePemain(String namaKompetisi, String namaTim, String namaPemain) {
        super.deletePemain(namaKompetisi, namaTim, namaPemain);
    }
    public void selectPemain(int counter) {
        try {
            String query = "select * from pemain where counter=" + counter + " LIMIT 1";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                this.nama = rs.getString("namaPemain");
                this.noPunggung = rs.getInt("noPunggung");
                this.posisi = rs.getString("posisi");
                this.jumlahGoal = rs.getInt("jumlahGol");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
