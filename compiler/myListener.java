package compiler;

import lexparse.*; //classes for lexer parser
import org.objectweb.asm.*;  //classes for generating bytecode
import org.objectweb.asm.Opcodes; //Explicit import for ASM bytecode constants


public class myListener extends tinyBaseListener{

	private ClassWriter cw; 
	private MethodVisitor mainVisitor;
	private String programName; 

	public myListener(String programName){
	       
		this.programName = programName;

	}//end constructor

	public void setupClass(){
		
		//Set up the classwriter
		cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,this.programName, null, "java/lang/Object",null);
	
		//Use local MethodVisitor to create the constructor for the object
		MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1,1);
        mv.visitEnd();
       	
		//Use global MethodVisitor to write bytecode according to entries in the parsetree	
	 	mainVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,  "main", "([Ljava/lang/String;)V", null, null);
        mainVisitor.visitCode();

	}//end setupClass

	public void closeClass(){
		//Use global MethodVisitor to finish writing the bytecode and write the binary file.
		mainVisitor.visitInsn(Opcodes.RETURN);
		mainVisitor.visitMaxs(3, 3);
		mainVisitor.visitEnd();

		cw.visitEnd();

        byte[] b = cw.toByteArray();

        Utilities.writeFile(b,this.programName+".class");
        
        System.out.println("Done!");

	}//end closeClass


	public void enterProgram(tinyParser.ProgramContext ctx){

		System.out.println("Enter program rule for first time");
		setupClass();
	}

	public void exitProgram(tinyParser.ProgramContext ctx){

		System.out.println("Leaving program rule. . .");
		closeClass();

	}

	public void exitInteger(tinyParser.IntegerContext ctx){

		System.out.println(ctx.getText());

	}	


	public void exitExpr(tinyParser.ExprContext ctx){

		System.out.println(ctx.getText());
	}

	@Override public void enterExpr_list(tinyParser.Expr_listContext ctx) { System.out.println(ctx.getText());}

	@Override
	public void enterWrite_stmt(tinyParser.Write_stmtContext ctx){

		String output = ctx.getText();
		mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mainVisitor.visitLdcInsn(output);
		mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);

	}//end enterWrite_stmt

}//end class





