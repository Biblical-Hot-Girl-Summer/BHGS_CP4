package compiler;

import lexparse.*;

public class myListener extends tinyBaseListener{

	public void enterProgram(tinyParser.ProgramContext ctx){

		System.out.println("Enter program rule for first time");
	
	}

	public void exitProgram(tinyParser.ProgramContext ctx){

		System.out.println("Leaving program rule. . .");


	}

	public void exitInteger(tinyParser.IntegerContext ctx){

		System.out.println(ctx.getText());

	}	


	public void exitExpr(tinyParser.ExprContext ctx){

		System.out.println(ctx.getText());
	}




}//end class
