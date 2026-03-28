package com.github.freva.asciitable.jmh;

import org.apache.commons.lang3.RandomStringUtils;
import org.jspecify.annotations.NullMarked;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NullMarked
public final class TableDataFactory {

    private int minHeaderLen = 5, maxHeaderLen = 10,
            minDataWordLen = 3, maxDataWordLen = 15,
            minDataWords = 1, maxDataWords = 25;

    private final Random random = new Random();

    public TableDataFactory(){}

    private String[] generateRandomHeader(final int colNum){
        String[] header = new String[colNum];
        for(int i = 0; i < colNum; i++){
            header[i] = RandomStringUtils.insecure().nextAlphabetic(minHeaderLen, maxHeaderLen);
        }
        return header;
    }

    private String generateRandomDatum(){
        int limit = random.nextInt(maxDataWords - minDataWords) + minDataWords;
        return Stream.generate(() -> RandomStringUtils.insecure().nextAlphabetic(minDataWordLen, maxDataWordLen))
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

    public TableDataFactory setMinHeaderLen(int minHeaderLen) {
        this.minHeaderLen = minHeaderLen;
        return this;
    }

    public TableDataFactory setMaxHeaderLen(int maxHeaderLen) {
        this.maxHeaderLen = maxHeaderLen;
        return this;
    }

    public TableDataFactory setMaxDataWordLen(int maxDataWordLen) {
        this.maxDataWordLen = maxDataWordLen;
        return this;
    }

    public TableDataFactory setMinDataWordLen(int minDataWordLen) {
        this.minDataWordLen = minDataWordLen;
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
