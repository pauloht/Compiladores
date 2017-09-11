/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author FREE
 */
public class Token {
    private Classificador classe = null;
    private Object valor = null;
    
    public Token(Classificador classe){
        this.classe = classe;
    }
    
    public Token(Classificador classe,Object valor){
        this.classe = classe;
        this.valor = valor;
    }
    
    public void printSignificado(){
        
    }
    
    
}
