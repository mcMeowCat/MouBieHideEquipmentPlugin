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

package com.cat.moubiehideequipment;

import com.cat.moubiehideequipment.packet.EquipmentPacketThread;
import com.moubiecat.api.manager.Manager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 代表一個數據包線程經理
 * @author MouBieCat
 */
public final class PacketThreadManager
        implements Manager<UUID, EquipmentPacketThread> {

    // 紀錄器
    private final Map<UUID, EquipmentPacketThread> threads = new HashMap<>();

    /**
     * 添加紀錄(該方法不會自動為您啟動線程，請手動操作)
     * @param player 玩家
     * @param obj 線程物件
     */
    public void add(final @NotNull UUID player, final @NotNull EquipmentPacketThread obj) {
        if (!this.hasKey(player))
            this.threads.put(player, obj);
    }

    /**
     * 停止一個線程(該方法會自動檢測並且安全停止線程)
     * @param player 玩家
     */
    public void remove(final @NotNull UUID player) {
        final EquipmentPacketThread thread = this.get(player);
        if (thread != null)
            thread.cancel();

        this.threads.remove(player);
    }

    /**
     * 獲取一個玩家在運行的線程
     * @param player 玩家
     * @return 線程物件
     */
    @Nullable
    public EquipmentPacketThread get(final @NotNull UUID player) {
        return this.threads.get(player);
    }

    /**
     * 檢查是否包含了該紀錄
     * @param player 玩家
     * @return 是否包含
     */
    public boolean hasKey(final @NotNull UUID player) {
        return this.threads.containsKey(player);
    }

    /**
     * 獲取所有資料集合表
     * @return 集合
     */
    @NotNull
    public Collection<EquipmentPacketThread> getValues() {
        return this.threads.values();
    }

}
