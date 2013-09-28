package org.glydar.core.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.glydar.api.logging.GlydarLogRecord;


public class CoreGlydarLogRecord extends LogRecord implements GlydarLogRecord {

    private static final long serialVersionUID = -8178899715814412568L;

    private final String      prefix;

    public CoreGlydarLogRecord(Level level, String message, String prefix) {
        super(level, message);
        this.prefix = prefix;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }
}
