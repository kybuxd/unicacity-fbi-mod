package dev.kybu.unicacity.fbi.runnable;

import dev.kybu.unicacity.fbi.UnicaCityFBIMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OfflinePlayerRunnable implements Runnable {

    private List<String> list;
    private final UnicaCityFBIMod unicaCityFBIMod;

    public OfflinePlayerRunnable(final UnicaCityFBIMod unicaCityFBIMod) {
        this.list = new ArrayList<>();
        this.unicaCityFBIMod = unicaCityFBIMod;
    }

    @Override
    public void run() {
        if(Minecraft.getMinecraft().player == null) return;
        if(Minecraft.getMinecraft().getConnection() == null) return;

        final List<String> playerList = getOnlinePlayers();

        // First run
        if(this.list.size() == 0) {
            this.list = playerList;
            return;
        }

        final List<String> disconnectedDiff = getDiff(this.list, playerList);
        final List<String> connectedDiff = getDiff(playerList, this.list);
        this.list = playerList;

        if(disconnectedDiff.size() > 5) return;
        if(connectedDiff.size() > 5) return;


        for (final String entityPlayerMP : disconnectedDiff) {
            this.unicaCityFBIMod.sendPrefixedMessageToPlayer("§7Spieler " + entityPlayerMP + " §7hat die Verbindung getrennt");
        }

        for (String name : connectedDiff) {
            this.unicaCityFBIMod.sendPrefixedMessageToPlayer("§7Spieler " + name + " §7hat den Server betreten");
        }
    }

    private List<String> getOnlinePlayers() {
        final List<String> list = new ArrayList<>();

        for (NetworkPlayerInfo networkPlayerInfo : Minecraft.getMinecraft().getConnection().getPlayerInfoMap()) {
            String playerName = Minecraft.getMinecraft().ingameGUI.getTabList().getPlayerName(networkPlayerInfo);
            list.add(playerName);
        }

        return list;
    }

    private List<String> getDiff(final List<String> list1, final List<String> list2) {
        final List<String> difference = new ArrayList<>(list1);
        difference.removeAll(list2);
        return difference;
    }
}
