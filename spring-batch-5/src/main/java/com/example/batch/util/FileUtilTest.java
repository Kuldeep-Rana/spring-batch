package com.example.batch.util;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtilTest {
    public static void main(String[] args) throws IOException {

        var localDate  = DateTimeFormatter.ofPattern("dd-MMM-yy").parse("04-Sep-81", LocalDate::from);

        System.out.println(java.sql.Date.valueOf(localDate));

        String path = "D://KULDEEP RANA//1stbday//";
        String pattern = path+"*month*";
        FileSystem fs = FileSystems.getDefault();
        PathMatcher pathMatcher = fs.getPathMatcher("glob:" + pattern);

        try(Stream<Path> stream = Files.walk(Path.of(path), FileVisitOption.FOLLOW_LINKS)){
            var list = stream.parallel()
                    .filter(pathMatcher::matches)
                    .filter(p -> !Files.isRegularFile(p))
                    .map(p -> FileLoader.loadResources(p.toString()))
                    .flatMap(files -> Arrays.stream(files.toArray()))
                    .collect(Collectors.toList());
            System.out.println(list);

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
