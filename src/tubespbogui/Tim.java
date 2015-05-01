package tubespbogui;


class Tim extends db_tim {
    private String nama;
    private int win = 0, lose = 0, draw = 0, nMember = 0, point = 0;
    public Tim(){}
    public Tim(String nama) {
        this.nama = nama;
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
