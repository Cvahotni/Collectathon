package me.spectralmage12.collectathon.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import me.spectralmage12.collectathon.Collectathon;
import me.spectralmage12.collectathon.GameManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@SuppressWarnings("ConstantConditions")
public class AddPlayerCommand extends CustomCommand {
    @Override
    public void register(Collectathon main) {
        new CommandAPICommand("addplayer")
                .withArguments(new GreedyStringArgument("player"))
                .withPermission(CommandPermission.OP)
                .executes((sender, args) -> {
                    String player = (String) args.get(0);
                    Player playerObject = Bukkit.getPlayer(player);

                    if(!Bukkit.getOnlinePlayers().contains(playerObject)) {
                        sender.sendMessage(ChatColor.RED + "That player isn't online right now!");
                        return;
                    }

                    if(!GameManager.doesNotHavePlayer(player)) {
                        sender.sendMessage(ChatColor.RED + "That player has already joined!");
                        return;
                    }

                    GameManager.addPlayer(player);
                    sender.sendMessage(ChatColor.WHITE + "Added " + player + " to the game.");
                })
                .register();
    }
}
