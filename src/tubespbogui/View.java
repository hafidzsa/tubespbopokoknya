/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubespbogui;

import java.util.Arrays;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author hafidz
 */
public class View extends javax.swing.JFrame {

    /**
     * Creates new form View
     */
    private Kompetisi komp;
    private database db;
    public View() {
        initComponents();
        komp = new Kompetisi();
        dummy.setText(komp.getNama());
        fillListKompetisi();
    }
    private void fillListKompetisi(){
        DefaultListModel m = new DefaultListModel();
        String allData = komp.getListKompetisi();
        String[] dataTuple = allData.split(" \n");
        String[][] data = new String[dataTuple.length][];
        String view;
        for (int i = 0; i < dataTuple.length;i++){
            data[i] = dataTuple[i].split(" ; ");
            view = Arrays.toString(data[i]);
            view = view.replaceAll("[^A-Za-z]+", "");
            m.addElement(view);
        }
        lKompetisi.setModel(m);
    }
    private void fillListTim(){
        DefaultListModel m = new DefaultListModel();
        String allData = komp.getListTim();
        String[] dataTuple = allData.split(" \n");
        String[][] data = new String[dataTuple.length][];
        String view;
        for (int i = 0; i < dataTuple.length;i++){
            data[i] = dataTuple[i].split(" ; ");
            view = Arrays.toString(data[i]);
            view = view.replaceAll("[^A-Za-z]+", "");
            m.addElement(view);
        }
        lListTim.setModel(m);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelWelcome = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pKompetisi = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tNamaKompetisi = new javax.swing.JTextField();
        tMaxTim = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lKompetisi = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDetail = new javax.swing.JTextArea();
        bAddKompetisi = new javax.swing.JButton();
        bEditKompetisi = new javax.swing.JButton();
        bPilihKompetisi = new javax.swing.JButton();
        bDeleteKompetisi = new javax.swing.JButton();
        pPendaftaran = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tNamaTim = new javax.swing.JTextField();
        bAddTim = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cNamaTim = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tNamaPEmain = new javax.swing.JTextField();
        tNoPunggung = new javax.swing.JTextField();
        cPosisi = new javax.swing.JComboBox();
        bAddPemain = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        lListTim = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabListPemain = new javax.swing.JTable();
        bEdit = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        dummy = new javax.swing.JLabel();
        pKlasemen = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabKlasemen = new javax.swing.JTable();
        bMulaiPertandingan = new javax.swing.JButton();
        pPertandingan = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        labTim1 = new javax.swing.JLabel();
        labTim2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tNopunggungGoal1 = new javax.swing.JTextField();
        tNoPunggungGoal2 = new javax.swing.JTextField();
        spinGoal1 = new javax.swing.JSpinner();
        spinGoal2 = new javax.swing.JSpinner();
        bSetButton2 = new javax.swing.JButton();
        bSetButton1 = new javax.swing.JButton();
        bEndPertandingan = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelWelcome.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        labelWelcome.setText("Welcome to Futsal Application...");

        jLabel1.setText("Nama Kompetisi :");

        jLabel2.setText("Maksimal Tim     :");

        tNamaKompetisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNamaKompetisiActionPerformed(evt);
            }
        });

        lKompetisi.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lKompetisi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lKompetisiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lKompetisi);

        taDetail.setColumns(20);
        taDetail.setRows(5);
        taDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("Detil"));
        taDetail.setEnabled(false);
        jScrollPane2.setViewportView(taDetail);

        bAddKompetisi.setText("Add");
        bAddKompetisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddKompetisiActionPerformed(evt);
            }
        });

        bEditKompetisi.setText("Edit");
        bEditKompetisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditKompetisiActionPerformed(evt);
            }
        });

        bPilihKompetisi.setText("Pilih");
        bPilihKompetisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPilihKompetisiActionPerformed(evt);
            }
        });

        bDeleteKompetisi.setText("Delete");

        javax.swing.GroupLayout pKompetisiLayout = new javax.swing.GroupLayout(pKompetisi);
        pKompetisi.setLayout(pKompetisiLayout);
        pKompetisiLayout.setHorizontalGroup(
            pKompetisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pKompetisiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pKompetisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pKompetisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pKompetisiLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tNamaKompetisi, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pKompetisiLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tMaxTim, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bAddKompetisi))
                .addGap(18, 18, 18)
                .addGroup(pKompetisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pKompetisiLayout.createSequentialGroup()
                        .addComponent(bEditKompetisi)
                        .addGap(18, 18, 18)
                        .addComponent(bPilihKompetisi)
                        .addGap(18, 18, 18)
                        .addComponent(bDeleteKompetisi)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pKompetisiLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        pKompetisiLayout.setVerticalGroup(
            pKompetisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pKompetisiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pKompetisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pKompetisiLayout.createSequentialGroup()
                        .addGroup(pKompetisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tNamaKompetisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pKompetisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tMaxTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bAddKompetisi)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(11, 11, 11)
                .addGroup(pKompetisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bEditKompetisi)
                    .addComponent(bPilihKompetisi)
                    .addComponent(bDeleteKompetisi))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Kompetisi", pKompetisi);

        pPendaftaran.setEnabled(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pendaftaran TIm"));

        jLabel3.setText("Nama Tim :");

        bAddTim.setText("Add");
        bAddTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tNamaTim))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bAddTim)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tNamaTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAddTim))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pendaftaran Pemain"));

        jLabel4.setText("Nama Tim :");

        cNamaTim.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Nama Pemain :");

        jLabel6.setText("No Punggung :");

        jLabel7.setText("Posisi :");

        cPosisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bAddPemain.setText("Add");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cNamaTim, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tNamaPEmain))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tNoPunggung)
                            .addComponent(cPosisi, 0, 98, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bAddPemain)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cNamaTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tNamaPEmain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tNoPunggung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cPosisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAddPemain))
        );

        lListTim.setBorder(javax.swing.BorderFactory.createTitledBorder("List Tim"));
        lListTim.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(lListTim);

        tabListPemain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tabListPemain);

        bEdit.setText("Edit");

        bDelete.setText("Delete");

        dummy.setText("jLabel9");

        javax.swing.GroupLayout pPendaftaranLayout = new javax.swing.GroupLayout(pPendaftaran);
        pPendaftaran.setLayout(pPendaftaranLayout);
        pPendaftaranLayout.setHorizontalGroup(
            pPendaftaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPendaftaranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPendaftaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pPendaftaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pPendaftaranLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pPendaftaranLayout.createSequentialGroup()
                        .addComponent(bEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dummy)))
                .addContainerGap())
        );
        pPendaftaranLayout.setVerticalGroup(
            pPendaftaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPendaftaranLayout.createSequentialGroup()
                .addGroup(pPendaftaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pPendaftaranLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pPendaftaranLayout.createSequentialGroup()
                        .addGroup(pPendaftaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pPendaftaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bEdit)
                            .addComponent(bDelete)
                            .addComponent(dummy))))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pendaftaran", pPendaftaran);

        tabKlasemen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tabKlasemen);

        bMulaiPertandingan.setText("Mulai Pertandingan");

        javax.swing.GroupLayout pKlasemenLayout = new javax.swing.GroupLayout(pKlasemen);
        pKlasemen.setLayout(pKlasemenLayout);
        pKlasemenLayout.setHorizontalGroup(
            pKlasemenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pKlasemenLayout.createSequentialGroup()
                .addContainerGap(380, Short.MAX_VALUE)
                .addComponent(bMulaiPertandingan)
                .addContainerGap())
            .addGroup(pKlasemenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pKlasemenLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5)
                    .addContainerGap()))
        );
        pKlasemenLayout.setVerticalGroup(
            pKlasemenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pKlasemenLayout.createSequentialGroup()
                .addContainerGap(235, Short.MAX_VALUE)
                .addComponent(bMulaiPertandingan)
                .addContainerGap())
            .addGroup(pKlasemenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pKlasemenLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(41, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Klasemen", pKlasemen);

        jLabel8.setFont(new java.awt.Font("Tunga", 1, 24)); // NOI18N
        jLabel8.setText("vs");

        labTim1.setFont(new java.awt.Font("Tunga", 1, 24)); // NOI18N
        labTim1.setText("TIM 1");

        labTim2.setFont(new java.awt.Font("Tunga", 1, 24)); // NOI18N
        labTim2.setText("TIM 2");

        jLabel11.setText("No Punggung Pemain :");

        jLabel12.setText("No Punggung Pemain :");

        bSetButton2.setText("Set Goal");

        bSetButton1.setText("Set Goal");

        bEndPertandingan.setText("Akhiri Pertandingan");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder("Live Update Goal"));
        jScrollPane6.setViewportView(jTextArea1);

        javax.swing.GroupLayout pPertandinganLayout = new javax.swing.GroupLayout(pPertandingan);
        pPertandingan.setLayout(pPertandinganLayout);
        pPertandinganLayout.setHorizontalGroup(
            pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPertandinganLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPertandinganLayout.createSequentialGroup()
                        .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tNopunggungGoal1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(bSetButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPertandinganLayout.createSequentialGroup()
                                .addComponent(labTim1)
                                .addGap(32, 32, 32)))
                        .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pPertandinganLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bSetButton2)
                                    .addComponent(jLabel12)
                                    .addComponent(tNoPunggungGoal2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(66, 66, 66))
                            .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pPertandinganLayout.createSequentialGroup()
                                    .addGap(79, 79, 79)
                                    .addComponent(jLabel8)
                                    .addGap(117, 117, 117)
                                    .addComponent(labTim2)
                                    .addGap(89, 89, 89))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pPertandinganLayout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addComponent(spinGoal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(59, 59, 59)
                                    .addComponent(spinGoal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(210, 210, 210)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPertandinganLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bEndPertandingan)
                        .addContainerGap())))
        );
        pPertandinganLayout.setVerticalGroup(
            pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPertandinganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTim1)
                    .addComponent(jLabel8)
                    .addComponent(labTim2))
                .addGap(5, 5, 5)
                .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pPertandinganLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(10, 10, 10)
                        .addComponent(tNopunggungGoal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pPertandinganLayout.createSequentialGroup()
                        .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinGoal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinGoal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tNoPunggungGoal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSetButton2)
                    .addComponent(bSetButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bEndPertandingan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pertandingan", pPertandingan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelWelcome)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelWelcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tNamaKompetisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNamaKompetisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNamaKompetisiActionPerformed

    private void bAddKompetisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddKompetisiActionPerformed
        // TODO add your handling code here:
        komp = new Kompetisi(((String) tNamaKompetisi.getText()),(Integer.valueOf((String) tMaxTim.getText())));
        komp.saveKompetisi();
        taDetail.append("Data berhasil dimasukan \n");
        String allData = komp.getListKompetisi();
        String[] dataTuple = allData.split(" \n");
        String[][] data = new String[dataTuple.length][];
        for (int i = 0; i < dataTuple.length;i++){
            data[i] = dataTuple[i].split(" ; ");
            taDetail.append(dataTuple[i]);
        }
        
        fillListKompetisi();
    }//GEN-LAST:event_bAddKompetisiActionPerformed

    private void bEditKompetisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditKompetisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bEditKompetisiActionPerformed

    private void bPilihKompetisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPilihKompetisiActionPerformed
        // TODO add your handling code here:
        komp.selectKompetisi(lKompetisi.getSelectedValue().toString());
        fillListTim();
        JOptionPane.showMessageDialog(null, "Kompetisi Berhasil terpilih", "Sukses", JOptionPane.WARNING_MESSAGE);
        cNamaTim.addItem(evt);
    }//GEN-LAST:event_bPilihKompetisiActionPerformed

    private void lKompetisiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lKompetisiMouseClicked
        // TODO add your handling code here:
        String temp=komp.getDetilKompetisi(lKompetisi.getSelectedValue().toString());
        taDetail.setText(temp);
    }//GEN-LAST:event_lKompetisiMouseClicked

    private void bAddTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddTimActionPerformed
        // TODO add your handling code here:
        komp.addTim(tNamaTim.getText());
        fillListTim();
        
    }//GEN-LAST:event_bAddTimActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddKompetisi;
    private javax.swing.JButton bAddPemain;
    private javax.swing.JButton bAddTim;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bDeleteKompetisi;
    private javax.swing.JButton bEdit;
    private javax.swing.JButton bEditKompetisi;
    private javax.swing.JButton bEndPertandingan;
    private javax.swing.JButton bMulaiPertandingan;
    private javax.swing.JButton bPilihKompetisi;
    private javax.swing.JButton bSetButton1;
    private javax.swing.JButton bSetButton2;
    private javax.swing.JComboBox cNamaTim;
    private javax.swing.JComboBox cPosisi;
    private javax.swing.JLabel dummy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JList lKompetisi;
    private javax.swing.JList lListTim;
    private javax.swing.JLabel labTim1;
    private javax.swing.JLabel labTim2;
    private javax.swing.JLabel labelWelcome;
    private javax.swing.JPanel pKlasemen;
    private javax.swing.JPanel pKompetisi;
    private javax.swing.JPanel pPendaftaran;
    private javax.swing.JPanel pPertandingan;
    private javax.swing.JSpinner spinGoal1;
    private javax.swing.JSpinner spinGoal2;
    private javax.swing.JTextField tMaxTim;
    private javax.swing.JTextField tNamaKompetisi;
    private javax.swing.JTextField tNamaPEmain;
    private javax.swing.JTextField tNamaTim;
    private javax.swing.JTextField tNoPunggung;
    private javax.swing.JTextField tNoPunggungGoal2;
    private javax.swing.JTextField tNopunggungGoal1;
    private javax.swing.JTextArea taDetail;
    private javax.swing.JTable tabKlasemen;
    private javax.swing.JTable tabListPemain;
    // End of variables declaration//GEN-END:variables
}
