package analisadorsintatico;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TokenType{
    Id(0),
    Sum(1),
    Mult(2),
    OpBracket(3),
    ClBracket(4),
    EOF(99),
    Invalido(-1);
    
    private static final Map<Integer,TokenType> terminalSymbol2TokenType = new HashMap<Integer, TokenType>();

    static{
        for(TokenType t : EnumSet.allOf(TokenType.class)){
            terminalSymbol2TokenType.put(t.terminalSymbol(), t);
        }
    }

    private final int terminalSymbol;
    
    private TokenType(Integer terminalSymbol) {
        this.terminalSymbol = terminalSymbol;
    }

    public int terminalSymbol(){
        return this.terminalSymbol;
    }

    public static TokenType byTerminalSymbol(Integer i) {
        return terminalSymbol2TokenType.get(i);
    }

}