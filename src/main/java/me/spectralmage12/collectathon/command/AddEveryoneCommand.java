package me.spectralmage12.collectathon.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import me.spectralmage12.collectathon.Collectathon;
import me.spectralmage12.collectathon.GameManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AddEveryoneCommand extends CustomCommand {
    @Override
    public void register(Collectathon main) {
        new CommandAPICommand("addeveryone")
                .withPermission(CommandPermission.OP)
                .executes((sender, args) -> {
                    for(Player player :  Bukkit.getOnlinePlayers()) {
                        if(player == null)
                            continue;

                        if(!GameManager.doesNotHavePlayer(player.getName())) {
                            sender.sendMessage(ChatColor.RED + player.getName() + " has already joined!");
                            continue;
                        }

                        GameManager.addPlayer(player.getName());
                        sender.sendMessage(ChatColor.WHITE + "Added " + player.getName() + " to the game.");
                    }
                })
                .register();
    }
}
