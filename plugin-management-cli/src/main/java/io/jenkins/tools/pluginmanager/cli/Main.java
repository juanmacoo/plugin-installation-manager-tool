package io.jenkins.tools.pluginmanager.cli;

import io.jenkins.tools.pluginmanager.config.Config;
import io.jenkins.tools.pluginmanager.impl.PluginManager;
import java.io.IOException;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;


public class Main {
    public static void main(String[] args) throws IOException {
        CliOptions options = new CliOptions();
        CmdLineParser parser = new CmdLineParser(options);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            parser.printUsage(System.err);
            System.err.println(e.getMessage());
            throw new IOException("Failed to read command-line arguments", e);
        }

        Config cfg = new Config();
        ConfigSetup configSetup = new ConfigSetup(cfg, options);
        configSetup.setup();
        PluginManager pm = new PluginManager(cfg);
        pm.start();
    }

}
