package com.github.freva.asciitable.jmh;

import org.apache.commons.lang3.RandomStringUtils;
import org.jspecify.annotations.NullMarked;

import java.nio.CharBuffer;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NullMarked
public final class TableDataFactory {

    private int minWordLen = 3, maxWordLen = 15,
            minDataWords = 1, maxDataWords = 25;

    private final Random random = new Random();

    public TableDataFactory(){}

    private String[] generateRandomHeader(final int colNum){
        String[] header = new String[colNum];
        for(int i = 0; i < colNum; i++){
            header[i] = String.valueOf(randomLowercase(minWordLen, maxWordLen));
        }
        return header;
    }

    private int randomInt(int min, int max){
        return random.nextInt(max - min) + min;
    }

    public CharBuffer randomLowercase(int minLen, int maxLen){
        char[] chars = new char[randomInt(minLen, maxLen)];
        for(int i = 0; i < chars.length; i++){
            chars[i] = (char) (Math.random()*26 + 'a');
        }
        return CharBuffer.wrap(chars);
    }

    public String generateRandomDatum(){
        int limit = randomInt(minDataWords, maxDataWords);
        return Stream.generate(() -> randomLowercase(minWordLen, maxWordLen))
                .limit(limit)
                .collect(Collectors.joining(" "));

    }

    private String[][] generateRandomData(final int colNum, final int rowNum){
        String[][] data = new String[rowNum][colNum];
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                data[i][j] = generateRandomDatum();
            }
        }
        return data;
    }

    public TableData generateRandomTableData(final int colNum, final int rowNum){
        String[] header = generateRandomHeader(colNum);
        String[][] data = generateRandomData(colNum, rowNum);
        return new TableData(header, data);
    }

    public TableDataFactory setMaxWordLen(int maxWordLen) {
        this.maxWordLen = maxWordLen;
        return this;
    }

    public TableDataFactory setMinWordLen(int minWordLen) {
        this.minWordLen = minWordLen;
        return this;
    }

    public TableDataFactory setMinDataWords(int minDataWords) {
        this.minDataWords = minDataWords;
        return this;
    }

    public TableDataFactory setMaxDataWords(int maxDataWords) {
        this.maxDataWords = maxDataWords;
        return this;
    }
}
