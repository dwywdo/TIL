package com.euiyub.springarchive;

import java.beans.PropertyEditorSupport;

/**
 * Implementing PropertyEditor is pretty much hard work.
 * We extends PropertyEditorSupport
 * getValue, setValue -> value of PropertyEditor shared by different threads
 * It means it's not thread-safe
 * This class must not be registered as singletone bean
 * Should be created as thread-safe-scoped-beans
 */
public class EventEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        final Event event = (Event) getValue();
        return event.getId().toString();
    }

    // Actually we only need to implement below method
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new Event(Integer.parseInt(text)));
    }

}
