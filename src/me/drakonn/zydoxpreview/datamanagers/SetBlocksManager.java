package me.drakonn.zydoxpreview.datamanagers;

import me.drakonn.zydoxpreview.ZydoxPreview;
import me.drakonn.zydoxpreview.util.Util;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public class SetBlocksManager {

    public static List<Location> setBlock = new ArrayList<>();

    private Configuration config;
    private ZydoxPreview plugin;
    public SetBlocksManager (ZydoxPreview plugin)
    {
        config = plugin.getConfig();
        this.plugin = plugin;
    }

    public void loadBlocks()
    {
        List<String> blocks = config.getStringList("setblocks");
        if(blocks.isEmpty())
            return;

        for(String loc : blocks)
            setBlock.add(Util.stringToLocation(plugin, loc));

    }

    public void saveBlocks()
    {
        List<String> blocks = new ArrayList<>();
        for(Location location : setBlock)
            blocks.add(Util.locationToString(location));

        config.set("setblocks", blocks);
        plugin.saveConfig();
    }
}
