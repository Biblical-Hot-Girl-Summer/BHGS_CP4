package compiler;

import lexparse.*;

/**
 * Ultimately did not need to use. So the visitor was never used.
 * @author Jacob Morris
 * @author Aaron Bone
 * @version 1.0
 * Compiler Assignment 4
 * CS322 - Compiler Construction
 * Fall 2021
 *
 */

public class myVisitor extends KnightCodeBaseVisitor{

	@Override
	public Object visitPrint(KnightCodeParser.PrintContext ctx){ 

		System.out.println("Statement context: " + ctx.getText());

		return super.visitPrint(ctx); 

	}	


}//end class