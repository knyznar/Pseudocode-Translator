grammar Pseudocode;

//////////////////////////////////////////////////// PARSER RULES ////////////////////////////////////////////////////
start : statement+ EOF; // root rule
statement : if_statement | variable_declaration | print; // | for_statement | while_statement | func_def;
//for_statement : ;  //TODO
if_statement :  IF LEFT_ROUND_BRACKET boolean_expression RIGHT_ROUND_BRACKET trueStatement=statement (ELSE falseStatement=statement)?;
//while_statement : ; //TODO
//func_def : ; //TODO
print : PRINT LEFT_ROUND_BRACKET STRING_LITERAL RIGHT_ROUND_BRACKET  ;
type : BOOL | STRING | CHAR | INT | VOID;
variable_declaration
    : type IDENTIFIER ASSIGN NUMBER_LITERAL | STRING_LITERAL | BOOL_LITERAL
    | type IDENTIFIER
    ;
boolean_expression
    : expression GT expression
    | expression LT expression
    | expression LE expression
    | expression GE expression
    | expression EQUALS expression
    | expression NOT_EQUALS expression
    | expression AND expression
    | expression OR expression
    | NOT expression
    | expression
    ;

expression
    : primary_expression math_operator primary_expression;

primary_expression : IDENTIFIER | NUMBER_LITERAL;

math_operator : ADD | SUB | MUL | DIV | MOD;


//////////////////////////////////////////////////// LEXER RULES (tokens) ////////////////////////////////////////////////////
PRINT : 'print' ;
ASSIGN : '=' ;
BOOL : 'bool' ;
BREAK : 'break' ;
CASE : 'case' ;
CHAR : 'char' ;
CONTINUE : 'continue' ;
ELSE : 'else' ;
FOR : 'for' ;
IF : 'if' ;
INT : 'int' ;
LONG : 'long' ;
RETURN : 'return' ;
STRING : 'string';
SWITCH : 'switch' ;
VOID : 'void' ;
WHILE : 'while' ;
LEFT_ROUND_BRACKET : '(' ;
RIGHT_ROUND_BRACKET : ')' ;
LEFT_BRACE : '{' ;
RIGHT_BRACE : '}' ;
LEFT_SQUARE_BRACKET : '[' ;
RIGHT_SQUARE_BRACKET : ']' ;
COMMA : ',' ;

// Boolean
GT : '>' ;
LT : '<' ;
NOT : 'not' ;
EQUALS : '==' ;
LE : '<=' ;
GE : '>=' ;
NOT_EQUALS : '!=' ;
AND : 'and' ;
OR : 'or' ;

// Math
ADD : '+' ;
SUB : '-' ;
MUL : '*' ;
DIV : '/' ;
MOD : '%' ;

// Identifiers
IDENTIFIER : LETTER LETTER_OR_DIGIT*  ;
fragment LETTER : [a-zA-Z_] ;
fragment LETTER_OR_DIGIT : [a-zA-Z0-9_] ;

//constants
fragment QUOTE : '"' ;
fragment MINUS : '-' ;
fragment FLOATING_POINT_SEPARATOR : '.';

// Literals
NUMBER_LITERAL :INTEGER_LITERAL | FLOATING_POINT_LITERAL;
INTEGER_LITERAL : MINUS? DECIMAL_NUMERAL ;
FLOATING_POINT_LITERAL : MINUS? DECIMAL_NUMERAL FLOATING_POINT_SEPARATOR DECIMAL_NUMERAL? ;
STRING_LITERAL : QUOTE ~('\n' | '\r' | '"')* QUOTE ;
BOOL_LITERAL :  'true' | 'false';

fragment DEC_DIGIT : [0-9] ;
fragment DECIMAL_NUMERAL
   : '0'
   | [1-9] DEC_DIGIT* ;

// Whitespace and comments
WS : [ \t\r\n\u000C]+ -> skip ;
NEWLINE : [\r\n]   -> skip ;
COMMENT : '#' .*?  -> channel(HIDDEN) ;
