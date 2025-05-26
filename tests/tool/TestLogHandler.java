package tool;


import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TestLogHandler extends Handler {
    private final StringBuilder logMessages = new StringBuilder();

    @Override
    public void publish(LogRecord record) {
        logMessages.append(record.getMessage()).append("\n");
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }

    public boolean containsMessage(String message) {
        return logMessages.toString().contains(message);
    }
}