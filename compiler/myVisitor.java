package compiler;

import lexparse.*;

public class myVisitor extends KnightCodeBaseVisitor{

	@Override
	public Object visitPrint(KnightCodeParser.PrintContext ctx){ 

		System.out.println("Statement context: " + ctx.getText());

		return super.visitPrint(ctx); 

	}	

	/*@Override
	public Object exitPrint(KnightCodeParser.PrintContext ctx){ 

		System.out.println("Write statement context: " + ctx.getText());
		return super.visitWrite_stmt(ctx); 

	}*/

}//end class