package me.spectralmage12.collectathon.listener;

import me.spectralmage12.collectathon.Collectathon;
import me.spectralmage12.collectathon.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PausedListener extends GameListener {
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

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(GameManager.doesNotHavePlayer(event.getPlayer().getName()))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if(GameManager.doesNotHavePlayer(event.getPlayer().getName()))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player player))
            return;

        if(GameManager.doesNotHavePlayer(player.getName()))
            return;

        event.setCancelled(true);
    }

    @Override
    public void onSelect(Collectathon main) {
        GameManager.setPaused(true);
    }
}
