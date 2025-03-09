/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author frank
 * @param <T>
 */
public class NodoLista<T> {
    private T valor;
    private NodoLista pNext;
    
    public NodoLista(T data){
        this.valor=data;
        this.pNext=null;
    }
    
    public T getValor(){
        return this.valor;
    }
    
    public void setValor(T data){
        this.valor=data;
    }

    public NodoLista getpNext() {
        return pNext;
    }

    public void setpNext(NodoLista pNext) {
        this.pNext = pNext;
    }
    
}
