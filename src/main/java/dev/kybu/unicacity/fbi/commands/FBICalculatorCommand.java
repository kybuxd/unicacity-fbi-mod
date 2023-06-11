package dev.kybu.unicacity.fbi.commands;

import dev.kybu.unicacity.fbi.UnicaCityFBIMod;
import dev.kybu.unicacity.fbi.calculator.IFBICalculator;
import dev.kybu.unicacity.fbi.calculator.impl.MethCookingCalculator;
import dev.kybu.unicacity.fbi.calculator.impl.WantedPointCalculator;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FBICalculatorCommand extends CommandBase implements ICommand {

    private final UnicaCityFBIMod fbiMod;
    private final Map<String, IFBICalculator> types;

    /*=-------------------------------------------------------=*/

    public FBICalculatorCommand(final UnicaCityFBIMod fbiMod) {
        this.fbiMod = fbiMod;

        this.types = new HashMap<>();
        this.types.put("wp", new WantedPointCalculator(fbiMod.getFbiModConfig()));
        this.types.put("meth", new MethCookingCalculator(fbiMod.getFbiModConfig()));
    }

    /*=-------------------------------------------------------=*/

    @Override
    public String getName() {
        return "fbicalc";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/fbicalc [Type] [Value]";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    /*=-------------------------------------------------------=*/

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length < 2) {
            this.fbiMod.sendPrefixedMessageToPlayer(getUsage(sender));
            return;
        }

        if(!this.types.containsKey(args[0])) {
            this.fbiMod.sendPrefixedMessageToPlayer("§cInvalider Typ");
            return;
        }

        try {
            Double.parseDouble(args[1]);
        } catch(final Exception exception) {
            this.fbiMod.sendPrefixedMessageToPlayer("§cValue ist keine Nummer");
            return;
        }

        final double value = Double.parseDouble(args[1]);

        final IFBICalculator calculator = this.getCalculatorForType(args[0]);
        this.fbiMod.sendPrefixedMessageToPlayer("§7Folgende Werte wurden berechnet:");
        for (final Map.Entry<String, String> entry : calculator.calculateValues(value).entrySet()) {
            this.fbiMod.sendPrefixedMessageToPlayer("  §8> §f" + entry.getKey() + "§8: §7§o" + entry.getValue());
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        final List<String> list = new ArrayList<>();
        switch (args.length) {
            case 1:
                list.addAll(types.keySet());
                break;
        }
        return list;
    }

    private IFBICalculator getCalculatorForType(final String type) {
        return this.types.getOrDefault(type, new WantedPointCalculator(this.fbiMod.getFbiModConfig()));
    }
}
