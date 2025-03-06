/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author frank
 */
public class Grafo {
    private int filas;
    private int columnas;
    private int maxNodos;
    private Casilla[] casillas;
    private List listaAdy[];
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
            //Se crea una lista por cada nodo y la informacion que guardan son los indices de las casillas vecinas
            int fila = i / columnas;
            int col = i % columnas;
            String id = obtenerLetra(col) + Integer.toString(fila + 1);
            casillas[i] = new Casilla(id, fila, col);       
             }
        construirGrafo();
    }
    public void construirGrafo(){
        for (int i=0; i<maxNodos;i++){
            int fila=this.casillas[i].getFila();
            int col=this.casillas[i].getColumna();
            for (int df = -1; df <= 1; df++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (df == 0 && dc == 0) continue;
                    int nf = fila + df;
                    int nc = col + dc;
                }
            }
        }
   // Para cada casilla, buscar vecinos en las 8 direcciones
         for (int i = 0; i < maxNodos; i++) {
            int fila = casillas[i].getFila();
            int col = casillas[i].getColumna();
            for (int df = -1; df <= 1; df++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (df == 0 && dc == 0) continue; // saltar la misma casilla
                    int nf = fila + df;
                    int nc = col + dc;
                    if (nf >= 0 && nf < filas && nc >= 0 && nc < columnas) {
                        int vecinoIndex = nf * columnas + nc;
                        // Agregar la arista si aún no existe (grafo no dirigido)
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
        
    
    
    public String obtenerLetra(int col) {
        return Character.toString((char)('A' + col));
        //(char)('A' + col): Convierte el entero col en un carácter. 'A' tiene un valor ASCII de 65. Entonces, si col es 0, el carácter resultante será 'A', si col es 1, el carácter resultante será 'B', y así sucesivamente.

        //Character.toString(...): Convierte el carácter a un String.
    }
    
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
    
    public void marcarBandera(int indice){
        if (this.casillas[indice].getMarcada()){
            this.casillas[indice].setMarcada(false);
            this.banderas--;

        } else if (! (this.casillas[indice].getMarcada()) && this.banderas<this.minas){
            this.casillas[indice].setMarcada(true);
            this.banderas++;
        }
        
    }
    
    public void barrerCasilla(int indice){
        if (!this.casillas[indice].getMarcada() && ! this.casillas[indice].getRevelada()){
            this.casillas[indice].setRevelada(true);
            if (this.casillas[indice].getTieneMina()){
                System.out.println("Juego terminado");
            } 
            //else {
//                 if (!this.casillas[indice].getTieneMina() && this.casillas[indice].getMinasAdyacentes() == 0) {
//                NodoLista nodo = listaAdy[indice].getpFirst();
//                while (nodo != null) {
//                    barrerCasilla(nodo.getValor());
//                    nodo = nodo.siguiente;
//            }
        //}
            }
        }
    }
    
    

    
    
    

