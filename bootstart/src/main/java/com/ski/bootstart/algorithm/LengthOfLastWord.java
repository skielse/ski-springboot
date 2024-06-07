package com.ski.bootstart.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ski
 * @date 2024/5/8
 */
@Slf4j
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
        String str = "Hello World World test";
        LengthOfLastWord lengthOfLastWord = new LengthOfLastWord();
        log.info("lengthOfLastWord={}", lengthOfLastWord.lengthOfLastWord(str));
    }
}
