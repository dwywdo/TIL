package me.dwywdo.labs.java.design_pattern.observer.refactoring_guru;

import org.junit.jupiter.api.Test;

import me.dwywdo.labs.java.design_pattern.observer.refactoring_guru.editor.Editor;
import me.dwywdo.labs.java.design_pattern.observer.refactoring_guru.listeners.EmailNotificationListener;
import me.dwywdo.labs.java.design_pattern.observer.refactoring_guru.listeners.LogOpenListener;

public class EditorTest {
    private final Editor editor = new Editor();

    @Test
    void editorTest() {
        editor.events.subscribe("open", new LogOpenListener("/path/to/log/file.txt"));
        editor.events.subscribe("save", new EmailNotificationListener("admin@example.com"));

        try {
            editor.openFile("test.txt");
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
