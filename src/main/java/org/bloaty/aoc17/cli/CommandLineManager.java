package org.bloaty.aoc17.cli;

import java.io.Console;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

public final class CommandLineManager {
    
    private static enum InputMode {
        INTERACTIVE,
        COMMAND_LINE,
        FILE;
    }
    
    private final CommandLineParser parser;
    private final HelpFormatter formatter;
    private final CommandLine commandLine;
    private final String projectName;
    private final CommandLineOptions options;
    
    private CommandLineManager(String projectName, String[] args) throws ParseException {
        this.parser = new DefaultParser();
        this.formatter = new HelpFormatter();
        this.projectName = projectName;
        this.options = CommandLineOptions.INSTANCE;
        this.commandLine = parser.parse(options.get(), args);
    }
    
    public static CommandLineManager with(String projectName, String[] args) throws ParseException {
        return new CommandLineManager(projectName, args);
    }
    
    public void printHelpAndExitIfNeeded() {
        if (helpRequested()) {
            printHelp();
            System.exit(0);
        }
    }
    
    public String getInput() {
        final InputMode inputMode = getInputMode();
        return getInput(inputMode);
    }
    
    public String getProblem() {
        if (!problemSpecified()) {
            printHelp();
            System.exit(1);
        }
        return commandLine.getOptionValue(CommandLineOptions.PROBLEM_NUMBER_OPTION_NAME);
    }
    
    public void printHelp() {
        formatter.printHelp(projectName, options.get());
    }
    
    private InputMode getInputMode() {
        checkForConflictingInputsAndExitIfNeeded();
        if (noInputModeSpecified()) {
            return InputMode.INTERACTIVE;
        }
        if (commandLineInputModeSpecified()) {
            return InputMode.COMMAND_LINE;
        }
        return InputMode.FILE;
    }
    
    private void checkForConflictingInputsAndExitIfNeeded() {
        if (incompatibleInputModesSpecified()) {
            System.err.println("Parsing command-line options failed: do not provide both command-line and file-based input.");
            printHelp();
            System.exit(1);
        }
    }
    
    private boolean problemSpecified() {
        return commandLine.hasOption(CommandLineOptions.PROBLEM_NUMBER_OPTION_NAME);
    }

    private boolean helpRequested() {
        return commandLine.hasOption(CommandLineOptions.HELP_OPTION_LONG_NAME) ||
               commandLine.hasOption(CommandLineOptions.HELP_OPTION_SHORT_NAME);
    }

    private boolean noInputModeSpecified() {
        return !fileInputModeSpecified() && !commandLineInputModeSpecified();
    }

    private boolean incompatibleInputModesSpecified() {
        return fileInputModeSpecified() && commandLineInputModeSpecified();
    }

    private boolean commandLineInputModeSpecified() {
        return commandLine.hasOption(CommandLineOptions.RAW_INPUT_DATA_OPTION_NAME);
    }
    
    private boolean fileInputModeSpecified() {
        return commandLine.hasOption(CommandLineOptions.INPUT_FILE_OPTION_NAME);
    }

    private String getInput(InputMode inputMode) {
        switch (inputMode) {
        case INTERACTIVE:
            return getInputFromConsole();
        case COMMAND_LINE:
            return getInputFromCommandLine();
        case FILE:
            return getInputFromFile();
        default:
            return null;
        }
    }

    private String getInputFromConsole() {
        Console console = System.console();
        return console.readLine("%s", "Input: ");
    }

    private String getInputFromCommandLine() {
        return commandLine.getOptionValue(CommandLineOptions.INPUT_FILE_OPTION_NAME);
    }

    private String getInputFromFile() {
        String fileName = commandLine.getOptionValue(CommandLineOptions.INPUT_FILE_OPTION_NAME);
        Path path = FileSystems.getDefault().getPath(fileName);
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            System.err.println("Input file does not exist or is not a file.");
            System.exit(1);
        }
        try {
            return Files.lines(path).collect(Collectors.joining("\n"));
        } catch (IOException | SecurityException e) {
            RuntimeException e2 = new RuntimeException("Problem reading from data file.");
            e2.setStackTrace(e.getStackTrace());
            throw e2;
        }
    }

}
