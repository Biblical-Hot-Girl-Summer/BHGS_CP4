grammar KnightCode ;
/** DO NOT CHANGE THIS FILE! */

@header{package lexparse; }

//PARSER Rules
file : 'PROGRAM' ID declare body ;
declare : 'DECLARE' variable+ ;
variable : vartype identifier ;
identifier: ID ;
vartype : 'INTEGER' |  'STRING' ;
body : 'BEGIN' stat+ 'END' ;
stat : setvar
	| expr
	| print
	| read
	| decision
	| loop
    ;
setvar : 'SET' ID ASSIGN (expr | STRING) ;

expr : '(' expr ')'		# Parenthesis		
	 | expr MUL expr	# Multiplication
	 | expr DIV expr	# Division
	 | expr ADD expr    # Addition
	 | expr SUB expr    # Subtraction
	 | expr comp expr   # Comparison
	 | NUMBER           # Number
	 | ID   		  	# Id														
     ;	

comp : GT 						
	 | LT 						
	 | EQ 						
	 | NEQ						 
	 ;
print : 'PRINT' (STRING | ID) ;
read : 'READ' ID ;
decision : 'IF' (NUMBER | ID) comp (NUMBER | ID) 'THEN' stat+ ('ELSE' stat+)* 'ENDIF' ;
loop : 'WHILE' (NUMBER | ID) comp (NUMBER | ID) 'DO' stat+ 'ENDWHILE' ;

//LEXER RULES
ID : LETTER (LETTER | [0-9])* ;
ESC : ('\\"' | '\\\\') ;
STRING : '"' (ESC|.)*? '"' ;
ASSIGN : ':=' ;
LETTER : [a-zA-Z] ;
NUMBER : [0-9]+ ;
MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
GT : '>' ;
LT : '<' ;
EQ : '=' ;
NEQ : '<>' ;
LINE_COMMENT : '#' .*? '\r'? '\n' -> skip ;
WS : [ \t\r\n]+ -> skip ;