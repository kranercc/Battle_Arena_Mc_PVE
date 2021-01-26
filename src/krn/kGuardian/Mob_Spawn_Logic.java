package krn.kGuardian;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_16_R3.SpawnerCreature;

public class Mob_Spawn_Logic {
	
	private Main pluginMain;
	private kUtils kUtils;
	
	private Player player;
	private Location playerLocation;
	private World world;
	
	
	Map<Integer, EntityType> dificulty_and_bots = new HashMap<Integer, EntityType>()
	{{
		/* EASY */
		put(1, EntityType.PIG);
		put(2, EntityType.CHICKEN);
		put(3, EntityType.SHEEP);
		put(4, EntityType.COW);
		
		/* MEDIUM */
		put(5, EntityType.SLIME);
		put(6, EntityType.LLAMA);
		put(7, EntityType.SILVERFISH);
		put(8, EntityType.SKELETON);
		
		/* HARD */
		put(9, EntityType.SPIDER);
		put(10, EntityType.ZOMBIE);
		put(11, EntityType.BLAZE);
		put(12, EntityType.CREEPER);
		
		
	}};
	
	public Mob_Spawn_Logic(Player player)
	{
		kUtils = new kUtils();
		
		this.player = player;
		this.playerLocation = player.getLocation();
		this.world = player.getWorld();
		
	}

	
	public Vector<Entity> SpawnEnemiesAround(Player player, int nEnemies, int enemeyPower)
	{	
		
		/* get nEnemies random points to spawn in randius */
		Vector<Location> positionsVector = new Vector<Location>();
		Location playerLocation = player.getLocation();
		
		for( int i = 0; i < nEnemies; i++)
		{
			float[] vector3Pos = kUtils.rand_point_circle(kUtils.rand_int(5, 10), new float[] {(float) playerLocation.getX(), (float) playerLocation.getY(), (float) playerLocation.getZ()}, false);
			
			
			Location temp_locationLocation = new Location(world,vector3Pos[0], vector3Pos[1], vector3Pos[2]);


			
			positionsVector.add(temp_locationLocation);
		}
		
		
		Vector<Entity> mobsEntities = new Vector<Entity>();
		
		/* spawn logic */
		
		switch (enemeyPower) {
		case 1:
		{
			for( int i = 0; i < positionsVector.size(); i ++)
			{
				System.out.println("Spawning " + i + " at " + positionsVector.get(i).getX() + " <> " + positionsVector.get(i).getZ());
				Entity mobEntity = world.spawnEntity(positionsVector.get(i), dificulty_and_bots.get(kUtils.rand_int(4, 1)));
	
				mobsEntities.add(mobEntity);
				
			}
			break;
		}
		case 2:
		{
			for( int i = 0; i < positionsVector.size(); i ++)
			{
				System.out.println("Spawning " + i + " at " + positionsVector.get(i).getX() + " <> " + positionsVector.get(i).getZ());
				Entity mobEntity = world.spawnEntity(positionsVector.get(i), dificulty_and_bots.get(kUtils.rand_int(2, 6)));
	
				mobsEntities.add(mobEntity);
				
			}
			break;		
		}
		case 3:
		{
			for( int i = 0; i < positionsVector.size(); i ++)
			{
				System.out.println("Spawning " + i + " at " + positionsVector.get(i).getX() + " <> " + positionsVector.get(i).getZ());
				Entity mobEntity = world.spawnEntity(positionsVector.get(i), dificulty_and_bots.get(kUtils.rand_int(4, 8)));
	
				mobsEntities.add(mobEntity);
				
			}
			break;
		}
		default:
			System.out.println("[kGuardian] Nu sunt mobi pentru runda la care a ajuns.");
		}
		
		
		for(Entity entity : mobsEntities)
		{
			entity.setGlowing(true);
		}
		
		return mobsEntities;
	}
	
	
	
}
