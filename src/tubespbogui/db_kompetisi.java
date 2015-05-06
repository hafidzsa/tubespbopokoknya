/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubespbogui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hafidz
 */
public class db_kompetisi {

    private database db;

    public db_kompetisi() {
        db = new database();
        db.connect();
    }

    protected void addKompetisi(String namaKompetisi, int maxTim) {
        String get = "select namaKompetisi from Kompetisi";
        ResultSet rs;
        rs = db.getData(get);
        int jum = 0;
        boolean cek = false;
        try {
            while (rs.next()) {
                if (rs.getString("namaKompetisi").equals(namaKompetisi)) {
                    cek = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cek) {
            JOptionPane.showMessageDialog(null, "Nama Kompetisi sudah ada", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            String query = "insert into kompetisi (namaKompetisi,maxTim) values('" + namaKompetisi + "','" + maxTim + "')";
            db.execute(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil ditambah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    protected void editKompetisi(String namaKompetisi, int maxTim, String temp) {
        String query = "update kompetisi set namaKompetisi='" + namaKompetisi + "', maxTim='" + maxTim + "' where namaKompetisi='" + temp + "'";
        db.execute(query);
        JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }

    protected void deleteKompetisi(String namaKompetisi) {
        String query = "delete from kompetisi where namaKompetisi='" + namaKompetisi + "'";
        db.execute(query);
        JOptionPane.showMessageDialog(null, "Data Berhasil dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getListKompetisi() {
        StringBuilder sb = new StringBuilder();
        try {
            String query = "select namaKompetisi from kompetisi";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                for (int i = 1; i <= 1; i++) {
                    sb.append(rs.getString(i));
                    sb.append(" ; ");
                }
                sb.append(" \n");
            }
            rs.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

    public String getDetilKompetisi(String nama) {
        String get1, get2, nk, tmp = null;
        get1 = "select namaKompetisi, maxTim from kompetisi where namaKompetisi='" + nama + "' LIMIT 1";
        ResultSet rs = db.getData(get1);

        try {
            rs.next();
            nk = rs.getString("namaKompetisi");
            tmp = "Nama Kompetisi : " + nk + "\nMaximal Kompetisi : " + rs.getInt("maxTim") + "\nList Tim : ";
            get2 = "select namaTim from tim where namaKompetisi='" + nk + "'";
            rs = db.getData(get2);
            while (rs.next()) {
                tmp = tmp.concat("\n" + rs.getString("namaTim"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    public boolean getStatus(String namaKompetisi) {
        boolean status = false;
        try {
            String query = "select statusTutupPendaftaran from Kompetisi where namaKompetisi='" + namaKompetisi + "'";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                status = rs.getBoolean(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(db_kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public void editStatus(String namaKompetisi, boolean newStatus) {
        String query = "update Kompetisi set statusTutupPendaftaran=" + newStatus + " where namaKompetisi='" + namaKompetisi + "'";
        db.execute(query);
    }

    public int getJumlahTim(String namaKompetisi) {
        int jmlTim = 0;
        try {
            String query = "select count(idTim) from Tim where namaKompetisi='" + namaKompetisi + "'";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                jmlTim = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(db_kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jmlTim;
    }

    public Tim[] getTimPemenang(String namaKompetisi) {
        Tim[] daftarPemenang = new Tim[3];
        try {
            int i = 0;
            String query = "select * from tim where namaKompetisi='" + namaKompetisi + "' order by point desc limit 3 ";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                Tim tmp = new Tim();
                tmp.setIdTim(rs.getInt("idTim"));
                tmp.setNama(rs.getString("namaTim"));
                tmp.setWin(rs.getInt("win"));
                tmp.setLose(rs.getInt("lose"));
                tmp.setDraw(rs.getInt("draw"));
                tmp.setPoint(rs.getInt("point"));
                daftarPemenang[i++] = tmp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(db_kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return daftarPemenang;
    }

    public Pemain getTopScorer(String namaKompetisi) {
        Pemain p = new Pemain();
        try {
            String query = "select * from pemain join tim using(idTim) where namaKompetisi='" + namaKompetisi + "' order by jumlahGol desc limit 1";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                p.selectPemain(rs.getInt("counter"));
                p.setTim(rs.getString("namaTim"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(db_kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public boolean getStatusPertandingan(String namaKompetisi) {
        boolean status = true;
        try {
            String query = "select status from Pertandingan where namaKompetisi='" + namaKompetisi + "'";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                if (!rs.getBoolean(1)) {
                    status = false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(db_kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
}
