package com.example.batch.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    public static List<File> loadResources(String directoryPath) {
        File directory = new File(directoryPath);

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Not a valid directory: " + directoryPath);
        }

        File[] matchingFiles = directory.listFiles();

        if (matchingFiles == null) {
            return new ArrayList<>();
        }

        return List.of(matchingFiles);
    }
}
