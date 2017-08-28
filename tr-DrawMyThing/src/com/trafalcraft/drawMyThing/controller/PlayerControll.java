package com.trafalcraft.drawMyThing.controller;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import com.google.common.collect.Maps;
import com.trafalcraft.drawMyThing.Main;
import com.trafalcraft.drawMyThing.Data.Joueurs;
import com.trafalcraft.drawMyThing.utils.Msg;

public class PlayerControll {
	private final Map<UUID, Joueurs> onlinePlayers = Maps.newHashMap();
	
	public void addPlayer(UUID uuid, String aname){
		if(!this.onlinePlayers.containsKey(uuid)){
			Player p = Bukkit.getServer().getPlayer(uuid);
			if(!Main.getAC().getArena(aname).getstatus().equalsIgnoreCase("lobby")){
				p.sendMessage(Msg.Prefix+"Une partie est déja lancé");
				return;
			}
			if(Main.getAC().getArena(aname).playerSize() == Main.getAC().getArena(aname).getMaxPlayers()){
				p.sendMessage(Msg.Prefix+"l'arène est déja pleine");
				return;
			}
			Joueurs joueurs = new Joueurs(uuid);
			onlinePlayers.put(uuid, joueurs);
			Main.getPC().getPlayer(uuid).setArena(aname);
			Main.getPC().getPlayer(uuid).setStatus("lobby");
			Main.getAC().addPlayer(aname, Bukkit.getServer().getPlayer(uuid));
			Location specspawn = Main.getAC().getArena(aname).getSpecSpawn();
			p.teleport(specspawn);
			p.getInventory().clear();
			for(Player allp:Bukkit.getOnlinePlayers()){
				if(Main.getPC().getPlayer(allp.getUniqueId()) == null){
					return;
				}
				if(Main.getPC().getPlayer(allp.getUniqueId()).getArena() == aname){
					allp.sendMessage(Msg.Prefix+p.getName()+" à rejoins le DrawMyThing ("+
					Main.getAC().getArena(aname).playerSize()+"/"+
					Main.getAC().getArena(aname).getMaxPlayers()+")");
				}
			}
		}
		
	}
	
	
	
	public void removePlayer(UUID uuid){
		if(this.onlinePlayers.containsKey(uuid)){
			Player p = Bukkit.getServer().getPlayer(uuid);
			String aname = Main.getPC().getPlayer(uuid).getArena();
			Main.getAC().removePlayer(aname, Bukkit.getServer().getPlayer(uuid));
			Main.getPC().getPlayer(uuid).setArena(null);
	        this.onlinePlayers.remove(uuid);
	        if(Main.getAC().getArena(aname).getstatus().equalsIgnoreCase("lobby")){
				for(Player allp:Bukkit.getOnlinePlayers()){
					if(Main.getPC().getPlayer(allp.getUniqueId()) == null){
						return;
					}
					allp.sendMessage(Main.getPC().getPlayer(allp.getUniqueId()).getArena());
					if(Main.getPC().getPlayer(allp.getUniqueId()).getArena() == aname){
						Bukkit.broadcastMessage("test1");
						allp.sendMessage(Msg.Prefix+p.getName()+" à quitté le DrawMyThing ("+
					Main.getAC().getArena(aname).playerSize()+"/"+
					Main.getAC().getArena(aname).getMaxPlayers()+")");
					}
				}
	        }
		}
	}
	
	public Joueurs getPlayer(UUID uuid){
		return onlinePlayers.get(uuid);
	}
	
	public Joueurs getPlayerByName(String name){
    	for (Joueurs gPlayer: onlinePlayers.values()) {
    		if (gPlayer.getName().equals(name)) {
    			return gPlayer;
    		}
    	} 
    	return null;   
	}
	
    public Collection<Joueurs> getAll() {
        return onlinePlayers.values();
    }
}
