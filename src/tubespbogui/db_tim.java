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
public class db_tim {

    protected database db;

    public db_tim() {
        db = new database();
        db.connect();
    }

    public void addTim(String namaTim, String namaKompetisi, int maxTim) {
        String get = "select namaTim from tim where namaKompetisi='" + namaKompetisi + "'";
        ResultSet rs;
        rs = db.getData(get);
        int jum = 0;
        boolean cek = false;
        try {
            while (rs.next()) {
                if (rs.getString("namaTim").equals(namaTim)) {
                    cek = true;
                }
                jum += 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (jum >= maxTim) {
            JOptionPane.showMessageDialog(null, "Tim dalam kompetisi sudah maksimal!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (cek) {
            JOptionPane.showMessageDialog(null, "Nama Tim sudah ada", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            String input = "insert into tim values (NULL,'" + namaTim + "',0,0,0,0,'" + namaKompetisi + "');";
            db.execute(input);
            JOptionPane.showMessageDialog(null, "Data berhasil dimasukkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void updateTim(String namaTim, String namaKompetisi, String temp) {
        String query = "update tim set namaTim='" + temp + "' where namaTim='" + namaTim + "' and namaKompetisi='" + namaKompetisi + "'";
        db.execute(query);
        JOptionPane.showMessageDialog(null, "Data Tim berhasil diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }

    public void deleteTim(String namaTim, String namaKompetisi) {
        String query = "delete from tim where namaTim='" + namaTim + "' and namaKompetisi='" + namaKompetisi + "'";
        db.execute(query);
        JOptionPane.showMessageDialog(null, "Data Tim berhasil dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }

    public void hasilPertandingan(Tim t) {
        String query = "update tim set win='" + t.getWin() + "', lose='" + t.getLose() + "', draw='" + t.getDraw() + "', point='" + t.getPoint() + "' where idTim='" + t.getId() + "'";
        db.execute(query);
    }

    public String getListTim(String namaKompetisi) {
        StringBuilder sb = new StringBuilder();
        try {
            String query = "select namaTim from tim where namaKompetisi='" + namaKompetisi + "'";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                sb.append(rs.getString("namaTim"));
                sb.append(" ; ");
                sb.append(" \n");
            }
            rs.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

    public String getListTimFull(String namaKompetisi) {
        StringBuilder sb = new StringBuilder();
        try {
            String query = "select namaTim,win,lose,draw,point from tim where namaKompetisi='" + namaKompetisi + "'";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                for (int i = 1; i <= 5; i++) {
                    sb.append(rs.getString(i));
                    sb.append(" ; ");
                }
                sb.append(" \n");
            }
            rs.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

    public int getJumlahPemain(String namaTim, String namaKompetisi) {
        int jmlPemain = 0;
        int idTim = new Pemain().getIdTim(namaTim, namaKompetisi);
        try {
            String query = "SELECT count(counter) FROM pemain join tim using(idTim) WHERE pemain.idTim=" + idTim + " and tim.namaKompetisi='" + namaKompetisi + "'";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                jmlPemain = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(db_kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jmlPemain;
    }
}
