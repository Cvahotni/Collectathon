package me.spectralmage12.collectathon.listener;

import me.spectralmage12.collectathon.Collectathon;
import me.spectralmage12.collectathon.GameManager;
import me.spectralmage12.collectathon.handler.GameStateHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EndingListener extends GameListener {
    private final int maxCountdown = 20;
    private int countdown = maxCountdown;
    private int taskID = -1;

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(GameManager.doesNotHavePlayer(event.getPlayer().getName()))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(GameManager.doesNotHavePlayer(event.getPlayer().getName()))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player player))
            return;

        if(GameManager.doesNotHavePlayer(player.getName()))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player player))
            return;

        if(GameManager.doesNotHavePlayer(player.getName()))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(GameManager.doesNotHavePlayer(event.getPlayer().getName()))
            return;

        event.setCancelled(true);
    }

    @Override
    public void onSelect(Collectathon main) {
        taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, () -> {
            if(countdown <= 0) {
                GameManager.resetPlayers();
                GameManager.clearPlayers();

                countdown = maxCountdown;
                GameStateHandler.setCurrentListener(main, "none");

                Bukkit.getServer().getScheduler().cancelTask(taskID);
            }

            else {
                GameManager.broadcast(ChatColor.GOLD + "Resetting in " + ChatColor.WHITE + countdown + ChatColor.GOLD + " seconds!");
                countdown -= 1;
            }
        }, 0L, 20L);
    }
}
