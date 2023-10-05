package me.spectralmage12.collectathon;

import me.spectralmage12.collectathon.command.*;
import me.spectralmage12.collectathon.handler.CommandHandler;
import me.spectralmage12.collectathon.handler.GameStateHandler;
import me.spectralmage12.collectathon.listener.EndingListener;
import me.spectralmage12.collectathon.listener.LobbyListener;
import me.spectralmage12.collectathon.listener.PausedListener;
import me.spectralmage12.collectathon.listener.PlayingListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Collectathon extends JavaPlugin {
    @Override
    public void onEnable() {
        CommandHandler.addCustomCommand(new GameStateSetCommand());
        CommandHandler.addCustomCommand(new AddPlayerCommand());
        CommandHandler.addCustomCommand(new RemovePlayerCommand());
        CommandHandler.addCustomCommand(new SetCollectorCommand());
        CommandHandler.addCustomCommand(new SetBorderCommand());
        CommandHandler.addCustomCommand(new PauseCommand());
        CommandHandler.addCustomCommand(new UnpauseCommand());
        CommandHandler.addCustomCommand(new AddEveryoneCommand());
        CommandHandler.addCustomCommand(new StartCommand());

        CommandHandler.register(this);

        GameStateHandler.addListener("lobby", new LobbyListener());
        GameStateHandler.addListener("playing", new PlayingListener());
        GameStateHandler.addListener("ending", new EndingListener());
        GameStateHandler.addListener("paused", new PausedListener());

        GameStateHandler.setCurrentListener(this, "none");
    }

    @Override
    public void onDisable() {

    }
}
