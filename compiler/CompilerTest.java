package compiler;
/**
 * This class encapsulates a basic grammar test.
 */

import java.util.Scanner;
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

	System.out.println("Compiler executing. . . ");

        try{
            input = CharStreams.fromFileName(args[0]);  //get the input
            lexer = new tinyLexer(input); //create the lexer
            tokens = new CommonTokenStream(lexer); //create the token stream
            parser = new tinyParser(tokens); //create the parser

	    //adding custom error listener
	    //parser.removeErrorListeners();

	    //parser.addErrorListener(new VerboseListener());

       
            ParseTree tree = parser.program();  //set the start location of the parser
             
           // System.out.println(tree.toStringTree(parser));
            
           //Trees.inspect(tree, parser);
            
        
	    Scanner scan = new Scanner(System.in);
	    System.out.print("Enter name for class file: ");
	    String classFile = scan.next();
	    System.out.print("Debug?  Y/N");
	    String debug = scan.next();
	    boolean debugFlag = false;
            if(debug.equals("Y"))
		debugFlag = true;		
	

	    myListener listener = new myListener(classFile, debugFlag);
	    ParseTreeWalker walker = new ParseTreeWalker();
	    walker.walk(listener, tree);
	    
	  // myVisitor visitor = new myVisitor();
	  // visitor.visit(tree);

       }
        catch(IOException e){
            System.out.println(e.getMessage());
        }


    }




}//end class
