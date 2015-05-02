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
    public db_kompetisi(){
        db=new database();
        db.connect();
    }
    protected void addKompetisi(String namaKompetisi, int maxTim){
        String query = "insert into kompetisi (namaKompetisi,maxTim) values('"+namaKompetisi+"','"+maxTim+"')";
        db.execute(query); 
        JOptionPane.showMessageDialog(null, "Data Berhasil ditambah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }
    protected void editKompetisi(String namaKompetisi,int maxTim,String temp){
        String query="update kompetisi set namaKompetisi='"+namaKompetisi+"', maxTim='"+maxTim+"' where namaKompetisi='"+temp+"'";
        db.execute(query);
        JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }
    protected void deleteKompetisi(String namaKompetisi){
        String query="delete from kompetisi where namaKompetisi='"+namaKompetisi+"'";
        db.execute(query);
        JOptionPane.showMessageDialog(null, "Data Berhasil dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }
     public String getListKompetisi(){
        StringBuilder sb = new StringBuilder();
        try {
            String query = "select namaKompetisi from kompetisi";
            ResultSet rs = db.getData(query);
            while(rs.next()){
                for (int i = 1; i<=1; i++){
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
    public String getDetilKompetisi(String nama){
        String get1,get2,nk,tmp = null;
        get1="select namaKompetisi, maxTim from kompetisi where namaKompetisi='"+nama+"' LIMIT 1";
        ResultSet rs=db.getData(get1);
        
        try {
            rs.next();
            nk=rs.getString("namaKompetisi");
            tmp="Nama Kompetisi : "+nk+"\nMaximal Kompetisi : "+rs.getInt("maxTim")+"\nList Tim : ";
            get2="select namaTim from tim where namaKompetisi='"+nk+"'";
            rs=db.getData(get2);
            while(rs.next()){
                tmp=tmp.concat("\n"+rs.getString("namaTim"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }
}
