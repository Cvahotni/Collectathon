package me.spectralmage12.collectathon.handler;

import me.spectralmage12.collectathon.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Random;

public class TargetBlockHandler {
    private static Location targetBlockLocation;

    public static void setTargetBlockLocation(World world) {
        double borderSize = GameManager.getMaxSize();
        Random random = new Random();

        int randomX = (int) (random.nextInt((int) ((borderSize / 2) - (-borderSize / 2))) + (-borderSize / 2));
        int randomZ = (int) (random.nextInt((int) ((borderSize / 2) - (-borderSize / 2))) + (-borderSize / 2));

        int surfaceY = world.getMaxHeight();

        for(int y = world.getMaxHeight() - 1; y >= 0; y--) {
            if(world.getBlockAt(new Location(world, randomX, y, randomZ)).getType() != Material.AIR &&
                    world.getBlockAt(new Location(world, randomX, y, randomZ)).getType() != Material.WATER) {
                surfaceY = y;
                break;
            }
        }

        targetBlockLocation = new Location(world, randomX, surfaceY, randomZ);
        Bukkit.getServer().getConsoleSender().sendMessage("Set block at: " + targetBlockLocation.getBlockX() + ", " + targetBlockLocation.getBlockY() + ", " + targetBlockLocation.getBlockZ());
    }

    public static void setTargetBlock(World world) {
        if(targetBlockLocation == null)
            return;

        world.getBlockAt(targetBlockLocation).setType(Material.OBSIDIAN);
    }

    public static Location getTargetBlockLocation() {
        return targetBlockLocation;
    }
}
