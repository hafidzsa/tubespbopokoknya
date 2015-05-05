package tubespbogui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


class Pertandingan extends db_pertandingan {
    database db;
    Klasemen k;
    private Tim tim1, tim2;
    private int goalTim1,goalTim2,status,pekan;
    public Pertandingan(){
        db=new database();
        db.connect();
    }
    public Pertandingan(Tim tim1, Tim tim2) {
        this.tim1 = tim1;
        this.tim2 = tim2;
        db=new database();
        db.connect();
    }
    public void setHasilPertandingan(int scoreTim1, int scoreTim2) {
        boolean match = true;
        if (match) {
            if (scoreTim1 > scoreTim2) {
                tim1.win();
                tim2.lose();
            } else if (scoreTim1 < scoreTim2) {
                tim1.lose();
                tim2.win();
            } else {
                tim1.draw();
                tim2.draw();
            }
        }
    }
    public int getPekan(){
        return pekan;
    }
    public Tim getTim1() {
        return tim1;
    }

    public Tim getTim2() {
        return tim2;
    }
    
    public void savePertandingan(String namaKompetisi){
        super.addPertandingan(namaKompetisi, this);
    }
    public void setPekan(int pekan){
        this.pekan=pekan;
    }
    public void selectPertandingan(String namaKompetisi){
        String query="select*from pertandingan where namaKompetisi='"+namaKompetisi+"' and status=false order by idPertandingan asc LIMIT 1 ";
        db.execute(query);
        ResultSet rs=db.getData(query);
        try{
        while(rs.next()){
                this.tim1=new Tim(namaKompetisi,rs.getInt("tim1"));
                this.tim2=new Tim(namaKompetisi,rs.getInt("tim2"));
                this.goalTim1=rs.getInt("goalTim1");
                this.goalTim2=rs.getInt("goalTim2");
                this.pekan=rs.getInt("pekan");
                this.status=rs.getInt("status");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setGoalTim1(){
        this.goalTim1++;
    }
    public void setGoalTim2(){
        this.goalTim2++;
    }
    public int getGoalTim1(){
        return goalTim1;
    }
    public int getGoalTim2(){
        return goalTim2;
    }
    public void mulaiPertandingan(){
        
    }
/*
    public void inputGoal(int noPunggung, Tim tim) {
        tim.getMember(noPunggung).setTmpGoal();
    }

    public void summary(int scoreTim1, int scoreTim2) {
        System.out.println("\nHasil Pertandingan :");
        System.out.println("Tim : " + tim1.getNama() + " Goal : " + scoreTim1);
        for (int i = 0; i < tim1.getNMember(); i++) {
            if (tim1.getMemberByIndex(i).getTmpGoal() != 0) {
                Pemain p = tim1.getMemberByIndex(i);
                System.out.println(p.getNama() + " Jumlah Goal : " + p.getTmpGoal());
                p.setGoal();
            }
        }
        System.out.println("Tim : " + tim2.getNama() + " Goal : " + scoreTim2);
        for (int i = 0; i < tim2.getNMember(); i++) {
            if (tim2.getMemberByIndex(i).getTmpGoal() != 0) {
                Pemain p = tim2.getMemberByIndex(i);
                System.out.println(p.getNama() + " Jumlah Goal : " + p.getTmpGoal());
                p.setGoal();
            }
        }
        System.out.println("");
    }
    */
}
