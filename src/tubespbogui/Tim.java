package tubespbogui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

class Tim extends db_tim {

    private int idTim;
    private String nama;
    private int win = 0, lose = 0, draw = 0, nMember = 0, point = 0;

    public Tim() {
    }

    public Tim(String nama) {
        this.nama = nama;
    }

    public Tim(String namaKompetisi, int id) {
        selectTimById(id, namaKompetisi);
    }
    //setter
    //hati hati ada kemungkinan array out of bound karena nMember tidak di set di konstruktor
   /* public void viewMember() {
     for (int i = 0; i < nMember; i++) {
     System.out.print(member[i].getNoPunggung() + " : ");
     System.out.println(member[i].getNama() + " " + member[i].getPosisi());
     }
     }
     */

    public void saveTim(String namaKompetisi, int maxTim) {
        super.addTim(this.nama, namaKompetisi, maxTim);
    }

    public void editTim(String namaKompetisi, String tempNama) {
        super.updateTim(this.nama, namaKompetisi, tempNama);
    }

    public void removeTim(String namaKompetisi) {
        super.deleteTim(this.nama, namaKompetisi);
        System.out.println(nama + "+" + namaKompetisi);
    }

    public void updateHasilPertandingan() {
        super.hasilPertandingan(this);
    }

    public ArrayList<Tim> selectAllTim(String namaKompetisi) {
        ArrayList<Tim> daftarTim = new ArrayList<>();
        try {
            String query = "select * from tim where namaKompetisi='" + namaKompetisi + "'";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                Tim tmp = new Tim();
                tmp.setIdTim(rs.getInt("idTim"));
                tmp.setNama(rs.getString("namaTim"));
                tmp.setWin(rs.getInt("win"));
                tmp.setLose(rs.getInt("lose"));
                tmp.setDraw(rs.getInt("draw"));
                tmp.setPoint(rs.getInt("point"));
                daftarTim.add(tmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return daftarTim;
    }

    public void selectTim(String namaTim, String namaKompetisi) {
        try {
            String query = "select * from tim where namaTim='" + namaTim + "' and namaKompetisi='" + namaKompetisi + "' LIMIT 1";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                this.setIdTim(rs.getInt("idTim"));
                this.setNama(rs.getString("namaTim"));
                this.setWin(rs.getInt("win"));
                this.setLose(rs.getInt("lose"));
                this.setDraw(rs.getInt("draw"));
                this.setPoint(rs.getInt("point"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectTimById(int id, String namaKompetisi) {
        try {
            String query = "select * from tim where idTim='" + id + "' and namaKompetisi='" + namaKompetisi + "' LIMIT 1";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                this.setIdTim(rs.getInt("idTim"));
                this.setNama(rs.getString("namaTim"));
                this.setWin(rs.getInt("win"));
                this.setLose(rs.getInt("lose"));
                this.setDraw(rs.getInt("draw"));
                this.setPoint(rs.getInt("point"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //scoring
    public void win() {
        this.setPoint(this.point + 3);
        this.win++;
    }

    public void lose() {
        this.lose++;
    }

    public void draw() {
        this.point++;
        this.draw++;
    }

    //getter
    public String getNama() {
        return nama;
    }

    public int getId() {
        return idTim;
    }

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }

    public int getDraw() {
        return draw;
    }

    public boolean cekTimReady(String namaKompetisi) {
        ArrayList<Tim> daftarTim = selectAllTim(namaKompetisi);
        boolean statusReady = true;
        String tmp = "Masih ada tim yang belum memiliki minimal 7 pemain : \n";
        for (Tim t : daftarTim) {
            if (super.getJumlahPemain(t.getNama(), namaKompetisi) < 7) {
                statusReady = false;
                tmp += t.getNama() + ", ";
            }
        }
        if (!statusReady) {
            JOptionPane.showMessageDialog(null, tmp, "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
        return statusReady;
    }
    /*
     public Pemain getMember(int noPunggung) {
     int idx = 999;
     for (int i = 0; i < nMember; i++) {
     if (member[i].getNoPunggung() == noPunggung) {
     idx = i;
     }
     }
     return member[idx];
     }

     public Pemain getMemberByIndex(int index) {
     return member[index];
     }

     public int getNMember() {
     return nMember;
     }
     */

    public int getPoint() {
        return (3 * win + draw);
    }

    public String toSTring() {
        return "nullExceptionLoh!!!";
    }

    public int compareTo(Tim compareTim) {
        int comparePoint = ((Tim) compareTim).getPoint();
        return comparePoint - this.point;
    }

    public void setIdTim(int idTim) {
        this.idTim = idTim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void setnMember(int nMember) {
        this.nMember = nMember;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
