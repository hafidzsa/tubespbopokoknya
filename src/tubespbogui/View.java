/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubespbogui;

import com.sun.glass.events.KeyEvent;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hafidz
 */
public class View extends javax.swing.JFrame {

    /**
     * Creates new form View
     */
    private Kompetisi tmpKomp;
    private database db;
    private Tim tim;
    private Pemain pemain;
    private Klasemen klasemen;
    private Pertandingan pertandingan;

    public View() {
        initComponents();
        this.setLocationRelativeTo(null);
        tmpKomp = new Kompetisi();
        tim = new Tim();
        pemain = new Pemain();
        klasemen = new Klasemen();
        pertandingan = new Pertandingan();
        fillListKompetisi();
        firstLaunch();
        startPane();
    }

    private void firstLaunch() {
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(3, false);
        jTabbedPane1.setEnabledAt(4, false);
    }

    private void startPane() {
        bEditKompetisi.setVisible(false);
        bDeleteKompetisi.setVisible(false);
        bPilihKompetisi.setVisible(false);
        bOKEditKompetisi.setVisible(false);
        bOKEditTim.setVisible(false);
        bOKEditPemain.setVisible(false);
        bCancelEditKompetisi.setVisible(false);
        bCancelEditTim.setVisible(false);
        bCancelEditPemain.setVisible(false);
        bAddKompetisi.setVisible(true);
        bAddTim.setVisible(true);
        bAddPemain.setVisible(true);
    }

    private void kompetisiSelected() {
        bEditKompetisi.setVisible(true);
        bDeleteKompetisi.setVisible(true);
        bPilihKompetisi.setVisible(true);
    }

    private void fillListKompetisi() {
        DefaultListModel m = new DefaultListModel();
        String allData = tmpKomp.getListKompetisi();
        String[] dataTuple = allData.split(" \n");
        String[][] data = new String[dataTuple.length][];
        String view;
        for (int i = 0; i < dataTuple.length; i++) {
            data[i] = dataTuple[i].split(" ; ");
            view = Arrays.toString(data[i]);
            view = view.replaceAll("\\[", "").replaceAll("\\]", "");
            m.addElement(view);
        }
        lKompetisi.setModel(m);
    }

    private void fillListTim() {
        DefaultComboBoxModel m1 = new DefaultComboBoxModel();
        DefaultListModel m2 = new DefaultListModel();
        String allData = tim.getListTim(tmpKomp.getNama());
        String[] dataTuple = allData.split(" \n");
        String[][] data = new String[dataTuple.length][];
        String view;
        for (int i = 0; i < dataTuple.length; i++) {
            data[i] = dataTuple[i].split(" ; ");
            view = Arrays.toString(data[i]);
            view = view.replaceAll("\\[", "").replaceAll("\\]", "");
            m1.addElement(view);
            m2.addElement(view);
        }
        cNamaTim.setModel(m1);
        lListTim.setModel(m2);
    }

    private void fillComboPemainTim1(String namaTim) {
        DefaultComboBoxModel m1 = new DefaultComboBoxModel();
        String allData = pemain.getListPemainFull(namaTim, tmpKomp.getNama());
        String[] dataTuple = allData.split(" \n");
        String[][] data = new String[dataTuple.length][];
        String view;
        for (int i = 0; i < dataTuple.length; i++) {
            data[i] = dataTuple[i].split(" ; ");
            view = data[i][1];
            m1.addElement(view);
        }
        pemainTim1.setModel(m1);
    }

    private void fillComboPemainTim2(String namaTim) {
        DefaultComboBoxModel m1 = new DefaultComboBoxModel();
        String allData = pemain.getListPemainFull(namaTim, tmpKomp.getNama());
        String[] dataTuple = allData.split(" \n");
        String[][] data = new String[dataTuple.length][];
        String view;
        for (int i = 0; i < dataTuple.length; i++) {
            data[i] = dataTuple[i].split(" ; ");
            view = data[i][1];
            m1.addElement(view);
        }
        pemainTim2.setModel(m1);
    }

    private void filltableTim() {
        tmpKomp.selectKompetisi(lKompetisi.getSelectedValue().toString());
        tim.selectTim(lListTim.getSelectedValue().toString(), tmpKomp.getNama());
        String allData = pemain.getListPemainFull(tim.getNama(), tmpKomp.getNama());
        String[] dataTuple = allData.split(" \n");
        String[][] data = new String[dataTuple.length][];
        for (int i = 0; i < dataTuple.length; i++) {
            data[i] = dataTuple[i].split(" ; ");
        }
        String[] title = {"Nama ", "No punggung", "Posisi", "Jumlah gol"};
        tabListPemain.setModel(new DefaultTableModel(data, title));
    }

