package me.snazzy.xpsend;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendCommand implements CommandExecutor {

    // Method to calculate a player's xp from their level.
    public int calculateXp(int level) {
        if(level >= 1 && level <= 16) {
            return (int)(Math.pow(level, 2) + 6 * level);

        } else if (level >= 17 && level <= 31) {
            return (int)( 2.5 * Math.pow(level, 2) - 40.5 * level + 360);

        } else if (level >= 32) {
            return (int)(4.5 * Math.pow(level, 2) - 162.5 * level + 2220);

        } else {
            return 0;

        }
    }

    // Method to calculate a player's level from their xp.
    public int calculateLevel(int xp) {
        if(xp >= 7 && xp <= 352) {
            return (int)(-3 + Math.sqrt(xp + 9));

        } else if (xp >= 353 && xp <= 1507) {
            return (int)((81 + Math.sqrt(40 * xp - 7839)) / 10);

        } else if (xp >= 1508) {
            return (int)((325 + Math.sqrt(72 * xp - 54215)) / 18);

        } else {
            return 0;

        }
    }

    // Command to send xp.
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true; // make sure sender is player, not console
        if (args.length != 2) return true; // check for number of command args

        Player target = Bukkit.getPlayer(args[0]);

        // Check if name given is the name of an online player.
        if (!Bukkit.getOnlinePlayers().contains(target)) {
            sender.sendMessage(ChatColor.RED + "That is not a valid player.");
            return true;
        }

        int sendAmount = Integer.parseInt(args[1]);
        int senderXp = calculateXp(((Player) sender).getLevel());
        int targetXp = calculateXp(target.getLevel());

        // Check that sender has enough xp.
        if (sendAmount > senderXp) {
            sender.sendMessage(ChatColor.RED + "You don't have that much xp.");
            return true;
        }

        // Check that the target isn't the sender.
        if (target == ((Player) sender).getPlayer()) {
            sender.sendMessage(ChatColor.RED + "You can't send xp to yourself.");
            return true;
        }

        // Set new xp values.
        senderXp = senderXp - sendAmount;
        targetXp = targetXp + sendAmount;

        // Convert new xp values to new level values (has some slight issues).
        int newSenderLevel = calculateLevel(senderXp);
        int newTargetLevel = calculateLevel(targetXp);

        // Make sure that xp sent will affect the target's level.
        // Otherwise sender looses xp but target doesn't gain xp.
        if (newTargetLevel == target.getLevel()) {
            sender.sendMessage(ChatColor.RED + "Must send more xp to affect recipients level.");
            return true;
        }

        // Set both player levels to their new values.
        ((Player) sender).setLevel(newSenderLevel);
        target.setLevel(newTargetLevel);

        sender.sendMessage(ChatColor.GREEN + "You sent " + sendAmount + " xp to " + target.getName() + ".");
        target.sendMessage(ChatColor.GREEN + sender.getName() + " sent you " + sendAmount + " xp.");

        return true;
    }
}
