package com.github.zhuaidadaya.MCH.Command;

public record CommandError<I extends String, EP extends Integer, CL extends Integer>(I INFO, EP ERR_POS,
                                                                                     CL COMMAND_LENGTH) implements CommandStats {
    @Override
    public CommandStats getCommandStats() {
        return this;
    }

    @Override
    public String getStats() {
        return this.stats();
    }

    public String stats() {
        return "error";
    }

    public String toString() {
        return "[INFO=" + INFO + ", ERR_POS=" + ERR_POS + ", COMMAND_LENGTH=" + COMMAND_LENGTH + "]";
    }

    @Override
    public String getInfo() {
        return INFO;
    }

    public Integer getErrPos() {
        return ERR_POS;
    }

    public Integer getCommandLength() {
        return COMMAND_LENGTH;
    }
}
