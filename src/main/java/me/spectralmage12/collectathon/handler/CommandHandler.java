package me.spectralmage12.collectathon.handler;

import me.spectralmage12.collectathon.Collectathon;
import me.spectralmage12.collectathon.command.CustomCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    private static final List<CustomCommand> commands = new ArrayList<>();

    public static void addCustomCommand(CustomCommand command) {
        commands.add(command);
    }

    public static void register(Collectathon main) {
        for(CustomCommand command : commands)
            command.register(main);
    }
}
