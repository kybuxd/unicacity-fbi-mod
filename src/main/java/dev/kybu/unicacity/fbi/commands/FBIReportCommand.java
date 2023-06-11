package dev.kybu.unicacity.fbi.commands;

import dev.kybu.unicacity.fbi.UnicaCityFBIMod;
import dev.kybu.unicacity.fbi.data.ReportType;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FBIReportCommand extends CommandBase implements ICommand {

    private final UnicaCityFBIMod fbiMod;

    /*=-------------------------------------------------------=*/

    public FBIReportCommand(final UnicaCityFBIMod fbiMod) {
        this.fbiMod = fbiMod;
    }

    /*=-------------------------------------------------------=*/

    @Override
    public String getName() {
        return "fbireport";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/fbireport [Event] [Nachricht]";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        final List<String> list = new ArrayList<>();
        switch (args.length) {
            case 1:
                for (ReportType value : ReportType.values()) {
                    list.add(value.toString());
                }
                break;
            case 2:
                list.add("Nachricht");
                break;
        }
        return list;
    }

    /*=-------------------------------------------------------=*/

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        final EntityPlayerSP player = (EntityPlayerSP) sender;
        if(args.length < 2) {
            fbiMod.sendPrefixedMessageToPlayer(getUsage(sender));
            return;
        }

        final ReportType type = getTypeByString(args[0]);
        if(type == null) {
            fbiMod.sendPrefixedMessageToPlayer("§cInvalider Typ angegeben");
            return;
        }

        final String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        fbiMod.sendPrefixedMessageToPlayer("§aDu hast eine Meldung an das FBI geschickt §7§o(" + type.toString() + ")");

        player.sendChatMessage("/sms 100564 custom \"Es wird " + type.getDisplayName() + " gemeldet!\" \"Nachricht von " + player.getName() + ": \n" + message + "\"");
    }

    private ReportType getTypeByString(final String typeStr) {
        for (ReportType value : ReportType.values()) {
            if(value.toString().equalsIgnoreCase(typeStr)) {
                return value;
            }
        }
        return null;
    }
}
