package me.spectralmage12.collectathon.listener;

import me.spectralmage12.collectathon.Collectathon;
import org.bukkit.event.Listener;

public abstract class GameListener implements Listener {
    public abstract void onSelect(Collectathon main);
}
