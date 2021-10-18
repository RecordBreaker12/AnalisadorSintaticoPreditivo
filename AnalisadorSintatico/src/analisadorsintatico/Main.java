package analisadorsintatico;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, Exception {
        String noError = "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\AnalisadorSintatico\\src\\analisadorsintatico\\TestComplete";
        Lexer lex1 = new Lexer(noError);
        Parser parse1 = new Parser(lex1);
        parse1.start();
        
        String syncError = "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\AnalisadorSintatico\\src\\analisadorsintatico\\Sync";
        Lexer lex2 = new Lexer(syncError);
        Parser parse2 = new Parser(lex2);
        parse2.start();
        
        String fatalError = "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\AnalisadorSintatico\\src\\analisadorsintatico\\Fatal";
        Lexer lex3 = new Lexer(fatalError);
        Parser parse3 = new Parser(lex3);
        parse3.start();
    }
    
}
