package me.spectralmage12.collectathon.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import me.spectralmage12.collectathon.Collectathon;
import me.spectralmage12.collectathon.GameManager;
import net.md_5.bungee.api.ChatColor;

public class RemovePlayerCommand extends CustomCommand {
    @Override
    public void register(Collectathon main) {
        new CommandAPICommand("removeplayer")
                .withArguments(new GreedyStringArgument("player"))
                .withPermission(CommandPermission.OP)
                .executes((sender, args) -> {
                    String player = (String) args[0];

                    if(GameManager.doesNotHavePlayer(player)) {
                        sender.sendMessage(ChatColor.RED + "That player isn't in the game.");
                        return;
                    }

                    GameManager.removePlayer(player);
                    sender.sendMessage(ChatColor.WHITE + "Removed " + player + " from the game.");
                })
                .register();
    }
}
