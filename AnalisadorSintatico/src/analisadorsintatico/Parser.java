package analisadorsintatico;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Parser {
    private final Lexer lex;
    private Token lookahead;
    int sync = 0;
    
    public Parser(Lexer lex) throws FileNotFoundException, IOException{
        this.lex = lex;
        lookahead = lex.proxToken();
    }
    
    public void start() throws Exception{
        E();
        System.out.println("Entrada aceita.");
        System.out.println("Erros de Sync: " + sync + ".");
    }
    
    private void match(Token token) throws Exception{
        if(lookahead.tipo == token.tipo){
            lookahead = lex.proxToken();
        }
        else{
            throw new Exception("Erro: Valores não batem: '" + lookahead.tipo + "' e '" + token.tipo + "'.");
        }
    }
    
    public void E() throws Exception{
        switch(lookahead.tipo){
            case Id:
                T();
                ELine();
                break;
                
            case Sum:
                throw new Exception("Erro: Simbolo não permitido em E: '" + lookahead.tipo + "'");
            
            case Mult:
                throw new Exception("Erro: Simbolo não permitido em E: '" + lookahead.tipo + "'");
                
            case OpBracket:
                T();
                ELine();
                break;
            
            case ClBracket:
                match(new Token(TokenType.ClBracket));
                sync++;
                E();
                break;
            
            case EOF:
                sync++;
                break;
        }
    }
    
    public void ELine() throws Exception{
        switch(lookahead.tipo){
            case Id:
                throw new Exception("Erro: Simbolo não permitido em E': '" + lookahead.tipo + "'");
                
            case Sum:
                match(new Token(TokenType.Sum));
                T();
                ELine();
                break;
                
            case Mult:
                throw new Exception("Erro: Simbolo não permitido em E': '" + lookahead.tipo + "'");
                
            case OpBracket:
                throw new Exception("Erro: Simbolo não permitido em E': '" + lookahead.tipo + "'");
                
            case ClBracket:
                break;
                
            case EOF:
                break;
        }
    }
    
    public void T() throws Exception{
        switch(lookahead.tipo){
            case Id:
                F();
                TLine();
                break;
                
            case Sum:
                match(new Token(TokenType.Sum));
                sync++;
                T();
                break;
                
            case Mult:
                throw new Exception("Erro: Simbolo não permitido em T: '" + lookahead.tipo + "'");
                
            case OpBracket:
                F();
                TLine();
                break;
                
            case ClBracket:
                match(new Token(TokenType.ClBracket));
                sync++;
                break;
                
            case EOF:
                sync++;
                break;
        }
    }
    
    public void TLine() throws Exception{
        switch(lookahead.tipo){
            case Id:
                throw new Exception("Erro: Simbolo não permitido em T': '" + lookahead.tipo + "'");
                
            case Sum:
                break;
                
            case Mult:
                match(new Token(TokenType.Mult));
                F();
                TLine();
                break;
                
            case OpBracket:
                throw new Exception("Erro: Simbolo não permitido em T': '" + lookahead.tipo + "'");
                
            case ClBracket:
                break;
                
            case EOF:
                break;
        }
    }
    
    public void F() throws Exception{
        switch(lookahead.tipo){
            case Id:
                match(new Token(TokenType.Id));
                break;
                
            case Sum:
                match(new Token(TokenType.Sum));
                sync++;
                F();
                break;
                
            case Mult:
                match(new Token(TokenType.Mult));
                sync++;
                F();
                break;
                
            case OpBracket:
                match(new Token(TokenType.OpBracket));
                E();
                match(new Token(TokenType.ClBracket));
                break;
                
            case ClBracket:
                match(new Token(TokenType.ClBracket));
                sync++;
                F();
                break;
                
            case EOF:
                sync++;
                break;
        }
    }
}
