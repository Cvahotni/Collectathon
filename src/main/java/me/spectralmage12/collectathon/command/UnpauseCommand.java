package me.spectralmage12.collectathon.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import me.spectralmage12.collectathon.Collectathon;
import me.spectralmage12.collectathon.GameManager;
import me.spectralmage12.collectathon.handler.GameStateHandler;
import net.md_5.bungee.api.ChatColor;

public class UnpauseCommand extends CustomCommand {
    @Override
    public void register(Collectathon main) {
        new CommandAPICommand("unpause")
                .withPermission(CommandPermission.OP)
                .executes((sender, args) -> {
                    GameManager.broadcast(ChatColor.GREEN + "The game has been un-paused!");
                    GameStateHandler.setCurrentListener(main, "playing");

                    sender.sendMessage(ChatColor.GREEN + "Un-paused the game.");
                })
                .register();
    }
}
