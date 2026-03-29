package com.github.freva.asciitable.jmh;

import org.apache.commons.lang3.RandomStringUtils;
import org.jspecify.annotations.NullMarked;

import java.nio.CharBuffer;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NullMarked
public final class TableDataFactory {

    private final int minWordLen = 3, maxWordLen = 15,
            minDataWords = 1, maxDataWords = 25;

    private final Random random = new Random();

    private final WordBag bag = new WordBag(minWordLen, maxWordLen, 100000);

    private final StringBuilder sb = new StringBuilder();

    public TableDataFactory(){}

    private String[] generateRandomHeader(final int colNum){
        String[] header = new String[colNum];
        for(int i = 0; i < colNum; i++){
            header[i] = bag.next();
        }
        return header;
    }

    private int randomInt(int min, int max){
        return random.nextInt(max - min) + min;
    }

    public String generateRandomDatum(){
        int limit = randomInt(minDataWords, maxDataWords);
        sb.setLength(0);
        sb.ensureCapacity(limit*maxWordLen);
        for(int i = 0; i < limit; i++){sb.append(bag.next()).append(' ');}
        return sb.delete(sb.length() - 1, sb.length()).toString();

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
}
