package me.drakonn.zydoxpreview.datamanagers;

import me.drakonn.zydoxpreview.ZydoxPreview;
import me.drakonn.zydoxpreview.util.Util;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    public static String KIT_NOT_FOUND;
    public static String NO_PERMISSION;
    public static List<String> help = new ArrayList<>();

    private Configuration config;
    public MessageManager (ZydoxPreview plugin)
    {
        config = plugin.getConfig();
    }

    public void loadMessages()
    {
        help.clear();
        ConfigurationSection section = config.getConfigurationSection("settings.messages");
        KIT_NOT_FOUND = Util.color(section.getString("kitnotfound"));
        NO_PERMISSION = Util.color(section.getString("nopermission"));
        setHelp();
    }

    private void setHelp()
    {
        help.add("§7-----------------§bZydoxPreview§7-----------------");
        help.add("§b/kitpreview §8- §fOpens kitpreview gui");
        help.add("§b/kitpreview (kit) §8- §fOpens a specifics kits gui");
        help.add("§b/kitpreview help §8- §fShow this help message");
        help.add("§b/kitpreview reload §8- §fReloads config");
        help.add("§b/kitpreview setblock §8- §fSets the block you are looking at to be clickable");
        help.add("§b/kitpreview delblock §8- §fRemoves the block you are looking at from being clickable");
    }
}
