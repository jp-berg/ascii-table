package com.github.freva.asciitable.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@State(Scope.Thread)
public class ExecutionPlan {

    private static final int noStoredElements = 10000;

    private int currentPos = 0;

    @Param({"2", "4", "8"})
    public int numCols;

    @Param({"1", "10", "100", "1000"})
    public int numRows;


    private final TableDataFactory f = new TableDataFactory();

    private List<TableData> storedElements;

    TableData next(){
        TableData d = storedElements.get(currentPos);
        currentPos++;
        if(currentPos == noStoredElements) currentPos = 0;
        return d;
    }


    public ExecutionPlan(){}

    @Setup
    public void setUp(){
        storedElements = Stream.generate(() -> f.generateRandomTableData(numCols, numRows))
                .limit(noStoredElements)
                .collect(Collectors.toList());
    }

}
