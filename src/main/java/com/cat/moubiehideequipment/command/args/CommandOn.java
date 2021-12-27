/**
 * LICENSE
 * MouBieHideEquipmentPlugin
 * -------------
 * Copyright (C) 2021 MouBieCat(MouBie_Yuki)
 * -------------
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package com.cat.moubiehideequipment.command.args;

import com.cat.moubiehideequipment.MouBieCat;
import com.cat.moubiehideequipment.packet.EquipmentPacketThread;
import com.moubiecat.api.commands.ICommand;
import com.moubiecat.api.commands.SenderType;
import com.moubiecat.moubieapi.commands.CommandAbstract;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表隱藏裝備指令
 * @author MouBieCat
 */
@ICommand(name = "hide", description = "用於將裝備隱藏的指令")
public final class CommandOn
        extends CommandAbstract {

    /**
     * 建構子
     */
    public CommandOn() {
        super("on", "用於將裝備隱藏的指令", SenderType.PLAYER_SENDER, new Permission("asda.asgag"));
    }

    @Override
    public boolean runCommand(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (!this.checkSenderType(sender) || !this.hasPermission(sender))
            return false;

        final Player player = (Player) sender;

        // 添加數據包發送
        MouBieCat.getInstance().getPacketManager().add(player.getUniqueId(), new EquipmentPacketThread(player));
        player.sendMessage(MouBieCat.PLUGIN_TITLE + MouBieCat.getInstance().getMessageFile().getHide());
        return true;
    }

    @Override
    @NotNull
    public List<String> runTabComplete(final @NotNull CommandSender sender, final @NotNull String[] args) {
        return new ArrayList<>();
    }

}
