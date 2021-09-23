package lol.wesley.welcomesystem;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public final class WelcomeSystem extends JavaPlugin implements Listener {
    ArrayList<Player> list = new ArrayList<>();
    ArrayList<String> words = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        list.clear();
        words.clear();
        Static.init();
        addWords();
        getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        CharSequence s = words.toString();
        if ((e.getMessage().toLowerCase().contains("wb") || e.getMessage().toLowerCase().contains("welcome"))&& Static.accepting) {
            if (e.getPlayer().getName().equals(Static.joiner)) return;
            System.out.println(e.getPlayer().toString());
            System.out.println(Static.joiner);
            e.getPlayer().sendMessage(net.md_5.bungee.api.ChatColor.of("#18db70") + "Thank you for welcoming " + Static.joiner + "! You got $10 for welcoming :))");
        }
    }
    @EventHandler
    public void joinEvent(PlayerJoinEvent e) {
        if (!Static.accepting) {
            new BukkitRunnable(){
                @Override
                public void run() {

                    Static.accepting = false;
                    Static.joiner = null;
                }
            }.runTaskLater(this, 100L);
            Static.accepting = true;
            Static.joiner = e.getPlayer().getName();
        }
    }

    public void addWords(){
        words.add("wb");
        words.add("welcome");
    }
}
