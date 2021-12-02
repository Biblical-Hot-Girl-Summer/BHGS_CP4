package compiler;

import java.util.HashMap;

import org.antlr.v4.runtime.ParserRuleContext; // need to debug every rule
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import lexparse.*; //classes for lexer parser
import org.objectweb.asm.*;  //classes for generating bytecode
import org.objectweb.asm.Opcodes; //Explicit import for ASM bytecode constants

/**
 * This is where the vast majority of the functionality for the compiler is based in.
 * The methods are overiden from the lexparse package, specifically the knightcode listener.
 * The symbol table is included here as well and most of the other extra classes
 * are implemented within this listener.
 * @author Jacob Morris
 * @author Aaron Bone
 * @version 1.0
 * Compiler Assignment 4
 * CS322 - Compiler Construction
 * Fall 2021
 *
 */

public class myListener extends KnightCodeBaseListener{
	
	private ClassWriter cw;
	private MethodVisitor mainVisitor;
	private String programName;
	private boolean debug;
	private String id;
	private Variable v;
	public HashMap<String, Variable> symbolTable = new HashMap<String, Variable>();
	private String INT = "INTEGER";
	private String STR = "STRING";
	
	public String stringOut;
	public int intOut;
	
	public String key;
	public String key2;
	public String keyID;
	public String iD;
	public String enterAndExitNumber;
	
	public Variable currentVar;
	public Variable extraVar;
	public Variable var1;
	public Variable var2;
	public int memoryCounter;
	
	public String genNum;
	public String genInt;
	public String genString;
	public String genIntStr;
	public boolean genBool;
	public boolean genPrint;
	
	public String op1;
	public int operator1;
	public String op2;
	public int operator2;
	public String decOp1;
	public int decOperator1;
	public String decOp2;
	public int decOperator2;
	public String operation;
	public boolean operationDone;
	public String arithmeticOperation;
	public int operationCount = 0;
	
	public String compString;
	public String decCompSymbol;
	public int decComparison;
	public int decCount;
	
	public String prev;
	public String then1;
	public String then2;
	public String else1;
	public String else2;
	
	public int count;
	public int num;
	public boolean expression1;
	public boolean expression2;
	public boolean printString;
	public boolean printTwice;
	public boolean exit;
	public boolean alreadyRead = false;
	public int readStoredLocation;

	
	
	public Label endDecLab0 = new Label();
	public Label endDecLab1 = new Label();
	public Label endDecLab2 = new Label();
	public Label endDecLab3 = new Label();
	public Label endDecLab4 = new Label();
	public Label endDecLab5 = new Label();
	public Label endDecLab6 = new Label();
	public Label endDecLab7 = new Label();
	public Label endDecLab8 = new Label();
	public Label endDecLab9 = new Label();
	
	public Label startOfElse0 = new Label();
	public Label startOfElse1 = new Label();
	public Label startOfElse2 = new Label();
	public Label startOfElse3 = new Label();
	public Label startOfElse4 = new Label();
	public Label startOfElse5 = new Label();
	public Label startOfElse6 = new Label();
	public Label startOfElse7 = new Label();
	public Label startOfElse8 = new Label();
	public Label startOfElse9 = new Label();
	
	public static int ifCount1 = 0;
	public static int elseCount1 = 0;
	
	public int[]ifCounterArr = new int[10];
	public int ifCount00 = 0;
	public int ifCount01 = 0;
	public int ifCount02 = 0;
	public int ifCount03 = 0;
	public int ifCount04 = 0;
	public int ifCount05 = 0;
	public int ifCount06 = 0;
	public int ifCount07 = 0;
	public int ifCount08 = 0;
	public int ifCount09 = 0;

	public static int decLabCount = 0;
	public int decCount2 = 0;

	public String decNestStack = "000";	
	public String decElseStack = "000";	
	public String decIfStack = "000";
	
	public Stacker decIfStacker = new Stacker();
	public Stacker decElseStacker = new Stacker();

	public boolean firstNestedDec = false;
	public int elseVisitor = 0;
	
	public int loopLabCount = 0;
	public int loopCount = 0;
	
	public String loopOp1;
	public int loopOperator1;
	public String loopOp2;
	public int loopOperator2;
	
	public String loopCompSymbol;
	
	public String loopNestStack = "000";	
	
	
	public Label endOfloop0 = new Label();
	public Label endOfloop1 = new Label();
	public Label endOfloop2 = new Label();
	public Label endOfloop3 = new Label();
	public Label endOfloop4 = new Label();
	public Label endOfloop5 = new Label();
	public Label endOfloop6 = new Label();
	public Label endOfloop7 = new Label();
	public Label endOfloop8 = new Label();
	public Label endOfloop9 = new Label();
	
	public Label startOfloop0 = new Label();
	public Label startOfloop1 = new Label();
	public Label startOfloop2 = new Label();
	public Label startOfloop3 = new Label();
	public Label startOfloop4 = new Label();
	public Label startOfloop5 = new Label();
	public Label startOfloop6 = new Label();
	public Label startOfloop7 = new Label();
	public Label startOfloop8 = new Label();
	public Label startOfloop9 = new Label();
	
	
	

	
	

	/*
	 * The default constructor that will take in the programs meant to be run
	 */
	public myListener(String programName, boolean debug){
	       
		this.programName = programName;
		this.debug = debug;

	}

	/*
	 * lays out the symbol table when utilized
	 */
	public void printHashMap(HashMap<String,Variable> map){
		
		Object[]keys = map.keySet().toArray();
		String val;
		int mem;
		boolean set;
		
		for(int i = 0; i < keys.length; i++){
			System.out.print(keys[i]);
			System.out.print(": " + map.get(keys[i]).variableType); 
			val = map.get(keys[i]).value;
			mem = map.get(keys[i]).memLoc;
			set = map.get(keys[i]).valueSet;
			System.out.println(", " + val + ", " + mem + ", " + set);
			
		} 	
		
	}
	
