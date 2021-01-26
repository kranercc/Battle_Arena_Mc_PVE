package krn.kGuardian;

import org.bukkit.plugin.java.JavaPlugin;

import krn.kGuardian.eventuri.krane_EventHandler;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		getServer().getPluginManager().registerEvents(
				new krane_EventHandler(new Game_Starter(this))
				, this);
		
	}
	
	
	
}
