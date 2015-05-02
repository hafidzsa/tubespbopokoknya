package tubespbogui;


public class Pemain extends db_pemain implements Comparable<Pemain> {

    private String nama, posisi;
    private int noPunggung, jumlahGoal, tmpGoal;
    public Pemain(){}
    public Pemain(String nama, int noPunggung, String posisi) {
        this.nama = nama;
        this.noPunggung = noPunggung;
        this.posisi = posisi;
    }

    //getter
    public String getNama() {
        return nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public int getNoPunggung() {
        return noPunggung;
    }

    public int getTmpGoal() {
        return tmpGoal;
    }

    public int getJumlahGoal() {
        return jumlahGoal;
    }

    //setter
    public void setGoal() {
        this.jumlahGoal += tmpGoal;
        this.tmpGoal = 0;
    }

    public void setTmpGoal() {
        this.tmpGoal++;
    }

    @Override
    public String toString() {
        return "nullExceptionLoh!!!";
    }

    @Override
    public int compareTo(Pemain comparePemain) {
        int compareJumlahGoal = ((Pemain) comparePemain).getJumlahGoal();
        return compareJumlahGoal - this.jumlahGoal;
    }
    public void savePemain(String namaKompetisi,String namaTim,String namaPemain,int noPunggung,String posisi){
        super.addPemain(namaKompetisi,namaTim,namaPemain,noPunggung,posisi);
    }
}
