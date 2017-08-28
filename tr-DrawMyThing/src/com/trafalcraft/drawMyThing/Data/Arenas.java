package com.trafalcraft.drawMyThing.Data;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.trafalcraft.drawMyThing.Main;
import com.trafalcraft.drawMyThing.utils.Msg;

public class Arenas {
	private String aname = null;
	private String astatus = "lobby";
	private Player drawer = null;
	private int maxPlayers = 8;
	private int minPlayers = 2;
	private int LobbyCooldown = 30;
	private int DrawCooldown = 60;
	private Location spawnDessinateur = null;
	private Location spawnSpec = null;
	private Location topBoard = null;
	private Location bottomBoard = null;
	private ArrayList <Player> playerList = new ArrayList<Player>();
	
	private static int taskLobby;
	private int temps;
	
	
	public Arenas(String aname){
		this.aname = aname;
	}
	
	public void setName(String aname){
		this.aname = aname;
	}
	
	public String getName(){
		return aname;
	}
	
	public void setstatus(String astatus){
		this.astatus = astatus;
	}
	
	public String getstatus(){
		return astatus;
	}
	
	public void setDrawer(Player drawer){
		this.drawer = drawer;
	}
	
	public Player getDrawer(){
		return drawer;
	}
	
	public void setMaxPlayers(int maxPlayers){
		this.maxPlayers = maxPlayers;
	}
	
	public int getMaxPlayers(){
		return maxPlayers;
	}
	
	public void setMinPlayers(int minPlayers){
		this.minPlayers = minPlayers;
	}
	
	public int getMinPlayers(){
		return minPlayers;
	}
	
	public void setLobbyCooldown(int secondes){
		this.LobbyCooldown = secondes;
	}
	
	public int getLobbyCooldown(){
		return LobbyCooldown;
	}
	
	public void setDrawCooldown(int secondes){
		this.DrawCooldown = secondes;
	}
	
	public int getDrawCooldown(){
		return DrawCooldown;
	}
	
	public void setDrawerSpawn(Location spawnDessinateur){
		this.spawnDessinateur = spawnDessinateur;
	}
	
	public Location getDrawerSpawn(){
		return spawnDessinateur;
	}
	
	public void setSpecSpawn(Location spawnSpec){
		this.spawnSpec = spawnSpec;
	}
	
	public Location getSpecSpawn(){
		return spawnSpec;
	}
	
	public void setTopBoard(Location topBoard){
		this.topBoard = topBoard;
	}
	
	public Location getTopBoard(){
		return topBoard;
	}
	
	public void setBottomBoard(Location bottomBoard){
		this.bottomBoard = bottomBoard;
	}
	
	public Location getBottomBoard(){
		return bottomBoard;
	}
	public void addPlayer(Player player){
		playerList.add(player);
	}
	
	public boolean containsPlayer(Player player){
		if(playerList.contains(player)){
			return true;
		}
		return false;
	}
	
	public int playerSize(){
		return 	playerList.size();
	}
	
	public void removePlayer(Player player){
		playerList.remove(player);
	}
	
	public void startLobbyTimer(){
		temps = 30;
		taskLobby = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getinstance(), new Runnable() {
            public void run() {
            	for(Player pl : Bukkit.getOnlinePlayers()){
                	if(Main.getAC().getArena(aname).containsPlayer(pl)){
//                    	if(lobbyCooldownRun = false){
//                    		Bukkit.getServer().getScheduler().cancelTask(task);
//                    		return;
//                    	}else{
                    		if(temps == 30||temps == 20||temps == 10||(temps <= 5&&temps>0)){
                    			pl.sendMessage(Msg.Prefix+Msg.Timer.toString().replace("$temps", temps+""));
                                pl.playSound(pl.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
                    		}else if(temps <= 0){
                    			pl.playSound(pl.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                    			
                    			pl.sendMessage(Msg.Prefix+Msg.ARENA_START.toString());
                        		Bukkit.getServer().getScheduler().cancelTask(taskLobby);
                    		}
                	}
                }
            	Bukkit.getLogger().info(temps+"");
            	temps = temps-1;
            	Bukkit.getLogger().info(temps+"");
            }
         }, 0, 20);
	}
	
	public void stopLobbyTimer(){
		Bukkit.getServer().getScheduler().cancelTask(taskLobby);
	}
	
	
	
}
