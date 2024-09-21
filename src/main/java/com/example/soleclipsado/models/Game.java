package com.example.soleclipsado.models;

/**
 * This model class contains the data of the game.
 * @author Felipe Garcia
 * @version 1.0
 */
public class Game {
    private String secretWord;
    private int errors = 0;
    private int validLetters = 0;
    private int aidCount = 0;

    /**
     * This method returns the number of hints that have been requested.
     * @return aid count
     */
    public int getAidCount() {
        return aidCount;
    }

    /**
     * This method increases the number of hints.
     */
    public void increaseHelps() {
        aidCount++;
    }

    /**
     * This method gets the character at the specified index.
     * @param index Index of the letter that will be returned.
     * @return the character of the specified index.
     */
    public char getCharacter(int index) {
        return secretWord.charAt(index);
    }

    /**
     * This method checks that the word has no whitespace, has the required length, and consists of only letters.
     * @param secretWord Secret word.
     * @return An error if there is one.
     */
    public String setSecretWord(String secretWord) {
        if (secretWord.contains(" ")) {
            return "¡Ops! Debe ser una sola palabra.\nNo puede contener espacios en blanco.";
        }

        if (!(secretWord.length() >= 6 && secretWord.length() <= 12)) {
            System.out.println(secretWord.length());
            return "¡Ops! La palabra debe tener entre 6 y 12 letras";
        }

        if (!secretWord.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
            return "¡Ops! La palabra solo puede tener letras del alfabeto";
        }

        this.secretWord = secretWord;

        return "";
    }

    /**
     * This method returns the length of the secret word.
     * @return length of the secret word
     */
    public int getWordSize() {
        return secretWord.length();
    }

    /**
     * This method checks if the player has won.
     * @return A boolean indicating whether the player won or not.
     */
    public boolean verifyWin() {
        return validLetters == secretWord.length();
    }

    /**
     * This method checks if the given character is equal to the character in the word at the specified index.
     * @param letter Letter that will be compared.
     * @param index Position of the letter.
     * @return A boolean indicating whether the letter is correct or incorrect.
     */
    public boolean verifyLetter(char letter, int index) {
        if (secretWord.charAt(index) == letter) {
            validLetters++;
            return true;
        }

        else {
            errors++;
            return false;
        }
    }

    /**
     * This method returns the number of mistakes.
     * @return the number of mistakes.
     */
    public int getErrors() {
        return errors;
    }
}
