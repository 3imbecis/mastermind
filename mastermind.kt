

import kotlin.io.readln
import SIZE_POSITIONS
import COLORS

const val MAX_TRIES = 10;
const val SIZE_POSITIONS = 4;
const val SIZE_COLORS = 6;
const val FIRST_COLOR = 'A';
val COLORS = FIRST_COLOR ..< FIRST_COLOR + SIZE_COLORS;

fun main(){

    val secret: String = generateSecret();

    println("Descubra o código em $MAX_TRIES tentativas.");
    println("$SIZE_POSITIONS posições e $SIZE_COLORS cores $COLORS sem repetições");

    for(numTries in 1..MAX_TRIES){

        val guess = readGuess(numTries);

        if(guess == secret){
            println("Parabéns!\nAcertou à ${numTries}ª tentativa.");
            return;
        }

        val corrects = getCorrects(guess, secret);
        val swapped = getSwapped(guess, secret);

        printTry(numTries, guess, corrects, swapped);

    }

    println("Não acertou em $MAX_TRIES tentativas. A solução era $secret.");

}

fun generateSecret(): String {

    var secret: String = "";

    while(secret.length < SIZE_POSITIONS){

        val c = COLORS.random();

        if(c in secret) continue;

        secret += c;

    }

    return secret;

}

fun readGuess(numTries: Int): String {
    
    print("${numTries}ª tentativa: ");
    var guess = readln();

    if(guess.length != SIZE_POSITIONS){
        println("A sua tentativa deve ter $SIZE_POSITIONS posições!");
        return readGuess(numTries);
    }
    
    for(c in guess){
        if(!(c in COLORS)){
            println("$c não é uma cor válida!");
            return readGuess(numTries);
        }
    }

    return guess;
}

fun getCorrects(guess: String, secret: String): Int {
    
    var corrects = 0;

    for(i in 0..<SIZE_POSITIONS){
        if(guess[i] == secret[i])
            corrects++;
    }

    return corrects;

}

fun getSwapped(guess: String, secret: String): Int {
    
    var swapped = 0;

    for(i in 0..<SIZE_POSITIONS){
        if(guess[i] in secret && guess[i] != secret[i])
            swapped++;
    }

    return swapped;

}

fun printTry(numTries: Int, guess: String, corrects: Int, swapped: Int){
    println("${numTries}ª : $guess -> ${corrects}C + ${swapped}T");
}