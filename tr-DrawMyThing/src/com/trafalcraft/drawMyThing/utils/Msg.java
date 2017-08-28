package com.trafalcraft.drawMyThing.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.trafalcraft.drawMyThing.Main;

public enum Msg {
	
	Prefix("§bDrawMyThing §9§l> §r§b "),
	ERREUR("§4Erreur §l> §r§c "),
	NO_PERMISSIONS("§4Erreur §9§l> §r§bVous n'avez pas la permission de faire sa."),
	Command_Use("§4Erreur §l> §r§cutilisation de la commande: §6/sw "),
	
	//ERREUR
	String_to_int("La valeur du spawn doit etre numérique et comprise entre 0 et $MaxPlayers"),
    set_size_Wb("La valeur du worldborder doit etre numérique et comprise entre 1 et 32 767"),
    classes_no_exist("La classe $classe n'existe pas"),
	Erreur_JOIN("Vous êtes déja en jeux, utilisé $cmd pour quitter"),
	Erreur_setBoard("Le bloc visé doit etre de la laine"),
	
	//setup
    Reload_Ok("Reload Ok!"),
    suppr_spawn_success("Le spawn $spawn a bien été supprimé, il reste: $rspawn spawn"),
    set_spawn_success("Le spawn du déssinateur a bien été configuré"),
    spawn_spec("Le spawn des spectateurs a bien été configuré"),
    set_wb("Le WorldBorder est bien configuré, le spectateur sera de la même taille"),
    setclasses("La classe $classe §ra bien été sauvegardé"),
    setBoard("Le point du tableau a bien été configuré"),
    min_players("Le nombre minimum de joueur dans l'arene est maintenant de $min-players"),
    max_players("Le nombre maximum de joueur dans l'arene est maintenant de $max-players"),
    lobbyCooldown("Le temp d'attente au lobby est maintenant de $lobbyCooldown"),
    drawCooldown("Le temp qu'a le dessinateur pour faire sont dessin est maintenant de $drawCooldown"),
    
    
    //jeux
    ARENA_START("La partie commence !"),
    Bienvenue("Bienvenue dans DrawMyThing"),
    Timer("La partie commence dans $temps secondes!"),
    Arret_Timer("Un joueur a quitté compte à rebours annulé!"),
    Join("$joueur a rejoint ($njoueursIG/$njoueurmax)");
	
	
	static JavaPlugin plugin = Main.instance;
	  public static void getHelp(Player sender){
	        sender.sendMessage("");
	        sender.sendMessage("§3§l-------------------SkyWars-------------------");
	        sender.sendMessage("§3/sw setup <nom de l'arene> §b- crée l'arène.");
	        sender.sendMessage("§3/sw spawn<numero> §b- Configurer le lieu de spawn des joueurs.");
	        sender.sendMessage("                       §3Version: §6" + plugin.getDescription().getVersion());
	        sender.sendMessage("§3------------------------------------------------");
	        sender.sendMessage("");
	        Bukkit.getLogger().info("\u001B[31m" + sender.getName() + "\u001B[36m" + " a regarde la page d'aide." + "\u001B[0m");
		  }
	  
	    private String value;

		private Msg(String value) {
			this.value = value;
	        //set(value);
	    }
	    public String toString(){
	    	return value;
	    }
		
/*	    void set(String value) {
	        //this.value = value;
	    }
*/
}
