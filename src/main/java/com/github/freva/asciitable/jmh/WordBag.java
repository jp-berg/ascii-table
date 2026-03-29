package com.github.freva.asciitable.jmh;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.nio.CharBuffer;
import java.util.Random;

@NullMarked
public final class WordBag {

    public final int minWordLen, maxWordLen;

    private final @Nullable String[] words;

    private final Random random = new Random();

    public WordBag(int minWordLen, int maxWordLen, int noWords) {
        this.minWordLen = minWordLen;
        this.maxWordLen = maxWordLen;
        this.words = new String[noWords];
    }

    public String next(){
        int i = random.nextInt(words.length);
        String word = words[i];
        if(word != null) return word;
        word = randomLowercase();
        words[i] = word;
        return word;
    }

    private int randomInt(int min, int max){
        return random.nextInt(max - min) + min;
    }

    private String randomLowercase(){
        char[] chars = new char[randomInt(minWordLen, maxWordLen)];
        for(int i = 0; i < chars.length; i++){
            chars[i] = (char) (Math.random()*26 + 'a');
        }
        return new String(chars);
    }
}
