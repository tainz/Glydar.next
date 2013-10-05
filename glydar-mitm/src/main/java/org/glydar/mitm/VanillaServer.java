package org.glydar.mitm;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;

import org.glydar.api.logging.GlydarLogger;
import org.glydar.core.protocol.packet.Packet10Chat;

public class VanillaServer implements Runnable {

    private static final String LOGGER_PREFIX = "Vanilla Server";
    private final GlydarLogger logger;
    private final File directory;
    private final String executable;
    private final AtomicBoolean restarting;

    private Process serverProcess;
    private Thread reader;

    protected VanillaServer(GlydarMitm mitm) {
        this.logger = mitm.getLogger(getClass(), LOGGER_PREFIX);
        Path executablePath = mitm.getBaseFolder().resolve(mitm.getConfig().getVanillaPath());
        this.directory = executablePath.getParent().toFile();
        this.executable = executablePath.toString();
        this.restarting = new AtomicBoolean(false);
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(serverProcess.getInputStream()));
            while (true) {
                try {
                    String line = in.readLine();
                    if (line == null || line.isEmpty()) {
                        continue;
                    }

                    if (line.contains("Waiting") && restarting.get()) {
                        for (Relay relay : GlydarMitm.getInstance().getRelays()) {
                            relay.connectToServer();
                            relay.sendToClient(new Packet10Chat("Reconnecting ..."));
                        }
                    }

                    logger.info(line);
                    Thread.sleep(1);
                }
                catch (IOException exc) {
                    logger.severe(exc, "Error reading Vanilla Server input stream");
                }
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
            serverProcess = Runtime.getRuntime().exec(executable, null, directory);
            reader = new Thread(this);
            reader.start();
        }
        catch (IOException exc) {
            logger.severe(exc, "Could not start the vanilla server. Shutting down.");
            GlydarMitmMain.shutdown();
        }
    }

    public boolean processExists() {
        return serverProcess != null;
    }

    public void shutDownGracefully() {
        if (processExists()) {
            serverProcess.destroy();
            reader.interrupt();
            serverProcess = null;
        }
    }

    public void restart() {
        shutDownGracefully();
        start();
    }

    public AtomicBoolean getRestarting() {
        return restarting;
    }
}
