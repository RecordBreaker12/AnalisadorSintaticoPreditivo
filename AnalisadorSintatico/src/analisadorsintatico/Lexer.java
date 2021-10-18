package analisadorsintatico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lexer {
    final private String espaco = " \n\t";
    static BufferedReader arq;
    public int posicao;
    public String input;
    
    public boolean temInput(){
        return !input.isEmpty() && posicao < input.length();
    }
    
    public Lexer(String file) throws FileNotFoundException, IOException{
        arq = new BufferedReader(new FileReader(file));
        input = arq.readLine();
        input = input += "$";
        posicao = 0;
    }
    
    private char proxChar(){
        if(posicao == input.length()){
            return Character.MIN_VALUE;
        }
        return input.charAt(posicao++);
    }
    
    public Token proxToken(){
        Character peek;
        do{
            peek = proxChar();
        }while(espaco.contains(peek.toString()));
        
        switch(peek){
            case '$':
                return new Token(TokenType.EOF);
            
            case '+':
                return new Token(TokenType.Sum);
              
            case '*':
                return new Token(TokenType.Mult);
            
            case '(':
                return new Token(TokenType.OpBracket);
                
            case ')':
                return new Token(TokenType.ClBracket);
            
            case 'i':
                peek = proxChar();
                peek = proxChar();
                
                if(peek != Character.MIN_VALUE){
                    posicao--;
                }
                
                return new Token(TokenType.Id);
                
            default:
                return new Token(TokenType.Invalido);
        }
    }
}