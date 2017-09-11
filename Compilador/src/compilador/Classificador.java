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
    OP_ARITMETICA, // + * - /
    OP_RELACIONAL, // == >= <= > < !=
    OP_BOOLEAN_DUAL, // || && ??
    OP_BOOLEAN_UNARIO, // !!
    ATRIBUICAO,
    PONTOVIRGULA,
    TRUE,
    FALSE,
    FOR,
    WHILE,
    ABREPARENTESES,
    FECHAPARENTESES,
    ABRECHAVES,
    FECHACHAVES,
    IF,
    ELSE,
    ERRO;
    private String msg;
    
    public String significado(){
        switch (this){
            case ATRIBUICAO :
                return("ATRIBUICAO");
            case BOOLEAN :
                return("BOOLEAN");
            case CHAR :
                return("CHAR");
            case ERRO :
                return("ERRO!="+msg);
            case FALSE :
                return("FALSE");
            case INT :
                return("INT");
            case NOME :
                return("variavel");
            case PONTOVIRGULA :
                return("PONTO E VIRGULA");
            case DOUBLE :
                return("DOUBLE");
            case STRING :
                return("STRING");
            case TRUE :
                return("TRUE");
            case VCHAR :
                return("Valor char");
            case VINTEIRO :
                return("Valor inteiro");
            case VDOUBLE :
                return("Valor double");
            case FOR : 
                return("FOR");
            case WHILE :
                return("WHILE");
            case OP_ARITMETICA :
                return("Operacao Aritmetica");
            case OP_BOOLEAN_DUAL :
                return("Operação booleana dual");
            case OP_BOOLEAN_UNARIO :
                return("Operação booleana unaria");
            case OP_RELACIONAL :
                return("Operação relacional");
            case ABRECHAVES :
                return("Abre chaves");
            case FECHACHAVES :
                return("Fecha chaves");
            case ABREPARENTESES :
                return("Abre parenteses");
            case FECHAPARENTESES :
                return("Fecha paranteses");
            case IF :
                return("IF");
            case ELSE :
                return("ELSE");
            default :
                return("DEFAULT");
        }
    }
}
