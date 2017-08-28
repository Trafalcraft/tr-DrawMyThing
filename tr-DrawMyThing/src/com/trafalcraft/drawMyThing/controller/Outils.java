package com.trafalcraft.drawMyThing.controller;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.trafalcraft.drawMyThing.Main;
import com.trafalcraft.drawMyThing.Data.Arenas;
import com.trafalcraft.drawMyThing.Data.Joueurs;


public class Outils {
	
	private static ArrayList<Block> BList = new ArrayList<Block>();
	private static String world;
    private static Arenas aname;
    private static Block bt;
    private static Block b1;
    private static Block b2;
    private static Block b3;
    private static Block b4;
    private static int yP;
    private static int yN;
    private static int xP;
    private static int xN;
    private static int zP;
    
	@SuppressWarnings("deprecation")
	public static void traiFin(Joueurs gPlayer) {
		Block b = gPlayer.getPlayer().getTargetBlock((Set<Material>) null, 100);
  		if(b.getType().getId() == gPlayer.getCouleur()){
  			return;
  		}else{
        	b.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
        	gPlayer.getPlayer().playSound(gPlayer.getPlayer().getLocation(), Sound.BAT_TAKEOFF, 1.0F, 1.0F);
  		}
	}
    
    
	@SuppressWarnings("deprecation")
	public static void clear(Joueurs gPlayer){
		      Block b  = gPlayer.getPlayer().getTargetBlock((Set<Material>) null, 100);
		      aname = Main.getAC().getArena(gPlayer.getArena());
		      world = gPlayer.getPlayer().getWorld().getName();
		      World w = Bukkit.getWorld(world);
		      
		      if(aname.getBottomBoard().getX() == aname.getTopBoard().getX()){
		    	  zP = (int) aname.getBottomBoard().getX();
		    	  
			      if(aname.getBottomBoard().getY() < aname.getTopBoard().getY()){
			    	  yP = (int) aname.getBottomBoard().getY();
			    	  yN = (int) aname.getTopBoard().getY();
			      }else{
			    	  yP = (int) aname.getTopBoard().getY();
				      yN = (int) aname.getBottomBoard().getY();
			      }
			      if(aname.getBottomBoard().getZ() < aname.getTopBoard().getZ()){
			    	  xP = (int) aname.getBottomBoard().getZ();
			    	  xN = (int) aname.getTopBoard().getZ();
			      }else{
			    	  xP = (int) aname.getTopBoard().getZ();
				      xN = (int) aname.getBottomBoard().getZ();
			      }
		      		for(int i = yP-1; i <= yN; i++){
		      			  for(int f = (int) xP-1;  f <= xN ; f++){
		      				    Block bt = w.getBlockAt(zP ,i, f);
				            	bt.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
		      			  }
		      			}
			      
		      }else if(aname.getBottomBoard().getY() == aname.getTopBoard().getY()){
		    	  zP = (int) aname.getBottomBoard().getY();
			      if(aname.getBottomBoard().getX() < aname.getTopBoard().getX()){
			    	  xP = (int) aname.getBottomBoard().getX();
			    	  xN = (int) aname.getTopBoard().getX();
			      }else{
				      xP = (int) aname.getTopBoard().getX();
				      xN = (int) aname.getBottomBoard().getX();
			      }
			      if(aname.getBottomBoard().getZ() < aname.getTopBoard().getZ()){
			    	  yP = (int) aname.getBottomBoard().getZ();
			    	  yN = (int) aname.getTopBoard().getZ();
			      }else{
				      yP = (int) aname.getTopBoard().getZ();
				      yN = (int) aname.getBottomBoard().getZ();
			      }
		      		for(int i = yP-1; i <= yN; i++){
		      			  for(int f = (int) xP-1;  f <= xN ; f++){
		      				    Block bt = w.getBlockAt(f ,zP, i);
				            	bt.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
		      			  }
		      			}
		      }else{
			      if(aname.getBottomBoard().getX() < aname.getTopBoard().getX()){
			    	  xP = (int) aname.getBottomBoard().getX();
			    	  xN = (int) aname.getTopBoard().getX();
			      }else{
				      xP = (int) aname.getTopBoard().getX();
				      xN = (int) aname.getBottomBoard().getX();
			      }
			      if(aname.getBottomBoard().getY() < aname.getTopBoard().getY()){
			    	  yP = (int) aname.getBottomBoard().getY();
			    	  yN = (int) aname.getTopBoard().getY();
			      }else{
			    	  yP = (int) aname.getTopBoard().getY();
				      yN = (int) aname.getBottomBoard().getY();
			      }
		    	  zP = (int) aname.getBottomBoard().getZ();
		      		for(int i = yP-1; i <= yN; i++){
		      			  for(int f = (int) xP-1;  f <= xN ; f++){
		      				    Block bt = w.getBlockAt(f ,i, zP);
				            	bt.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
		      			  }
		      			}
		      }
		      if(b.getType() != Material.WOOL){
		    	  return;
		      }
					     
		      gPlayer.getPlayer().playSound(gPlayer.getPlayer().getLocation(), Sound.SPLASH, 1.0F, 1.0F);
		 
	}


