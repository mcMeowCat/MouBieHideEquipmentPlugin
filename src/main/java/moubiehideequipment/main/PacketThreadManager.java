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

package moubiehideequipment.main;

import moubiehideequipment.packet.PacketThread;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * 代表一個術數包線程經理
 * @param <T> 有關 PacketThread 的繼承類
 * @author MouBieCat
 */
public final class PacketThreadManager<T extends PacketThread> {

    // 紀錄器
    @NotNull
    private final Map<Player, T> packetThreads = new HashMap<>();

    /**
     * 添加紀錄(該方法不會自動為您啟動線程，請手動操作)
     * @param player 玩家
     * @param obj 線程物件
     */
    public void add(final @NotNull Player player, final @NotNull T obj) {
        final T beforeObj = this.get(player);
        if (beforeObj != null)
            beforeObj.cancel();

        this.packetThreads.put(player, obj);
    }

    /**
     * 停止一個線程(該方法會自動檢測並且安全停止線程)
     * @param player 玩家
     */
    public void remove(final @NotNull Player player) {
        final T obj = this.get(player);
        if (obj != null)
            obj.cancel();

        this.packetThreads.remove(player);
    }

    /**
     * 獲取一個玩家在運行的線程
     * @param player 玩家
     * @return 線程物件
     */
    @Nullable
    public T get(final @NotNull Player player) {
        return this.packetThreads.get(player);
    }

    /**
     * 該玩家是否擁有一個線程
     * @param player 玩家
     * @return 是否包含
     */
    public boolean containsPlayer(final @NotNull Player player) {
        return this.packetThreads.containsKey(player);
    }

}
