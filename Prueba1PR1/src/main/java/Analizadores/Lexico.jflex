/* 1. Package e Importaciones */
package Analizadores;
import java_cup.runtime.Symbol;

import Errores.Excepcion;
import java.util.ArrayList;

%%
/* 2. Configuarciones para el analisis (Opciones y Declaraciones) j */
%{
    //Codigo de usuario en sintaxis java
    //Agregar clases, variables, arreglos, objetos etc...

    public ArrayList<Excepcion> Errores = new ArrayList();
%}

//Directivas
%class Lexico
%public 
%cup
%char
%column
%full
%line
%unicode


//Inicializar el contador de columna y fila con 1
%init{ 
    yyline = 1;
    yychar = 1;
%init}


// Expresiones Regulares
BLANCOS=[ \r\t]+
D=[0-9]+
DD=[0-9]+("."[  |0-9]+)?

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

comentariosimple = "//" {InputCharacter}* {LineTerminator}?
comentariovariaslineas = (\<\!(\s*|.*?)*\!\>)
SEPARADOR = "%%"?

CaracteresAscii = [\!\"\#\$\%\&\'\(\)\*\+\,\-\.\/\:\;\<\=\>\?\@\[\\\]\^\_\`\{\|\}]
CaracteresEspeciales = (\\(n)) | (\\(')) | (\\(')('))

NotacionA = [a-zA-Z]\~[a-zA-Z] | [0-9]\~[0-9] | ((([a-zA-Z0-9]|{CaracteresAscii}),)+([a-zA-Z0-9]|{CaracteresAscii})) | (\!\~\&) | {CaracteresAscii}  |  {CaracteresEspeciales}
VariableA = [a-zA-Z_]+[a-zA-Z0-9_]*

//ExpresionPolacaA = [\.\+\|\?\*]+(\s|\{|\}|\w|\d|\"|\.|\+|\||\?|\*|(\;\"))+
ExpresionPolacaA = [\.\+\|\?\*]+(\s|\{|\}|\w|\d|\"|\.|\+|\||\?|\*|(({CaracteresAscii}|{CaracteresEspeciales}|\w|\d)+\"))+

EjemploExpresion = (\"(\'|\"|.*?)*\")

%%
/* 3. Reglas Semanticas*/
"CONJ" {  System.out.println("Reconocio PR: "+yytext()); return new Symbol(sym.PR_CONJ,yyline,yychar,yytext());}

";" { System.out.println("Reconocio "+yytext()+" punto y coma"); return new Symbol(sym.PTCOMA,yyline,yychar, yytext());} 
//"(" { System.out.println("Reconocio "+yytext()+" parentesis abre"); return new Symbol(sym.PARIZQ,yyline,yychar, yytext());} 
//")" { System.out.println("Reconocio "+yytext()+" parentesis cierra"); return new Symbol(sym.PARDER,yyline,yychar, yytext());} 
//"[" { System.out.println("Reconocio "+yytext()+" corchete abre"); return new Symbol(sym.CORIZQ,yyline,yychar, yytext());} 
//"]" { System.out.println("Reconocio "+yytext()+" corchete cierra"); return new Symbol(sym.CORDER,yyline,yychar, yytext());} 
"}" { System.out.println("Reconocio "+yytext()+" llave cierra"); return new Symbol(sym.LLAVDER,yyline,yychar, yytext());} 
"{" { System.out.println("Reconocio "+yytext()+" llave abre"); return new Symbol(sym.LLAVIZQ,yyline,yychar, yytext());} 

"->" { System.out.println("Reconocio "+yytext()+" flecha"); return new Symbol(sym.FLECH,yyline,yychar, yytext());} 
":" { System.out.println("Reconocio "+yytext()+" dos puntos"); return new Symbol(sym.DOSPTS,yyline,yychar, yytext());} 



\n {yychar=1;}

{VariableA} {return new Symbol(sym.VARIABLE,yyline,yychar, yytext());} 
{NotacionA} {return new Symbol(sym.NOTACION,yyline,yychar, yytext());} 
{ExpresionPolacaA} {return new Symbol(sym.EXPOLACA,yyline,yychar, yytext());}
{EjemploExpresion} {return new Symbol(sym.EJEXPRE,yyline,yychar, yytext());}



{BLANCOS} {}
{SEPARADOR} {}
{comentariosimple} {System.out.println("Comentario: "+yytext()); }
{comentariovariaslineas} {System.out.println("Comentario Varias Lineas: "+yytext()); }
//{D} {return new Symbol(sym.ENTERO,yyline,yychar, yytext());} 
//{DD} {return new Symbol(sym.DECIMAL,yyline,yychar, yytext());} 




. {
    //Aqui se debe guardar los valores (yytext(), yyline, yychar ) para posteriormente generar el reporte de errores Léxicos.
    System.out.println("Este es un error lexico: "+yytext()+ ", en la linea: "+yyline+", en la columna: "+yychar);
    Errores.add(new Excepcion("Léxico", "Caracter no válido detectado: " + yytext(), yyline + "", yychar + ""));
}