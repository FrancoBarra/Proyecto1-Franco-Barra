/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author frank
 */
public class List<T> {
    private NodoLista pFirst; //nodo apuntador al primero
    private int size; //tamaño de la lista
    private NodoLista pLast;
    
    //Constructor de la clase Lista
    public List() {
        this.pFirst = null;
        this.size = 0; 
        this.pLast=null;
    }
    
    //Metodos get y set para los atrubutos

    public NodoLista getpFirst() {
        return pFirst;
    }

    public void setpFirst(NodoLista pFirst) {
        this.pFirst = pFirst;
    }

    public NodoLista getpLast() {
        return pLast;
    }

    public void setpLast(NodoLista pLast) {
        this.pLast = pLast;
    }
    

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * Devuelve True si la lista esta vacia
     * @return boolean
     */
    public boolean isEmpty(){
        return this.pFirst == null;
    }
    /**
     * Agraga un elemente al principio de la lista
     * @param data dato a agregar
     */
   public void addFirst (T data){
        NodoLista<T> NodoListaInfo = new NodoLista<>(data);
        if (isEmpty()) {
            pFirst=NodoListaInfo;
            pLast=NodoListaInfo;
            
        }
        else{
            NodoListaInfo.setpNext(this.pFirst);
            this.pFirst=NodoListaInfo;
        }
    }
    /**
     * elimina el ultimo elemento de la lista
     */
    public void deleteFirst(){
        NodoLista<T> delete = pFirst.getpNext();
        this.pFirst=delete;
    }
    
    /**
     * Agraga un elemente al final de la lista
     * @param data dato a agregar
     */
    public void addLast(T data){
       NodoLista<T> NodoListainfo= new NodoLista<T>(data);
        if (isEmpty()){
            this.pFirst=NodoListainfo;
            this.pLast=NodoListainfo;
           
        }else {
            this.pLast.setpNext(NodoListainfo);
            this.pLast=NodoListainfo;
        }
    }
    
    
    
        /**
         * Recorre la lista hasta encontrar el objeto buscado
         * @param x objeto a buscar
         * @return boolean
         */
       public boolean busqueda(T x){
        NodoLista<T> pAux=pFirst;
        while (pAux!=null){
            if (pAux.getValor()==x){
                return true;
            
            }
            pAux=pAux.getpNext();
            
        }
        return false;
    }

    }
 

    
    

