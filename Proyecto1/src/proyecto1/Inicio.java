/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto1;

/**
 *
 * @author frank
 */
public class Inicio extends javax.swing.JFrame {
    Juego game=new Juego();
    /**
     * Creates new form Inicio
     * @param pantalla
     */
    public Inicio(Juego pantalla) {
        initComponents();
        this.game=pantalla;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        botonInicio = new javax.swing.JButton();
        cargarPartida = new javax.swing.JButton();
        numColumnas = new javax.swing.JTextField();
        numFilas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        numBombs = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Buscaminas");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 100, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Franco Barra Daniel Suarez y Vyckhy Sarmiento");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 370, -1));

        botonInicio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonInicio.setText("Iniciar");
        botonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInicioActionPerformed(evt);
            }
        });
        jPanel1.add(botonInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        cargarPartida.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cargarPartida.setText("Cargar Partida");
        cargarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarPartidaActionPerformed(evt);
            }
        });
        jPanel1.add(cargarPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, -1, -1));

        numColumnas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numColumnasActionPerformed(evt);
            }
        });
        jPanel1.add(numColumnas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 140, 50));
        jPanel1.add(numFilas, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, 140, 50));

        jLabel3.setText("Ingrese el número de columnas");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        jLabel4.setText("Ingrese el numero de filas");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, -1, -1));
        jPanel1.add(numBombs, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, 160, 50));

        jLabel5.setText("Ingrese el  número de bombas");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarPartidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cargarPartidaActionPerformed

    private void botonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInicioActionPerformed
        // TODO add your handling code here:
        this.game.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonInicioActionPerformed

    private void numColumnasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numColumnasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numColumnasActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonInicio;
    private javax.swing.JButton cargarPartida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField numBombs;
    private javax.swing.JTextField numColumnas;
    private javax.swing.JTextField numFilas;
    // End of variables declaration//GEN-END:variables
}
