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
    private database db; 
    public db_tim(){
         db=new database();
         db.connect();
     } 
     public void addTim(String namaTim,String namaKompetisi,int maxTim) {
        String get="select namaTim from tim where namaKompetisi='"+namaKompetisi+"'";
        ResultSet rs;
        rs = db.getData(get);
        int jum=0;
        boolean cek = false;
        try {
            while(rs.next()){
                if(rs.getString("namaTim").equals(namaTim)){
                    cek=true;
                }
               jum+=1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(jum>=maxTim){
            JOptionPane.showMessageDialog(null, "Tim dalam kompetisi sudah maksimal!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }else if(cek){
            JOptionPane.showMessageDialog(null, "Nama Tim sudah ada", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
        else{
            String input="insert into tim values ('"+namaKompetisi+"','"+namaTim+"')";
            db.execute(input);
            JOptionPane.showMessageDialog(null, "Data berhasil", "Peringatan", JOptionPane.WARNING_MESSAGE);
            
        }
    }
    public String getListTim(String namaKompetisi){
        StringBuilder sb = new StringBuilder();
        try {
            String query = "select namaTim from tim where namaKompetisi='"+namaKompetisi+"'";
            ResultSet rs = db.getData(query);
            while(rs.next()){
                    sb.append(rs.getString("namaTim"));
                    sb.append(" ; ");
                sb.append(" \n");
            }
            rs.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
}
