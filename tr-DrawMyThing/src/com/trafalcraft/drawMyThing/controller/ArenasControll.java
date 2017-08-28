package com.trafalcraft.drawMyThing.controller;

import java.util.Collection;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Maps;
import com.trafalcraft.drawMyThing.Main;
import com.trafalcraft.drawMyThing.Data.Arenas;
import com.trafalcraft.drawMyThing.utils.Location_String;

public class ArenasControll {
	private JavaPlugin plugin = Main.instance;
	private final Map<String, Arenas> activeMap = Maps.newHashMap();


	public void addMap(String name){
		if(!this.activeMap.containsKey(name)){
			Arenas arene = new Arenas(name);
			activeMap.put(name, arene);
		}
	}
	
	public boolean contains(String aname){
		if(this.activeMap.containsKey(aname)){
			return true;
		}
		return false;
	}
	
	public void removeMap(String name){
		if(this.activeMap.containsKey(name)){
			activeMap.remove(name);
		}
	}
	
	public Arenas getArena(String name){
		return activeMap.get(name);
	}
	
	public void addPlayer(String aname, Player player){
		if(player.isOnline()){
			activeMap.get(aname).addPlayer(player);
			int playerSize = activeMap.get(aname).playerSize();
			if(playerSize > activeMap.get(aname).getMinPlayers()){
				
			}
		}else{
			plugin.getLogger().warning("Le joueur "+player+" n'est pas connecté");
		}
	}
	
	public boolean containsPlayer(String aname, Player player){
		return activeMap.get(aname).containsPlayer(player);
	}
	
	public int size(String aname){
		return activeMap.size();
	}
	
	public void removePlayer(String aname, Player player){
		if(player.isOnline()){
			if(activeMap.get(aname).containsPlayer(player)){
				activeMap.get(aname).removePlayer(player);
			}else{
				plugin.getLogger().warning("l'arène "+aname+" ne contient pas le joueur" + player.getName());
			}
		}else{
			plugin.getLogger().warning("Le joueur "+player+" n'est pas connecté");
		}
	}

    public Collection<Arenas> getAll() {
        return activeMap.values();
    }
    
	public String saveArena(String name) {
		Arenas arene = activeMap.get(name);
		if(arene != null){
			String aname = activeMap.get(name).getName();
			try{
				plugin.getConfig().set("arenes."+aname+".max-joueur", arene.getMaxPlayers());
				plugin.getConfig().set("arenes."+aname+".min-joueur", arene.getMinPlayers());
				plugin.getConfig().set("arenes."+aname+".lobbyCooldown", arene.getLobbyCooldown());
				plugin.getConfig().set("arenes."+aname+".drawCooldown", arene.getDrawCooldown());
				plugin.getConfig().set("arenes."+aname+".spawns.Dessinateur", Location_String.LocationToString(arene.getDrawerSpawn()));
				plugin.getConfig().set("arenes."+aname+".spawns.spec", Location_String.LocationToString(arene.getSpecSpawn()));
				plugin.getConfig().set("arenes."+aname+".tableau.haut", Location_String.LocationToString(arene.getTopBoard()));
				plugin.getConfig().set("arenes."+aname+".tableau.bas", Location_String.LocationToString(arene.getBottomBoard()));
				plugin.saveConfig();
				return "success";
			}catch(Exception e){
				return "echec";
			}
		}
		return "Vous devez d'abord créer une map avec /dmt create <nom de l'arène>";
	}
}
