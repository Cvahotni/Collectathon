package me.spectralmage12.collectathon.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import me.spectralmage12.collectathon.Collectathon;
import me.spectralmage12.collectathon.GameManager;
import me.spectralmage12.collectathon.handler.GameStateHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class StartCommand extends CustomCommand {
    @Override
    public void register(Collectathon main) {
        new CommandAPICommand("start")
                .withArguments(new StringArgument("collector"))
                .withArguments(new IntegerArgument("border"))
                .withPermission(CommandPermission.OP)
                .executes((sender, args) -> {
                    String collector = (String) args[0];
                    int amount = (int) args[1];

                    GameManager.setMaxSize(amount);
                    World world = Bukkit.getWorld("world");

                    if(world == null) {
                        sender.sendMessage(ChatColor.RED + "World is null! Contact support!");
                        return;
                    }

                    world.getWorldBorder().setCenter(new Location(world, 0, 0, 0));
                    world.getWorldBorder().setSize(GameManager.getMaxSize());

                    if(GameManager.doesNotHavePlayer(collector)) {
                        sender.sendMessage(ChatColor.RED + "That player isn't in the game.");
                        return;
                    }

                    GameManager.setCollector(collector);
                    GameStateHandler.setCurrentListener(main, "lobby");
                })
                .register();
    }
}
