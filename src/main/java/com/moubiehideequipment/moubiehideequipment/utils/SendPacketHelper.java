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

package com.moubiehideequipment.moubiehideequipment.utils;

import com.moubieapi.moubieapi.reflect.CraftBukkitReflect;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

/**
 * 代表發送數據包有關的幫手類
 * @author MouBieCat
 */
public final class SendPacketHelper {

    /**
     * 發送一個數據包給玩家
     * @param player 玩家
     * @param packet 數據包
     */
    public static void sendPacket(final @NotNull Player player, final @NotNull Packet<?> packet) {
        final Method getHandle = CraftBukkitReflect.getMethod(player.getClass(), "getHandle");
        final EntityPlayer entityPlayer = (EntityPlayer) CraftBukkitReflect.invoke(getHandle, player);
        entityPlayer.b.a(packet);
    }

}
