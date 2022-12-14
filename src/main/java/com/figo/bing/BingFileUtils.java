package com.figo.bing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class BingFileUtils {
    private static Path BING_PATH = Paths.get("bing-wallpaper.md");
    private static Path BING_CSS = Paths.get("bg.css");

    public static void writeBing(Image image) throws IOException {
        if (!Files.exists(BING_PATH)) {
            Files.createFile(BING_PATH);
        }
        Files.write(BING_PATH, image.formatMarkdown().getBytes(), StandardOpenOption.APPEND);
        Files.write(BING_PATH, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
    }
    public static void writeBingCss(Image image) throws IOException {
        if (!Files.exists(BING_CSS)) {
            Files.createFile(BING_CSS);
        }
        Files.write(BING_CSS, image.toBackGround().getBytes());
    }
}
