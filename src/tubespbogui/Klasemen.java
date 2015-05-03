package tubespbogui;


import static java.lang.Math.random;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

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
        tim=new Tim();
    }
    public void allTim(String namaKompetisi){
        DefaultComboBoxModel m1 = new DefaultComboBoxModel();
        DefaultListModel m2 = new DefaultListModel();
        String allData = tim.getListTim(namaKompetisi);
        String[] dataTuple = allData.split(" \n");
        String[][] data = new String[dataTuple.length][];
        String view;
        for (int i = 0; i < dataTuple.length;i++){
            data[i] = dataTuple[i].split(" ; ");
            view = Arrays.toString(data[i]);
            view = view.replaceAll("[^A-Za-z]+", "");
            System.out.println(view);
        }
    }
    public void setMatch(String namaKompetisi) {
        int index = 0;
        for (int j = 0; j < nTim; j++) {
            for (int l = j + 1; l < nTim; l++) {
                tanding[index] = new Pertandingan(daftarPeserta[j], daftarPeserta[l]);
                tanding[index].savePertandingan(namaKompetisi);
                index++;
            }
        }
        nPertandingan = index;
    }
    public void suffle(String namaKompetisi) {
        setMatch(namaKompetisi);
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
