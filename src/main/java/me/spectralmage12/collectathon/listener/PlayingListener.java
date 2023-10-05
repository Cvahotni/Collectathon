package me.spectralmage12.collectathon.listener;

import me.spectralmage12.collectathon.Collectathon;
import me.spectralmage12.collectathon.GameManager;
import me.spectralmage12.collectathon.handler.TargetBlockHandler;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class PlayingListener extends GameListener {
    private Collectathon main;

    @Override
    public void onSelect(Collectathon main) {
        this.main = main;

        World world = Bukkit.getWorld("world");

        if(world == null)
            return;

        if(!GameManager.isPaused()) {
            TargetBlockHandler.setTargetBlockLocation(world);
            TargetBlockHandler.setTargetBlock(world);

            GameManager.massGive(new ItemStack(Material.COMPASS, 1));
        }

        else
            GameManager.setPaused(false);
    }

    @EventHandler
    public void onDeath(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player player))
            return;

        if(event.getDamage() < player.getHealth())
            return;

        if(!GameManager.getCollector().equals(player.getName()))
            return;

        GameManager.end(main, ChatColor.GREEN + "The collector has died!");

        player.setGameMode(GameMode.SPECTATOR);
        event.setCancelled(true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(player.getName().equals(GameManager.getCollector())) {
            player.setCompassTarget(TargetBlockHandler.getTargetBlockLocation());

            Location location = player.getLocation();
            World world = location.getWorld();

            if(world == null)
                return;

            if(world.getName().contains("nether") || world.getName().contains("end")) {
                player.spawnParticle(Particle.MOB_APPEARANCE, player.getLocation(), 8);

                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 7.0f, 1.0f);
                player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 0.6f, 1.0f);
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 0.5f, 1.0f);
                player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 6.0f, 10.0f);

                for(int i = 0; i < player.getInventory().getContents().length; i++) {
                    ItemStack stack = player.getInventory().getItem(i);

                    if(stack == null)
                        continue;

                    if(stack.getType().equals(Material.AIR))
                        continue;

                    Random random = new Random();
                    int randomInt = random.nextInt(player.getInventory().getContents().length);

                    player.getInventory().setItem(i, new ItemStack(Material.AIR));
                    player.getInventory().setItem(randomInt, stack);
                }

                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "YOU SHOULDN'T HAVE COME HERE! >:D HAHAHAHAH");
                player.setFireTicks(20 * 69);
                player.showDemoScreen();

                world.createExplosion(player.getLocation(), 1.0f);

                event.setCancelled(true);
            }
        }

        else {
            Player collector = Bukkit.getPlayer(GameManager.getCollector());

            if(collector == null)
                return;

            player.setCompassTarget(collector.getLocation());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        World world = event.getBlock().getWorld();
        Location location = event.getBlock().getLocation();

        if(TargetBlockHandler.getTargetBlockLocation().equals(location))
            GameManager.modifyTargetBlocksLeft(main, world);
    }
}
