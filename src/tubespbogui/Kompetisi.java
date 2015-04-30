package tubespbogui;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.regex.*;

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

    public void addTim(String nama) {
        
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
        String query = "delete from kompetisi where namaKompetisi = '"+nama+"'";
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
}
