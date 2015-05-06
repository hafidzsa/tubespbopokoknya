package tubespbogui;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Klasemen {

    private Scanner input;
    private int nTim;
    private Tim[] daftarPeserta;
    private int nPertandingan;
    private Pertandingan[] tanding;
    private int pekan;
    private boolean setSuffle = false;
    private Tim tim;

    public Klasemen() {
        tanding = new Pertandingan[50];
        tim = new Tim();
    }

    public void allTim(String namaKompetisi) {
        String allData = tim.getListTim(namaKompetisi);
        String[] dataTuple = allData.split(" \n");
        daftarPeserta = new Tim[dataTuple.length];
        nTim = dataTuple.length;
        String[][] data = new String[dataTuple.length][];
        String view;
        int i = 0;
        while (i < dataTuple.length) {
            data[i] = dataTuple[i].split(" ; ");
            view = Arrays.toString(data[i]);
            view = view.replaceAll("\\[", "").replaceAll("\\]", "");
            Tim tmpTim = new Tim();
            tmpTim.selectTim(view, namaKompetisi);
            daftarPeserta[i] = tmpTim;
            System.out.println(daftarPeserta[i].getNama());
            i++;
        }
        System.out.println("2");
        for (Tim daftarPeserta1 : daftarPeserta) {
            System.out.println(daftarPeserta1.getNama());
        }
    }

    public void setMatch(String namaKompetisi) {
        int index = 0;
        for (int j = 0; j < nTim; j++) {
            for (int l = j + 1; l < nTim; l++) {
                tanding[index] = new Pertandingan(daftarPeserta[j], daftarPeserta[l]);
                index++;
            }
        }
        nPertandingan = index;
    }

    public void suffle() {
        Random rnd = new Random();
        rnd.nextInt();
        for (int i = 0; i < nTim; i++) {
            int change = i + rnd.nextInt(nTim - i);
            int index = rnd.nextInt(i + 1);
            Pertandingan a = tanding[index];
            tanding[index] = tanding[i];
            tanding[i] = a;
        }

    }

    public void setPekan(String namaKompetisi) {
        for (int i = 0; i < nPertandingan; i++) {
            tanding[i].setPekan(i + 1);
            System.out.println(tanding[i].getTim1().getNama() + " vs " + tanding[i].getTim2().getNama());
            tanding[i].savePertandingan(namaKompetisi);
        }
    }

    public void setPertandingan(String namaKompetisi) {
        setMatch(namaKompetisi);
        suffle();
        setPekan(namaKompetisi);
    }
    /*
     public void menu(Tim[] daftartim, int jumTim) {
     int pil = 0;
     this.daftarPeserta = daftartim;
     if (this.nTim != jumTim) {
     this.nTim = jumTim;
     suffle();
     }
     System.out.println("Jumlah Pertandingan : " + getnPertandingan());
     input = new Scanner(System.in);
     do {
     System.out.println("1.Lihat Jadwal Pertandingan");
     if (getPekan() != getnPertandingan()) {
     System.out.println("2.Mulai Pertandingan Pekan ke " + (getPekan() + 1));
     } else {
     System.out.println("Semua Pertandingan telah selesai");
     }
     System.out.println("0.Exit");
     System.out.print("Masukan Angka : ");
     try {
     pil = input.nextInt();
     switch (pil) {
     case 1:
     ViewMatch();
     break;
     case 2:
     mulaiPertandingan();
     break;
     }
     } catch (InputMismatchException e) {
     System.out.println("Input harus angka");
     } finally {
     input = new Scanner(System.in);
     }
     } while (pil != 0);
     }

     public void addPeserta(Tim p) {
     daftarPeserta[nTim] = p;
     nTim++;
     }

     public Tim getPeserta(int x) {
     return daftarPeserta[x];
     }

     public Tim[] getDaftarTim() {
     return daftarPeserta;
     }

     public int getnTim() {
     return nTim;
     }

     public String toString() {
     return "nullExceptionLoh!!!";
     }

     // Set Pertandingan siapa lawan siapa :p
   

     public void ViewMatch() {
     for (int i = 0; i < getnPertandingan(); i++) {
     System.out.println("Pekan " + (i + 1) + " : " + tanding[i].getTim1().getNama() + " VS " + tanding[i].getTim2().getNama());

     }
     }

  

     public void mulaiPertandingan() {
     int pil;
     int goalTim1 = 0;
     int goalTim2 = 0;
     boolean local_cek;
     do {
     System.out.println("");
     System.out.println(tanding[getPekan()].getTim1().getNama() + " VS " + tanding[getPekan()].getTim2().getNama());
     System.out.println("1.Goal Tim " + tanding[getPekan()].getTim1().getNama());
     System.out.println("2.Goal Tim " + tanding[getPekan()].getTim2().getNama());
     System.out.println("9.Selesai");
     System.out.print("Input Pilihan : ");
     pil = input.nextInt();
     switch (pil) {
     case 1:
     local_cek = false;
     do {
     tanding[getPekan()].getTim1().viewMember();
     System.out.print("Nomor punggung : ");
     try {
     tanding[getPekan()].inputGoal(input.nextInt(), tanding[getPekan()].getTim1());
     goalTim1++;
     local_cek = true;
     } catch (ArrayIndexOutOfBoundsException e) {
     System.out.println("Nomor Punggung tidak ada");
     } finally {
     input = new Scanner(System.in);
     }
     } while (local_cek != true);
     break;
     case 2:
     local_cek = false;
     do {
     tanding[getPekan()].getTim2().viewMember();
     System.out.print("Nomor punggung : ");
     try {
     tanding[getPekan()].inputGoal(input.nextInt(), tanding[getPekan()].getTim2());
     goalTim2++;
     local_cek = true;
     } catch (ArrayIndexOutOfBoundsException e) {
     System.out.println("Nomor Punggung tidak ada");
     } finally {
     input = new Scanner(System.in);
     }
     } while (local_cek != true);
     break;
     }
     } while (pil != 9);
     tanding[getPekan()].summary(goalTim1, goalTim2);
     tanding[getPekan()].setHasilPertandingan(goalTim1, goalTim2);
     pekan += 1;
     }

     /**
     * @return the nPertandingan
     */

    public int getnPertandingan() {
        return nPertandingan;
    }

    /**
     * @return the pekan
     */
    public int getPekan() {
        return pekan;
    }
}
