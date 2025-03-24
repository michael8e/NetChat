package nhadobas.net.spigot.chat;

import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.event.EventHandler;

public class NetChat extends Command implements Listener {
    public NetMain pasta;
    public List<String> input = new ArrayList();
    
    public NetChat(NetMain Nethad) {
	super("netchat", "netchat.use", new String[0]);
	this.pasta = Nethad;
	this.pasta.getProxy().getPluginManager().registerCommand(this.pasta, this);
	this.pasta.getProxy().getPluginManager().registerListener(this.pasta, this);
    }
    
    public void execute(CommandSender s, String[] args) {
	if(!this.input.contains(s.getName())) {
	    this.input.add(s.getName());
	    s.sendMessage( new TextComponent (ChatColor.DARK_GREEN + "NetChat focus toggled on."));
	    return;
	}

	this.input.remove(s.getName());
	s.sendMessage( new TextComponent (ChatColor.DARK_RED + "NetChat focus toggled off."));
    }

    @EventHandler
    public void onPlayerJoin(PostLoginEvent e) {
        ProxiedPlayer s = e.getPlayer();
        if(!this.input.contains(s.getName())) {
            this.input.add(s.getName());
            s.sendMessage( new TextComponent (ChatColor.DARK_GREEN + "NetChat focus toggled on."));
            return;
        }
    }

    @EventHandler
	public void onPlayerChat(ChatEvent e) {
	if((e.getSender() instanceof ProxiedPlayer)) {
	    ProxiedPlayer s = (ProxiedPlayer)e.getSender();
	    if((this.input.contains(s.getName())) && (!e.isCommand()) && (s.hasPermission("netchat.use"))) {
		e.setCancelled(true);
		for(ProxiedPlayer pl : this.pasta.getProxy().getPlayers())
		    if(((pl instanceof ProxiedPlayer)) && (pl.hasPermission("netchat.use")))
			pl.sendMessage(new TextComponent(ChatColor.DARK_RED + "[" + ChatColor.BLUE + s.getServer().getInfo().getName() + ChatColor.DARK_RED + "] " + ChatColor.GOLD + s.getName() + ChatColor.RESET + ": " + e.getMessage()));
		return;
	    }
	    return;
	}
    }
}

