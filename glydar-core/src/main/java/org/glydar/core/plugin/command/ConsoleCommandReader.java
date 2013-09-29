package org.glydar.core.plugin.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.glydar.api.Backend;
import org.glydar.api.logging.GlydarLogger;

public class ConsoleCommandReader extends Thread {

    private final Backend              backend;
    private final GlydarLogger         logger;
    private final ConsoleCommandSender sender;

    ConsoleCommandReader(Backend backend) {
        this.backend = backend;
        this.logger = backend.getLogger(ConsoleCommandReader.class, "Message");
        this.sender = new ConsoleCommandSender(logger);

        setDaemon(true);
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String cmdLine = bufferedReader.readLine();
            sender.sendMessage("Executing command " + cmdLine);
            backend.getCommandManager().execute(sender, cmdLine);
        }
        catch (IOException exc) {
            logger.warning(exc, "Error while reading console command");
        }
        catch (NullPointerException e) {
            // Catch NPE
        }
    }
}
