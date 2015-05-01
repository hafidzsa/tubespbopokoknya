package tubespbogui;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.swing.JOptionPane;

public class Kompetisi {
    database db;
    private String nama;
    private Scanner input = new Scanner(System.in);
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
    public Kompetisi(){
        db = new database();
        db.connect();
    }
    public Kompetisi(String nama, int max) {
        this.nama = nama;
        maxTim = max;
        //daftarTim = new Tim[max];
       // klas = new Klasemen(max);
        db = new database();
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
    
    public void setMaxTim(int max){
        this.maxTim=max;
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

    public void addTim(String namaTim) {
        String get="select namaTim from tim where namaKompetisi='"+this.nama+"'";
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
            String input="insert into tim values ('"+this.nama+"','"+namaTim+"')";
            db.execute(input);
            JOptionPane.showMessageDialog(null, "Data berhasil", "Peringatan", JOptionPane.WARNING_MESSAGE);
            
        }
    }
    public String getListTim(){
        StringBuilder sb = new StringBuilder();
        try {
            String query = "select namaTim from tim where namaKompetisi='"+this.nama+"'";
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
    public void removeTim() {
    }
    public void addPemain() {
        
    }
    //DATABASE ALGORITM
    public void saveKompetisi(){
        db.connect();
        String query = "insert into kompetisi (namaKompetisi,maxTim) values('"+nama+"','"+maxTim+"')";
        db.execute(query);    
    }
    public void hapusKompetisi(String nama){
        db.connect();
        String query = "delete from tim where namaKompetisi = '"+nama+"'";
        db.execute(query);
        query = "delete from kompetisi where namaKompetisi = '"+nama+"'";
        db.execute(query);
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
    public void selectKompetisi(String nama){
        String query="select namaKompetisi, maxTim from kompetisi where namaKompetisi='"+nama+"' LIMIT 1";
        ResultSet rs=db.getData(query);
        try {
            while(rs.next()){
                this.maxTim=rs.getInt("maxTim");
                this.nama=rs.getString("namaKompetisi");
             }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
