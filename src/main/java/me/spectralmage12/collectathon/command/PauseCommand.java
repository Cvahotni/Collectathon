package me.spectralmage12.collectathon.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import me.spectralmage12.collectathon.Collectathon;
import me.spectralmage12.collectathon.GameManager;
import me.spectralmage12.collectathon.handler.GameStateHandler;
import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("ConstantConditions")
public class PauseCommand extends CustomCommand {
    @Override
    public void register(Collectathon main) {
        new CommandAPICommand("pause")
                .withPermission(CommandPermission.OP)
                .executes((sender, args) -> {
                    GameManager.broadcast(ChatColor.RED + "The game has been paused!");
                    GameStateHandler.setCurrentListener(main, "paused");

                    sender.sendMessage(ChatColor.GREEN + "Paused the game.");
                })
                .register();
    }
}
