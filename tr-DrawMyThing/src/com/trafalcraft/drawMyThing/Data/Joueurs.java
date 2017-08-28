package com.trafalcraft.drawMyThing.Data;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Joueurs {

	
	private String pname = null;
	private UUID uuid = null;
	private String aname = null;
	private int pscore = 0;
	private String pstatus = "hors-jeux";
	private byte pcouleur = 0;
	private Boolean pdrawer = false;
	
	public Joueurs(UUID uuid){
		this.uuid = uuid;
		pname = getPlayer().getName();
	}
	
	public void setName(String pname){
		this.pname = pname;
	}
	
	public String getName(){
		return pname;
	}
	
	public Player getPlayer(){
		return Bukkit.getServer().getPlayer(uuid);
	}
	
	public UUID getuuid(){
		return uuid;
	}
	
	public void setArena(String aname){
		this.aname = aname;
	}
	
	public String getArena(){
		return aname;
	}
	
	public void setScore(int pscore){
		this.pscore = pscore;
	}
	
	public int getScore(){
		return pscore;
	}
	
	public void setStatus(String pstatus){
		this.pstatus = pstatus;
	}
	
	public String getStatus(){
		return pstatus;
	}
	
	public void setCouleur(byte pcouleur){
		this.pcouleur = pcouleur;
	}
	
	public byte getCouleur(){
		return pcouleur;
	}
	
	public void setDrawer(Boolean pdrawer){
		this.pdrawer = pdrawer;
	}
	
	public boolean isDrawer(){
		return pdrawer;
	}
}
