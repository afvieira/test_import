/*
 * Linguagem de Programação Imperativa tipo C
 * ano letivo 13/14
 */

grammar eg13_lingC;


programa : declaracoes funcoes
        ;

declaracoes : declconsts decltipos declvariaveis
        ;

declconsts : (declconst ';')* 
        ;

declconst : 'define' idC exp
          ;

decltipos : (decltipo ';')* 
          ;
    
decltipo : 'typedef' idT tipoAvancado
         | 'typedef' 'struct' idT '{' declvariaveis '}' tipoAvancado
         ;
           
funcoes : funcao+
        ;

funcao : cabecfuncao corpofuncao
        ;

cabecfuncao : idT idF '(' parametros? ')'
            ;

parametros : parametro+
           ;

parametro : idT idP 
          ;

corpofuncao : '{' declvariaveis instrucoes '}' 
              ;
             
declvariaveis : (declvariavel ';')* 
            ;

declvariavel : idT listadcls
            ;

listadcls : dcl (',' dcl)* 
          | '(' idV (',' idV)* ')' '=' exp 
          ;

dcl  : idV ('=' exp)?
     | idV '[' conste ']'
     ;

tipoAvancado : ('*')? idT ('[' conste ']')? 
        ;           

/* Instruções --------------------------------------------------------------- */

instrucoes : (instrucao ';')+ ;

instrucao  : atrib
           | invocFunc 
           | controlo
           | leitura
           | escrita
           ;

atrib      : idV '=' exp;

invocFunc  : idF '(' exps? ')';

exps       : exp ( ',' exp)*;

exp        : termo
           | exp OPAD termo?
           ;

termo      : fator
           | termo OPMUL fator;

fator      : conste
           | invocFunc
           | idV
           | '(' exp ')'
           | idV '[' conste ']'
           ;

leitura    : 'read' '(' vars  ')' ;

vars       : idV (',' idV)* ;

escrita    : 'write' '(' exps ')' ;

controlo   : cond
           | ciclo
           | paragem
           ;

cond       : 'if' '(' exp ')' '{' instrucoes '}' (('elsif' '(' exp ')' '{' instrucoes '}')* 'else' '{' instrucoes '}')? ;

ciclo      : 'while' '(' exp ')' '{' instrucoes '}' 
           | 'for' '(' atrib?  ';' exp ';' exp? ')' '{' instrucoes '}'
           | 'do' '{' instrucoes '}' 'while' '(' exp ')'
           ;

paragem    : 'break'
           | 'return' exp?
           ;

/* Identificadores ---------------------------------------------------------- */

idF   : ID ; // ID Função
idT   : ID ; // ID Tipo
idP   : ID ; // ID ParÃ¢metro
idV   : ID ; // ID Variável
idC   : ID ; // ID Constante

conste : idC 
       | INT 
       | STRING 
       ;

/*--------------- Lexer ------------------------------------------------------*/

ID    :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'-')* ;

OPAD  : ( '+' | '-' | '||' );

OPMUL : ( '*' | '/' | '&&' );

INT : [0-9]+ ;

WS  :   [ \t\r\n]  -> skip
    ;

STRING : '"' ( ESC_SEQ | ~('"') )* '"' ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;
 
fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') 
    ;