	/*
	 * pushes an int onto a stack
	 */
	public int push(Stacker s, int value){
		
		if(s.head == 29){
			return -1;
		} else {	
			s.head++;
			s.stack[s.head] = value;
			return value;
		}
	
	}
	
	/*
	 * takes the top value off of the stack
	 */
	public int pop(Stacker s){
		
		int value = 0;
		if(s.head == 0){
			return value;
		} else {	
			
			value = s.stack[s.head];
			s.stack[s.head] = 0;
			s.head--;
			return value;
		}
	
	}
	
	/*
	 * Look at the top of the stack
	 */
	public int peek(Stacker s){
		
		return s.stack[s.head];

	}
	
	/*
	 * Prepares the class file and the beginnings of ASM
	 */
	public void setupClass(){
		
		cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,this.programName, null, "java/lang/Object",null);
		MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		mv.visitVarInsn(Opcodes.ALOAD, 0); 
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(1,1);
		mv.visitEnd();
   	 	mainVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,  "main", "([Ljava/lang/String;)V", null, null);
	 	mainVisitor.visitCode();

	}

	/*
	 * Closes the class and ends ASM operations. Also creates a class file
	 */
	public void closeClass(){
		mainVisitor.visitInsn(Opcodes.RETURN);
		mainVisitor.visitMaxs(3, 3);
		mainVisitor.visitEnd();

		cw.visitEnd();

		byte[] b = cw.toByteArray();

		Utilities.writeFile(b,this.programName+".class");
     
		System.out.println("Done!");

	}


	/*
	 * Enter the file actually being executed and start setting up the class
	 */
	public void enterFile(KnightCodeParser.FileContext ctx){

		System.out.println("Enter program rule for first time");
		setupClass();
	}

	/*
	 * cComplete run of file and finish making the class
	 */
	public void exitFile(KnightCodeParser.FileContext ctx){

		System.out.println("Leaving program rule. . .");
		closeClass();

	}

	/*
	 * Prints out the context
	 */
	private void printContext(String ctx){
		System.out.println(ctx);
	}

	/*
	 * Reveals the rules via context
	 */
	@Override 
	public void enterEveryRule(ParserRuleContext ctx){ 
		if(debug) {
			printContext(ctx.getText());
		}
	}

	/*
	 * Checks what data type is being printed (int and string)
	 */
	@Override
	public void enterPrint(KnightCodeParser.PrintContext ctx){
		
		System.out.println("Type here:");
		String output = ctx.getChild(1).getText();
		mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		
		if(symbolTable.containsKey(key2)){
			genPrint = false;
			extraVar = symbolTable.get(key2);
			
			intOut = extraVar.memLoc;
			if(extraVar.isString()){
				printString = true;
			} else { 	
				printString = false;
			}
			
		} else {
			genPrint = true;
			stringOut = key2; 
		}

	}
	
	/*
	 * Actually prints out the result of enterPrint depending on what data type the value is
	 */
	@Override public void exitPrint(KnightCodeParser.PrintContext ctx){ 

		if(genPrint){
			
			
			mainVisitor.visitLdcInsn(stringOut);
			mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
		} else {
			if(printString){
				
				
				mainVisitor.visitVarInsn(Opcodes.ALOAD, intOut);
				mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
			} else {
				
				
				mainVisitor.visitVarInsn(Opcodes.ILOAD, intOut);
				mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
			}
		}
	}

	/*
	 * Mainly just indicate when entering declare part of grammar
	 */
	@Override public void enterDeclare(KnightCodeParser.DeclareContext ctx) { 
		
		System.out.println("Enter declare");
		
		count = ctx.getChildCount();
	}

	/*
	 * Indicator that compiler is exiting declare state
	 */
	@Override public void exitDeclare(KnightCodeParser.DeclareContext ctx) { 
		
		System.out.println("Exit declare");
	}

	/*
	 * Entering the variable state but also actually setting up a variable object
	 */
	@Override public void enterVariable(KnightCodeParser.VariableContext ctx) { 
		
		System.out.println("Enter variable");
		
		Variable var = new Variable();
		
		String identifier = ctx.getChild(1).getText();
		var.variableType = ctx.getChild(0).getText();
		var.memLoc = memoryCounter;
		
		
		
		symbolTable.put(identifier, var);

		memoryCounter++;
		
	}

	/*
	 * Exit the variable state
	 */
	@Override public void exitVariable(KnightCodeParser.VariableContext ctx) { 
		
		System.out.println("Exit variable");
	}
	
	/*
	 * Entering body state
	 */
	@Override public void enterBody(KnightCodeParser.BodyContext ctx) { 
		
		System.out.println("Enter body!");
		
		count = ctx.getChildCount();
	}

	/*
	 * Exiting body state
	 */
	@Override public void exitBody(KnightCodeParser.BodyContext ctx) { 
		
		System.out.println("Exit body!");
	}

	/*
	 * Entering setvar state as well as checking if child is in symbol table
	 */
	@Override public void enterSetvar(KnightCodeParser.SetvarContext ctx) { 
		if(exit)
			return;
			
		System.out.println("Enter setvar");
		operationCount = 0;
		genIntStr = "";
		
		if(ctx.getChild(1) != null)
			key = ctx.getChild(1).getText();
			

		if(symbolTable.containsKey(key)){
			currentVar = symbolTable.get(key);
		} else {
			System.out.println("Failed to Compile");

			exit = true;
			return;
		
		}
		
		if(currentVar.isString()){
		
			if(ctx.getChild(3).getChildCount() != 0){
				System.out.println("Failed to Compile");

				exit = true;
				return;
			
			}
		
			
			genIntStr = ctx.getChild(3).getText();

		}
	}

	/*
	 * Leave setvarr state and start putting values in symbol table
	 */
	@Override public void exitSetvar(KnightCodeParser.SetvarContext ctx) { 
		
		currentVar.value = genIntStr;
		
		int store = currentVar.memLoc;
		
		genBool = currentVar.isString();
			
		if(genBool){
	
			mainVisitor.visitLdcInsn(currentVar.value);
			mainVisitor.visitVarInsn(Opcodes.ASTORE,store);
		} else {
			
			mainVisitor.visitVarInsn(Opcodes.ISTORE,store);
		}
		currentVar.valueSet = true;
		symbolTable.put(key, currentVar);
		
    	    
    	    	operation = "";
    	    	genIntStr = "";
    	    	
    	    	int tempBoolElse = peek(decElseStacker);
		if(tempBoolElse > 0){
			int newUsage = peek(decIfStacker);			
				if(newUsage == 1){

					Label temper;
					Label tempEnd;
			 		int currentUsage = Character.getNumericValue(decNestStack.charAt(0));
					switch(currentUsage){
						case 1: {
							tempEnd = endDecLab0;
						 	temper = startOfElse0;						
							break;
						}
						case 2: {
							tempEnd = endDecLab1;
							temper = startOfElse1;
							break;
						}
						case 3: {
							tempEnd = endDecLab2;
							temper = startOfElse2;						
							break;
						}
						case 4: {
							tempEnd = endDecLab3;
							temper = startOfElse3;
							break;
						}
						case 5: {
							tempEnd = endDecLab4;
							temper = startOfElse4;
							break;
						}
						case 6: {						
							tempEnd = endDecLab5;
							temper = startOfElse5;
							break;
						}
						case 7: { 						
							tempEnd = endDecLab6;
							temper = startOfElse6;
							break;
					
						}
						case 8: {
							tempEnd = endDecLab7;
							temper = startOfElse7;
							break;
					
						}
						case 9: { 
							tempEnd = endDecLab8;
							temper = startOfElse8;
							break;
						
						}
						case 10: { 
							tempEnd = endDecLab9;
							temper = startOfElse9;
							break;
					
						}
						default: {
							System.out.println("Jump Fail");

							exit = true;
							return;
						}	
							
					}
					mainVisitor.visitJumpInsn(Opcodes.GOTO, tempEnd);
					mainVisitor.visitLabel(temper);
					pop(decElseStacker);
					pop(decIfStacker);	
				} else if(newUsage > 1){

					newUsage = pop(decIfStacker);
					newUsage--;
					push(decIfStacker,newUsage);
				}
		}
		System.out.println("Exit setvar");
	}
	
	/*
	 * When entering a parentheses state, start saving the locations for future 
	 * operations
	 */
	@Override public void enterParenthesis(KnightCodeParser.ParenthesisContext ctx) { 
		
		System.out.println("Enter parenthesis");
		
		
		
		genIntStr += "(";
		arithmeticOperation = ")" + arithmeticOperation;
	}
	
	
	/*
	 * Leave parentheses state
	 */
	@Override public void exitParenthesis(KnightCodeParser.ParenthesisContext ctx) { 
		
		genIntStr += arithmeticOperation.charAt(0);
		if(arithmeticOperation.length() != 0) {
			arithmeticOperation = arithmeticOperation.substring(1);
		}
			
		System.out.println("Exit parenthesis");
	}

	/*
	 * Enter multiplication state, prepare for multiplication
	 */
	@Override public void enterMultiplication(KnightCodeParser.MultiplicationContext ctx) { 
		
		System.out.println("Enter multiplication");
		operationCount++;
		arithmeticOperation = "*" + arithmeticOperation;
	}

	/*
	 * Exit multiplication state but also actually execute bytecode multiplication
	 */
	@Override public void exitMultiplication(KnightCodeParser.MultiplicationContext ctx) { 
		
		operationCount--;	
		

		mainVisitor.visitInsn(Opcodes.IMUL);
            		
		System.out.println("Exit multiplication");
	}

	/*
	 * Enter addition state and prepare for future addition
	 */
	@Override public void enterAddition(KnightCodeParser.AdditionContext ctx) { 
		
		System.out.println("Enter addition");
		operationCount++;
		
		arithmeticOperation = "+" + arithmeticOperation;
	}

	/*
	 * Leave addition state and execute bytecode addition
	 */
	@Override public void exitAddition(KnightCodeParser.AdditionContext ctx) { 
		
		
		operationCount--;	

		mainVisitor.visitInsn(Opcodes.IADD);
                      	
		System.out.println("Exit addition");
	}

	/*
	 * Enter subtraction state and prepare for future subtraction
	 */
	@Override public void enterSubtraction(KnightCodeParser.SubtractionContext ctx) { 
		
		System.out.println("Enter subtraction");
		operationCount++;
		arithmeticOperation = "-"+ arithmeticOperation;
	}

	/*
	 * Leave the state and perform asm subtraction
	 */
	@Override public void exitSubtraction(KnightCodeParser.SubtractionContext ctx) { 
		
		operationCount--;
		mainVisitor.visitInsn(Opcodes.ISUB);
            	           	
		System.out.println("Exit subtraction");
	}

	/*
	 * Enter state whe
	 */
	@Override public void enterNumber(KnightCodeParser.NumberContext ctx) { 
		
		System.out.println("Enter Number");
		enterAndExitNumber = ctx.getText();
		genIntStr += enterAndExitNumber;	
	}

	/*
	 * Exit number state while pushing number onto (asm) stack
	 */
	@Override public void exitNumber(KnightCodeParser.NumberContext ctx) { 
		
		num = Integer.valueOf(enterAndExitNumber);	
		mainVisitor.visitIntInsn(Opcodes.SIPUSH, num);
		System.out.println("Exit Number");
	}

	/*
	 * Enter comparison state and start getting ready to compare some values
	 */
	@Override public void enterComparison(KnightCodeParser.ComparisonContext ctx) { 
		
		System.out.println("Enter Comparison");
		
		if(ctx.getChildCount() != 0){
			compString = ctx.getChild(1).getChild(0).getText();	
			operation = compString + operation;
			if(compString.equals("<>"))
				printTwice = true;

		}	
	}

	/*
	 * Execute actual asm comparisons
	 */
	@Override public void exitComparison(KnightCodeParser.ComparisonContext ctx) { 
		Label label1 = new Label();
    	Label label2 = new Label();
  
    		if(compString.equals(">")){
			
			mainVisitor.visitJumpInsn(Opcodes.IF_ICMPLE, label1);
			arithmeticOperation = ">"+arithmeticOperation;
			
		} else if(compString.equals("<")){
			
			mainVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, label1);
			arithmeticOperation = "<"+arithmeticOperation;
		
		} else if(compString.equals("<>")){
		
			mainVisitor.visitJumpInsn(Opcodes.IF_ICMPEQ, label1);
			arithmeticOperation = "<>"+arithmeticOperation;
		
		} else if(compString.equals("=")){
		
			mainVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label1);
			arithmeticOperation = "="+arithmeticOperation;
		
		}
		
	mainVisitor.visitInsn(Opcodes.ICONST_1);
	mainVisitor.visitJumpInsn(Opcodes.GOTO, label2);
	mainVisitor.visitLabel(label1);
	mainVisitor.visitInsn(Opcodes.ICONST_0);
	mainVisitor.visitLabel(label2);
	
		    	
	System.out.println("Exit Comparison");
		
	}

	/*
	 * Prepare to divide
	 */
	@Override public void enterDivision(KnightCodeParser.DivisionContext ctx) { 
		System.out.println("Enter division");
		operationCount++;
		arithmeticOperation = "/"+arithmeticOperation;

}
	/*
	 * Actually divide with asm
	 */
	@Override public void exitDivision(KnightCodeParser.DivisionContext ctx) {
	operationCount--;		
	mainVisitor.visitInsn(Opcodes.IDIV);
	System.out.println("Exit division");

}
	/*
	 * Enter id state, check if id is in symbol table
	 */
	@Override public void enterId(KnightCodeParser.IdContext ctx) { 
		System.out.println("enter ID");
		keyID = ctx.getText();
		
		if(symbolTable.containsKey(keyID)){
			var1 = symbolTable.get(keyID);
			op1 = keyID;
			
			operator1 = var1.memLoc;
			
			
			if(var1.variableType.equalsIgnoreCase(STR) && operationCount > 0){
				System.out.println("Failed to Compile");

				exit = true;
				return;
			
			}
			
			if(var1.isString()){
				mainVisitor.visitIntInsn(Opcodes.ALOAD, operator1);
			} else {
				mainVisitor.visitIntInsn(Opcodes.ILOAD, operator1);
			}
		} else {
			System.out.println("Failed to Compile");
			
			exit = true;
			return;
		
		}
		
		genIntStr += op1;
		

	}
	
	/*
	 * leave id state
	 */
	@Override public void exitId(KnightCodeParser.IdContext ctx) { 
		System.out.println("Exit ID");
		
		genIntStr += arithmeticOperation.charAt(0);
		if(arithmeticOperation.length() != 0) {
			arithmeticOperation = arithmeticOperation.substring(1);
		}
			arithmeticOperation = arithmeticOperation.substring(1);
		if(printTwice){
			genIntStr += arithmeticOperation.charAt(0);
			if(arithmeticOperation.length() != 0) {
				arithmeticOperation = arithmeticOperation.substring(1);
			}
			printTwice = false;	
		}	
	}
	
	/*
	 * enter read state, prepare to set up scanner for input
	 */
	@Override public void enterRead(KnightCodeParser.ReadContext ctx) { 
		System.out.println("Enter read\n");
	
	
	if(ctx.getChild(1) != null)
		key = ctx.getChild(1).getText();
		
	System.out.println("\n"+key);	
	if(symbolTable.containsKey(key)){
		currentVar = symbolTable.get(key);
	} 
	else {
		System.out.println("Failed to Compile");

		exit = true;
		return;
	
	}

	if(alreadyRead){
		
	
	
	} else {
		alreadyRead = true;
		
		readStoredLocation = memoryCounter;
	
		mainVisitor.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");
        		mainVisitor.visitInsn(Opcodes.DUP);
        		mainVisitor.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System", "in", "Ljava/io/InputStream;");
        		mainVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V" , false);
        		mainVisitor.visitVarInsn(Opcodes.ASTORE,readStoredLocation);
	
	
		memoryCounter++;
		
	}


}
	/*
	 * implement scanner and read any input
	 */
	@Override public void exitRead(KnightCodeParser.ReadContext ctx) { 
		mainVisitor.visitVarInsn(Opcodes.ALOAD,readStoredLocation);
		
		
          	
	
		genBool = (currentVar.isString());
			
		if(genBool){
	
			
			mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextLine", "()Ljava/lang/String;", false);
			mainVisitor.visitVarInsn(Opcodes.ASTORE,currentVar.memLoc);
						
		} else {
		
			
			mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
			mainVisitor.visitVarInsn(Opcodes.ISTORE,currentVar.memLoc);
		
			
			mainVisitor.visitVarInsn(Opcodes.ALOAD,readStoredLocation);
			mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextLine", "()Ljava/lang/String;", false);
			mainVisitor.visitInsn(Opcodes.POP);
			
		}
		
		currentVar.valueSet = true;
		symbolTable.put(key, currentVar);
		
		int tempBoolElse = peek(decElseStacker);
		
		if(tempBoolElse > 0){
		
			int newUsage = peek(decIfStacker);
		
			
				if(newUsage == 1){
					
					Label temper;
					Label tempEnd;
			 		int currentUsage = Character.getNumericValue(decNestStack.charAt(0));
					switch(currentUsage){
						case 1: {
							tempEnd = endDecLab0;
							temper = startOfElse0;						
							break;
						}
						case 2: {
							tempEnd = endDecLab1;
							temper = startOfElse1;
							break;
						}
						case 3: {
							tempEnd = endDecLab2;
							temper = startOfElse2;						
							break;
						}
						case 4: {
							tempEnd = endDecLab3;
							temper = startOfElse3;
							break;
						}
						case 5: {
							tempEnd = endDecLab4;
							temper = startOfElse4;
							break;
						}
						case 6: {						
							tempEnd = endDecLab5;
							temper = startOfElse5;
							break;
						}
						case 7: { 						
							tempEnd = endDecLab6;
							temper = startOfElse6;
							break;
					
						}
						case 8: {
							tempEnd = endDecLab7;
							temper = startOfElse7;
							break;
					
						}
						case 9: { 
							tempEnd = endDecLab8;
							temper = startOfElse8;
							break;
						
						}
						case 10: { 
							tempEnd = endDecLab9;
							temper = startOfElse9;
							break;
					
						}
						default: {
						
							System.out.println("Jump Error");

							
							exit = true;
							return;
						}	
							
					}

		
					mainVisitor.visitJumpInsn(Opcodes.GOTO, tempEnd);
					mainVisitor.visitLabel(temper);
					
					
					pop(decElseStacker);
					pop(decIfStacker);

				
					elseVisitor--;
				} else if(newUsage > 1){
					
					
					newUsage = pop(decIfStacker);
					newUsage--;
					push(decIfStacker,newUsage);

				}
		}

		
		System.out.println("Exit read\n");
	}

	/*
	 * Enter decision state, check syntax for correctness, and track if-else
	 */
	@Override public void enterDecision(KnightCodeParser.DecisionContext ctx) { 
		
		System.out.println("Enter Decision");
		if(decLabCount > 9){
		
		System.out.println("If-Else Overflow");

			exit = true;
			return;

		} else {
			decLabCount++;	
			decCount2++;		
		}
		decNestStack = decLabCount + decNestStack;
		
		decCount = ctx.getChildCount();
		
		if(decCount < 7){
		
			System.out.println("Syntax Error");			
			
			exit = true;
			return;
		
		
		}
		

		
		if(!ctx.getChild(0).getText().equalsIgnoreCase("IF")){
		
			System.out.println("Syntax Error");
			
			
			exit = true;
			return;
		
		
		}
		if(!ctx.getChild(4).getText().equalsIgnoreCase("THEN")){
		
			System.out.println("Syntax Error");
			
			
			exit = true;
			return;
		
		
		}
		if(ctx.getChild(decCount-2).getText().equalsIgnoreCase("ELSE")||ctx.getChild(5).getText().equalsIgnoreCase("ELSE")){
		
				System.out.println("Syntax Error");

				exit = true;
				return;	
		}	
		if(!ctx.getChild(decCount-1).getText().equalsIgnoreCase("ENDIF")){
		
			System.out.println("Syntax Error");

			exit = true;
			return;
		}
		
		
	
		
		decOp1 = ctx.getChild(1).getText();
		if(symbolTable.containsKey(decOp1)){
			var1 = symbolTable.get(decOp1);
			
			if(!var1.valueSet||var1.variableType.equalsIgnoreCase(STR)){
				
				System.out.println("Comparison Error");

				
				exit = true;
				return;
        			
				
			}
			
			operator1 = var1.memLoc;
			mainVisitor.visitIntInsn(Opcodes.ILOAD, operator1);
		} else {
			try{
            			
            			decOperator1 = Integer.valueOf(decOp1);
            			mainVisitor.visitIntInsn(Opcodes.SIPUSH, decOperator1);
            			
            			
       		     } catch(NumberFormatException e){
				System.out.println("Comparison Error");
				exit = true;
				return;		
        		     }
		}
		
		decOp2 = ctx.getChild(3).getText();
		if(symbolTable.containsKey(decOp2)){
			var2 = symbolTable.get(decOp2);

			if(!var2.valueSet || var2.variableType.equalsIgnoreCase(STR)){
				
				System.out.println("Comparison Error");
				exit = true;
				return;	
			}
			
			operator2 = var2.memLoc;
			mainVisitor.visitIntInsn(Opcodes.ILOAD, operator2);
		} else {
		
			  try{
            			
            			decOperator2 = Integer.valueOf(decOp2);
            			mainVisitor.visitIntInsn(Opcodes.SIPUSH, decOperator2);	
       		     } catch(NumberFormatException e){
				System.out.println("Comparison Error");

				
				exit = true;
				return;
        			
        		     }	
		}		
			
		decCompSymbol = ctx.getChild(2).getChild(0).getText();
		
		String temporaryCounterString;
		
		int temporaryIfCounter = 0;
		int temporaryElseCounter = 0;
		int tempI = 5;
		int elseNodeNum = -1;
		boolean elseFound = false;
		
		while(tempI < decCount && !elseFound){
		
			if(ctx.getChild(tempI).getText().equalsIgnoreCase("ELSE")){
					prev = "ELSE";
					elseNodeNum = tempI;
					elseFound = true;
			} else {
			
				if(!ctx.getChild(tempI).getText().equalsIgnoreCase("ENDIF")){
					temporaryIfCounter++;
					
					
					if(ctx.getChild(tempI).getText().substring(0,5).equalsIgnoreCase("WHILE")){
				
							int t = ctx.getChild(tempI).getChild(0).getChildCount();
							int f = ctx.getChild(tempI).getChild(0).getChildCount()-6;
							String tempoStringer = "";							
							int i = 5;
							while(i<t-1){								
								tempoStringer = ctx.getChild(tempI).getChild(0).getChild(i).getChild(0).getChild(0).getText();
								if(tempoStringer.length()>=5){	
								if(ctx.getChild(tempI).getChild(0).getChild(i).getChild(0).getChild(0).getText().substring(0,5).equalsIgnoreCase("WHILE")){
									
									int cll = ctx.getChild(tempI).getChild(0).getChild(i).getChild(0).getChildCount();
									int j = 5;
									while(j<cll-1){
										String tempnextstringer = ctx.getChild(tempI).getChild(0).getChild(i).getChild(0).getChild(0).getText();
										if(tempnextstringer.length()>=5){				
							if(ctx.getChild(tempI).getChild(0).getChild(i).getChild(0).getChild(j).getChild(0).getText().substring(0,5).equalsIgnoreCase("WHILE")){
									
									
												System.out.println("While Overflow");

				
												exit = true;
												return;								
										}			
										}
										j++;
									}
									f+= cll-6;		
								}
								}
								i++;
							}		
							temporaryIfCounter += f;	
					}
				}
			}	
			tempI++;
		}	



		if(elseFound){	
			elseVisitor++;
			while(tempI < decCount){	
				if(!ctx.getChild(tempI).getText().equalsIgnoreCase("ENDIF")){
					temporaryElseCounter++;
					
					System.out.println(ctx.getChild(tempI).getText());
				
				}
			
				tempI++;
			
			}


		} else {
			System.out.println("No else statements");
		}	

		int brukOgKast = pop(decIfStacker);
		brukOgKast += temporaryElseCounter;
		push(decIfStacker, brukOgKast);
		push(decIfStacker, temporaryIfCounter);	
		push(decElseStacker, temporaryElseCounter);
		int tempElse = elseCount1;
		elseCount1 += temporaryElseCounter;
		ifCount1 +=temporaryIfCounter;	

			
		
		Label temp; 
		Label tempEnd;
		
			
			int currentUsage = Character.getNumericValue(decNestStack.charAt(0));
			switch(currentUsage){
				case 1: {
					temp = startOfElse0;
					tempEnd = endDecLab0;
					break;
				}
				case 2: {
					temp = startOfElse1;
					tempEnd = endDecLab1;
					break;
				}
				case 3: {
					temp = startOfElse2;
					tempEnd = endDecLab2;
					break;
				}
				case 4: {
					temp = startOfElse3;
					tempEnd = endDecLab3;
					break;
				}
				case 5: {
					temp = startOfElse4;
					tempEnd = endDecLab4;
					break;
				}
				case 6: {
					temp = startOfElse5;
					tempEnd = endDecLab5;
					break;
				}
				case 7: { 
					temp = startOfElse6;
					tempEnd = endDecLab6;
					break;
				
				}
				case 8: {
					temp = startOfElse7;
					tempEnd = endDecLab7;
					break;
				
				}
				case 9: { 
					temp = startOfElse8;
					tempEnd = endDecLab8;
					break;
				
				}
				case 10: { 
					temp = startOfElse9;
					tempEnd = endDecLab9;
					break;
				
				}
				default: {
				
					System.out.println("Jump Fail");

					
					exit = true;
					return;
				}		
			}
			
		String tempStringDecBla = "ifComp... ";
		if(elseCount1 > tempElse){
			tempStringDecBla += "startOfElse Label: ";
			
		} else {
			tempStringDecBla += "end Label: ";
			temp = tempEnd;
				
		}	
		
			if(decCompSymbol.equals(">")){
				
				mainVisitor.visitJumpInsn(Opcodes.IF_ICMPLE, temp);
				
			} else if(decCompSymbol.equals("<")){
				
				mainVisitor.visitJumpInsn(Opcodes.IF_ICMPGE,temp);
			
			} else if(decCompSymbol.equals("<>")){
			
				mainVisitor.visitJumpInsn(Opcodes.IF_ICMPEQ, temp);
			
			} else if(decCompSymbol.equals("=")){
			
				mainVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, temp);
			
			}
			
	}
	
	/*
	 * Finish syntax assessment and if-else check
	 */
	@Override public void exitDecision(KnightCodeParser.DecisionContext ctx) { 
		
			Label temper; 	
			Label temp;
			int currentUsage = Character.getNumericValue(decNestStack.charAt(0));
			
			switch(currentUsage) {
		
				case 1: {
					temp = endDecLab0;
					temper = startOfElse0;	
					break;
				}
				case 2: {
					temp = endDecLab1;
					temper = startOfElse1;	
					break;
				}
				case 3: {
					temp = endDecLab2;
					temper = startOfElse1;	
					break;
				}
				case 4: {
					temp = endDecLab3;
					temper = startOfElse1;	
					break;
				}
				case 5: {
					temp = endDecLab4;
					temper = startOfElse1;	
					break;
				}
				case 6: {
					temper = startOfElse5;
					temp = endDecLab5;
					break;
				}
				case 7: { 
					temper = startOfElse6;
					temp = endDecLab6;
					break;
				
				}
				case 8: {
					temper = startOfElse7;
					temp = endDecLab7;
					break;
				
				}
				case 9: { 
					temper = startOfElse8;
					temp = endDecLab8;
					break;
				
				}
				case 10: { 
					temper = startOfElse9;
					temp = endDecLab9;
					break;
				
				}
				default: {
					temper = startOfElse1;	
			
					System.out.println("Jump Failure");

					
					exit = true;
					return;
				}	
					
			}
			decCount2--;

		
		mainVisitor.visitLabel(temp);
		
		if(decNestStack.length() != 0)
			decNestStack = decNestStack.substring(1);
		
		
		int tempBoolElse = peek(decElseStacker);
		int newUsage;
		if(tempBoolElse > 0){		
			newUsage = peek(decIfStacker);		
			
				if(newUsage == 1){

					Label tempEnd;
			 		currentUsage = Character.getNumericValue(decNestStack.charAt(0));
					switch(currentUsage){
						case 1: {
							tempEnd = endDecLab0;
							temper = startOfElse0;						
							break;
						}
						case 2: {
							tempEnd = endDecLab1;
							temper = startOfElse1;
							break;
						}
						case 3: {
							tempEnd = endDecLab2;
							temper = startOfElse2;						
							break;
						}
						case 4: {
							tempEnd = endDecLab3;
							temper = startOfElse3;
							break;
						}
						case 5: {
							tempEnd = endDecLab4;
							temper = startOfElse4;
							break;
						}
						case 6: {						
							tempEnd = endDecLab5;
							temper = startOfElse5;
							break;
						}
						case 7: { 						
							tempEnd = endDecLab6;
							temper = startOfElse6;
							break;
					
						}
						case 8: {
							tempEnd = endDecLab7;
							temper = startOfElse7;
							break;
					
						}
						case 9: { 
							tempEnd = endDecLab8;
							temper = startOfElse8;
							break;
						
						}
						case 10: { 
							tempEnd = endDecLab9;
							temper = startOfElse9;
							break;
					
						}
						default: {
						
							System.out.println("Unable to Jump");

							
							exit = true;
							return;
						}	
							
					}

		
					mainVisitor.visitJumpInsn(Opcodes.GOTO, tempEnd);
					mainVisitor.visitLabel(temper);
					
					
					pop(decElseStacker);
					pop(decIfStacker);


					elseVisitor--;
				} else if(newUsage > 1){
					
					
					newUsage = pop(decIfStacker);
					newUsage--;
					push(decIfStacker,newUsage);

					
				}
		} 
		else {
		
			System.out.println("elsecount is less than 1");
			pop(decIfStacker);
			pop(decElseStacker);
			newUsage = pop(decIfStacker);
			newUsage--;
			push(decIfStacker,newUsage);
		
		}

		System.out.println("Exit Decision");
	}

	/*
	 * enter loop starts counting amount of loops to go through
	 */
	@Override public void enterLoop(KnightCodeParser.LoopContext ctx) { 
		
		System.out.println("Enter loop");
		
		if(loopLabCount > 9){
		
			System.out.println("While Overflow");
			
			exit = true;
			return;

		} else {
			loopLabCount++;	
			loopCount++;		
		}
		loopNestStack = loopLabCount + loopNestStack;
		System.out.println("Current stack = " + loopNestStack);	

		int syntaxTest = ctx.getChildCount();
		if(syntaxTest < 7){
			System.out.println("Syntax Error");
			
			
			exit = true;
			return;
		}

		if(!ctx.getChild(0).getText().equalsIgnoreCase("WHILE")){
		
			System.out.println("Syntax Error");
						
			exit = true;
			return;
		
		}
		
		if(!ctx.getChild(4).getText().equalsIgnoreCase("DO")){
		
			System.out.println("Syntax Error");

			
			exit = true;
			return;
		
		}
		
		if(!ctx.getChild(syntaxTest-1).getText().equalsIgnoreCase("ENDWHILE")){
		
				System.out.println("Syntax Error");


			
				exit = true;
				return;	
		}
		
		
		Label temp; 
		Label tempEnd;
		
			
			int currentUsage = Character.getNumericValue(loopNestStack.charAt(0));
			switch(currentUsage){
				case 1: {
					temp = startOfloop0;
					tempEnd = endOfloop0;
					break;
				}
				case 2: {
					temp = startOfloop1;
					tempEnd = endOfloop1;
					break;
				}
				case 3: {
					temp = startOfloop2;
					tempEnd = endOfloop2;
					break;
				}
				case 4: {
					temp = startOfloop3;
					tempEnd = endOfloop3;
					break;
				}
				case 5: {
					temp = startOfloop4;
					tempEnd = endOfloop4;
					break;
				}
				case 6: {
					temp = startOfloop5;
					tempEnd = endOfloop5;
					break;
				}
				case 7: { 
					temp = startOfloop6;
					tempEnd = endOfloop6;
					break;
				
				}
				case 8: {
					temp = startOfloop7;
					tempEnd = endOfloop7;
					break;
				
				}
				case 9: { 
					temp = startOfloop8;
					tempEnd = endOfloop8;
					break;
				
				}
				case 10: { 
					temp = startOfloop9;
					tempEnd = endOfloop9;
					break;
				
				}
				default: {
					System.out.println("Comparison Error");
	
					exit = true;
					return;
				}		
			}
		

		mainVisitor.visitLabel(temp);

		loopOp1 = ctx.getChild(1).getText();
		if(symbolTable.containsKey(loopOp1)){
			var1 = symbolTable.get(loopOp1);
			
			if(!var1.valueSet||var1.variableType.equalsIgnoreCase(STR)){
				
				System.out.println("Comparison Error");

				
				exit = true;
				return;	
			}
			
			operator1 = var1.memLoc;
			mainVisitor.visitIntInsn(Opcodes.ILOAD, operator1);
		} 
		else {
			try{
            			
            			loopOperator1 = Integer.valueOf(loopOp1);
            			mainVisitor.visitIntInsn(Opcodes.SIPUSH, loopOperator1);
            			
            			
       		     } catch(NumberFormatException e){
				System.out.println("Comparison Error");

				
				exit = true;
				return;
        			
        		     }
		
		}
		
		loopOp2 = ctx.getChild(3).getText();
		if(symbolTable.containsKey(loopOp2)){
			var2 = symbolTable.get(loopOp2);

			if(!var2.valueSet || var2.variableType.equalsIgnoreCase(STR)){
				

				System.out.println("Comparison Error");

				
				exit = true;
				return;	
			}
			
			operator2 = var2.memLoc;
			mainVisitor.visitIntInsn(Opcodes.ILOAD, operator2);
		} else {
		
			  try{
            			
            			loopOperator2 = Integer.valueOf(loopOp2);
            			mainVisitor.visitIntInsn(Opcodes.SIPUSH, loopOperator2);	
       		     } catch(NumberFormatException e){
				System.out.println("Comparison Error");

				
				exit = true;
				return;
        			
        		     }	
		}		
			
		loopCompSymbol = ctx.getChild(2).getChild(0).getText();
	
		if(loopCompSymbol.equals(">")){
				mainVisitor.visitJumpInsn(Opcodes.IF_ICMPLE, tempEnd);
				
			} 
		else if(decCompSymbol.equals("<")){
				
				mainVisitor.visitJumpInsn(Opcodes.IF_ICMPGE,tempEnd);
			
			} 
		else if(decCompSymbol.equals("<>")){
			
				mainVisitor.visitJumpInsn(Opcodes.IF_ICMPEQ, tempEnd);
				
			} 
		else if(decCompSymbol.equals("=")){
			
				mainVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, tempEnd);
			
			}

		
			syntaxTest = ctx.getChildCount() - 6;		
		
	}

	/*
	 * End loop count and jump to appropriate labels for looping to perform
	 */
	@Override public void exitLoop(KnightCodeParser.LoopContext ctx) { 
		
		Label temp;
		Label temper;
		int currentUsage = Character.getNumericValue(loopNestStack.charAt(0));
		
		switch(currentUsage) {
	
			case 1: {
				temp = endOfloop0;
				temper = startOfloop0;
				break;
			}
			case 2: {
				temp = endOfloop1;
				temper = startOfloop1;
				break;
			}
			case 3: {
				temp = endOfloop2;
				temper = startOfloop2;
				break;
			}
			case 4: {
				temp = endOfloop3;
				temper = startOfloop3;
				break;
			}
			case 5: {
				temp = endOfloop4;
				temper = startOfloop4;
				break;
			}
			case 6: {
				temp = endOfloop5;
				temper = startOfloop5;
				break;
			}
			case 7: { 
				temp = endOfloop6;
				temper = startOfloop6;
				break;
			
			}
			case 8: {
				temp = endOfloop7;
				temper = startOfloop7;
				break;
			
			}
			case 9: {
				temp = endOfloop8;
				temper = startOfloop8;
				break;
			
			}
			case 10: { 
				temp = endOfloop9;
				temper = startOfloop9;
				break;
			
			}
			default: {

				
				exit = true;
				return;
			}	
				
		}
		
	
	mainVisitor.visitJumpInsn(Opcodes.GOTO,temper);
	mainVisitor.visitLabel(temp);
	  
	if(loopNestStack.length() != 0)
		loopNestStack = loopNestStack.substring(1);
	
	System.out.println("Current stack = " + loopNestStack);	
		
	int tempBoolElse = peek(decElseStacker);
	
	if(tempBoolElse > 0){
	
		int newUsage = peek(decIfStacker);
	
		System.out.println("current newUsage = " + newUsage );
		
			if(newUsage == 1){	

				Label tempEnd;
		 		currentUsage = Character.getNumericValue(decNestStack.charAt(0));
				switch(currentUsage){
					case 1: {
						tempEnd = endDecLab0;
						temper = startOfElse0;						
						break;
					}
					case 2: {
						tempEnd = endDecLab1;
						temper = startOfElse1;
						break;
					}
					case 3: {
						tempEnd = endDecLab2;
						temper = startOfElse2;						
						break;
					}
					case 4: {
						tempEnd = endDecLab3;
						temper = startOfElse3;
						break;
					}
					case 5: {
						tempEnd = endDecLab4;
						temper = startOfElse4;
						break;
					}
					case 6: {						
						tempEnd = endDecLab5;
						temper = startOfElse5;
						break;
					}
					case 7: { 						
						tempEnd = endDecLab6;
						temper = startOfElse6;
						break;
				
					}
					case 8: {
						tempEnd = endDecLab7;
						temper = startOfElse7;
						break;
				
					}
					case 9: { 
						tempEnd = endDecLab8;
						temper = startOfElse8;
						break;
					
					}
					case 10: { 
						tempEnd = endDecLab9;
						temper = startOfElse9;
						break;
				
					}
					default: {
					
						
						exit = true;
						return;
					}	
						
				}

	
				mainVisitor.visitJumpInsn(Opcodes.GOTO, tempEnd);
				mainVisitor.visitLabel(temper);
				
				
				pop(decElseStacker);
				pop(decIfStacker);
		
			
				elseVisitor--;
			} else if(newUsage > 1){

				
				
				newUsage = pop(decIfStacker);
				newUsage--;
				push(decIfStacker,newUsage);

				
			}
		
		
	}

		
	System.out.println("Exit loop");
	}

}






