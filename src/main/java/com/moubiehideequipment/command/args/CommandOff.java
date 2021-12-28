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

package com.moubiehideequipment.command.args;

import com.moubieapi.api.commands.ICommand;
import com.moubieapi.api.commands.SenderType;
import com.moubieapi.moubieapi.commands.CommandAbstract;
import com.moubiehideequipment.MouBieCat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 用於將裝備顯示隱藏的指令
 * @author MouBieCat
 */
@ICommand(name = "off", description = "用於將裝備顯示隱藏的指令")
public final class CommandOff
        extends CommandAbstract {

    /**
     * 建構子
     */
    public CommandOff() {
        super("off", "", SenderType.PLAYER_SENDER);
    }

    @Override
    public boolean runCommand(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (!this.checkSenderType(sender) || !this.hasPermission(sender))
            return false;

        final Player player = (Player) sender;

        // 刪除數據包發送
        MouBieCat.getInstance().getPacketManager().remove(player.getUniqueId());
        sender.sendMessage(MouBieCat.PLUGIN_TITLE + MouBieCat.getInstance().getMessageFile().getNotHide());
        return false;
    }

    @Override
    @NotNull
    public List<String> runTabComplete(final @NotNull CommandSender sender, final @NotNull String[] args) {
        return new ArrayList<>();
    }

}
