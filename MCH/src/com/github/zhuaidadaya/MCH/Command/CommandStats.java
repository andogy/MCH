package com.github.zhuaidadaya.MCH.Command;

@Command
public interface CommandStats extends Commands {
    String getStats();
    CommandStats getCommandStats();
    String getInfo();

    String toString();
}
