package tubespbogui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Pertandingan extends db_pertandingan {

    database db;
    Klasemen k;
    private Tim tim1, tim2;
    private int goalTim1, goalTim2, status, pekan, id;

    public Pertandingan() {
        db = new database();
        db.connect();
    }

    public Pertandingan(Tim tim1, Tim tim2) {
        this.tim1 = tim1;
        this.tim2 = tim2;
        db = new database();
        db.connect();
    }

    public void setHasilPertandingan() {
        boolean match = true;
        if (match) {
            if (goalTim1 > goalTim2) {
                tim1.win();
                tim2.lose();
            } else if (goalTim1 < goalTim2) {
                tim1.lose();
                tim2.win();
            } else {
                tim1.draw();
                tim2.draw();
            }
        }
        tim1.updateHasilPertandingan();
        tim2.updateHasilPertandingan();
        super.setHasil(this);
    }

    public int getPekan() {
        return pekan;
    }

    public Tim getTim1() {
        return tim1;
    }

    public Tim getTim2() {
        return tim2;
    }

    public int getJumlahPertandinganKompetisi(String namaKompetisi) {
        return super.getJumlahPertandingan(namaKompetisi);
    }

    public void savePertandingan(String namaKompetisi) {
        super.addPertandingan(namaKompetisi, this);
    }

    public void setPekan(int pekan) {
        this.pekan = pekan;
    }

    public void selectPertandingan(String namaKompetisi) {
        String query = "select*from pertandingan where namaKompetisi='" + namaKompetisi + "' and status=false order by idPertandingan asc LIMIT 1 ";
        db.execute(query);
        ResultSet rs = db.getData(query);
        try {
            while (rs.next()) {
                this.id = rs.getInt("idPertandingan");
                this.tim1 = new Tim(namaKompetisi, rs.getInt("tim1"));
                this.tim2 = new Tim(namaKompetisi, rs.getInt("tim2"));
                this.goalTim1 = rs.getInt("goalTim1");
                this.goalTim2 = rs.getInt("goalTim2");
                this.pekan = rs.getInt("pekan");
                this.status = rs.getInt("status");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kompetisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setGoalTim1() {
        this.goalTim1++;
    }

    public void setGoalTim2() {
        this.goalTim2++;
    }

    public void annGoalTim1() {
        this.goalTim1--;
    }

    public void annGoalTim2() {
        this.goalTim2--;
    }

    public int getGoalTim1() {
        return goalTim1;
    }

    public int getGoalTim2() {
        return goalTim2;
    }

    public int getId() {
        return id;
    }
}
