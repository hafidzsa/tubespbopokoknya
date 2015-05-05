package tubespbogui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


class Tim extends db_tim {
    private int idTim;
    private String nama;
    private int win = 0, lose = 0, draw = 0, nMember = 0, point = 0;
    public Tim(){}
    public Tim(String nama) {
        this.nama = nama;
    }
    public Tim(String namaKompetisi, int id){
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
    public void saveTim(String namaKompetisi,int maxTim){
        super.addTim(this.nama, namaKompetisi,maxTim);
    }
    public void editTim(String namaKompetisi,String tempNama){
        super.updateTim(this.nama, namaKompetisi, tempNama);
    }
    public void removeTim(String namaKompetisi){
        super.deleteTim(this.nama, namaKompetisi);
        System.out.println(nama+"+"+namaKompetisi);
    }
    public void updateHasilPertandingan(){
        super.hasilPertandingan(this);
    }
    public Tim[] selectAllTim(String namaKompetisi){
        try {
            String query="select * from tim where namaKompetisi='"+namaKompetisi+"'";
            ResultSet rs=db.getData(query);
            while(rs.next()){
                this.idTim=rs.getInt("idTim");
                this.nama=rs.getString("namaTim");
                this.win=rs.getInt("win");
                this.lose=rs.getInt("lose");
                this.draw=rs.getInt("draw");
                this.point=rs.getInt("point");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void selectTim(String namaTim, String namaKompetisi){
        try {
            String query="select * from tim where namaTim='"+namaTim+"' and namaKompetisi='"+namaKompetisi+"' LIMIT 1";
            ResultSet rs=db.getData(query);
            while(rs.next()){
                this.idTim=rs.getInt("idTim");
                this.nama=rs.getString("namaTim");
                this.win=rs.getInt("win");
                this.lose=rs.getInt("lose");
                this.draw=rs.getInt("draw");
                this.point=rs.getInt("point");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void selectTimById(int id, String namaKompetisi){
        try {
            String query="select * from tim where idTim='"+id+"' and namaKompetisi='"+namaKompetisi+"' LIMIT 1";
            ResultSet rs=db.getData(query);
            while(rs.next()){
                this.idTim=rs.getInt("idTim");
                this.nama=rs.getString("namaTim");
                this.win=rs.getInt("win");
                this.lose=rs.getInt("lose");
                this.draw=rs.getInt("draw");
                this.point=rs.getInt("point");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //scoring
    public void win() {
        this.point += 3;
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
    public int getId(){
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
}
