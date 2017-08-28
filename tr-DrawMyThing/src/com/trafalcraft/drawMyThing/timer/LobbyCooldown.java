package com.trafalcraft.drawMyThing.timer;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.trafalcraft.drawMyThing.Main;
import com.trafalcraft.drawMyThing.utils.Msg;

public class LobbyCooldown {
	private JavaPlugin plugin = Main.instance;
	private int LobbyTask;
    private int temps = 30;
    private boolean lobbyCooldownRun;
 //   private String lobbyCooldownMap;
	
	
	private boolean lobbyCooldown(final String aname){
		temps = 30;
		LobbyTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {
            	for(Player pl : Bukkit.getOnlinePlayers()){
                	if(Main.getAC().getArena(aname).containsPlayer(pl)){
                    	if(lobbyCooldownRun = false){
                    		Bukkit.getServer().getScheduler().cancelTask(LobbyTask);
                    		return;
                    	}else{
                    		if(temps == 30||temps == 20||temps == 10||(temps <= 5&&temps>0)){
                    			pl.sendMessage(Msg.Prefix+Msg.Timer.toString().replace("$temps", temps+""));
                                pl.playSound(pl.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
                    		}else if(temps <= 0){
                    			pl.playSound(pl.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                    			
                    			pl.sendMessage(Msg.Prefix+Msg.ARENA_START.toString());
                        		Bukkit.getServer().getScheduler().cancelTask(LobbyTask);
                    		}
                    	}
                	}
                }
            	Bukkit.getLogger().info(temps+"");
            	temps = temps-1;
            	Bukkit.getLogger().info(temps+"");
            }
         }, 0, 20);
	return false;
	}
	
	
	public void runLobbyCooldown(String aname){
		lobbyCooldownRun = true;
	//	this.lobbyCooldownMap = aname;
		lobbyCooldown(aname);
	}
	public void stopLobbyCooldown(){
		lobbyCooldownRun = false;
	}
}
