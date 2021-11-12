// Generated from KnightCode.g4 by ANTLR 4.9.2
package lexparse; 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KnightCodeParser}.
 */
public interface KnightCodeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KnightCodeParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(KnightCodeParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnightCodeParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(KnightCodeParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnightCodeParser#declare}.
	 * @param ctx the parse tree
	 */
	void enterDeclare(KnightCodeParser.DeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnightCodeParser#declare}.
	 * @param ctx the parse tree
	 */
	void exitDeclare(KnightCodeParser.DeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnightCodeParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(KnightCodeParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnightCodeParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(KnightCodeParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnightCodeParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(KnightCodeParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnightCodeParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(KnightCodeParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnightCodeParser#vartype}.
	 * @param ctx the parse tree
	 */
	void enterVartype(KnightCodeParser.VartypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnightCodeParser#vartype}.
	 * @param ctx the parse tree
	 */
	void exitVartype(KnightCodeParser.VartypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnightCodeParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(KnightCodeParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnightCodeParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(KnightCodeParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnightCodeParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(KnightCodeParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnightCodeParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(KnightCodeParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnightCodeParser#setvar}.
	 * @param ctx the parse tree
	 */
	void enterSetvar(KnightCodeParser.SetvarContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnightCodeParser#setvar}.
	 * @param ctx the parse tree
	 */
	void exitSetvar(KnightCodeParser.SetvarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parenthesis}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenthesis(KnightCodeParser.ParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parenthesis}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenthesis(KnightCodeParser.ParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Multiplication}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplication(KnightCodeParser.MultiplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Multiplication}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplication(KnightCodeParser.MultiplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Addition}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddition(KnightCodeParser.AdditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Addition}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddition(KnightCodeParser.AdditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Subtraction}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSubtraction(KnightCodeParser.SubtractionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Subtraction}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSubtraction(KnightCodeParser.SubtractionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Number}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumber(KnightCodeParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumber(KnightCodeParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Comparison}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterComparison(KnightCodeParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Comparison}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitComparison(KnightCodeParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Division}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDivision(KnightCodeParser.DivisionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Division}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDivision(KnightCodeParser.DivisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Id}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(KnightCodeParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(KnightCodeParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnightCodeParser#comp}.
	 * @param ctx the parse tree
	 */
	void enterComp(KnightCodeParser.CompContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnightCodeParser#comp}.
	 * @param ctx the parse tree
	 */
	void exitComp(KnightCodeParser.CompContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnightCodeParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(KnightCodeParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnightCodeParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(KnightCodeParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnightCodeParser#read}.
	 * @param ctx the parse tree
	 */
	void enterRead(KnightCodeParser.ReadContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnightCodeParser#read}.
	 * @param ctx the parse tree
	 */
	void exitRead(KnightCodeParser.ReadContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnightCodeParser#decision}.
	 * @param ctx the parse tree
	 */
	void enterDecision(KnightCodeParser.DecisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnightCodeParser#decision}.
	 * @param ctx the parse tree
	 */
	void exitDecision(KnightCodeParser.DecisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnightCodeParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(KnightCodeParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnightCodeParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(KnightCodeParser.LoopContext ctx);
}