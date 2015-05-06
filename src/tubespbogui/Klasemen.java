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
    
    public int getnPertandingan() {
        return nPertandingan;
    }

    public int getPekan() {
        return pekan;
    }
}
