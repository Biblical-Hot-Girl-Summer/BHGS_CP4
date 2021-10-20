package compiler;
/**
 * This class encapsulates a basic grammar test.
 */

import java.io.IOException;
//ANTLR packages
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.Trees;

import lexparse.*;

public class CompilerTest{


    public static void main(String[] args){
        CharStream input;
        tinyLexer lexer;
        CommonTokenStream tokens;
        tinyParser parser;

        try{
            input = CharStreams.fromFileName(args[0]);  //get the input
            lexer = new tinyLexer(input); //create the lexer
            tokens = new CommonTokenStream(lexer); //create the token stream
            parser = new tinyParser(tokens); //create the parser
       
            ParseTree tree = parser.program();  //set the start location of the parser
             
            
            Trees.inspect(tree, parser);
            
            //System.out.println(tree.toStringTree(parser));
        
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }


    }




}//end class
