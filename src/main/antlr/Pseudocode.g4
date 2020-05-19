grammar Pseudocode;

//////////////////////////////////////////////////// PARSER RULES ////////////////////////////////////////////////////
start : statement+ EOF; // root rule
statement : if_statement | variable_declaration | print | for_statement | while_statement ;
for_statement : FOR '(' for_conditions ')' '{' statement* '}';
if_statement :  IF '(' boolean_expression ')' '{' statement* '}' (ELSE '{' statement* '}')?;
while_statement : WHILE '(' boolean_expression  ')' '{' statement* '}';
print : PRINT LEFT_ROUND_BRACKET STRING_LITERAL RIGHT_ROUND_BRACKET  ;
type : BOOL | STRING | CHAR | INT | VOID | FLOAT;
variable_declaration
    : type IDENTIFIER ASSIGN INTEGER_LITERAL | FLOATING_POINT_LITERAL | STRING_LITERAL | BOOL_LITERAL
    | type IDENTIFIER
    ;
for_conditions
    : IDENTIFIER '=' INTEGER_LITERAL ':' INTEGER_LITERAL
    | IDENTIFIER '=' INTEGER_LITERAL ':' expression
    ;
boolean_expression
    : expression GT expression      // ex. a+b > c+d
    | expression LT expression
    | expression LE expression
    | expression GE expression
    | expression EQUALS expression
    | expression NOT_EQUALS expression
    | expression GT primary_expression      // ex. a+b > 0
    | expression LT primary_expression
    | expression LE primary_expression
    | expression GE primary_expression
    | expression EQUALS primary_expression
    | expression NOT_EQUALS primary_expression
    | primary_expression GT primary_expression      // ex. a < b
    | primary_expression LT primary_expression
    | primary_expression LE primary_expression
    | primary_expression GE primary_expression
    | primary_expression EQUALS primary_expression
    | primary_expression NOT_EQUALS primary_expression
    | expression AND expression
    | expression OR expression
    | NOT expression
    | primary_expression AND primary_expression
    | primary_expression OR primary_expression
    | NOT primary_expression
    | expression
    ;

expression
    : primary_expression math_operator primary_expression (math_operator primary_expression)*;

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
WHILE : 'while' ;
IF : 'if' ;
INT : 'int' ;
FLOAT : 'float' ;
RETURN : 'return' ;
STRING : 'string';
SWITCH : 'switch' ;
VOID : 'void' ;
LEFT_ROUND_BRACKET : '(' ;
RIGHT_ROUND_BRACKET : ')' ;
LEFT_BRACE : '{' ;
RIGHT_BRACE : '}' ;
LEFT_SQUARE_BRACKET : '[' ;
RIGHT_SQUARE_BRACKET : ']' ;
COMMA : ',' ;
COLON : ':' ;

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
INTEGER_LITERAL : MINUS? DECIMAL_NUMERAL ;
FLOATING_POINT_LITERAL : MINUS? DECIMAL_NUMERAL FLOATING_POINT_SEPARATOR DECIMAL_NUMERAL? ;
NUMBER_LITERAL :INTEGER_LITERAL | FLOATING_POINT_LITERAL;
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
