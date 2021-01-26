package krn.kGuardian.eventuri;

import org.apache.commons.lang.ObjectUtils.Null;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;
import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
import com.gmail.filoghost.holographicdisplays.api.line.HologramLine;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import com.google.common.base.Objects;
import com.mojang.bridge.game.GameSession;

import krn.kGuardian.Game_Logic;
import krn.kGuardian.Game_Starter;
import krn.kGuardian.Main;
import krn.kGuardian.kUtils;
import net.minecraft.server.v1_16_R3.EntityFox.i;

public class krane_EventHandler implements Listener {
	
	private Main pluginMain;
	private Player player;
	private Game_Starter game_Starter;
	private Game_Logic game_Logic;
	private kUtils kUtils;
	
	public krane_EventHandler(Game_Starter game) {
	
		this.player = game.playerEntity;
		this.game_Starter = game;
		this.pluginMain = game.plugin;
		this.kUtils = game.kUtils;
	}
	
	
	Hologram hologram;
	boolean graphics_inited = false;
	
	@EventHandler
	public void onPlayerExit(PlayerQuitEvent event)
	{
		hologram.delete();
		game_Logic.remove_all_mobs();
		game_Starter.IN_GAME = false;
		
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		
		/* identifier pe locatia de la block + numele blockului */
//		System.out.println(event.getAction().name() + " pe " + event.getClickedBlock().toString());
		//HolographicDisplays
		if(game_Starter.IN_GAME)
		{

				
			try {
				hologram.clearLines();
					
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			hologram.insertTextLine(0, "~Status~");
			hologram.insertTextLine(1, "Mobs Per Wave: 3");
			hologram.insertTextLine(2, "Starting Difficulty: 1");
		
			
			
			if(!graphics_inited)
			{
				Location location = kUtils.get_position_in_front(this.player, -5, false);
				hologram.teleport(this.player.getWorld(), location.getX(), location.getY(), location.getZ());
				graphics_inited = true;
			}
			
			
			

		}
		
		
	}
	
	@EventHandler
	public void onEntityHit(org.bukkit.event.entity.EntityDamageByEntityEvent event)
	{
		
		if(game_Starter.IN_GAME)
		{
			Entity theMobEntity = event.getEntity();
			
			Entity playerEntity = event.getDamager();
		}		
		
		
		
	}
	
	boolean inited = false;
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		
		/* if exit game then everything should be re-inited */
		if(game_Starter.IN_GAME == false) { inited = false;  }

		/* clean up after exiting the game */
		if(game_Starter.IN_GAME == false && inited == false) 
		{
			try {
				for(int i =0; i < game_Logic.mobs.size(); i++)
				{
					game_Logic.mobs.get(i).remove();
				}
			} catch (Exception e) {
			}
		}
		
		
		/* main game logic */
		if(game_Starter.IN_GAME)
		{

			this.player = event.getPlayer();
			
			if(!inited)
			{
				
				Location hologramLocation = this.player.getLocation().add(0, 5, 0);
				hologram = HologramsAPI.createHologram(this.pluginMain, hologramLocation);
				
				game_Logic = new Game_Logic(event.getPlayer());
				game_Logic.New_Round();
				inited = true;
			}
		
			game_Logic.Game_Tracker(event.getPlayer());
			
			
			
			if(game_Logic.GAME_OVER) { game_Starter.IN_GAME = false; inited = false; }
			if(game_Logic.WINNER) { System.out.println("Winner"); }
			
			
		}
	}
	
}
