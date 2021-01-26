package krn.kGuardian;

import java.security.PublicKey;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.plugin.Plugin;

public class Game_Starter implements CommandExecutor, Listener{
	public Main plugin;
	private Mob_Spawn_Logic mob_Spawn_Logic;
	
	String GAME_WORLD_NAME = "game_world";
	
	public Game_Logic game_Logic;
	public Thread game_thread;
	public kUtils kUtils;
	public Player playerEntity;
	public boolean STOP_GAME = false;
	public boolean IN_GAME = false;
	
	
	public Game_Starter(Main pluginMain)
	{
		this.plugin = pluginMain;
		
		pluginMain.getCommand("kGuardian").setExecutor(this);
		pluginMain.getCommand("exitGuardian").setExecutor(this);

		kUtils = new kUtils();
		
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		IN_GAME = true;
		playerEntity = (Player)sender;

		
		
		System.out.println("[kGuardian] Migrating from " + playerEntity.getWorld().getName() + 
														" to " + GAME_WORLD_NAME);
		
		/* bag muie ca e greu cu multe lumi */
		
//		Bukkit.getServer().createWorld(new WorldCreator(GAME_WORLD_NAME));
		
//		for(int i = 0; i< Bukkit.getServer().getWorlds().size(); i++)
//		{
//			System.out.println(Bukkit.getServer().getWorlds().get(i).getName());
//		}
//		
//		World world = Bukkit.getServer().getWorld(GAME_WORLD_NAME);
//		
		
		World world = playerEntity.getWorld();
		
		
		if(cmd.getName().equals("exitGuardian")) { playerEntity.teleport(Bukkit.getServer().getWorld("world").getSpawnLocation()); STOP_GAME = true; IN_GAME=false; return false;}
		
		
				
		Location game_fieldLocation = new Location(world, -36.135, 91.0, 191.557);
		playerEntity.teleport(game_fieldLocation);
		
		
		/* game starting */
		kUtils.display_text(playerEntity, "Game Started");
				
		
		
		
		
//		game_thread = new Thread( new Runnable() {
//			
//			@Override
//			public void run() {
//				STOP_GAME = false;
//				while(true)
//				{
//					try {
//									
//						if(game_Logic.GAME_OVER && game_Logic.WINNER)
//						{
//							/* gimme the loot */
//							playerEntity.giveExp(1000);
//							break;
//						}
//					
//						
//						
//						//game_Logic.Game_Tracker();
//						
//						
//						if(STOP_GAME) { break; } 
//						Thread.sleep(200);
//					} catch (InterruptedException e) {
//
//						System.out.println("[kGuardian] big problem game kaput shaize");
//						playerEntity.teleport(world.getSpawnLocation());
//						break;
//						
//					}
//				}
//			}
//		});
//		
//		game_thread.start();
		
		
		return false;
		
	}


	
	
}