    private void filltableKlasemen() {
        String allData = tim.getListTimFull(tmpKomp.getNama());
        String[] dataTuple = allData.split(" \n");
        String[][] data = new String[dataTuple.length][];
        for (int i = 0; i < dataTuple.length; i++) {
            data[i] = dataTuple[i].split(" ; ");
        }
        String[] title = {"Nama TIM", "Win", "Lose", "Draw", "Point"};
        tabKlasemen.setModel(new DefaultTableModel(data, title));
    }

    private void filltableJadwalPertandingan() {
        if (tmpKomp.getJumlahTimKompetisi() < 3) {
            JOptionPane.showMessageDialog(null, "Tim yang dimasukkan minimal 3", "Peringatan", JOptionPane.WARNING_MESSAGE);
            tmpKomp.updateStatusTutupPendaftaran(false);
        } else {
            String allData = pertandingan.getListJadwal(tmpKomp.getNama());
            String[] dataTuple = allData.split(" \n");
            String[][] data = new String[dataTuple.length][];
            String tim1, tim2, status;
            Tim tmpTim = new Tim();
            for (int i = 0; i < dataTuple.length; i++) {
                data[i] = dataTuple[i].split(" ; ");
                tmpTim.selectTimById(Integer.parseInt(data[i][0]), tmpKomp.getNama());
                tim1 = tmpTim.getNama();
                tmpTim = new Tim();
                tmpTim.selectTimById(Integer.parseInt(data[i][2]), tmpKomp.getNama());
                tim2 = tmpTim.getNama();
                if (data[i][5].endsWith("0")) {
                    status = "Pertandingan belum dimulai";
                } else {
                    status = "Pertandingan Selesai";
                }
                data[i][0] = tim1;
                data[i][2] = tim2;
                data[i][5] = status;
            }
            String[] title = {"Tim 1", "Goal Tim 1", "Tim 2", "Goal Tim 2", "Pekan", "Status"};
            tJadwalPertandingan.setModel(new DefaultTableModel(data, title));
        }
    }

    private void setBukaPendaftaran(boolean a) {
        bEdit.setVisible(a);
        bDelete.setVisible(a);
        bAddTim.setVisible(a);
        bAddPemain.setVisible(a);
        tNamaTim.setEditable(a);
        tNamaPEmain.setEditable(a);
        tNoPunggung.setEditable(a);
        jTabbedPane1.setEnabledAt(2, !(a));
        jTabbedPane1.setEnabledAt(3, !(a));
        tbTutupPendaftaran.setEnabled(a);
    }

