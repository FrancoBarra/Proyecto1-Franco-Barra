/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Random;
import javax.swing.*;

/**
 *
 * @author frank
 */
public class Grafo<T> {
    private int filas;
    private int columnas;
    private int maxNodos;
    private Casilla[] casillas;
    private List[] listaAdy;
    private int banderas;
    private int minas;

 

    public Grafo(int filas, int columnas, int mina) {
        this.filas = filas;
        this.columnas = columnas;
        this.maxNodos = filas*columnas;
        this.casillas = new Casilla[maxNodos];
        this.listaAdy = new List[maxNodos];
        this.banderas = 0;
        this.minas=mina;
   
     
    //el for recorre la cantidad de nodos existentes
        for (int i = 0; i < maxNodos; i++) {
            listaAdy[i] = new List();
            //Se crea una lista por cada nodo que luego guardaran la informacion de los indices de las casillas vecinas
            int fila = i / columnas;
            int col = i % columnas;
            String id = obtenerLetra(col) + Integer.toString(fila + 1);
            casillas[i] = new Casilla(id, fila, col);       
             }
        construirGrafo();
    }
    //Metodos getters y setters
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getMaxNodos() {
        return maxNodos;
    }

    public Casilla[] getCasillas() {
        return casillas;
    }

    public List[] getListaAdy() {
        return this.listaAdy;
    }

    public int getBanderas() {
        return banderas;
    }

    public int getMinas() {
        return minas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void setCasillas(Casilla[] casillas) {
        this.casillas = casillas;
    }

    public void setMaxNodos(int maxNodos) {
        this.maxNodos = maxNodos;
    }

    public void setListaAdy(List[] listaAdy) {
        this.listaAdy = listaAdy;
    }

    public void setMinas(int minas) {
        this.minas = minas;
    }

    public void setBanderas(int banderas) {
        this.banderas = banderas;
    }
    
    
    /**
     * recorre las casillas del grafo para agregarles los indice de sus casillas vecinas a cada lista del arreglo listaAdy
     */
    public void construirGrafo(){ 
   //Este codigo va a agregar las casillas adyacentes de cada casilla a las listaAdy
         for (int i = 0; i < maxNodos; i++) {
            int fila = casillas[i].getFila();
            int col = casillas[i].getColumna();
            //Se reduce el grafo a una matriz 3x3 en donde la casilla base sera la coordenada(0,0)
            for (int j = -1; j <= 1; j++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (j == 0 && dc == 0) continue; 
                    int nf = fila + j;
                    int nc = col + dc;
                    if (nf >= 0 && nf < filas && nc >= 0 && nc < columnas) {
                        int vecinoIndex = nf * columnas + nc;
                        
                        if (!listaAdy[i].busqueda(vecinoIndex)) {
                            listaAdy[i].addFirst(vecinoIndex);
                        }
                        if (!listaAdy[vecinoIndex].busqueda(i)) {
                            listaAdy[vecinoIndex].addFirst(i);
                        }
                    }
                }
            }
        }
    }
    
        
    
