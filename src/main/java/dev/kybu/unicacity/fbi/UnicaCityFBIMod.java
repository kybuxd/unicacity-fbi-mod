package dev.kybu.unicacity.fbi;

import dev.kybu.unicacity.fbi.commands.FBICalculatorCommand;
import dev.kybu.unicacity.fbi.commands.FBIMethCommand;
import dev.kybu.unicacity.fbi.commands.FBIReportCommand;
import dev.kybu.unicacity.fbi.config.FBIEquipConfig;
import dev.kybu.unicacity.fbi.config.FBIModConfig;
import dev.kybu.unicacity.fbi.service.ConfigurationService;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import  org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Mod(modid = "unicacityfbimod", name = "UnicaCity FBI Mod", version = "1.0")
public class UnicaCityFBIMod {

    private Logger logger;
    private FBIModConfig fbiModConfig;
    private ConfigurationService configurationService;
    private FBIEquipConfig fbiEquipConfig;
    private ScheduledExecutorService scheduledExecutorService;

    /*=-------------------------------------------------------=*/

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        this.configurationService = new ConfigurationService();
        this.logger = event.getModLog();
        this.fbiModConfig = this.loadConfiguration(new File("fbi-config.json"), FBIModConfig.class);
        this.fbiEquipConfig = this.loadConfiguration(new File("fbi-equip.json"), FBIEquipConfig.class);
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        registerCommands(FBIReportCommand.class, FBIMethCommand.class, FBICalculatorCommand.class);
    }

    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        // Debug
        //this.scheduledExecutorService.scheduleAtFixedRate(new OfflinePlayerRunnable(this), 0,1, TimeUnit.SECONDS);
    }

    /*=-------------------------------------------------------=*/

    public Logger getLogger() {
        return logger;
    }

    public String getPrefix() {
        return "§9FBI §8| §7";
    }

    public FBIModConfig getFbiModConfig() {
        return fbiModConfig;
    }

    public FBIEquipConfig getFbiEquipConfig() {
        return fbiEquipConfig;
    }

    /*=-------------------------------------------------------=*/

    public void sendMessageToPlayer(final String message) {
        final EntityPlayerSP player = Minecraft.getMinecraft().player;
        if(player == null) return;

        player.sendMessage(new TextComponentString(message.replace("§", "\u00a7")));
    }

    public void sendPrefixedMessageToPlayer(final String message) {
        sendMessageToPlayer(getPrefix() + message);
    }

    public void saveEquipConfig() {
        this.configurationService.saveConfiguration(new File("fbi-equip.json"), this.fbiEquipConfig);
    }

    /*=-------------------------------------------------------=*/

    @SafeVarargs
    private final boolean registerCommands(final Class<? extends CommandBase>... commands) {
        try {
            for(final Class<? extends CommandBase> command : commands) {
                ClientCommandHandler.instance.registerCommand(command.getConstructor(UnicaCityFBIMod.class).newInstance(this));
            }
            return true;
        } catch(final Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private <T> T loadConfiguration(final File file, Class<T> clazz) {
        if(!file.exists()) {
            try {
                final T config = clazz.getConstructor().newInstance();
                this.configurationService.saveConfiguration(file, config);
                return config;
            } catch (final Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }

        return this.configurationService.readConfiguration(file, clazz);
    }
}
