package krn.kGuardian;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class kUtils {

	
	
	public int rand_int(int max, int min)
	{
		Random rng = new Random();
		int next = rng.nextInt(max) + min;
		return next;
	}
	
	
	public Location get_position_in_front(Player p, float scale, boolean eye)
	{
	

		org.bukkit.util.Vector forwardVector = new Vector();
		if(eye) 
		{
			forwardVector = p.getEyeLocation().getDirection().multiply(scale);
				
		}
		else {
			forwardVector = p.getFacing().getDirection().multiply(scale); 
		}
		
		Location location = p.getLocation().add(forwardVector.getX(), forwardVector.getY() + p.getHeight(), forwardVector.getZ());
		
		
		return location;
	}
	
	
	public float[] rand_point_circle(int radius, float[] vectorPos, boolean _3D)
	{
	    Random r = new Random();
	    float randomRadius = (float) (r.nextDouble() * radius);
	    float theta =  (float) Math.toRadians(r.nextDouble() * 360);
	    float phi = (float) Math.toRadians(r.nextDouble() * 180 - 90);
	 
	    float x = (float) (randomRadius * Math.cos(theta) * Math.sin(phi));
	    float y = (float) (randomRadius * Math.sin(theta) * Math.cos(phi));
	    float z = (float) (randomRadius * Math.cos(phi));
	    x = vectorPos[0] + x; y = vectorPos[1] + y; z = vectorPos[2] + z;
	    float[] newLoc = {x, vectorPos[1], z};

	    if(_3D) { newLoc[1] = y; }
	    
	    return newLoc;
		
	}
	
	
	public float getDistanceBetweenVector(float[] src, float[] dst)
	{
		float difX = src[0] - dst[0];
		float difY = src[1] - dst[1];
		float difZ = src[2] - dst[2];
		
//		System.out.println("[kGuardian] " + src[0] + "<>" + dst[0]);
		
		return (float) Math.sqrt( Math.pow(difX, 2) + Math.pow(difY, 2) + Math.pow(difZ, 2) );
	}
	
	
	public void display_text(Player player, String textString)
	{
		String player_name = player.getName();
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title " + player_name + " title [{\"text\":\"" +textString+ "\",\"color\":\"gold\"}]"); //JSON formatting is invalid!

	}
	
	
}
