package me.dwywdo.labs.java.design_pattern.observer.refactoring_guru.listeners;

import java.io.File;

public interface EventListener {
    void update(String eventType, File file);
}
