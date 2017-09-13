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
    ID,
    STRING,
    OP_ARITMETICA, // + * - /
    OP_RELACIONAL, // == >= <= > < !=
    OP_BOOLEAN_DUAL, // || && ??
    NEGACAO, // !!
    TIPO,
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
    START,
    IF,
    ELSE,
    ERRO;
    
    public String significado(){
        switch (this){
            case ATRIBUICAO :
                return("=");
            case ERRO :
                return("ERRO");
            case FALSE :
                return("Palavra reservada 'false'");
            case ID :
                return("id");
            case PONTOVIRGULA :
                return(";");
            case STRING :
                return("String ");
            case TRUE :
                return("Palavra reservada 'true'");
            case VCHAR :
                return("Caracter");
            case VINTEIRO :
                return("Inteiro");
            case VDOUBLE :
                return("Double");
            case FOR : 
                return("palavra reservada 'for'");
            case WHILE :
                return("palavra reservada 'while'");
            case OP_ARITMETICA :
                return("Operacao Aritmetica");
            case OP_BOOLEAN_DUAL :
                return("Operação booleana de dois operandos : ");
            case NEGACAO :
                return("Operação de negacao '!!'");
            case OP_RELACIONAL :
                return("Operação relacional");
            case ABRECHAVES :
                return("{");
            case FECHACHAVES :
                return("}");
            case ABREPARENTESES :
                return("(");
            case FECHAPARENTESES :
                return(")");
            case IF :
                return("Palavra reservada 'if'");
            case ELSE :
                return("Palavra reservada 'else'");
            case TIPO :
                return("Tipo");
            case START :
                return("Palavra reservada 'start'");
            default :
                return("DEFAULT");
        }
    }
}
