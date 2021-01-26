package krn.kGuardian;

import java.util.Iterator;
import java.util.Vector;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEditBookEvent;

public class Game_Logic {
	public Main pluginMain;
	public kUtils kUtils;
	public Mob_Spawn_Logic mob_Spawn_Logic;
	
	public Player player;
	public Location playerLocation;
	public World world;
	
	public Vector<Entity> mobs;
	
	public boolean GAME_OVER = false;
	public boolean WINNER = false;
	
	public  float[] Game_Playground_Starting_Position;
	
	public Game_Logic(Player player)
	{
		kUtils = new kUtils();
		
		this.player = player;
		this.playerLocation = player.getLocation();
		this.world = player.getWorld();
		this.Game_Playground_Starting_Position = new float[]{(float) this.playerLocation.getX(), (float) this.playerLocation.getY(), (float) this.playerLocation.getZ()};
		
		mob_Spawn_Logic = new Mob_Spawn_Logic(player);		

	}
	
	
	
	public int round_tracker = 1;
	
	public void New_Round()
	{
		System.out.println("[kGuardian] Incepe runda: " + round_tracker);
		mobs = mob_Spawn_Logic.SpawnEnemiesAround(player, 3, round_tracker);

		
		
		
		round_tracker ++;

		
	}
	
	
	public void remove_all_mobs()
	{
		for(int i =0; i < mobs.size(); i++)
		{
			System.out.println("removed " + mobs.get(i).getName());
			mobs.get(i).remove();
		}
	}
	
	public void Game_Tracker(Player player)
	{
		playerLocation = player.getLocation();
		
		
		if(this.round_tracker == 10 && this.mobs.size() == 0) 
		{ 
			/* WINNER */
			System.out.println("[kGuardian] Bv " + player.getName() + " e cv");
			this.WINNER = true;
			this.GAME_OVER = true;
		}
		
		if(mobs.size() == 0) { New_Round(); }
		
		System.out.println("[kGuardian] " + "Round: " + round_tracker + " Mobs: " + mobs.size());
		for(int i = 0; i < mobs.size(); i ++)
		{			
			if(mobs.get(i).isDead())
			{
				mobs.remove(i);
			}
		}
		
		
		
		/* safety checks */
		float distance_me_to_x = kUtils.getDistanceBetweenVector(new float[] {(float) playerLocation.getX(), 
																(float) playerLocation.getY(),
																(float) playerLocation.getZ()},
																new float[] {Game_Playground_Starting_Position[0],
														   		Game_Playground_Starting_Position[1],
														   		Game_Playground_Starting_Position[2]});

		System.out.println("[kGuardian] distance_me_to_x " + distance_me_to_x );
		if(distance_me_to_x > 200)
		{
			this.GAME_OVER = true;
			kUtils.display_text(this.player, "You have gone too far");
			
		}
		
//		if(player.isDead()) { this.GAME_OVER = true; kUtils.display_text(this.player, "You died");}
//		if(!player.getWorld().getName().toString().equals("game_world")) { this.GAME_OVER = true; kUtils.display_text(this.player, "You changed the world"); }
//		
	}
}
