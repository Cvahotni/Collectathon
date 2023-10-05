package me.spectralmage12.collectathon.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import me.spectralmage12.collectathon.Collectathon;
import me.spectralmage12.collectathon.handler.GameStateHandler;
import net.md_5.bungee.api.ChatColor;

public class GameStateSetCommand extends CustomCommand {
    @Override
    public void register(Collectathon main) {
        new CommandAPICommand("setgamestate")
                .withArguments(new GreedyStringArgument("state"))
                .withPermission(CommandPermission.OP)
                .executes((sender, args) -> {
                    String state = (String) args[0];
                    GameStateHandler.setCurrentListener(main, state);

                    sender.sendMessage(ChatColor.WHITE + "Set the current state to: " + state + ".");
                })
                .register();
    }
}
