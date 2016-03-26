package me.stoi2m1;

import org.bukkit.event.Listener;

import me.stoi2m1.CrackshotSoundDesigner;

public class ListenerClass implements Listener {
	
	public ListenerClass(CrackshotSoundDesigner plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);	
	}
}
