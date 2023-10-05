package me.spectralmage12.collectathon.handler;

import me.spectralmage12.collectathon.Collectathon;
import me.spectralmage12.collectathon.listener.GameListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public class GameStateHandler {
    private static String currentListenerId;
    private static final HashMap<String, GameListener> listeners = new HashMap<>();

    public static void addListener(String id, GameListener listener) {
        listeners.put(id, listener);
    }

    public static void setCurrentListener(Collectathon main, String id) {
        if(id.equals(currentListenerId))
            return;

        currentListenerId = id;
        Bukkit.getServer().getConsoleSender().sendMessage("Setting! " + id);

        for (Map.Entry<String, GameListener> set : listeners.entrySet()) {
            String currentId = set.getKey();
            GameListener currentListener = set.getValue();

            if(currentId.equals(currentListenerId)) {
                currentListener.onSelect(main);
                enableListener(main, currentListener);
            }

            else
                disableListener(currentListener);
        }
    }

    private static void enableListener(Collectathon main, Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, main);
    }

    private static void disableListener(Listener listener) {
        HandlerList.unregisterAll(listener);
    }
}
