package com.trafalcraft.drawMyThing;



import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.trafalcraft.drawMyThing.Data.Joueurs;
import com.trafalcraft.drawMyThing.controller.ArenasControll;
import com.trafalcraft.drawMyThing.controller.PlayerControll;
import com.trafalcraft.drawMyThing.timer.LobbyCooldown;
import com.trafalcraft.drawMyThing.utils.Location_String;
import com.trafalcraft.drawMyThing.utils.Msg;
import com.trafalcraft.drawMyThing.Listeners;



public class Main extends JavaPlugin{
	
	public static Main instance;
	private PlayerControll pc;
	private ArenasControll ac;
	private LobbyCooldown lc;
	private Joueurs j;
	
	public void onEnable(){
		instance = this;
        this.getLogger().info("Draw My Thing chargé");
        this.getLogger().info("le serveur tourne en " + Bukkit.getServer().getVersion());
        pc = new PlayerControll();
        ac = new ArenasControll();
        lc = new LobbyCooldown();
        
		Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), this);
		
		instance.getConfig().options().copyDefaults(true);
		instance.saveDefaultConfig();
		instance.reloadConfig();
		for(String aname : instance.getConfig().getConfigurationSection("arenes").getKeys(false)){
			if(!ac.contains(aname)){
				try{
				ac.addMap(aname);
				ac.getArena(aname).setMaxPlayers(instance.getConfig().getInt("arenes."+aname+".max-joueur"));
				ac.getArena(aname).setMinPlayers(instance.getConfig().getInt("arenes."+aname+".min-joueur"));
				ac.getArena(aname).setLobbyCooldown(instance.getConfig().getInt("arenes."+aname+".lobbyCooldown"));
				ac.getArena(aname).setDrawCooldown(instance.getConfig().getInt("arenes."+aname+".drawCooldown"));
				ac.getArena(aname).setDrawerSpawn(Location_String.StringToLoc(instance.getConfig().getString("arenes."+aname+".spawns.Dessinateur")));
				ac.getArena(aname).setSpecSpawn(Location_String.StringToLoc(instance.getConfig().getString("arenes."+aname+".spawns.spec")));	
				ac.getArena(aname).setTopBoard(Location_String.StringToLoc(instance.getConfig().getString("arenes."+aname+".tableau.haut")));
				ac.getArena(aname).setBottomBoard(Location_String.StringToLoc(instance.getConfig().getString("arenes."+aname+".tableau.bas")));	
				this.getLogger().info("Map "+aname+" chargé avec succes");
				}catch(Exception e){
					this.getLogger().warning("Une erreur est survenu pendant le chargement de l'arene: "+aname);
				}
			}
		}
	}
	public void onDisable(){
		
	}

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[]args){
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("DrawMyThing")){
				if(args.length == 0){
				Msg.getHelp(p);
				return true;
				}
				if(args[0].equalsIgnoreCase("help")){
					Msg.getHelp(p);
					return true;
				}
				if(p.isOp() && pc.getPlayer(p.getUniqueId()) == null){
					if(args.length == 0){
						if(args[0].equalsIgnoreCase("reload")){
							instance.reloadConfig();
							p.sendMessage(Msg.Prefix.toString()+Msg.Reload_Ok);
							return true;
						}	
					}
					if(args[0].equalsIgnoreCase("listarena")){
						p.sendMessage(ac.getAll()+"");
						return true;
					}
					if(args.length == 2){
						if(args[0].equalsIgnoreCase("createarena")){
							if(ac.getArena(args[1]) != null){
								p.sendMessage("L'arene existe déja");
								return false;
							}
							ac.addMap(args[1]);
							p.sendMessage(Msg.Prefix.toString()+"succes");
							return true;
						}
						if(args[0].equalsIgnoreCase("setDrawerSpawn")){
							if(ac.getArena(args[1]) == null){
								p.sendMessage(Msg.Prefix+"l'arene "+args[1]+ " n'existe pas");
								return false;
							}
							ac.getArena(args[1]).setDrawerSpawn(p.getLocation());
							p.sendMessage(Msg.Prefix.toString()+"succes");
							return true;
						}
						if(args[0].equalsIgnoreCase("setSpecSpawn")){
							if(ac.getArena(args[1]) == null){
								p.sendMessage(Msg.Prefix+"l'arene "+args[1]+ " n'existe pas");
								return false;
							}
							ac.getArena(args[1]).setSpecSpawn(p.getLocation());
							p.sendMessage(Msg.Prefix.toString()+"succes");
							return true;
						}
						if(args[0].equalsIgnoreCase("setTopBoard")){
							if(ac.getArena(args[1]) == null){
								p.sendMessage(Msg.Prefix+"l'arene "+args[1]+ " n'existe pas");
								return false;
							}
							ac.getArena(args[1]).setTopBoard(p.getTargetBlock((Set<Material>) null, 100).getLocation());
							p.sendMessage(Msg.Prefix.toString()+"succes");
							return true;
						}
						if(args[0].equalsIgnoreCase("setBottomBoard")){
							if(ac.getArena(args[1]) == null){
								p.sendMessage(Msg.Prefix+"l'arene "+args[1]+ " n'existe pas");
								return false;
							}
							ac.getArena(args[1]).setBottomBoard(p.getTargetBlock((Set<Material>) null, 100).getLocation());
							p.sendMessage(Msg.Prefix.toString()+"succes");
							return true;
						}
						if(args[0].equalsIgnoreCase("savearena")){
							instance.reloadConfig();
							p.sendMessage(Msg.Prefix.toString()+ac.saveArena(args[1]));
							return true;
						}
						if(args[0].equalsIgnoreCase("join")){
							if(ac.getArena(args[1]) == null){
								p.sendMessage(Msg.Prefix+"l'arene "+args[1]+ " n'existe pas");
								return false;
							}
							pc.addPlayer(p.getUniqueId(), args[1]);
							
							p.sendMessage("§atu a bien rejoins le DrawMyThing ");
							return true;
						}
					}
					if(args.length == 3){
						if(args[0].equalsIgnoreCase("setMaxPlayers")){
							int maxp;
							if(ac.getArena(args[1]) == null){
								p.sendMessage(Msg.Prefix+"l'arene "+args[1]+ " n'existe pas");
								return false;
							}
							try {
								maxp = Integer.parseInt(args[2]);
								ac.getArena(args[1]).setMaxPlayers(maxp);
								p.sendMessage(Msg.Prefix.toString()+Msg.max_players.toString().replace("$max-players", args[2]));
								return true;
							}
							catch (NumberFormatException nfe) { 
								p.sendMessage(Msg.ERREUR + "texte a creer");						
							return false;
							}
						}
						if(args[0].equalsIgnoreCase("setMinPlayers")){
							if(ac.getArena(args[1]) == null){
								p.sendMessage(Msg.Prefix+"l'arene "+args[1]+ " n'existe pas");
								return false;
							}
							int minp;
							try {
								minp = Integer.parseInt(args[2]);
								ac.getArena(args[1]).setMinPlayers(minp);
								p.sendMessage(Msg.Prefix.toString()+Msg.min_players.toString().replace("$min-players", args[2]));
								return true;
							}
							catch (NumberFormatException nfe) { 
								p.sendMessage(Msg.ERREUR + "texte a creer");	
								return false;
							}
						}
						if(args[0].equalsIgnoreCase("setLobbyCooldown")){
							if(ac.getArena(args[1]) == null){
								p.sendMessage(Msg.Prefix+"l'arene "+args[1]+ " n'existe pas");
								return false;
							}
							int LobbyCooldown;
							try {
								LobbyCooldown = Integer.parseInt(args[2]);
								ac.getArena(args[1]).setLobbyCooldown(LobbyCooldown);
								p.sendMessage(Msg.Prefix.toString()+Msg.lobbyCooldown.toString().replace("$lobbyCooldown", args[2]));
								return true;
							}
							catch (NumberFormatException nfe) { 
								p.sendMessage(Msg.ERREUR + "texte a creer");	
								return false;
							}
						}
						if(args[0].equalsIgnoreCase("drawCooldown")){
							if(ac.getArena(args[1]) == null){
								p.sendMessage(Msg.Prefix+"l'arene "+args[1]+ " n'existe pas");
								return false;
							}
							int drawCooldown;
							try {
								drawCooldown = Integer.parseInt(args[2]);
								ac.getArena(args[1]).setLobbyCooldown(drawCooldown);
								p.sendMessage(Msg.Prefix.toString()+Msg.drawCooldown.toString().replace("$drawCooldown", args[2]));
								return true;
							}
							catch (NumberFormatException nfe) { 
								p.sendMessage(Msg.ERREUR + "texte a creer");	
								return false;
							}
						}
					}
				}
				if(args[0].equalsIgnoreCase("leave")){
					if(Main.getPC().getPlayer(p.getUniqueId()).getArena() == null){
						p.sendMessage("§atu n'est pas dans une arène");
						return false;
					}
					pc.removePlayer(p.getUniqueId());
					p.sendMessage("§atu a bien quitté le DrawMyThing");
					return true;
				}
				if(args[0].equalsIgnoreCase("info")){
/*					p.sendMessage(pc.getAll()+";");
					p.sendMessage(pc.getPlayer(p.getUniqueId())+";");
					p.sendMessage(pc.getPlayerByName(p.getName())+";");
					p.sendMessage("--");*/
					if(pc.getPlayer(p.getUniqueId()) != null){

						p.sendMessage("§4getName §r"+pc.getPlayer(p.getUniqueId()).getName()+"");
						p.sendMessage("§4getStatus §r"+pc.getPlayer(p.getUniqueId()).getStatus()+"");
						p.sendMessage("§4getCouleur §r"+pc.getPlayer(p.getUniqueId()).getCouleur()+"");
						p.sendMessage("§4getPlayer §r"+pc.getPlayer(p.getUniqueId()).getPlayer()+"");
						p.sendMessage("§4getScore §r"+pc.getPlayer(p.getUniqueId()).getScore()+"");
						p.sendMessage("§4getUUID §r"+pc.getPlayer(p.getUniqueId()).getuuid()+"");
						p.sendMessage("§4getArena §r"+pc.getPlayer(p.getUniqueId()).getArena()+"");
						p.sendMessage("§aVoici vos infos du DrawMyThing §6/dmt info");
						Main.getAC().getArena(Main.getPC().getPlayer(p.getUniqueId()).getArena()).stopLobbyTimer();
						//getRunLobbyCooldown().stopLobbyCooldown();
						
					}else{
						p.sendMessage("§atu n'a pas rejoins le DrawMyThing §6/dmt info");
					}
					return true;
				}
				if(args[0].equalsIgnoreCase("areneinfo")){
/*					p.sendMessage(pc.getAll()+";");
					p.sendMessage(pc.getPlayer(p.getUniqueId())+";");
					p.sendMessage(pc.getPlayerByName(p.getName())+";");
					p.sendMessage("--");*/
					if(ac.getArena("test") != null){
					/*	p.sendMessage("§4getName §r"+ac.getArena("test").getName()+"");
						p.sendMessage("§4getSize §r"+ac.getArena("test").playerSize()+"");					
						p.sendMessage("§4getMinPlayers §r"+ac.getArena("test").getMinPlayers()+"");
						p.sendMessage("§4getMaxPlayers §r"+ac.getArena("test").getMaxPlayers()+"");
						p.sendMessage("§4getStatus §r"+ac.getArena("test").getstatus()+"");
						p.sendMessage("§4getDrawer §r"+ac.getArena("test").getDrawer()+"");
						p.sendMessage("§aVoici vos infos du DrawMyThing §6/dmt info");
						*/
						Main.getAC().getArena(Main.getPC().getPlayer(p.getUniqueId()).getArena()).startLobbyTimer();
						//getRunLobbyCooldown().runLobbyCooldown("test");
					}else{
						p.sendMessage("§apas d'arene test");
					}
					return true;
				}
			}
			p.sendMessage("commande raté");
		}
		return false;
	}
	
	public static Main getinstance(){
		return instance;
	}
	
	public static PlayerControll getPC(){
		return instance.pc;
	}
	public static ArenasControll getAC(){
		return instance.ac;
	}
	public Joueurs getJoueurs(){
		return instance.j;
	}
	public LobbyCooldown getRunLobbyCooldown(){
		return instance.lc;
	}
}