	@SuppressWarnings("deprecation")
	public static void traitEpais(Joueurs gPlayer) {
	      world = gPlayer.getPlayer().getWorld().getName();
	      World w = Bukkit.getWorld(world);
	      Block b = gPlayer.getPlayer().getTargetBlock((Set<Material>) null, 100);
	      Location tloc = b.getLocation();
	      double X = tloc.getX();
	      double Y = tloc.getY();
	      double Z = tloc.getZ();
	      double Xplus = tloc.getX()+1;
	      double Xmoins = tloc.getX()-1;
	      double Yplus = tloc.getY()+1;
	      double Ymoins = tloc.getY()-1;
	      double Zplus = tloc.getZ()+1;
	      double Zmoins = tloc.getZ()-1;
	      Block bt = w.getBlockAt((int) X ,(int)Y ,(int) Z);
	      Block bt1 = w.getBlockAt((int) Xplus ,(int)Y ,(int) Z);
	      Block bt2 = w.getBlockAt((int) Xmoins ,(int)Y ,(int) Z);
	      Block bt3 = w.getBlockAt((int) X ,(int)Yplus ,(int) Z);
	      Block bt4 = w.getBlockAt((int) X ,(int)Ymoins ,(int) Z);
	      Block bt5 = w.getBlockAt((int) X ,(int)Y ,(int) Zplus);
	      Block bt6 = w.getBlockAt((int) X ,(int)Y ,(int) Zmoins);
		      if(bt.getType() == Material.WOOL){
	    	  bt.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
	      }
	      if(bt1.getType() == Material.WOOL){
	    	  bt1.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
	      }
	      if(bt2.getType() == Material.WOOL){
	    	  bt2.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
	      }
	      if(bt3.getType() == Material.WOOL){
	    	  bt3.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
	      }
	      if(bt4.getType() == Material.WOOL){
	    	  bt4.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
	      }
	      if(bt5.getType() == Material.WOOL){
	    	  bt5.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
	      }
	      if(bt6.getType() == Material.WOOL){
	    	  bt6.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
	      }

	      if(b.getType() != Material.WOOL){
	    	  return;
	      }
	      	if(gPlayer.getCouleur() <= 15 && gPlayer.getCouleur() >= 0){
	      		if(b.getType().getId() == gPlayer.getCouleur()){
	      			
	      			}else{
	            	b.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
	            	gPlayer.getPlayer().playSound(gPlayer.getPlayer().getLocation(), Sound.BAT_TAKEOFF, 1.0F, 1.0F);
	      		}
	      	}
	 }


	@SuppressWarnings("deprecation")
	public static void clearZone(Joueurs gPlayer) {
        Block b = gPlayer.getPlayer().getTargetBlock((Set<Material>) null, 100);
        int bb = b.getData();
        if (bb != gPlayer.getCouleur())
        {
          if (b.getType() != Material.WOOL) {
            return;
          }
          world = gPlayer.getPlayer().getWorld().getName();
          World w = Bukkit.getWorld(world);
          BList.add(b);
          b.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
          int beugstop = 0;
          while (BList.size() > 0)
          {
            beugstop++;
            if (beugstop > 100) {
              break;
            }
            for (int i = 0; i < BList.size(); i++)
            {
              if (i > 400) {
                break;
              }
              bt = ((Block)BList.get(i));
              BList.remove(i);
              int X = bt.getX();
              int Y = bt.getY();
              int Z = bt.getZ();
              if (w.getBlockAt(X, Y + 1, Z).getType() != Material.AIR)
              {
                b1 = w.getBlockAt(X, Y + 1, Z);
                if ((b1.getType() == bt.getType()) && (b1.getData() == bb))
                {
                  b1.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
                  BList.add(b1);
                }
              }
              if (w.getBlockAt(X, Y - 1, Z).getType() != Material.AIR)
              {
                b2 = w.getBlockAt(X, Y - 1, Z);
                if ((b2.getType() == bt.getType()) && (b2.getData() == bb))
                {
                  b2.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
                  BList.add(b2);
                }
              }
              if (w.getBlockAt(X - 1, Y, Z).getType() != Material.AIR)
              {
                b3 = w.getBlockAt(X - 1, Y, Z);
                if ((b3.getType() == bt.getType()) && (b3.getData() == bb))
                {
                  b3.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
                  BList.add(b3);
                }
              }
              if (w.getBlockAt(X, Y, Z - 1).getType() != Material.AIR)
              {
                b3 = w.getBlockAt(X, Y, Z - 1);
                if ((b3.getType() == bt.getType()) && (b3.getData() == bb))
                {
                  b3.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
                  BList.add(b3);
                }
              }
              if (w.getBlockAt(X + 1, Y, Z).getType() != Material.AIR)
              {
                b4 = w.getBlockAt(X + 1, Y, Z);
                if ((b4.getType() == bt.getType()) && (b4.getData() == bb))
                {
                  b4.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
                  BList.add(b4);
                }
              }
              if (w.getBlockAt(X, Y, Z + 1).getType() != Material.AIR)
              {
                b4 = w.getBlockAt(X, Y, Z + 1);
                if ((b4.getType() == bt.getType()) && (b4.getData() == bb))
                {
                  b4.setTypeIdAndData(Material.WOOL.getId(), gPlayer.getCouleur(), true);
                  BList.add(b4);
                }
              }
              b1 = null;
              b2 = null;
              b3 = null;
              b4 = null;
            }
          }
        }
        BList.clear();
        gPlayer.getPlayer().playSound(gPlayer.getPlayer().getLocation(), Sound.SPLASH, 1.0F, 1.0F);
      }
	}

