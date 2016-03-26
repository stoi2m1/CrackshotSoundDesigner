package me.stoi2m1;

import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class CrackshotSoundDesigner extends JavaPlugin {
	@Override
	public void onEnable() {
		new ListenerClass(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onCommand(org.bukkit.command.CommandSender, org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 *
	 * catch entered commands from a user in game
	 */
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// command /shotsound
		if (cmd.getName().equalsIgnoreCase("shotsound") && sender instanceof Player) {
			//NOTE_PLING-2-2-0,NOTE_PLING-2-2-5
			//sound-vol-ptch-dlay

			Player p = (Player) sender;
			boolean all = false;
			String[] sounds = new String[0];
			
			if (args.length == 0) {
				p.sendMessage("Syntax Error: /playsound [all] <sound1>,<sound2>,<sound3>...");
				/* *** maybe list the sound options (each version is different) *** */
				/* *** maybe list more info on how to use the command *** */
				p.playSound(p.getLocation(), Sound.BAT_HURT, 1, 1);
			} else {
				
				// degug code - p.sendMessage("|"+args[0]+"|");
				// first arg could be all to allow everyone to hear
				if (args[0].equals("all")) all = true;
				
				
				// debug code - p.sendMessage("all: "+all);
				
				for ( int i = 0; i < args.length; i++) {
					
					// debug code - p.sendMessage("args length: "+args.length);
					// first arg can be all
					// if we only have two args its possible we have multiple sounds with no spaces
					/* **** a mixture of spaces between commas will break this **** */
					if (args.length == 2 && args[0] == "all") {
						// split into sounds since there are no spaces
						sounds = args[i].split("\\,");
					} else {
						// remove any appended commas
						sounds = args[i].split("\\,");
					}
					
					//how many sounds are there to play
					// debug code - p.sendMessage("sounds length: "+sounds.length);
					
					// loop through all of the sounds
					for ( int ii = 0; ii < sounds.length; ii++) {
						
						// separate the sound parts
						String[] parts = sounds[ii].split("\\-");
						// debug code - p.sendMessage("parts length: "+parts.length);
						
						// if there are not 4 sound parts we skip this one
						/* **** maybe we should create an error for outputting later **** */
						if (parts.length != 4) continue;
						
						playDelaySound(
							all, 
							p, 
							Sound.valueOf(parts[0]), 
							Float.valueOf(parts[1]), 
							Float.valueOf(parts[2]), 
							Integer.parseInt(parts[3])
						);
						// debug code - p.sendMessage("played sound "+parts[0]);
					}
				}
			}
			
			return true;
		}
		
		
		// command /shotsounds
		if (cmd.getName().equalsIgnoreCase("shotsounds") && sender instanceof Player) {
			int page = 1;
			
			if (args.length != 0) page = Integer.parseInt(args[0]); // what page did user enter
	
			SortedMap<Integer, String> map = new TreeMap<Integer, String>(); // Collections.reverseOrder()
			//map.put(1, "Thing");
		    
			for(int i = 0; i < Sound.values().length; i++) 
				map.put(i, Sound.values()[i].name());
		    			
		    paginate(sender, map, page, 10);
		    
		    return true;
		}
		
		return false;	
	}
	
	
	/*
	 * play delayed sound in ticks
	 */
	public void playDelaySound(final boolean all, final Player player, final Sound s, final float v, final float p, int d) {
		Bukkit.getServer().getScheduler().runTaskLater(this, new Runnable(){
			public void run(){
				Location l = player.getLocation();
				World w = l.getWorld();

				// if not all play sound for player else play for all around player
				if (!all) player.playSound(l, s, v, p);
				else w.playSound(l, s, v, p);
			}
		},d); // run code in run() after ticksToWait ticks
	}
	
	
	/*
	 * pagination function for outputting lists to chat in pages
	 */
	public  void paginate(CommandSender sender, SortedMap<Integer, String> map, int page, int pageLength) {
	    sender.sendMessage(ChatColor.YELLOW + "List: Page (" + String.valueOf(page) + " of " + (((map.size() % pageLength) == 0) ? map.size() / pageLength : (map.size() / pageLength) + 1));
	    int i = 0, k = 0;
	    page--;
	    for (final Entry<Integer, String> e : map.entrySet()) {
	        k++;
	        if ((((page * pageLength) + i + 1) == k) && (k != ((page * pageLength) + pageLength + 1))) {
	            i++;
	            sender.sendMessage(ChatColor.YELLOW + " - " + e.getValue());
	        }
	    }
	}
}
