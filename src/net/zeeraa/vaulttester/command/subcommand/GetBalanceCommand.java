package net.zeeraa.vaulttester.command.subcommand;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;
import net.zeeraa.zcommandlib.command.ZSubCommand;
import net.zeeraa.zcommandlib.command.utils.AllowedSenders;

public class GetBalanceCommand extends ZSubCommand {
	public GetBalanceCommand() {
		super("getbalance");
		setPermission("vaulttester.command.vaulttester.getbalance");
		setPermissionDefaultValue(PermissionDefault.OP);
		setAllowedSenders(AllowedSenders.ALL);
		setDescription("Check a players balance");
		setHelpString("/vaulttester getbalance [Player]" + ChatColor.AQUA + "Check a players balance");
		addHelpSubCommand();
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		Player player = null;

		if (args.length == 0) {
			if (sender instanceof Player) {
				player = (Player) sender;
			} else {
				sender.sendMessage(ChatColor.RED + "Please provide a player");
				return false;
			}
		} else {
			player = Bukkit.getServer().getPlayer(args[0]);

			if (player == null) {
				sender.sendMessage(ChatColor.RED + "Could not find player " + args[0]);
				return false;
			}
		}

		RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);

		Economy econ = rsp.getProvider();

		sender.sendMessage(ChatColor.GOLD + player.getName() + "s balance: " + ChatColor.AQUA + econ.getBalance(player));

		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
		List<String> result = new ArrayList<String>();

		if (args.length == 0 || args.length == 1) {
			for (Player player : Bukkit.getServer().getOnlinePlayers()) {
				result.add(player.getName());
			}
		}
		return result;
	}
}