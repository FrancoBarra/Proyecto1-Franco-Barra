/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author frank
 */
public class Juego extends javax.swing.JFrame {
   private Grafo grafo;
   private JButton[] botones;
   private boolean isBandera;
   private int banderas;
   private boolean metBusqueda;
   private boolean cargar;
   
  
    public Juego(Grafo tabla, boolean cargar, boolean metBusqueda) {
        initComponents();
        this.grafo = tabla;
        this.cargar=cargar;
        this.NumMinas.setText(Integer.toString(tabla.getMinas()));
        //Esta variable isBandera sera la que permita al programa cuando se debe colocar una bandera o revelar una casilla
        this.isBandera=false;
        inicializarUI();
        if (!cargar){
            this.grafo.agregarMinas();
        } 
        this.banderas=tabla.getMinas(); 
        this.resBanderas.setText(Integer.toString(tabla.getMinas()));
        this.grafo.calcularMinasAdyacentes();
        this.metBusqueda=metBusqueda;
        
    }
    /**
     * Se crea la interfaz en el jPanel1 con los botones de las casillas y ademas se agrega con lambda una funcion ActionPerformance de JButton para mostrar los cambios del grafo
     */
    private void inicializarUI() {
        //Se Agregan a jPanel1 los atributos y condiciones que tendra el tablero del buscaminas  
        setTitle("Buscaminas");  
        this.jPanel1.setSize(grafo.getColumnas() * 100, grafo.getFilas() * 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jPanel1.setLayout(new GridLayout(grafo.getFilas(), grafo.getColumnas()));
        this.botones = new JButton[grafo.getMaxNodos()];
        //El siguiente codigo agrega y modifica un jButton por cada nodo del tablero
        for (int i = 0; i < grafo.getMaxNodos(); i++) {
            this.botones[i] = new JButton();
            final int indice= i;  
            final Grafo tablero=this.grafo;
            
        
            //En esta parte se utiliza la libreria java.awt.event.ActionEvent; para agregar un metodo a la clase de jButton
            this.botones[i].addActionListener((ActionEvent e) -> {
                //Si isBandera es verdadero, significa que el jugador quiere colocar una bandera sobre una casilla. El siguiente codigo realiza esa funcion
                if (this.isBandera){
                    if ("B".equals(botones[indice].getText()) ){
                        tablero.marcarBandera(indice);
                       
                        botones[indice].setText("");
                        int numB=Integer.parseInt(this.resBanderas.getText());
                        numB++;
                        this.banderas++;
                        String str=Integer.toString(numB);
                        this.resBanderas.setText(str);
                        
                    } else{
                        if (this.banderas>0){
                            tablero.marcarBandera(indice);
                            botones[indice].setText("B");
                            if (tablero.getCasillas()[indice].getTieneMina()){
                              
                              
                            }
                            
                            int numB=Integer.parseInt(this.resBanderas.getText());
                            numB--;
                            this.banderas--;
                            String str=Integer.toString(numB);
                            this.resBanderas.setText(str);
                        } else {
                             JOptionPane.showMessageDialog(jPanel1, "No te quedan mas banderas");
                        }
                    }
                //Si isBandera es false significa que el jugador quiere revelar una casilla. Este codigo es el engargado de ello.
                } else{
                    if (botones[indice].getText()!="B"){
                   
                    //Aca comprobamos que si tiene mina. En caso de detenerla se revelan todas las demas y se acaba el juego
                        if (tablero.getCasillas()[indice].getTieneMina()){
                            JOptionPane.showMessageDialog(jPanel1, "Boom, Fin del juego");
                            botones[indice].setText("¡¡BOOM!!");
                            for (int j = 0; j < grafo.getMaxNodos(); j++) {
                                if (grafo.getCasillas()[j].getTieneMina()) {
                                    botones[j].setText("¡¡BOOM!!");
                              
                                }
                                //Aca deshabilitamos todos los botones restantes
                                botones[j].setEnabled(false);
                            }
                            botones[indice].setEnabled(false);
                     
                        } else {
                        //Si la casilla no tiene mina, se obtiene a traves de la funcion barrerCasilla de la clase grafo, el numero de minas adyacentes
                            if (this.metBusqueda){
                                this.grafo.barridoBFS(indice, botones);
                            } else{
                                this.grafo.booleanDFS(indice, botones);
                            }
                        }
                        
                    }
                } victoria();
            }); //se agregan los botones al jPanel1
                this.jPanel1.add(botones[i]);
            }
            if (this.cargar){
                cargarTablero(this.grafo,botones);
            }
                   
    }  
    /**
     * Recorre el grafo para comprobar que todas las casillas esten reveladas o todas las minas marcadas
     */
    public void victoria(){
        boolean isMarcadas=true;
        boolean isReveladas=true;
        for(Casilla c:this.grafo.getCasillas()){
            if (!c.getTieneMina() && !c.getRevelada()){
                isReveladas=false;
            }
            else if(c.getTieneMina()&& !c.getMarcada()) {
                isMarcadas=false;
            }
        }
                
        if (isMarcadas || isReveladas){
            JOptionPane.showMessageDialog(jPanel1, "¡¡GANASTE!!");
        } 
    }
    /**
     * Modifica la interfaz para que muestre el grafo cargado
     * @param tablero grafo de la partida guardada
     * @param botones botones de la interfaz nueva a modificar
     */
    
    public void cargarTablero(Grafo tablero,JButton[] botones){
        for (int i=0; i<tablero.getMaxNodos();i++){
            if (tablero.getCasillas()[i].getRevelada()) {
                String num=Integer.toString(tablero.getCasillas()[i].getMinasAdyacentes());
                botones[i].setText(num);
                botones[i].setEnabled(false);
            }
                
        }
    }
    
    
  
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NumMinas = new javax.swing.JLabel();
        BoolBanderas = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        resBanderas = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 950, 460));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Minas");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, -1, -1));

        NumMinas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NumMinas.setText("jLabel3");
        jPanel2.add(NumMinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, -1, -1));

        BoolBanderas.setText("Off");
        BoolBanderas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoolBanderasActionPerformed(evt);
            }
        });
        jPanel2.add(BoolBanderas, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 80, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Banderas");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Restantes:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, -1, -1));

        resBanderas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        resBanderas.setText("jLabel5");
        jPanel2.add(resBanderas, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, -1, -1));

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel2.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Activa o desactiva el uso de banderas
     * @param evt 
     */
    private void BoolBanderasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoolBanderasActionPerformed
        // TODO add your handling code here:
        //Este boton cambia el valor de isBandera para que el usuario pueda revelar casilla o colocar una bandera
        if (this.isBandera){
            this.isBandera=false;
            this.BoolBanderas.setText("Off");
        } else {
            this.isBandera=true;
            this.BoolBanderas.setText("On");
        }
    }//GEN-LAST:event_BoolBanderasActionPerformed
    /**
     * guarda el grafo en un archivo csv
     * @param evt 
     */
    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
        Csv csv = new Csv();
        csv.guardarGrafoCSV(csv.seleccionarArchivo("guardar"), grafo);
    }//GEN-LAST:event_guardarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BoolBanderas;
    private javax.swing.JLabel NumMinas;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel resBanderas;
    // End of variables declaration//GEN-END:variables
}
