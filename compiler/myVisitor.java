package compiler;

import lexparse.*;

public class myVisitor extends tinyBaseVisitor{

	@Override
	public Object visitStmt(tinyParser.StmtContext ctx){ 

		System.out.println("Statement context: " + ctx.getText());

		return super.visitStmt(ctx); 

	}	

	@Override
	public Object visitWrite_stmt(tinyParser.Write_stmtContext ctx){ 

		System.out.println("Write statement context: " + ctx.getText());
		return super.visitWrite_stmt(ctx); 

	}

}//end class






