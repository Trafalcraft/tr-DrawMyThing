package com.trafalcraft.drawMyThing;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.trafalcraft.drawMyThing.Data.Joueurs;
import com.trafalcraft.drawMyThing.controller.Outils;
//import com.trafalcraft.drawMyThing.controller.PlayerControll;

public class Listeners implements Listener{

//	  private PlayerControll pControll;

		@EventHandler (priority = EventPriority.HIGH)
		  public void onPlayerLeave(PlayerQuitEvent e){
			Player p = e.getPlayer();
			Main.getPC().removePlayer(p.getUniqueId());
		}
	  

		@EventHandler
		public void inventoryClick(InventoryClickEvent e){
			Player p = (Player) e.getWhoClicked();
			//if(Main.getPC().getPlayer(p.getUniqueId()).isDrawer()){
			Main.getPC().getPlayer(p.getUniqueId()).setCouleur((byte) e.getRawSlot());
			e.getWhoClicked().closeInventory();
			e.setCancelled(true);
//					}
			   }
		
		
	 @EventHandler (priority = EventPriority.LOWEST)
	  public void onInteract(PlayerInteractEvent e){
			Player p = e.getPlayer();
			if (e.getPlayer().getItemInHand().getType() == Material.AIR ){
				return;
			}
			if(e.getPlayer().getTargetBlock((Set<Material>) null, 100).getType() != Material.WOOL){
				return;
			}
			Joueurs gPlayer = Main.getPC().getPlayer(p.getUniqueId());
/*			if(e.getPlayer().getTargetBlock((Set<Material>) null, 100).getX()<xP
					||e.getPlayer().getTargetBlock((Set<Material>) null, 100).getX()>xN
					||e.getPlayer().getTargetBlock((Set<Material>) null, 100).getY()<yP
					||e.getPlayer().getTargetBlock((Set<Material>) null, 100).getY()>yN
					||e.getPlayer().getTargetBlock((Set<Material>) null, 100).getZ()!=zP){
					return;
				}*/
			Material itemInHand = e.getItem().getType();
			if(gPlayer != null){
				//if(Main.getPC().getPlayer(p.getUniqueId()).isDrawer()){
					//Utiliser les outils
					if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
						if(itemInHand == Material.WOOD_SPADE){
							Outils.traiFin(gPlayer);
						}
						if(itemInHand == Material.STONE_SPADE){
							Outils.traitEpais(gPlayer);
						}
					    if (itemInHand == Material.IRON_SPADE)
					    {
					    	Outils.clearZone(gPlayer); 
					    }
						if(itemInHand == Material.BUCKET){
							Outils.clear(gPlayer);
						}
						return;
					}
					
					//Ouvrir le menu des couleurs
					if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK ){
						if(itemInHand == Material.WOOD_SPADE || itemInHand == Material.IRON_SPADE || itemInHand == Material.BUCKET || itemInHand == Material.STONE_SPADE){
						    Inventory inventory = Bukkit.createInventory(p, 18, "Couleur pinceau");
						    for (int i = 0; i <= 15; i++) {
							    ItemStack wooll = new ItemStack(Material.WOOL, 1, (short)i);
							    inventory.addItem(wooll);
						    }
							    p.openInventory(inventory);
						 }
					}
				//}
				return;
			}
	}
	

}
