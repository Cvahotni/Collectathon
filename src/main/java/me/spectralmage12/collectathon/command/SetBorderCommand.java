package me.spectralmage12.collectathon.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.IntegerArgument;
import me.spectralmage12.collectathon.Collectathon;
import me.spectralmage12.collectathon.GameManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class SetBorderCommand extends CustomCommand {
    @Override
    public void register(Collectathon main) {
        new CommandAPICommand("setgameborder")
                .withArguments(new IntegerArgument("size"))
                .withPermission(CommandPermission.OP)
                .executes((sender, args) -> {
                    int amount = (int) args[0];

                    GameManager.setMaxSize(amount);
                    World world = Bukkit.getWorld("world");

                    if(world == null) {
                        sender.sendMessage(ChatColor.RED + "World is null! Contact support!");
                        return;
                    }

                    world.getWorldBorder().setCenter(new Location(world, 0, 0, 0));
                    world.getWorldBorder().setSize(GameManager.getMaxSize());
                    sender.sendMessage(ChatColor.WHITE + "Set the max spawn size to: " + amount + ".");
                })
                .register();
    }
}
