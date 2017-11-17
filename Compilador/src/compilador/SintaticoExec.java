package compilador;


import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FREE
 */
public class SintaticoExec {
    private ArrayList< Token > entrada;
    private Stack< Token > pilha;
    Token tokenIp;
    StringBuilder saida;
    int ip = 0;
    int linhaContador = 0;
    
    public SintaticoExec(ArrayList tokens){
        entrada = tokens;
        saida = new StringBuilder();
        pilha = new Stack();
        //pilha.add(new Token(Classificador.FIMDELEITURA,"$"));
        pilha.add(new Token(Classificador.NAOTERMINAL,"<start>"));
    }
    
    private void ntStart(){
        if (tokenIp.getClasse() != Classificador.TIPO){
            throw new IllegalArgumentException("Esperava-se 'int' recebeu "+tokenIp.printSignificado());
        }
        pilha.add(new Token(Classificador.PONTOVIRGULA,";"));
        pilha.add(new Token(Classificador.NAOTERMINAL,"<bloco1>"));
        pilha.add(new Token(Classificador.FECHAPARENTESES,")"));
        pilha.add(new Token(Classificador.ABREPARENTESES,"("));
        pilha.add(new Token(Classificador.START,"start"));
        pilha.add(new Token(Classificador.TIPO,"int"));
        
        System.out.println("<start> -> int start() <bloco1> ;");
    }
    
    private void ntBloco1(){
        if (tokenIp.getClasse() != Classificador.ABRECHAVES){
            throw new IllegalArgumentException("Esperava-se '{' recebeu "+tokenIp.printSignificado());
        }
        pilha.add(new Token(Classificador.NAOTERMINAL,"<bloco2>"));
        pilha.add(new Token(Classificador.ABRECHAVES,"{"));
        
        System.out.println("<bloco1> -> { <bloco2>");
    }
    
