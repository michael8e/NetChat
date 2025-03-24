package nhadobas.net.spigot.chat;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class NetMain extends Plugin implements Listener {
    private static NetMain noodles;
    public static ServerInfo hub;
    
    public void onEnable() {
	noodles = this;
	getProxy().getLogger().log(Level.INFO, "NetChat may or may not enable!");
	new NetChat(this);
	getProxy().getLogger().log(Level.INFO, "NetChat is enabled!");
    }

    public void onDisable() {
	getProxy().getLogger().log(Level.INFO, "NetChat is disabling!");
    }

    public static NetMain getPlugin() {
	return noodles;
    }
}
