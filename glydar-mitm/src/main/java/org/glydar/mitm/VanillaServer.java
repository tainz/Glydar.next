package org.glydar.mitm;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.glydar.api.logging.GlydarLogger;
import org.glydar.core.protocol.packet.Packet10Chat;

public class VanillaServer implements Runnable {

    private static final String LOGGER_PREFIX = "Vanilla Server";
    private static final String VANILLE_QUIT_MESSAGE = " Enter Q to quit.";

    private final GlydarLogger logger;
    private final File directory;
    private final String executable;

    private Process process;
    private Thread reader;
    private boolean restarting;

    protected VanillaServer(GlydarMitm mitm) {
        this.logger = mitm.getLogger(getClass(), LOGGER_PREFIX);
        Path executablePath = mitm.getBaseFolder().resolve(mitm.getConfig().getVanillaPath());
        this.directory = executablePath.getParent().toFile();
        this.executable = executablePath.toString();
        this.restarting = false;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (true) {
                try {
                    String line = in.readLine();
                    if (line == null) {
                        try {
                            // Check if the process has terminated
                            process.exitValue();

                            logger.info("Restarting");
                            shutdownGracefully();
                            restarting = true;
                            start();
                            return;
                        }
                        catch (IllegalThreadStateException exc2) {
                            // Process is apparently still running, go on
                            continue;
                        }
                    }

                    if (line.contains("Waiting") && restarting) {
                        List<Relay> relays = new ArrayList<>(GlydarMitm.getInstance().getRelays());
                        Collections.sort(relays, new ByEntityId());
                        for (Relay relay : relays) {
                            restarting = false;
                            relay.sendToClient(new Packet10Chat("Reconnecting ..."));
                            relay.connectToServer();
                        }
                    }

                    line = line.replace(VANILLE_QUIT_MESSAGE, "");
                    if (!line.isEmpty()) {
                        logger.info(line);
                    }
                }
                catch (IOException exc) {
                    logger.severe(exc, "Error reading Vanilla Server input stream");
                }

                Thread.sleep(1);
            }
        }
        catch (InterruptedException exc) {
            // That's ok
        }
    }

    public void start() {
        if (processExists()) {
            logger.severe("Tried to start the Vanilla Server while it was still running.");
            return;
        }

        try {
            process = Runtime.getRuntime().exec(executable, null, directory);
            reader = new Thread(this);
            reader.start();
        }
        catch (IOException exc) {
            logger.severe(exc, "Could not start the vanilla server. Shutting down.");
            GlydarMitmMain.shutdown();
        }
    }

    public boolean processExists() {
        return process != null;
    }

    public void shutdownGracefully() {
        if (processExists()) {
            reader.interrupt();
            process.destroy();
            process = null;
        }
    }
}

class ByEntityId implements Comparator<Relay> {

    @Override
    public int compare(Relay relay1, Relay relay2) {
        return (int) relay1.getEntityId() - (int) relay2.getEntityId();
    }
}
