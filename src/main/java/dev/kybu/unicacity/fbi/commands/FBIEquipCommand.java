package dev.kybu.unicacity.fbi.commands;

import dev.kybu.unicacity.fbi.UnicaCityFBIMod;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class FBIEquipCommand extends CommandBase implements ICommand {

    private final UnicaCityFBIMod fbiMod;

    public FBIEquipCommand(final UnicaCityFBIMod fbiMod) {
        this.fbiMod = fbiMod;
    }

    /*=-------------------------------------------------------=*/

    @Override
    public String getName() {
        return "fbiequip";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/fbiequip";
    }

    /*=-------------------------------------------------------=*/

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        final EntityPlayerSP player = (EntityPlayerSP) sender;
    }
}
