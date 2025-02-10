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
    

    public boolean isEmpty(){
        return this.pFirst == null;
    }
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
    
    public void deleteFirst(){
        NodoLista<T> delete = pFirst.getpNext();
        this.pFirst=delete;
    }
    
    public void print(){
        NodoLista<T> pAux = this.pFirst;
        
        while(pAux != null){
            System.out.println(pAux.getValor());
            pAux = pAux.getpNext();
        }
    }
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
    public void deletebyNumber(int number){
        NodoLista<T> pAux=this.pFirst;
        NodoLista<T> pPrev=this.pFirst;
        
        for(int i=1; i <=number; i++){
            if (pAux!= null){
                pPrev=pAux;
                pAux=pAux.getpNext();
                
            }else{
                System.out.println("No existe ese elemento");
                break;
            }
        }
        if (pAux!=null){
            pPrev.setpNext(pAux.getpNext());
            pAux=null;
        }
        
    }
    public void setSize(){
        NodoLista<T> pAux=pFirst;
        if (!isEmpty()&& pAux.getpNext()==null){
            size++;
        }
        else{
            while (pAux.getpNext()!=null){
                size++;
                pAux=pAux.getpNext();
            }
        }
    }
       public NodoLista<T> getNodoListabyNumber(int x){
        NodoLista<T> pAux=pFirst;
        for (int i=0;i<x;i++){
            pAux=pAux.getpNext();
        }
        return pAux;
    }

    }
 

    
    

