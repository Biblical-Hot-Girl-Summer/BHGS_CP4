// Generated from KnightCode.g4 by ANTLR 4.9.2
package lexparse; 
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link KnightCodeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface KnightCodeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link KnightCodeParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(KnightCodeParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link KnightCodeParser#declare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclare(KnightCodeParser.DeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link KnightCodeParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(KnightCodeParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link KnightCodeParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(KnightCodeParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link KnightCodeParser#vartype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVartype(KnightCodeParser.VartypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link KnightCodeParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(KnightCodeParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link KnightCodeParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(KnightCodeParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link KnightCodeParser#setvar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetvar(KnightCodeParser.SetvarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parenthesis}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis(KnightCodeParser.ParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Multiplication}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplication(KnightCodeParser.MultiplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Addition}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddition(KnightCodeParser.AdditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Subtraction}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtraction(KnightCodeParser.SubtractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(KnightCodeParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Comparison}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(KnightCodeParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Division}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivision(KnightCodeParser.DivisionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link KnightCodeParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(KnightCodeParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link KnightCodeParser#comp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp(KnightCodeParser.CompContext ctx);
	/**
	 * Visit a parse tree produced by {@link KnightCodeParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(KnightCodeParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link KnightCodeParser#read}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead(KnightCodeParser.ReadContext ctx);
	/**
	 * Visit a parse tree produced by {@link KnightCodeParser#decision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecision(KnightCodeParser.DecisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link KnightCodeParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(KnightCodeParser.LoopContext ctx);
}