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
         String query="insert into pertandingan (tim1,tim2,pekan,status,namaKompetisi) values ('"
                 +p.getTim1().getId()+"','"+p.getTim2().getId()+"','"+p.getPekan()+"','false'"+namaKompetisi+"')";
         db.execute(query);
     }
}
