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
public enum Classificador {
    VINTEIRO,//valor inteiro
    VDOUBLE,//valor real 
    VCHAR,//valor char
    NOME,
    INT,//palavra reservada int
    DOUBLE,//palavra reservada double
    CHAR,//palavra reservada char
    BOOLEAN,//palavra reservada boolean
    STRING,
    SOMA,
    SUB,
    DIV,
    MUL,
    IGUALDADE,
    ATRIBUICAO,
    PONTOVIRGULA,
    AND,
    OR,
    NOT,
    XOR,
    DIFERENTEDE,
    MAIOR,
    MENOR,
    MAIORIGUAL,
    MENORIGUAL,
    TRUE,
    FALSE,
    ERRO;
    
    private String msg;
    
    public void setMsg(String msg){
        this.msg = msg;
    }
    
    public String significado(){
        switch (this){
            case AND :
                return("AND");
            case ATRIBUICAO :
                return("ATRIBUICAO");
            case BOOLEAN :
                return("BOOLEAN");
            case CHAR :
                return("CHAR");
            case DIFERENTEDE :
                return("DIFERENTE DE");
            case DIV :
                return("DIVISAO");
            case ERRO :
                return("ERRO!="+msg);
            case FALSE :
                return("FALSE");
            case IGUALDADE :
                return("IGUALDADE");
            case INT :
                return("INT");
            case MAIOR :
                return("MAIOR QUE");
            case MAIORIGUAL :
                return("MAIOR IGUAL QUE");
            case MENOR :
                return("MENOR QUE");
            case MENORIGUAL :
                return("MENOR IGUAL QUE");
            case MUL :
                return ("MULTIPLICACAO");
            case NOME :
                return("NOME");
            case NOT :
                return("NEGACAO");
            case OR :
                return("OR");
            case PONTOVIRGULA :
                return("PONTO E VIRGULA");
            case DOUBLE :
                return("DOUBLE");
            case SOMA :
                return("SOMA");
            case STRING :
                return("STRING");
            case SUB :
                return("SUB");
            case TRUE :
                return("TRUE");
            case VCHAR :
                return("Valor char");
            case VINTEIRO :
                return("Valor inteiro");
            case VDOUBLE :
                return("Valor double");
            case XOR :
                return("XOR");
            default :
                return("DEFAULT");
        }
    }
}
