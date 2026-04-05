package com.github.freva.asciitable.jmh;

import com.github.freva.asciitable.AsciiTableBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.jspecify.annotations.NullMarked;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NullMarked
public final class TableData {

    final String[] header;
    final String[][] data;

    TableData(String[] header, String[][] data) {
      this.header = header;
      this.data = data;
    }

    public AsciiTableBuilder populate(AsciiTableBuilder b) {
        return b.header(header).data(data);
    }
}
