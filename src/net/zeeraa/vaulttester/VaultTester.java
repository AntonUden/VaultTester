package net.zeeraa.vaulttester;

import org.bukkit.plugin.java.JavaPlugin;

import net.zeeraa.vaulttester.command.VaultTesterCommand;
import net.zeeraa.zcommandlib.command.registrator.ZCommandRegistrator;

public class VaultTester extends JavaPlugin {
	@Override
	public void onEnable() {
		ZCommandRegistrator.registerCommand(this, new VaultTesterCommand());
	}
}