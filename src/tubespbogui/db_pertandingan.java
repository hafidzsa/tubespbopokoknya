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
 * @author Farhad
 */
public class db_pertandingan {

/**
 *
 * @author hafidz
 */
    protected database db; 
    public db_pertandingan(){
         db=new database();
         db.connect();
     } 
     public void addPertandingan(String namaKompetisi, Pertandingan p) {
         String query="insert into pertandingan (tim1,tim2,pekan,status,namaKompetisi) values ("
                 +p.getTim1().getId()+","+p.getTim2().getId()+","+p.getPekan()+",false,'"+namaKompetisi+"')";
         db.execute(query);
     }
      public String getListJadwal(String namaKompetisi){
        StringBuilder sb = new StringBuilder();
        try {
            String query = "select * from pertandingan where namaKompetisi='"+namaKompetisi+"' order by pekan asc";
            ResultSet rs = db.getData(query);
            while(rs.next()){
                sb.append(rs.getString(2));
                sb.append(" ; ");
                sb.append(rs.getInt(4));
                sb.append(" ; ");
                sb.append(rs.getString(3));
                sb.append(" ; ");
                sb.append(rs.getInt(5));
                sb.append(" ; ");
                sb.append(rs.getString(6));
                sb.append(" ; ");
                sb.append(rs.getString(7));
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
