package com.github.zhuaidadaya.MCH.Command;

@Command
public class CommandChecker<I extends String> implements CommandStats{
    public I INFO;

    public CommandChecker(I INFO) {
        this.INFO = INFO;
    }

    @Override
    public String getStats() {
        return stats();
    }

    @Override
    public CommandStats getCommandStats() {
        return this;
    }

    @Override
    public String getInfo() {
        return INFO;
    }

    public String stats() {
        return "pass";
    }

    public String toString() {
        return "[INFO=" + INFO + "]";
    }
}
