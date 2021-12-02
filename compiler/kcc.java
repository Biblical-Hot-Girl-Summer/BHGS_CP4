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

/**
 * The file that holds the main and acts as the larger container of the compiler
 * by allowing myListener to do the vast majority of the functionality
 * @author Jacob Morris
 * @author Aaron Bone
 * @version 1.0
 * Compiler Assignment 4
 * CS322 - Compiler Construction
 * Fall 2021
 *
 */

public class kcc{


	/*
	 * The main where the program (or compiler in this case) actually executes
	 */
    public static void main(String[] args){
        CharStream input;
        KnightCodeLexer lexer;
        CommonTokenStream tokens;
        KnightCodeParser parser;

        
	System.out.println("Compiler executing. . . ");

	String file;
	String output;
	if(args.length == 2) {
		file = args[0];
		output = args[1];	
	} 
	else if(args.length == 1) {
		file = args[0];
		output = "output/output1";
	}
	else {
		file = "tests/example1.kc";
		output = "output/output1";
	}
	
        try{
            input = CharStreams.fromFileName(file);  //get the input
            lexer = new KnightCodeLexer(input); //create the lexer
            tokens = new CommonTokenStream(lexer); //create the token stream
            parser = new KnightCodeParser(tokens); //create the parser
            ParseTree tree = parser.file();  //set the start location of the parser
            myListener listener = new myListener(output, false);
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, tree);

       }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}//end class
