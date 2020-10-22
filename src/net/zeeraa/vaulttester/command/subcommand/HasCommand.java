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

public class HasCommand extends ZSubCommand {

	public HasCommand() {
		super("has");
		setPermission("vaulttester.command.vaulttester.has");
		setPermissionDefaultValue(PermissionDefault.OP);
		setAllowedSenders(AllowedSenders.ALL);
		setDescription("Check if a player has a certain amount of balance");
		setHelpString("/vaulttester getbalance <Balance> [Player]" + ChatColor.AQUA + "Check if a player has a certain amount of balance");
		addHelpSubCommand();
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		Player player = null;

		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Please provide an amount");
			return false;
		}

		double amount;

		try {
			amount = Double.parseDouble(args[0]);
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "Please provide a valid amount");
			return false;
		}

		if (args.length == 1) {
			if (sender instanceof Player) {
				player = (Player) sender;
			} else {
				sender.sendMessage(ChatColor.RED + "Please provide a player");
				return false;
			}
		} else {
			player = Bukkit.getServer().getPlayer(args[1]);

			if (player == null) {
				sender.sendMessage(ChatColor.RED + "Could not find player " + args[1]);
				return false;
			}
		}

		RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);

		Economy econ = rsp.getProvider();

		sender.sendMessage(econ.has(player, amount) ? ChatColor.GREEN + player.getName() + " has more than or equal to " + amount : ChatColor.RED + player.getName() + " has less that " + amount);

		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
		List<String> result = new ArrayList<String>();

		if (args.length == 1 || args.length == 2) {
			for (Player player : Bukkit.getServer().getOnlinePlayers()) {
				result.add(player.getName());
			}
		}
		return result;
	}
}