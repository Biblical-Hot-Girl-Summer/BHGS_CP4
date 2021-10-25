package compiler;

import org.antlr.v4.runtime.*;
import java.util.List;
import java.util.Collections;

public class VerboseListener extends BaseErrorListener{

	public void syntaxError(Recognizer<?, ?> recognizer,
				Object offendingSymbol,
				int line, int charPositionInLine,
				String msg,
				RecognitionException e)
	{

		List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
		Collections.reverse(stack);
		//System.err.println("rule stack: " + stack);
		//System.err.println("line "+line+":"+charPositionInLine+" at " + offendingSymbol+": "+msg);
		System.err.println("Exterminate!!!!");
		underlineError(recognizer, (Token)offendingSymbol, line, charPositionInLine);
	}//end syntaxError

	protected void underlineError(Recognizer recognizer, 
			Token offendingSymbol, int line, int charPositionInLine)
	{

		CommonTokenStream tokens = (CommonTokenStream)recognizer.getInputStream();
		String input = tokens.getTokenSource().getInputStream().toString();
		String[] lines = input.split("\n");
		String errorLine = lines[line-1];
		System.err.println(errorLine);
		for(int i=0; i<charPositionInLine; i++) System.err.print(" ");
		int start = offendingSymbol.getStartIndex();
		int stop = offendingSymbol.getStopIndex();
		if(start>=0 && stop>=0){
			for(int i = start; i<stop; i++) System.err.print("^");
		}
		System.err.println();

	}//end underlineError

}//end class
