package net.zeeraa.vaulttester.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

import net.zeeraa.vaulttester.command.subcommand.GetBalanceCommand;
import net.zeeraa.vaulttester.command.subcommand.HasAccountCommand;
import net.zeeraa.vaulttester.command.subcommand.HasCommand;
import net.zeeraa.vaulttester.command.subcommand.WithdrawCommand;
import net.zeeraa.zcommandlib.command.ZCommand;
import net.zeeraa.zcommandlib.command.utils.AllowedSenders;

public class VaultTesterCommand extends ZCommand {

	public VaultTesterCommand() {
		super("vaulttester");
		setAliases(ZCommand.generateAliasList("vt"));
		setPermission("vaulttester.command.vaulttester");
		setPermissionDefaultValue(PermissionDefault.OP);
		setAllowedSenders(AllowedSenders.ALL);
		addSubCommand(new GetBalanceCommand());
		addSubCommand(new HasAccountCommand());
		addSubCommand(new HasCommand());
		addSubCommand(new WithdrawCommand());
		addHelpSubCommand();
		setEmptyTabMode(true);
		setFilterAutocomplete(true);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		sender.sendMessage(ChatColor.GOLD + "Use " + ChatColor.AQUA + "/vaulttester help " + ChatColor.GOLD + "for help");
		return false;
	}
}