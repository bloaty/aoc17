package org.bloaty.aoc17;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public enum CommandLineOptions {
    
    INSTANCE;
    
    public static final String RAW_INPUT_DATA_OPTION_NAME = "input-data";
    public static final String INPUT_FILE_OPTION_NAME = "input-file";
    public static final String PROBLEM_NUMBER_OPTION_NAME = "problem-number";
    public static final String HELP_OPTION_LONG_NAME = "help";
    public static final String HELP_OPTION_SHORT_NAME = "h";
    
    private final Options options;
    
    private CommandLineOptions() {
        final Option rawInputDataOption = Option.builder()
                .longOpt(RAW_INPUT_DATA_OPTION_NAME)
                .desc("string of raw data input")
                .hasArg(true)
                .required(false)
                .numberOfArgs(1)
                .build();
        
        final Option inputFileOption = Option.builder()
                .longOpt(INPUT_FILE_OPTION_NAME)
                .desc("file containing raw problem input")
                .hasArg(true)
                .required(false)
                .numberOfArgs(1)
                .build();
        
        final Option problemNumberOption = Option.builder()
                .longOpt(PROBLEM_NUMBER_OPTION_NAME)
                .desc("problem number: 1A, 1B, etc.")
                .hasArg(true)
                .required(true)
                .numberOfArgs(1)
                .build();
        
        final Option helpOption = Option.builder(HELP_OPTION_SHORT_NAME)
                .longOpt(HELP_OPTION_LONG_NAME)
                .hasArg(false)
                .required(false)
                .build();
        
        this.options = new Options();
        options.addOption(rawInputDataOption);
        options.addOption(inputFileOption);
        options.addOption(problemNumberOption);
        options.addOption(helpOption);
    }
    
    public Options get() {
        return options;
    }

}
