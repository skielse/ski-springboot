package com.ski.bootstart.algorithm;

/**
 * @author ski
 * @date 2024/5/8
 */
public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        int index = s.length() - 1;
        while (s.charAt(index) == ' ') {
            index--;
        }
        int wordLength = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            wordLength++;
            index--;
        }
        return wordLength;
    }

    public static void main(String[] args) {

    }
}
