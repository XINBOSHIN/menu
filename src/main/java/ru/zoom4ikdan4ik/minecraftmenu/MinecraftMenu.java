package ru.zoom4ikdan4ik.minecraftmenu;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent.Post;
import net.minecraftforge.common.MinecraftForge;
import ru.zoom4ikdan4ik.minecraftmenu.gui.GuiCustomMenu;
import ru.zoom4ikdan4ik.minecraftmenu.interfaces.IMinecraftMenu;
import ru.zoom4ikdan4ik.minecraftmenu.managers.ConfigManager;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = ConfigManager.MODID, name = ConfigManager.MODNAME)
public class MinecraftMenu implements IMinecraftMenu {

    @EventHandler
    public void preLoad(FMLPreInitializationEvent event) {
        configManager.loadConfig(event.getSuggestedConfigurationFile());
        configManager.saveConfig();

        ServerList serverList = new ServerList(Minecraft.getMinecraft());

        for (String server : configManager.servers) {
            String[] data = server.split("->");

            serverList.addServerData(new ServerData(data[0], data[1]));
        }

        serverList.saveServerList();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new MinecraftMenu());
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (event.gui instanceof GuiMainMenu)
            event.gui = new GuiCustomMenu();
    }

    @SubscribeEvent
    public void onGuiScreenInit(Post event) {
        GuiScreen gui = event.gui;

        if (gui instanceof GuiMultiplayer) {
            int x = event.gui.width / 2 - 160;
            List add = new ArrayList();

            for (Object button : event.buttonList)
                if (button instanceof GuiButton) {
                    GuiButton gb = (GuiButton) button;
                    if (gb.id != 7 && gb.id != 2 && gb.id != 3 && gb.id != 4) {
                        gb.yPosition = event.gui.height - 40;
                        gb.xPosition = x;
                        gb.width = 100;
                        x += 110;
                        add.add(gb);
                    }
                }

            event.buttonList.clear();

            for (Object o : add)
                event.buttonList.add(o);
        }
    }
}
