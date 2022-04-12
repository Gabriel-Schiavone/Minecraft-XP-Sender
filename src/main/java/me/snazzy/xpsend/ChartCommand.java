package me.snazzy.xpsend;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(ChatColor.DARK_GREEN + "Level    Xp" + ChatColor.GREEN +
                "\n  1 ---- 7" +
                "\n  2 ---- 16" +
                "\n  3 ---- 27" +
                "\n  4 ---- 40" +
                "\n  5 ---- 55" +
                "\n  6 ---- 72" +
                "\n  7 ---- 91" +
                "\n  8 ---- 112" +
                "\n  9 ---- 135" +
                "\n 10 ---- 160" +
                "\n 11 ---- 187" +
                "\n 12 ---- 216" +
                "\n 13 ---- 247" +
                "\n 14 ---- 280" +
                "\n 15 ---- 315" +
                "\n 16 ---- 352" +
                "\n 17 ---- 394" +
                "\n 18 ---- 441" +
                "\n 19 ---- 493" +
                "\n 20 ---- 550" +
                "\n 21 ---- 612" +
                "\n 22 ---- 679" +
                "\n 23 ---- 751" +
                "\n 24 ---- 828" +
                "\n 25 ---- 910" +
                "\n 26 ---- 997" +
                "\n 27 ---- 1089" +
                "\n 28 ---- 1186" +
                "\n 29 ---- 1288" +
                "\n 30 ---- 1395");
        return true;
    }
}