    /**
     * Devuelve la letra de la columna segun la tabla ASCII
     * @param col numero de columnas
     * @return String
     */
    public String obtenerLetra(int col) {
        return Character.toString((char)('A' + col));
        //(char)('A' + col): Convierte el entero col en un carácter. 'A' tiene un valor ASCII de 65. Entonces, si col es 0, el carácter resultante será 'A', si col es 1, el carácter resultante será 'B', y así sucesivamente.

        //Character.toString(...): Convierte el carácter a un String.
    }
    /**
     * recorre cada casilla del grafo para agregar la cantidad de minas introducidas de forma aleatoria
     */
    public void agregarMinas(){
        Random minado=new Random();
        int counter=0;
        while (counter<this.minas){
            int indice=minado.nextInt(this.maxNodos);
            if (!this.casillas[indice].getTieneMina()){
                this.casillas[indice].setTieneMina(true);
                counter=counter+1;
            }
            
        }
    }
    /**
     * Marca o desmarca con una bandera la casilla seleccionada
     * @param indice indice de la casilla
     */
    public void marcarBandera(int indice){
        if (this.casillas[indice].getMarcada()){
            this.casillas[indice].setMarcada(false);
            this.banderas--;

        } else if (! (this.casillas[indice].getMarcada()) && this.banderas<this.minas){
            this.casillas[indice].setMarcada(true);
            this.banderas++;
        }
        
    }
    /**
     * Metodo de busqueda BFS para revelar las casillas y sus adyacentes en caso de no tener minas cercanas
     * @param i indice de la casilla seleccionada
     * @param botones arreglo de botones de la interfaz Juego
     */
    public void barridoBFS(int i, JButton[] botones){
        int[] cola=new int[this.maxNodos];
        int inicio=0;
        int fin=0;
        boolean[] visto=new boolean[this.maxNodos];
        cola[fin++]=i;
        visto[i]=true;
        while(inicio<fin){
            int indiceActual=cola[inicio++];
            this.casillas[indiceActual].setRevelada(true);
            String num=Integer.toString(this.casillas[indiceActual].getMinasAdyacentes());
            if (this.casillas[indiceActual].getMinasAdyacentes()>0){
                botones[indiceActual].setText(num);
                
            } else {
                NodoLista pAux=this.listaAdy[indiceActual].getpFirst();
                while(pAux!=null){
                    int indiceAdy = ((Integer) pAux.getValor()).intValue();
                    if (!visto[indiceAdy]){
                       
                
                        cola[fin++]=indiceAdy;
                        String numAdy=Integer.toString(this.casillas[indiceAdy].getMinasAdyacentes());
                        if (this.casillas[indiceAdy].getMinasAdyacentes()>0){
                            botones[indiceAdy].setText(numAdy);
                           
                            botones[indiceAdy].setEnabled(false);
                        } 
                        
                        visto[indiceAdy]=true;
                    }
                    pAux=pAux.getpNext();
                }
            }
            botones[indiceActual].setEnabled(false);
            
    }
    }
    /**
      Metodo de busqueda DFS para revelar las casillas y sus adyacentes en caso de no tener minas cercanas
     * @param i indice de la casilla seleccionada
     * @param botones arreglo de botones de la interfaz Juego
     * @param visto Arreglo que determina cuales casillas han sido visitadas
     */
    public void barridoDFS(int i,JButton[] botones, boolean[] visto){
        visto[i]=true;

      
        this.casillas[i].setRevelada(true);
        this.casillas[i].setRevelada(true);
            String num=Integer.toString(this.casillas[i].getMinasAdyacentes());
            if (this.casillas[i].getMinasAdyacentes()>0){
                botones[i].setText(num);
                
            } else {
                NodoLista pAux=this.listaAdy[i].getpFirst();
                while(pAux!=null){
                    int indiceAdy = ((Integer) pAux.getValor()).intValue();
                    if (!visto[indiceAdy]){
                        barridoDFS(indiceAdy,botones,visto);
                        String numAdy=Integer.toString(this.casillas[indiceAdy].getMinasAdyacentes());
                        if (this.casillas[indiceAdy].getMinasAdyacentes()>0){
                            botones[indiceAdy].setText(numAdy);
                            botones[indiceAdy].setEnabled(false);
                        } 
                        
                        visto[indiceAdy]=true;
                    }
                    pAux=pAux.getpNext();
                    
                }
            }
            botones[i].setEnabled(false);
            
    }
    /**
     * metodo creado para crear el arreglo "visto" fuera del metodo barridoDFS con el fin de guardar los cambios realizados en dicho arreglo
     * @param i indice de la casilla
     * @param botones arreglo de los botones de la interfaz Juego
     */
    public void booleanDFS(int i,JButton[] botones){
        boolean[] visto=new boolean[this.maxNodos];
        barridoDFS(i,botones,visto);
    }
    /**
     * recorre las casillas adyacentes de cada casilla para contar cuales tienen mina y modifica el atributo "minasAdyacentes"
     */
    public void calcularMinasAdyacentes() { 
        //Esta funcion es la que calcula la cantidad de minas adyacentes de cada casilla
        for (int i = 0; i < maxNodos; i++) { 
        int count = 0;
        NodoLista nodo = listaAdy[i].getpFirst(); 
        //recorremos listaAdy la cual guarda las casillas adyacente a la casilla a la que se esta las minas adyacentes
        while(nodo != null) { 
            int j = ((Integer) nodo.getValor()).intValue();
            if (casillas[j].getTieneMina()) {
                count++; 
            }
            nodo = nodo.getpNext(); }
        casillas[i].setMinasAdyacentes(count); } 
        }
       
    
}

    
    
    

