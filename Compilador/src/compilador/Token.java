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
    String erroMsg;
    
    public Token(Classificador classe){
        this.classe = classe;
    }
    
    public Token(Classificador classe,Object valor){
        this.classe = classe;
        this.valor = valor;
    }
    
    public void printSignificado(){
        String resp = classe.significado();
        if (!(valor==null)){
            resp = resp + ",valor : " + valor.toString();
        }
        System.out.println(resp);
    }
    
    public void setMsg(String msg){
        this.erroMsg = msg;
    }
    
    
}