    public void setBukaPendaftaranPeserta(boolean a) {
        tNoPunggung.setEditable(a);
        tNamaPEmain.setEditable(a);
        bAddPemain.setVisible(a);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
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
        bOKEditKompetisi = new javax.swing.JButton();
        bCancelEditKompetisi = new javax.swing.JButton();
        pPendaftaran = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tNamaTim = new javax.swing.JTextField();
        bAddTim = new javax.swing.JButton();
        bOKEditTim = new javax.swing.JButton();
        bCancelEditTim = new javax.swing.JButton();
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
        bOKEditPemain = new javax.swing.JButton();
        bCancelEditPemain = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        lListTim = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabListPemain = new javax.swing.JTable();
        bEdit = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        dummy = new javax.swing.JLabel();
        tbTutupPendaftaran = new javax.swing.JToggleButton();
        pKlasemen = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabKlasemen = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        pJadwalPertandingan = new javax.swing.JPanel();
        bMulaiPertandingan = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tJadwalPertandingan = new javax.swing.JTable();
        pPertandingan = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        labTim1 = new javax.swing.JLabel();
        labTim2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        spinGoal1 = new javax.swing.JSpinner();
        spinGoal2 = new javax.swing.JSpinner();
        bSetGoalTim2 = new javax.swing.JButton();
        bSetGoalTim1 = new javax.swing.JButton();
        bEndPertandingan = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tfLiveUpdate = new javax.swing.JTextArea();
        pemainTim2 = new javax.swing.JComboBox();
        pemainTim1 = new javax.swing.JComboBox();
        bAnulirGoalTim1 = new javax.swing.JButton();
        bAnulirGoalTim2 = new javax.swing.JButton();

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane8.setViewportView(jTextArea2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelWelcome.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        labelWelcome.setText("Welcome to Futsal Application...");

        jLabel1.setText("Nama Kompetisi :");

        jLabel2.setText("Maksimal Tim     :");

        tMaxTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tMaxTimKeyTyped(evt);
            }
        });

        lKompetisi.setBorder(javax.swing.BorderFactory.createTitledBorder("List Kompetisi"));
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

        taDetail.setEditable(false);
        taDetail.setColumns(20);
        taDetail.setRows(5);
        taDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("Detil"));
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
        bDeleteKompetisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteKompetisiActionPerformed(evt);
            }
        });

        bOKEditKompetisi.setText("OK");
        bOKEditKompetisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOKEditKompetisiActionPerformed(evt);
            }
        });

        bCancelEditKompetisi.setText("Cancel");
        bCancelEditKompetisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelEditKompetisiActionPerformed(evt);
            }
        });

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
                    .addGroup(pKompetisiLayout.createSequentialGroup()
                        .addComponent(bOKEditKompetisi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCancelEditKompetisi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bAddKompetisi)))
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
                        .addGroup(pKompetisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bAddKompetisi)
                            .addComponent(bCancelEditKompetisi)
                            .addComponent(bOKEditKompetisi))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pKompetisiLayout.createSequentialGroup()
                        .addGroup(pKompetisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pKompetisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bDeleteKompetisi)
                    .addComponent(bEditKompetisi)
                    .addComponent(bPilihKompetisi))
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

        bOKEditTim.setText("OK");
        bOKEditTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOKEditTimActionPerformed(evt);
            }
        });

        bCancelEditTim.setText("Cancel");
        bCancelEditTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelEditTimActionPerformed(evt);
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bOKEditTim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCancelEditTim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAddTim)
                    .addComponent(bOKEditTim)
                    .addComponent(bCancelEditTim)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pendaftaran Pemain"));

        jLabel4.setText("Nama Tim :");

        cNamaTim.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "null" }));
        cNamaTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cNamaTimActionPerformed(evt);
            }
        });

        jLabel5.setText("Nama Pemain :");

        jLabel6.setText("No Punggung :");

        jLabel7.setText("Posisi :");

        tNamaPEmain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNamaPEmainKeyTyped(evt);
            }
        });

        tNoPunggung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNoPunggungKeyTyped(evt);
            }
        });

        cPosisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Penyerang", "Gelandang", "Bek", "Kiper" }));

        bAddPemain.setText("Add");
        bAddPemain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddPemainActionPerformed(evt);
            }
        });

        bOKEditPemain.setText("OK");
        bOKEditPemain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOKEditPemainActionPerformed(evt);
            }
        });

        bCancelEditPemain.setText("Cancel");
        bCancelEditPemain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelEditPemainActionPerformed(evt);
            }
        });

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
                            .addComponent(cPosisi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bOKEditPemain)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCancelEditPemain)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAddPemain)
                    .addComponent(bOKEditPemain)
                    .addComponent(bCancelEditPemain)))
        );

        lListTim.setBorder(javax.swing.BorderFactory.createTitledBorder("List Tim"));
        lListTim.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lListTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lListTimMouseClicked(evt);
            }
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
                "Nama", "No Punggung", "Posisi", "Jumlah Gol"
            }
        ));
        jScrollPane4.setViewportView(tabListPemain);

        bEdit.setText("Edit");
        bEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditActionPerformed(evt);
            }
        });

        bDelete.setText("Delete");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        dummy.setText("Nama Kompetisinya");

        tbTutupPendaftaran.setText("Tutup Pendaftaran");
        tbTutupPendaftaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbTutupPendaftaranActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pPendaftaranLayout = new javax.swing.GroupLayout(pPendaftaran);
        pPendaftaran.setLayout(pPendaftaranLayout);
        pPendaftaranLayout.setHorizontalGroup(
            pPendaftaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPendaftaranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPendaftaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pPendaftaranLayout.createSequentialGroup()
                        .addComponent(dummy)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pPendaftaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pPendaftaranLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pPendaftaranLayout.createSequentialGroup()
                        .addComponent(bEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tbTutupPendaftaran)))
                .addContainerGap())
        );
        pPendaftaranLayout.setVerticalGroup(
            pPendaftaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPendaftaranLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dummy)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(pPendaftaranLayout.createSequentialGroup()
                .addGroup(pPendaftaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pPendaftaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bEdit)
                    .addComponent(bDelete)
                    .addComponent(tbTutupPendaftaran))
                .addGap(0, 0, Short.MAX_VALUE))
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

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pKlasemenLayout = new javax.swing.GroupLayout(pKlasemen);
        pKlasemen.setLayout(pKlasemenLayout);
        pKlasemenLayout.setHorizontalGroup(
            pKlasemenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pKlasemenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pKlasemenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .addGroup(pKlasemenLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pKlasemenLayout.setVerticalGroup(
            pKlasemenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pKlasemenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Klasemen", pKlasemen);

        bMulaiPertandingan.setText("Mulai Pertandingan");
        bMulaiPertandingan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMulaiPertandinganActionPerformed(evt);
            }
        });

        tJadwalPertandingan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tJadwalPertandingan);

        javax.swing.GroupLayout pJadwalPertandinganLayout = new javax.swing.GroupLayout(pJadwalPertandingan);
        pJadwalPertandingan.setLayout(pJadwalPertandinganLayout);
        pJadwalPertandinganLayout.setHorizontalGroup(
            pJadwalPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pJadwalPertandinganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pJadwalPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pJadwalPertandinganLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bMulaiPertandingan)))
                .addContainerGap())
        );
        pJadwalPertandinganLayout.setVerticalGroup(
            pJadwalPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pJadwalPertandinganLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bMulaiPertandingan)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Jadwal Pertandingan", pJadwalPertandingan);

        jLabel8.setFont(new java.awt.Font("Tunga", 1, 24)); // NOI18N
        jLabel8.setText("vs");

        labTim1.setFont(new java.awt.Font("Tunga", 1, 24)); // NOI18N
        labTim1.setText("TIM 1");

        labTim2.setFont(new java.awt.Font("Tunga", 1, 24)); // NOI18N
        labTim2.setText("TIM 2");

        jLabel11.setText("No Punggung Pemain :");

        jLabel12.setText("No Punggung Pemain :");

        spinGoal1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        spinGoal1.setEnabled(false);

        spinGoal2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        spinGoal2.setEnabled(false);

        bSetGoalTim2.setText("Set Goal");
        bSetGoalTim2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSetGoalTim2ActionPerformed(evt);
            }
        });

        bSetGoalTim1.setText("Set Goal");
        bSetGoalTim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSetGoalTim1ActionPerformed(evt);
            }
        });

        bEndPertandingan.setText("Akhiri Pertandingan");
        bEndPertandingan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEndPertandinganActionPerformed(evt);
            }
        });

        tfLiveUpdate.setEditable(false);
        tfLiveUpdate.setColumns(20);
        tfLiveUpdate.setRows(5);
        tfLiveUpdate.setBorder(javax.swing.BorderFactory.createTitledBorder("Live Update Goal"));
        jScrollPane6.setViewportView(tfLiveUpdate);

        pemainTim2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        pemainTim1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bAnulirGoalTim1.setText("Anulir Goal");
        bAnulirGoalTim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnulirGoalTim1ActionPerformed(evt);
            }
        });

        bAnulirGoalTim2.setText("Anulir Goal");
        bAnulirGoalTim2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnulirGoalTim2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pPertandinganLayout = new javax.swing.GroupLayout(pPertandingan);
        pPertandingan.setLayout(pPertandinganLayout);
        pPertandinganLayout.setHorizontalGroup(
            pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPertandinganLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pPertandinganLayout.createSequentialGroup()
                        .addComponent(labTim1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(115, 115, 115))
                    .addGroup(pPertandinganLayout.createSequentialGroup()
                        .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(spinGoal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pemainTim1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pPertandinganLayout.createSequentialGroup()
                        .addComponent(spinGoal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labTim2))
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pemainTim2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPertandinganLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bEndPertandingan)
                .addContainerGap())
            .addGroup(pPertandinganLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pPertandinganLayout.createSequentialGroup()
                        .addComponent(bSetGoalTim1)
                        .addGap(18, 18, 18)
                        .addComponent(bAnulirGoalTim1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bSetGoalTim2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bAnulirGoalTim2)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        pPertandinganLayout.setVerticalGroup(
            pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPertandinganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTim1)
                    .addComponent(jLabel8)
                    .addComponent(labTim2)
                    .addComponent(spinGoal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinGoal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pemainTim2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pemainTim1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pPertandinganLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bSetGoalTim1)
                            .addComponent(bAnulirGoalTim1)))
                    .addGroup(pPertandinganLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pPertandinganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bSetGoalTim2)
                            .addComponent(bAnulirGoalTim2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bEndPertandingan)
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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAddKompetisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddKompetisiActionPerformed
        // TODO add your handling code here:
        if (tNamaKompetisi.getText().equals("") || tMaxTim.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informasi yang dimasukkan kurang", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
        if (tNamaKompetisi.getText().toString().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Informasi yang dimasukkan tidak ditemukan huruf", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (Integer.valueOf((String) tMaxTim.getText()) < 3) {
            JOptionPane.showMessageDialog(null, "Jumlah Tim minimal 3", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            tmpKomp = new Kompetisi(((String) tNamaKompetisi.getText()), (Integer.valueOf((String) tMaxTim.getText())));
            tmpKomp.saveKompetisi();
            taDetail.append("Data berhasil dimasukan \n");
            String allData = tmpKomp.getListKompetisi();
            String[] dataTuple = allData.split(" \n");
            String[][] data = new String[dataTuple.length][];
            for (int i = 0; i < dataTuple.length; i++) {
                data[i] = dataTuple[i].split(" ; ");
                taDetail.append(dataTuple[i]);
            }
            fillListKompetisi();
            tNamaKompetisi.setText("");
            tMaxTim.setText("");
        }
    }//GEN-LAST:event_bAddKompetisiActionPerformed

    private void bEditKompetisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditKompetisiActionPerformed
        // TODO add your handling code here:
        bOKEditKompetisi.setVisible(true);
        bCancelEditKompetisi.setVisible(true);
        bAddKompetisi.setVisible(false);
        tmpKomp.selectKompetisi(lKompetisi.getSelectedValue().toString());
        tNamaKompetisi.setText("" + tmpKomp.getNama());
        tMaxTim.setText("" + tmpKomp.getMaxTim());
    }//GEN-LAST:event_bEditKompetisiActionPerformed

    private void bPilihKompetisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPilihKompetisiActionPerformed
        // TODO add your handling code here:
        tmpKomp.selectKompetisi(lKompetisi.getSelectedValue().toString());
        fillListTim();
        JOptionPane.showMessageDialog(null, "Kompetisi Berhasil terpilih", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setSelectedIndex(1);
        dummy.setText("Kompetisi: " + tmpKomp.getNama());
        startPane();
        setBukaPendaftaran(!tmpKomp.getStatusTutupPendaftaran());
        if (tmpKomp.getStatusTutupPendaftaran()) {
            filltableKlasemen();
            filltableJadwalPertandingan();
        }
        pertandingan.selectPertandingan(tmpKomp.getNama());
        bMulaiPertandingan.setText("Mulai pertandingan pekan ke-" + pertandingan.getPekanForLabel(tmpKomp.getNama()));
        if (tmpKomp.getStatusPertandinganBerakhir() && tmpKomp.getStatusTutupPendaftaran()) {
            bMulaiPertandingan.setVisible(false);
        } else {
            bMulaiPertandingan.setVisible(true);
        }
        if (tmpKomp.getJumlahTimKompetisi() == 0) {
            setBukaPendaftaranPeserta(false);
        } else {
            setBukaPendaftaranPeserta(true);
        }
    }//GEN-LAST:event_bPilihKompetisiActionPerformed

    private void lKompetisiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lKompetisiMouseClicked
        // TODO add your handling code here:
        tmpKomp.selectKompetisi(lKompetisi.getSelectedValue().toString());
        String temp = tmpKomp.getDetilKompetisi(lKompetisi.getSelectedValue().toString());
        taDetail.setText(temp);
        kompetisiSelected();
        if (tmpKomp.getStatusPertandinganBerakhir() && tmpKomp.getStatusTutupPendaftaran()) {
            temp = "\n\nTim Pemenang :\n";
            for (Tim t : tmpKomp.getTimPemenangKompetisi()) {
                temp += "Tim: " + t.getNama() + ", Point: " + t.getPoint() + "\n";
            }
            Pemain p = tmpKomp.getTopScorerKompetisi();
            temp += "\nTop Scorer : " + p.getNama()
                    + ", Asal Tim: " + p.getTim()
                    + ", Jumlah Goal: " + p.getJumlahGoal();
            taDetail.append(temp);
        }
    }//GEN-LAST:event_lKompetisiMouseClicked

    private void bAddTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddTimActionPerformed
        // TODO add your handling code here:
        tmpKomp.selectKompetisi(lKompetisi.getSelectedValue().toString());
        if (tNamaTim.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informasi yang dimasukkan kurang", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (tNamaTim.getText().toString().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Informasi yang dimasukkan tidak ditemukan huruf", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            tim = new Tim(tNamaTim.getText());
            tim.saveTim(tmpKomp.getNama(), tmpKomp.getMaxTim());
            fillListTim();
            tNamaTim.setText("");
        }
        setBukaPendaftaranPeserta(true);
    }//GEN-LAST:event_bAddTimActionPerformed

    private void bDeleteKompetisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteKompetisiActionPerformed
        // TODO add your handling code here:
        tmpKomp.selectKompetisi(lKompetisi.getSelectedValue().toString());
        tmpKomp.removeKompetisi();
        fillListKompetisi();
    }//GEN-LAST:event_bDeleteKompetisiActionPerformed

    private void bOKEditKompetisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOKEditKompetisiActionPerformed
        // TODO add your handling code here:
        if (tNamaKompetisi.getText().equals("") || tMaxTim.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informasi yang dimasukkan kurang", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (tNamaKompetisi.getText().toString().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Informasi yang dimasukkan tidak ditemukan huruf", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (Integer.valueOf((String) tMaxTim.getText()) < 3) {
            JOptionPane.showMessageDialog(null, "Jumlah Tim minimal 3", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (Integer.valueOf(tMaxTim.getText()) < tmpKomp.getJumlahTimKompetisi()) {
            JOptionPane.showMessageDialog(null, "Jumlah tim yang telah ada lebih banyak dari informasi yang diinputkan", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            String temp = tmpKomp.getNama();
            tmpKomp.setNama(tNamaKompetisi.getText());
            tmpKomp.setMaxTim(Integer.valueOf(tMaxTim.getText()));
            tmpKomp.updateKompetisi(temp);
            fillListKompetisi();
            startPane();
        }
    }//GEN-LAST:event_bOKEditKompetisiActionPerformed

    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed
        // TODO add your handling code here:
        if (tabListPemain.getSelectedRow() >= 0) {
            //visible button for edit pemain
            if (tabListPemain.getValueAt(tabListPemain.getSelectedRow(), tabListPemain.getSelectedColumn()) != null) {
                bAddPemain.setVisible(false);
                bOKEditPemain.setVisible(true);
                bCancelEditPemain.setVisible(true);
                bAddTim.setVisible(true);
                bOKEditTim.setVisible(false);
                bCancelEditTim.setVisible(false);
                cNamaTim.getModel().setSelectedItem(lListTim.getSelectedValue().toString());
                tNamaPEmain.setText(tabListPemain.getValueAt(tabListPemain.getSelectedRow(), 0).toString());
                tNoPunggung.setText(tabListPemain.getValueAt(tabListPemain.getSelectedRow(), 1).toString());
                cPosisi.getModel().setSelectedItem(tabListPemain.getValueAt(tabListPemain.getSelectedRow(), 2).toString());
            }
        } else if (lListTim.getSelectedValue() != null) {
            //visible button for edit tim
            bAddTim.setVisible(false);
            bOKEditTim.setVisible(true);
            bCancelEditTim.setVisible(true);
            bAddPemain.setVisible(true);
            bOKEditPemain.setVisible(false);
            bCancelEditPemain.setVisible(false);
            tNamaTim.setText(lListTim.getSelectedValue().toString());
        }
    }//GEN-LAST:event_bEditActionPerformed

    private void lListTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lListTimMouseClicked
        // TODO add your handling code here:
        filltableTim();
        tabListPemain.clearSelection();
    }//GEN-LAST:event_lListTimMouseClicked

    private void bCancelEditKompetisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelEditKompetisiActionPerformed
        // TODO add your handling code here:
        bOKEditKompetisi.setVisible(false);
        bAddKompetisi.setVisible(true);
        tNamaKompetisi.setText(null);
        tMaxTim.setText(null);
        bCancelEditKompetisi.setVisible(false);
    }//GEN-LAST:event_bCancelEditKompetisiActionPerformed

    private void bOKEditTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOKEditTimActionPerformed
        // TODO add your handling code here:
        if (tNamaTim.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informasi yang dimasukkan kurang", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (tNamaTim.getText().toString().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Informasi yang dimasukkan tidak ditemukan huruf", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            tim.selectTim(lListTim.getSelectedValue().toString(), tmpKomp.getNama());
            tim.editTim(tmpKomp.getNama(), tNamaTim.getText());
            fillListTim();
            startPane();
            tNamaTim.setText(null);
        }
    }//GEN-LAST:event_bOKEditTimActionPerformed

    private void bCancelEditTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelEditTimActionPerformed
        // TODO add your handling code here:
        tNamaTim.setText(null);
        startPane();
    }//GEN-LAST:event_bCancelEditTimActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
        if (tabListPemain.getSelectedRow() >= 0) {
            //deletePemain
            String tmpNamaPemain = (String) tabListPemain.getValueAt(tabListPemain.getSelectedRow(), 0);
            pemain.removePemain(tmpKomp.getNama(), tim.getNama(), tmpNamaPemain);
        } else if (lListTim.getSelectedValue() != null) {
            //deleteTim
            tim.selectTim(lListTim.getSelectedValue().toString(), tmpKomp.getNama());
            tim.removeTim(tmpKomp.getNama());
            fillListTim();
            if (tmpKomp.getJumlahTimKompetisi() == 0) {
                setBukaPendaftaranPeserta(false);
            } else {
                setBukaPendaftaranPeserta(true);
            }
        }
    }//GEN-LAST:event_bDeleteActionPerformed

    private void tbTutupPendaftaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbTutupPendaftaranActionPerformed
        // TODO add your handling code here:
        if (tmpKomp.getJumlahTimKompetisi() < 3) {
            JOptionPane.showMessageDialog(null, "Tim yang dimasukkan minimal 3", "Peringatan", JOptionPane.WARNING_MESSAGE);
            tmpKomp.updateStatusTutupPendaftaran(false);
        } else {
            if (new Tim().cekTimReady(tmpKomp.getNama())) {
                tbTutupPendaftaran.setEnabled(false);
                filltableKlasemen();
                klasemen.allTim(tmpKomp.getNama());
                klasemen.setPertandingan(tmpKomp.getNama());
                filltableJadwalPertandingan();
                tmpKomp.selectKompetisi(lKompetisi.getSelectedValue().toString());
                startPane();
                tmpKomp.updateStatusTutupPendaftaran(true);
                setBukaPendaftaran(!tmpKomp.getStatusTutupPendaftaran());
                bMulaiPertandingan.setVisible(true);
            } else {
                tmpKomp.updateStatusTutupPendaftaran(false);
            }
        }
    }//GEN-LAST:event_tbTutupPendaftaranActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        filltableKlasemen();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bAddPemainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddPemainActionPerformed
        // TODO add your handling code here:
        tmpKomp.selectKompetisi(lKompetisi.getSelectedValue().toString());
        String tmp = tmpKomp.getNama();
        if (tNamaPEmain.getText().equals("") || tNoPunggung.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informasi yang dimasukkan kurang", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (tNamaPEmain.getText().toString().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Informasi yang dimasukkan tidak ditemukan huruf", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            pemain = new Pemain(tNamaPEmain.getText(), (Integer.parseInt(tNoPunggung.getText())), cPosisi.getSelectedItem().toString());
            pemain.savePemain(tmp, cNamaTim.getSelectedItem().toString(), tNamaPEmain.getText(), Integer.parseInt(tNoPunggung.getText()), cPosisi.getSelectedItem().toString());
            tNamaPEmain.setText("");
            tNoPunggung.setText("");
        }
    }//GEN-LAST:event_bAddPemainActionPerformed

    private void bCancelEditPemainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelEditPemainActionPerformed
        // TODO add your handling code here:
        tNamaPEmain.setText("");
        tNoPunggung.setText("");
        startPane();
    }//GEN-LAST:event_bCancelEditPemainActionPerformed

    private void bOKEditPemainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOKEditPemainActionPerformed
        // TODO add your handling code here:
        tmpKomp.selectKompetisi(lKompetisi.getSelectedValue().toString());
        String tmp = tmpKomp.getNama();
        if (tNamaPEmain.getText().equals("") || tNoPunggung.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informasi yang dimasukkan kurang", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (tNamaPEmain.getText().toString().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Informasi yang dimasukkan tidak ditemukan huruf", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            pemain = new Pemain(tNamaPEmain.getText(), (Integer.parseInt(tNoPunggung.getText())), cPosisi.getSelectedItem().toString());
            pemain.updatePemain(tmp, cNamaTim.getSelectedItem().toString(), tNamaPEmain.getText(), Integer.parseInt(tNoPunggung.getText()), Integer.valueOf(tabListPemain.getValueAt(tabListPemain.getSelectedRow(), 1).toString()), cPosisi.getSelectedItem().toString());
            tNamaPEmain.setText("");
            tNoPunggung.setText("");
            startPane();
        }
    }//GEN-LAST:event_bOKEditPemainActionPerformed

    private void bMulaiPertandinganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMulaiPertandinganActionPerformed
        // TODO add your handling code here:
        pertandingan.selectPertandingan(tmpKomp.getNama());
        labTim1.setText(pertandingan.getTim1().getNama());
        labTim2.setText(pertandingan.getTim2().getNama());
        fillComboPemainTim1(pertandingan.getTim1().getNama());
        fillComboPemainTim2(pertandingan.getTim2().getNama());
        jTabbedPane1.setEnabledAt(4, rootPaneCheckingEnabled);
        bAnulirGoalTim1.setEnabled(false);
        bAnulirGoalTim2.setEnabled(false);
        jTabbedPane1.setSelectedIndex(4);
        spinGoal1.getModel().setValue(0);
        spinGoal2.getModel().setValue(0);
        tfLiveUpdate.setText("");
    }//GEN-LAST:event_bMulaiPertandinganActionPerformed

    private void cNamaTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cNamaTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cNamaTimActionPerformed

    private void bSetGoalTim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSetGoalTim1ActionPerformed
        // TODO add your handling code here:
        pemain.tambahGol(tmpKomp.getNama(), pertandingan.getTim1().getNama(), Integer.parseInt(pemainTim1.getSelectedItem().toString()), 1);
        pertandingan.setGoalTim1();
        spinGoal1.setValue(pertandingan.getGoalTim1());
        bAnulirGoalTim1.setEnabled(true);
        tfLiveUpdate.append("pemain dengan noPunggung " + pemainTim1.getSelectedItem().toString() + " dari Tim 1 mencetak goal\n");
    }//GEN-LAST:event_bSetGoalTim1ActionPerformed

    private void bSetGoalTim2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSetGoalTim2ActionPerformed
        // TODO add your handling code here:
        pemain.tambahGol(tmpKomp.getNama(), pertandingan.getTim2().getNama(), Integer.parseInt(pemainTim2.getSelectedItem().toString()), 1);
        pertandingan.setGoalTim2();
        spinGoal2.setValue(pertandingan.getGoalTim2());
        bAnulirGoalTim2.setEnabled(true);
        tfLiveUpdate.append("pemain dengan noPunggung " + pemainTim2.getSelectedItem().toString() + " dari Tim 2 mencetak goal\n");
    }//GEN-LAST:event_bSetGoalTim2ActionPerformed

    private void bEndPertandinganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEndPertandinganActionPerformed
        // TODO add your handling code here:
        pertandingan.setHasilPertandingan();
        filltableKlasemen();
        filltableJadwalPertandingan();
        jTabbedPane1.setEnabledAt(4, false);
        jTabbedPane1.setSelectedIndex(3);
        bMulaiPertandingan.setText("Mulai pertandingan pekan ke-" + pertandingan.getPekanForLabel(tmpKomp.getNama()));
        String temp = "Semua Pertandingan telah dilakukan";
        if (tmpKomp.getStatusPertandinganBerakhir() && tmpKomp.getStatusTutupPendaftaran()) {
            temp += "\n\nTim Pemenang :\n";
            for (Tim t : tmpKomp.getTimPemenangKompetisi()) {
                temp += "Tim: " + t.getNama() + ", Point: " + t.getPoint() + "\n";
            }
            Pemain p = tmpKomp.getTopScorerKompetisi();
            temp += "\nTop Scorer : " + p.getNama()
                    + ", Asal Tim: " + p.getTim()
                    + ", Jumlah Goal: " + p.getJumlahGoal();
            JOptionPane.showMessageDialog(null, temp, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            bMulaiPertandingan.setVisible(false);
        }
    }//GEN-LAST:event_bEndPertandinganActionPerformed

    private void tMaxTimKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tMaxTimKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tMaxTimKeyTyped

    private void tNoPunggungKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNoPunggungKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tNoPunggungKeyTyped

    private void tNamaPEmainKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNamaPEmainKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) || Character.isWhitespace(c)) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tNamaPEmainKeyTyped

    private void bAnulirGoalTim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnulirGoalTim1ActionPerformed
        // TODO add your handling code here:
        pemain.tambahGol(tmpKomp.getNama(), pertandingan.getTim1().getNama(), Integer.parseInt(pemainTim1.getSelectedItem().toString()), -1);
        pertandingan.annGoalTim1();
        spinGoal1.setValue(pertandingan.getGoalTim1());
        if (pertandingan.getGoalTim1() == 0) {
            bAnulirGoalTim1.setEnabled(false);
        }
        tfLiveUpdate.append("anulir goal dari pemain dengan noPunggung " + pemainTim1.getSelectedItem().toString() + "dari Tim 1\n");
    }//GEN-LAST:event_bAnulirGoalTim1ActionPerformed

    private void bAnulirGoalTim2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnulirGoalTim2ActionPerformed
        // TODO add your handling code here:
        pemain.tambahGol(tmpKomp.getNama(), pertandingan.getTim2().getNama(), Integer.parseInt(pemainTim2.getSelectedItem().toString()), -1);
        pertandingan.annGoalTim2();
        spinGoal2.setValue(pertandingan.getGoalTim2());
        if (pertandingan.getGoalTim2() == 0) {
            bAnulirGoalTim2.setEnabled(false);
        }
        tfLiveUpdate.append("anulir goal dari pemain dengan noPunggung " + pemainTim1.getSelectedItem().toString() + "dari Tim 2\n");
    }//GEN-LAST:event_bAnulirGoalTim2ActionPerformed

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
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    new View().setVisible(true);
                } catch (Exception ex) {
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddKompetisi;
    private javax.swing.JButton bAddPemain;
    private javax.swing.JButton bAddTim;
    private javax.swing.JButton bAnulirGoalTim1;
    private javax.swing.JButton bAnulirGoalTim2;
    private javax.swing.JButton bCancelEditKompetisi;
    private javax.swing.JButton bCancelEditPemain;
    private javax.swing.JButton bCancelEditTim;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bDeleteKompetisi;
    private javax.swing.JButton bEdit;
    private javax.swing.JButton bEditKompetisi;
    private javax.swing.JButton bEndPertandingan;
    private javax.swing.JButton bMulaiPertandingan;
    private javax.swing.JButton bOKEditKompetisi;
    private javax.swing.JButton bOKEditPemain;
    private javax.swing.JButton bOKEditTim;
    private javax.swing.JButton bPilihKompetisi;
    private javax.swing.JButton bSetGoalTim1;
    private javax.swing.JButton bSetGoalTim2;
    private javax.swing.JComboBox cNamaTim;
    private javax.swing.JComboBox cPosisi;
    private javax.swing.JLabel dummy;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JList lKompetisi;
    private javax.swing.JList lListTim;
    private javax.swing.JLabel labTim1;
    private javax.swing.JLabel labTim2;
    private javax.swing.JLabel labelWelcome;
    private javax.swing.JPanel pJadwalPertandingan;
    private javax.swing.JPanel pKlasemen;
    private javax.swing.JPanel pKompetisi;
    private javax.swing.JPanel pPendaftaran;
    private javax.swing.JPanel pPertandingan;
    private javax.swing.JComboBox pemainTim1;
    private javax.swing.JComboBox pemainTim2;
    private javax.swing.JSpinner spinGoal1;
    private javax.swing.JSpinner spinGoal2;
    private javax.swing.JTable tJadwalPertandingan;
    private javax.swing.JTextField tMaxTim;
    private javax.swing.JTextField tNamaKompetisi;
    private javax.swing.JTextField tNamaPEmain;
    private javax.swing.JTextField tNamaTim;
    private javax.swing.JTextField tNoPunggung;
    private javax.swing.JTextArea taDetail;
    private javax.swing.JTable tabKlasemen;
    private javax.swing.JTable tabListPemain;
    private javax.swing.JToggleButton tbTutupPendaftaran;
    private javax.swing.JTextArea tfLiveUpdate;
    // End of variables declaration//GEN-END:variables
}
