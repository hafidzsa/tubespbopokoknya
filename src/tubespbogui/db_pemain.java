/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubespbogui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hafidz
 */
public class db_pemain {

    protected database db;

    public db_pemain() {
        db = new database();
        db.connect();
    }

    public int getIdTim(String namaTim, String namaKompetisi) {
        int idTim;
        StringBuilder sb = new StringBuilder();
        try {
            String query = "select tim.idTim from tim where namaTim ='" + namaTim + "' and namaKompetisi='" + namaKompetisi + "';";
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
            java.util.logging.Logger.getLogger(Pemain.class.getName()).log(Level.SEVERE, null, ex);
        }
        String allData = sb.toString();
        String[] dataTuple = allData.split(" \n");
        String[][] data = new String[dataTuple.length][];
        String view = "xxx";
        for (int i = 0; i < dataTuple.length; i++) {
            data[i] = dataTuple[i].split(" ; ");
            view = Arrays.toString(data[i]);
            view = view.replaceAll("[^0-9]", "");
        }
        idTim = Integer.parseInt(view);
        return idTim;
    }

    public void addPemain(String namaKompetisi, String namaTim, String namaPemain, int noPunggung, String posisi) {
        int idTim;
        idTim = getIdTim(namaTim, namaKompetisi);
        String get = "select noPunggung from pemain where idTim=" + idTim + ";";
        ResultSet rs;
        rs = db.getData(get);
        int jum = 0;
        boolean cek = false;
        try {
            while (rs.next()) {
                if (rs.getString("noPunggung").equals(Integer.toString(noPunggung))) {
                    cek = true;
                }
                jum += 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (jum >= 7) {
            JOptionPane.showMessageDialog(null, "jumlah pemain dalam Tim sudah maksimal!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (cek) {
            JOptionPane.showMessageDialog(null, "Nomer punggung sudah digunakan", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            String input = "insert into pemain values (" + idTim + "," + noPunggung + ",'" + namaPemain + "','" + posisi + "',0,NULL);";
            db.execute(input);
            JOptionPane.showMessageDialog(null, "Data pemain berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void editPemain(String namaKompetisi, String namaTim, String namaPemain, int noPunggung, int oldNoPunggung, String posisi) {
        int idTim;
        idTim = getIdTim(namaTim, namaKompetisi);
        String get = "select noPunggung from pemain where idTim=" + idTim + ";";
        ResultSet rs;
        rs = db.getData(get);
        int jum = 0;
        boolean cek = false;
        try {
            while (rs.next()) {
                if (rs.getString("noPunggung").equals(Integer.toString(oldNoPunggung))) {
                } else if (rs.getString("noPunggung").equals(Integer.toString(noPunggung))) {
                    cek = true;
                }
                jum += 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (jum >= 7) {
            JOptionPane.showMessageDialog(null, "jumlah pemain dalam Tim sudah maksimal!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (cek) {
            JOptionPane.showMessageDialog(null, "Nomer punggung sudah digunakan", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            String input = "update pemain set noPunggung=" + noPunggung + ",namaPemain='" + namaPemain + "',posisi='" + posisi + "' where idTim=" + idTim;
            db.execute(input);
            JOptionPane.showMessageDialog(null, "Data pemain berhasil diubah", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public String getListPemainFull(String namaTim, String namaKompetisi) {
        int idTim;
        idTim = getIdTim(namaTim, namaKompetisi);
        StringBuilder sbb = new StringBuilder();
        try {
            String query = "select namaPemain,noPunggung,posisi,jumlahGol from pemain where idTim=" + idTim + "";
            ResultSet rs = db.getData(query);
            while (rs.next()) {
                for (int i = 1; i <= 4; i++) {
                    sbb.append(rs.getString(i));
                    sbb.append(" ; ");
                }
                sbb.append(" \n");
            }
            rs.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Pemain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sbb.toString();
    }

    public void deletePemain(String namaKompetisi, String namaTim, String namaPemain) {
        int idTim = getIdTim(namaTim, namaKompetisi);
        String query = "delete from pemain where idTim='" + idTim + "' and namaPemain='" + namaPemain + "'";
        db.execute(query);
        JOptionPane.showMessageDialog(null, "Data Pemain berhasil dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }

    public void tambahGol(String namaKompetisi, String namaTim, int noPunggung, int Gol) {
        int idTim, jumlahGol;
        idTim = getIdTim(namaTim, namaKompetisi);
        StringBuilder sb = new StringBuilder();
        try {
            String query = "select jumlahGol from pemain where idTim =" + idTim + " and noPunggung=" + noPunggung + ";";
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
            java.util.logging.Logger.getLogger(Pemain.class.getName()).log(Level.SEVERE, null, ex);
        }
        String allData = sb.toString();
        String[] dataTuple = allData.split(" \n");
        String[][] data = new String[dataTuple.length][];
        String view = "xxx";
        for (int i = 0; i < dataTuple.length; i++) {
            data[i] = dataTuple[i].split(" ; ");
            view = Arrays.toString(data[i]);
            view = view.replaceAll("[^0-9]", "");
        }
        jumlahGol = Integer.parseInt(view);
        jumlahGol = jumlahGol + Gol;
        String input = "update pemain set jumlahGol=" + jumlahGol + " where idTim=" + idTim + " and noPunggung=" + noPunggung + ";";
        db.execute(input);
    }
}
