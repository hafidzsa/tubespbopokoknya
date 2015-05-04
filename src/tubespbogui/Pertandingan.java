package tubespbogui;


class Pertandingan extends db_pertandingan {

    Klasemen k;
    private Tim tim1, tim2;
    private int pekan;
    public Pertandingan(){
    }
    public Pertandingan(Tim tim1, Tim tim2) {
        this.tim1 = tim1;
        this.tim2 = tim2;
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
