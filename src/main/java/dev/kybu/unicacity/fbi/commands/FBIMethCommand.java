package dev.kybu.unicacity.fbi.commands;

import dev.kybu.unicacity.fbi.UnicaCityFBIMod;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class FBIMethCommand extends CommandBase implements ICommand {

    private final UnicaCityFBIMod fbiMod;

    public FBIMethCommand(final UnicaCityFBIMod fbiMod) {
        this.fbiMod = fbiMod;
    }

    @Override
    public String getName() {
        return "fbimeth";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/fbimeth [0 Default, Reinheit]";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        final EntityPlayerSP entityPlayerSP = (EntityPlayerSP) sender;

        if(args.length == 0) {
            entityPlayerSP.sendChatMessage("/asservatenkammer get Meth 50 0");
            return;
        }

        try {
            entityPlayerSP.sendChatMessage("/asservatenkammer get Meth 50 " + Integer.parseInt(args[0]));
        } catch(final Exception exception) {
            this.fbiMod.sendPrefixedMessageToPlayer("Hurensohn");
        }
    }
}
