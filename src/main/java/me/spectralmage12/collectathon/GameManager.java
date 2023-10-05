package me.spectralmage12.collectathon;

import me.spectralmage12.collectathon.handler.GameStateHandler;
import me.spectralmage12.collectathon.handler.TargetBlockHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static int maxSize = 6000;
    private static final int targetBlocksLeftMax = 10;
    private static int targetBlocksLeft = targetBlocksLeftMax;
    private static final List<String> players = new ArrayList<>();
    private static String collector;
    private static boolean paused;

    public static void start(Collectathon main) {
        broadcast(ChatColor.GOLD + "THE GAME HAS STARTED!");
        broadcast(ChatColor.WHITE + "Good Luck!");

        GameStateHandler.setCurrentListener(main, "playing");
    }

    public static void end(Collectathon main, String reason) {
        broadcast(ChatColor.GOLD + "THE GAME HAS ENDED!");
        broadcast(ChatColor.GOLD + "Reason: " + ChatColor.RESET + reason);

        targetBlocksLeft = targetBlocksLeftMax;

        broadcastSound(Sound.ENTITY_ENDER_DRAGON_DEATH);
        GameStateHandler.setCurrentListener(main, "ending");
    }

    public static void resetPlayers() {
        for(String playerName : players) {
            Player player = Bukkit.getPlayer(playerName);

            if (player == null)
                continue;

            resetPlayer(player);
        }
    }

    public static void resetPlayer(Player player) {
        player.getInventory().clear();
        player.setHealth(player.getMaxHealth());

        player.setFoodLevel(20);
        player.setSaturation(20);

        player.getActivePotionEffects().clear();
        player.setGameMode(GameMode.SURVIVAL);
    }

    public static void addPlayer(String name) {
        players.add(name);
    }

    public static void removePlayer(String name) {
        players.remove(name);
    }

    public static void clearPlayers() {
        setCollector("");
        players.clear();
    }

    public static boolean doesNotHavePlayer(String name) {
        return !players.contains(name);
    }

    public static String getCollector() {
        return collector;
    }

    public static void setCollector(String name) {
        collector = name;
    }

    public static void broadcast(String msg) {
        for(String playerName : players) {
            Player player = Bukkit.getPlayer(playerName);

            if(player == null)
                continue;

            player.sendMessage(msg);
        }
    }

    public static void broadcastSound(Sound sound) {
        for(String playerName : players) {
            Player player = Bukkit.getPlayer(playerName);

            if(player == null)
                continue;

            player.playSound(player, sound, 10.0f, 1.0f);
        }
    }

    public static void massGive(ItemStack stack) {
        for(String playerName : players) {
            Player player = Bukkit.getPlayer(playerName);

            if(player == null)
                continue;

            player.getInventory().addItem(stack);
        }
    }

    public static void modifyTargetBlocksLeft(Collectathon main, World world) {
        targetBlocksLeft -= 1;

        TargetBlockHandler.setTargetBlockLocation(world);
        TargetBlockHandler.setTargetBlock(world);

        broadcast(ChatColor.GREEN + "The collector found the block! " + targetBlocksLeft + " are left!");
        broadcastSound(Sound.ITEM_GOAT_HORN_SOUND_0);

        if(targetBlocksLeft <= 0)
            end(main, ChatColor.RED + "The collector found all the blocks!");
    }

    public static int getMaxSize() {
        return maxSize;
    }

    public static void setMaxSize(int maxSize) {
        GameManager.maxSize = maxSize;
    }

    public static boolean isPaused() {
        return paused;
    }

    public static void setPaused(boolean value) {
        paused = value;
    }
}