    private void ntBloco2(){
        if (tokenIp.getClasse() != Classificador.FECHACHAVES && tokenIp.getClasse() != Classificador.TIPO && tokenIp.getClasse() != Classificador.IF && tokenIp.getClasse() != Classificador.WHILE && tokenIp.getClasse() != Classificador.FOR && tokenIp.getClasse() != Classificador.ID && tokenIp.getClasse() != Classificador.PRINT){
            throw new IllegalArgumentException("Esperava-se '}' ou um tipo ou um nome ou print ou for ou while ou if, recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.FECHACHAVES){
            pilha.add(new Token(Classificador.FECHACHAVES,"}"));
            System.out.println("<bloco2> -> }");
        }else{
            pilha.add(new Token(Classificador.FECHACHAVES,"}"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<linha1>"));
            System.out.println("<bloco2> -> <linha1>");
        }
    }
    
    private void ntLinha1(){
        if (tokenIp.getClasse() != Classificador.TIPO && tokenIp.getClasse() != Classificador.ID && tokenIp.getClasse() != Classificador.IF && tokenIp.getClasse() != Classificador.WHILE && tokenIp.getClasse() != Classificador.FOR && tokenIp.getClasse() != Classificador.PRINT){
            throw new IllegalArgumentException("Esperava-se tipo ou um nome ou print ou for ou while ou if, recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.TIPO){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<linha2>"));
            pilha.add(new Token(Classificador.PONTOVIRGULA,";"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<varDec1>"));
            System.out.println("<linha1> -> <varDec1> ;");
        }else if(tokenIp.getClasse()==Classificador.ID){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<linha2>"));
            pilha.add(new Token(Classificador.PONTOVIRGULA,";"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<atribuicao1>"));
            System.out.println("<linha1> -> <atribuicao1> ;");
        }else if(tokenIp.getClasse()==Classificador.PRINT){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<linha2>"));
            pilha.add(new Token(Classificador.PONTOVIRGULA,";"));
            pilha.add(new Token(Classificador.FECHAPARENTESES,")"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<printparametros1>"));
            pilha.add(new Token(Classificador.ABREPARENTESES,"("));
            pilha.add(new Token(Classificador.PRINT,"print"));
            System.out.println("<linha1> -> print( <printparametros1> ) ; <linha2>");
        }else{
            pilha.add(new Token(Classificador.NAOTERMINAL,"<linha2>"));
            pilha.add(new Token(Classificador.PONTOVIRGULA,";"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<condicao1>"));
            System.out.println("<linha1> -> <condicao1> ; <linha2>");
        }
    }
    
    private void ntLinha2(){
        if (tokenIp.getClasse() != Classificador.TIPO && tokenIp.getClasse() != Classificador.ID && tokenIp.getClasse() != Classificador.IF && tokenIp.getClasse() != Classificador.WHILE && tokenIp.getClasse() != Classificador.FOR && tokenIp.getClasse() != Classificador.PRINT && tokenIp.getClasse() != Classificador.FECHACHAVES){
            throw new IllegalArgumentException("Esperava-se um tipo ou um nome ou print ou for ou while ou if, recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.FECHACHAVES){
            System.out.println("<linha2> -> #");
        }else{
            pilha.add(new Token(Classificador.NAOTERMINAL,"<linha1>"));
            System.out.println("<linha2> -> <linha1>");
        }
    }
    
    private void ntVarDec1(){
        if (tokenIp.getClasse()!=Classificador.TIPO){
            throw new IllegalArgumentException("Esperava-se um tipo, recebeu "+tokenIp.printSignificado());
        }
        pilha.add(new Token(Classificador.NAOTERMINAL,"<varDec2>"));
        pilha.add(new Token(Classificador.ID,"id"));
        pilha.add(new Token(Classificador.TIPO,"tipo"));
        System.out.println("<varDec1> -> tipo id <varDec2>");
    }
    
    private void ntVarDec2(){
        if (tokenIp.getClasse()!=Classificador.ATRIBUICAO && tokenIp.getClasse()!=Classificador.PONTOVIRGULA){
            throw new IllegalArgumentException("Esperava-se '=' ou ';', recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()== Classificador.ATRIBUICAO){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<atribuicao2>"));
            pilha.add(new Token(Classificador.ATRIBUICAO,"="));
            System.out.println("<varDec2> -> = <atribuicao2>");
        }else{
            System.out.println("<varDec2> -> = #");
        }
    }
    
    private void ntCondicao1(){
        if (tokenIp.getClasse()!=Classificador.IF && tokenIp.getClasse()!=Classificador.WHILE && tokenIp.getClasse()!=Classificador.FOR){
            throw new IllegalArgumentException("Esperava-se if ou while ou for, recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.IF){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<condicao2>"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<bloco1>"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expBool1>"));
            pilha.add(new Token(Classificador.IF,"if"));
            System.out.println("<condicao1> -> = if <expBool1> <bloco1> <condicao2>");
        }else if (tokenIp.getClasse()==Classificador.WHILE){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<bloco1>"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expBool1>"));
            pilha.add(new Token(Classificador.WHILE,"while"));
            System.out.println("<condicao1> -> = while <expBool1> <bloco1>");
        }else{
            pilha.add(new Token(Classificador.NAOTERMINAL,"<bloco1>"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expfor>"));
            pilha.add(new Token(Classificador.FOR,"for"));
            System.out.println("<condicao1> -> = for <expfor> <bloco1>");
        }
    }
    
    private void ntCondicao2(){
        if (tokenIp.getClasse()!=Classificador.ELSE && tokenIp.getClasse()!=Classificador.PONTOVIRGULA){
            throw new IllegalArgumentException("Esperava-se else ou ';', recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.ELSE){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<bloco1>"));
            pilha.add(new Token(Classificador.ELSE,"else"));
            System.out.println("<condicao2> -> = else <bloco1>");
        }else{
            System.out.println("<condicao2> -> = #");
        }
    }
    
    private void ntExpressaoFor(){
        if (tokenIp.getClasse()!=Classificador.ABREPARENTESES){
            throw new IllegalArgumentException("Esperava-se '(', recebeu "+tokenIp.printSignificado());
        }
        pilha.add(new Token(Classificador.FECHAPARENTESES,")"));
        pilha.add(new Token(Classificador.NAOTERMINAL,"<expAritm1>"));
        pilha.add(new Token(Classificador.ATRIBUICAO,"="));
        pilha.add(new Token(Classificador.ID,"id"));
        pilha.add(new Token(Classificador.PONTOVIRGULA,";"));
        pilha.add(new Token(Classificador.NAOTERMINAL,"<expRelacional1>"));
        pilha.add(new Token(Classificador.PONTOVIRGULA,";"));
        pilha.add(new Token(Classificador.NAOTERMINAL,"<atribuicao1>"));
        pilha.add(new Token(Classificador.ABREPARENTESES,"("));
        System.out.println("<expfor> -> = ( <atribuicao1> ; <expRelacional1> ; id = <expAritm1> )");
    }
    
    private void ntAtribuicao1(){
        if (tokenIp.getClasse()!=Classificador.ID){
            throw new IllegalArgumentException("Esperava-se id, recebeu "+tokenIp.printSignificado());
        }
        pilha.add(new Token(Classificador.NAOTERMINAL,"<atribuicao2>"));
        pilha.add(new Token(Classificador.ATRIBUICAO,"="));
        pilha.add(new Token(Classificador.ID,"id"));
        System.out.println("<atribuicao1> -> id = <atribuicao2>");
    }
    
    private void ntAtribuicao2(){
        if (tokenIp.getClasse()!=Classificador.ID && tokenIp.getClasse()!=Classificador.ABREPARENTESES && tokenIp.getClasse()!=Classificador.FALSE && tokenIp.getClasse()!=Classificador.TRUE && tokenIp.getClasse()!=Classificador.VCHAR && tokenIp.getClasse()!=Classificador.VDOUBLE && tokenIp.getClasse()!=Classificador.VINTEIRO){
            throw new IllegalArgumentException("Esperava-se id ou '(' ou um valor inteiro,pf,char ou booleano, recebeu "+tokenIp.printSignificado());
        }
        if(tokenIp.getClasse()==Classificador.ABREPARENTESES){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expAritm2>"));
            pilha.add(new Token(Classificador.FECHAPARENTESES,")"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expAritm1>"));
            pilha.add(new Token(Classificador.ABREPARENTESES,"("));
            System.out.println("<atribuicao2> -> ( <expAritm1> ) <expAritm2>");
        }else{
            pilha.add(new Token(Classificador.NAOTERMINAL,"<atribuicao3>"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expRelacional2>"));
            System.out.println("<atribuicao2> -> <expRelacional2><atribuicao3>");
        }
    }
    
    private void ntAtribuicao3(){
        if (tokenIp.getClasse()!=Classificador.OP_ARITMETICA && tokenIp.getClasse()!=Classificador.PONTOVIRGULA){
            throw new IllegalArgumentException("Esperava-se um operador aritmetico ou ';' , recebeu "+tokenIp.printSignificado());
        }
        if(tokenIp.getClasse()==Classificador.OP_ARITMETICA){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expAritm2>"));
            System.out.println("<atribuicao3> -> <expAritm2>");
        }else{
            System.out.println("<atribuicao3> -> #");
        }
    }
    
    private void ntBool1(){
        if (tokenIp.getClasse()!=Classificador.ABREPARENTESES && tokenIp.getClasse()!=Classificador.NEGACAO){
            throw new IllegalArgumentException("Esperava-se '!!' ou ')', recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.ABREPARENTESES){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expBool2>"));
            pilha.add(new Token(Classificador.ABREPARENTESES,"("));
            System.out.println("<expBool1> -> ( <expBool2> ");
        }else{
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expBool2>"));
            pilha.add(new Token(Classificador.ABREPARENTESES,"("));
            pilha.add(new Token(Classificador.NEGACAO,"!!"));
            System.out.println("<expBool1> -> !!( <expBool2> ");
        }
    }
    
    private void ntBool2(){
        if (tokenIp.getClasse()!=Classificador.NEGACAO && tokenIp.getClasse()!=Classificador.ID && tokenIp.getClasse()!=Classificador.FALSE && tokenIp.getClasse()!=Classificador.TRUE && tokenIp.getClasse()!=Classificador.VCHAR && tokenIp.getClasse()!=Classificador.VDOUBLE && tokenIp.getClasse()!=Classificador.VINTEIRO){
            throw new IllegalArgumentException("Esperava-se '!!' ou um valor inteiro,pf,char ou booleano, recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.NEGACAO){
            pilha.add(new Token(Classificador.FECHAPARENTESES,")"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expBool2>"));
            pilha.add(new Token(Classificador.ABREPARENTESES,"("));
            pilha.add(new Token(Classificador.NEGACAO,"!!"));
            System.out.println("<expBool2> -> !!(<expBool2>)");
        }else{
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expBool3>"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expRelacional1>"));
            System.out.println("<expBool2> -> <expRelacional1><expBool3>");
        }
    }
    
    private void ntBool3(){
        if (tokenIp.getClasse()!=Classificador.OP_BOOLEAN_DUAL && tokenIp.getClasse()!=Classificador.FECHAPARENTESES){
            throw new IllegalArgumentException("Esperava-se ')' ou um operador booleano, recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.OP_BOOLEAN_DUAL){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expBool3>"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expRelacional1>"));
            pilha.add(new Token(Classificador.OP_BOOLEAN_DUAL,"opBool"));
            System.out.println("<expBool3> -> opBooleano  <expRelacional1> <expBool3>");
        }else{
            pilha.add(new Token(Classificador.FECHAPARENTESES,")"));
            System.out.println("<expBool3> -> )");
        }
    }
    
    private void ntVarValor(){
        if (tokenIp.getClasse()!=Classificador.FALSE && tokenIp.getClasse()!=Classificador.TRUE && tokenIp.getClasse()!=Classificador.VCHAR && tokenIp.getClasse()!=Classificador.VDOUBLE && tokenIp.getClasse()!=Classificador.VINTEIRO){
            throw new IllegalArgumentException("Esperava-se um valor inteiro,pf,char ou booleano, recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.TRUE){
            pilha.add(new Token(Classificador.TRUE,"true"));
            System.out.println("<varvalor> -> true");
        }else if (tokenIp.getClasse()==Classificador.FALSE){
            pilha.add(new Token(Classificador.FALSE,"false"));
            System.out.println("<varvalor> -> false");
        }else if (tokenIp.getClasse()==Classificador.VCHAR){
            pilha.add(new Token(Classificador.VCHAR,"char"));
            System.out.println("<varvalor> -> valorchar");
        }else if (tokenIp.getClasse()==Classificador.VDOUBLE){
            pilha.add(new Token(Classificador.VDOUBLE,"double"));
            System.out.println("<varvalor> -> valordouble");
        }else{
            pilha.add(new Token(Classificador.VINTEIRO,"int"));
            System.out.println("<varvalor> -> valorint");
        }
    }
    
    private void ntRelacional1(){
        if (tokenIp.getClasse()!=Classificador.ID && tokenIp.getClasse()!=Classificador.FALSE && tokenIp.getClasse()!=Classificador.TRUE && tokenIp.getClasse()!=Classificador.VCHAR && tokenIp.getClasse()!=Classificador.VDOUBLE && tokenIp.getClasse()!=Classificador.VINTEIRO){
            throw new IllegalArgumentException("Esperava-se um id ou valor inteiro,pf,char ou booleano, recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.ID){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expRelacional2>"));
            pilha.add(new Token(Classificador.OP_RELACIONAL,"opRelacional"));
            pilha.add(new Token(Classificador.ID,"id"));
            System.out.println("<expRelacional1> -> id opRelacional <expRelacional2>");
        }else{
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expRelacional2>"));
            pilha.add(new Token(Classificador.OP_RELACIONAL,"opRelacional"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<varvalor>"));
            System.out.println("<expRelacional1> -> <varvalor> opRelacional <expRelacional2>");
        }
    }
    
    private void ntRelacional2(){
        if (tokenIp.getClasse()!=Classificador.ID && tokenIp.getClasse()!=Classificador.FALSE && tokenIp.getClasse()!=Classificador.TRUE && tokenIp.getClasse()!=Classificador.VCHAR && tokenIp.getClasse()!=Classificador.VDOUBLE && tokenIp.getClasse()!=Classificador.VINTEIRO){
            throw new IllegalArgumentException("Esperava-se um nome ou valor inteiro,pf,char ou booleano, recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.ID){
            pilha.add(new Token(Classificador.ID,"id"));
            System.out.println("<expRelacional2> -> id");
        }else{
            pilha.add(new Token(Classificador.NAOTERMINAL,"<varvalor>"));
            System.out.println("<expRelacional2> -> <varvalor>");
        }
    }
    
    private void ntAritmetica1(){
        if (tokenIp.getClasse()!=Classificador.ID && tokenIp.getClasse()!=Classificador.FALSE && tokenIp.getClasse()!=Classificador.TRUE && tokenIp.getClasse()!=Classificador.VCHAR && tokenIp.getClasse()!=Classificador.VDOUBLE && tokenIp.getClasse()!=Classificador.VINTEIRO && tokenIp.getClasse()!=Classificador.ABREPARENTESES){
            throw new IllegalArgumentException("Esperava-se um id ou '(' ou ')', recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()!=Classificador.ABREPARENTESES){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expAritm2>"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expRelacional2>"));
            System.out.println("<expAritm1> -> <expRelacional2> <expAritm2>");
        }else{
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expAritm2>"));
            pilha.add(new Token(Classificador.FECHAPARENTESES,")"));
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expAritm1>"));
            pilha.add(new Token(Classificador.ABREPARENTESES,"("));
            System.out.println("<expAritm1> -> ( <expAritm1> ) <expAritm2>");
        }
    }
    
    private void ntAritmetica2(){
        if (tokenIp.getClasse()!=Classificador.OP_ARITMETICA && tokenIp.getClasse()!=Classificador.FECHAPARENTESES && tokenIp.getClasse()!=Classificador.ABREPARENTESES && tokenIp.getClasse()!=Classificador.PONTOVIRGULA){
            throw new IllegalArgumentException("Esperava-se um operacao aritmetica ou '(' ou ';', recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.OP_ARITMETICA){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<expAritm1>"));
            pilha.add(new Token(Classificador.OP_ARITMETICA,"opAritmetico"));
            System.out.println("<expAritm2> -> opAritm <expAritm1>");
        }else{
            System.out.println("<expAritm2> -> #");
        }
    }
    
    private void ntPrintparametros1(){
        if (tokenIp.getClasse()!=Classificador.STRING&&tokenIp.getClasse()!=Classificador.ID&&tokenIp.getClasse()!=Classificador.FECHAPARENTESES){
            throw new IllegalArgumentException("Esperava-se uma String, um id ou ')', recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.STRING){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<printparametros2>"));
            pilha.add(new Token(Classificador.STRING,"valor string"));
            System.out.println("<printparametros1> -> string <printparametros2>");
        }else if (tokenIp.getClasse()==Classificador.ID){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<printparametros2>"));
            pilha.add(new Token(Classificador.ID,"id"));
            System.out.println("<printparametros1> -> id <printparametros2>");
        }else{
            System.out.println("<printparametros1> -> #");
        }
    }
    
    private void ntPrintparametros2(){
        if (tokenIp.getClasse()!=Classificador.VIRGULA&&tokenIp.getClasse()!=Classificador.FECHAPARENTESES){
            throw new IllegalArgumentException("Esperava-se uma ',' ou ')', recebeu "+tokenIp.printSignificado());
        }
        if (tokenIp.getClasse()==Classificador.VIRGULA){
            pilha.add(new Token(Classificador.NAOTERMINAL,"<printparametros1>"));
            pilha.add(new Token(Classificador.VIRGULA,","));
            System.out.println("<printparametros1> -> , <printparametros1>");
        }else{
            System.out.println("<printparametros2> -> #");
        }
    }
    
    private void gerenteDeNaoTerminal(String t){
        switch(t){
            case "<start>" : 
                ntStart();
                break;
            case "<bloco1>" :
                ntBloco1();
                break;
            case "<bloco2>" :
                ntBloco2();
                break;
            case "<linha1>" :
                ntLinha1();
                break;
            case "<linha2>" :
                ntLinha2();
                break;
            case "<varDec1>" : 
                ntVarDec1();
                break;
            case "<varDec2>" :
                ntVarDec2();
                break;
            case "<atribuicao1>" :
                ntAtribuicao1();
                break;
            case "<atribuicao2>" :
                ntAtribuicao2();
                break;
            case "<atribuicao3>" :
                ntAtribuicao3();
                break;
            case "<varvalor>" :
                ntVarValor();
                break;
            case "<expRelacional1>" :
                ntRelacional1();
                break;
            case "<expRelacional2>" :
                ntRelacional2();
                break;
            case "<expBool1>" :
                ntBool1();
                break;
            case "<expBool2>" :
                ntBool2();
                break;
            case "<expBool3>" :
                ntBool3();
                break;
            case "<expAritm1>" :
                ntAritmetica1();
                break;
            case "<expAritm2>" :
                ntAritmetica2();
                break;
            case "<condicao1>" :
                ntCondicao1();
                break;
            case "<condicao2>" :
                ntCondicao2();
                break;
            case "<expfor>" :
                ntExpressaoFor();
                break;
            case "<printparametros1>" :
                ntPrintparametros1();
                break;
            case "<printparametros2>" :
                ntPrintparametros2();
                break;
            default :
                throw new IllegalArgumentException("Não existe caso para " + t);
        }
    }
    
    public void processarCadeia(){
        try{
            while (true){
                if (pilha.empty() || (ip>=entrada.size())){
                    System.out.println("fim");
                    if (pilha.empty() && ip>=entrada.size()){
                        System.out.println("Gramatica aprovada!");
                        System.out.println("Saida : " + saida);
                    }else{
                        System.out.println("Gramatica incorreta!");
                        if (pilha.empty()==false){
                            System.out.println("Talvez você esqueceu '"+ ((String)pilha.pop().getValor()) + "' em linha " + linhaContador);
                        }else{
                            System.out.println("Existem codigo fora de int start(){ }!");
                        }
                    }
                    break;
                }else{
                      /*
                        Object[] tempArray = pilha.toArray();
                        StringBuilder tempPilha = new StringBuilder("| ");
                        for (int i=0;i<tempArray.length;i++){
                            Token o = (Token)tempArray[i];
                            tempPilha.append((String)o.getValor()+" |");
                        }
                        System.out.println(tempPilha);
                    // */
                    
                    tokenIp = entrada.get(ip);
                    linhaContador = tokenIp.getLinha();
                    Token topoPilha = pilha.pop();
                    //System.out.println("ip : " + tokenIp.printSignificado());
                    //System.out.println("topoPilha : " + topoPilha.printSignificado());
                    if (topoPilha.getClasse().equals( Classificador.NAOTERMINAL )){
                        //System.out.println("nao terminal : " + (String)topoPilha.getValor());
                        gerenteDeNaoTerminal((String)topoPilha.getValor());
                    }else{ //terminal
                        if (tokenIp.getClasse()==topoPilha.getClasse()){
                            //System.out.println("terminal "+topoPilha.printSignificado());
                            if (tokenIp.getValor()==null){
                                saida.append(topoPilha.getClasse().significado());
                            }else{
                                try{
                                    saida.append((String)tokenIp.getValor());
                                }catch(Exception e){
                                    saida.append(topoPilha.getClasse().significado());
                                }
                            }
                            saida.append(" | ");
                            ip++;
                        }else{
                            Object valor = tokenIp.getValor();
                            if (valor==null){
                                valor = tokenIp.getClasse().significado();
                            }
                            throw new IllegalArgumentException("Pilha e entrada diferenciando, talvez você esqueceu '"+ ((String)topoPilha.getValor()) + "' no lugar de '" +  (String)valor + "' em linha " + linhaContador);
                        }
                    }
                }
                /*
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SintaticoExec.class.getName()).log(Level.SEVERE, null, ex);
                }
                */
            }
        }catch(IllegalArgumentException e){
            //e.printStackTrace();
            System.out.println("Gramatica incorreta!");
            System.out.println("Erro : " + e.getMessage());
        }
    }
}
