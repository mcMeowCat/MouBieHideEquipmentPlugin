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

package com.cat.moubiehideequipment.packet;

import com.mojang.datafixers.util.Pair;
import com.cat.moubiehideequipment.MouBieCat;
import com.moubiecat.moubieapi.itemstack.ItemStackBuilder;
import net.minecraft.network.protocol.game.PacketPlayOutEntityEquipment;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表隱藏裝備的數據包線程
 * @author MouBieCat
 */
public final class EquipmentPacketThread
        extends PacketThread {

    /**
     * 建構子
     * @param player 擁有者
     */
    public EquipmentPacketThread(final @NotNull Player player) {
        super(player);
        this.runTaskTimer(MouBieCat.getInstance(), 0L, 5 * 20L);
    }

    /**
     * 運行該線程做的事情
     */
    @Override
    public void run() {
        this.hide();
    }

    /**
     * 取消該線程
     * @throws IllegalStateException 異常
     */
    @Override
    public synchronized void cancel()
            throws IllegalStateException {
        this.notHide();
        super.cancel();
    }

    /**
     * 發送隱藏裝備數據包
     */
    private void hide() {
        // 建造隱藏物品項目
        final ItemStack nmsItemStack = ItemStackBuilder.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.AIR));

        // 發送的裝備插槽
        final List<Pair<EnumItemSlot, ItemStack>> packetEnumItems = new ArrayList<>();

        packetEnumItems.add(new Pair<>(EnumItemSlot.f, nmsItemStack));
        packetEnumItems.add(new Pair<>(EnumItemSlot.e, nmsItemStack));
        packetEnumItems.add(new Pair<>(EnumItemSlot.d, nmsItemStack));
        packetEnumItems.add(new Pair<>(EnumItemSlot.c, nmsItemStack));

        // 實例數據包
        final PacketPlayOutEntityEquipment entityEquipment =
                new PacketPlayOutEntityEquipment(this.player.getEntityId(), packetEnumItems);
        this.sendToPlayers(entityEquipment);
    }

    /**
     * 發送不再隱藏裝備數據包
     */
    private void notHide() {
        // 發送的裝備插槽
        final List<Pair<EnumItemSlot, ItemStack>> packetEnumItems = new ArrayList<>();

        // 獲取玩家身上裝備
        final PlayerInventory inventory = player.getInventory();
        final org.bukkit.inventory.ItemStack helmet = inventory.getHelmet();
        final org.bukkit.inventory.ItemStack chestplate = inventory.getChestplate();
        final org.bukkit.inventory.ItemStack leggings = inventory.getLeggings();
        final org.bukkit.inventory.ItemStack boots = inventory.getBoots();

        if (helmet != null)
            packetEnumItems.add(new Pair<>(EnumItemSlot.f, ItemStackBuilder.asNMSCopy(helmet)));

        if (chestplate != null)
            packetEnumItems.add(new Pair<>(EnumItemSlot.e, ItemStackBuilder.asNMSCopy(chestplate)));

        if (leggings != null)
            packetEnumItems.add(new Pair<>(EnumItemSlot.d, ItemStackBuilder.asNMSCopy(leggings)));

        if (boots != null)
            packetEnumItems.add(new Pair<>(EnumItemSlot.c, ItemStackBuilder.asNMSCopy(boots)));

        // 實例數據包
        final PacketPlayOutEntityEquipment entityEquipment =
                new PacketPlayOutEntityEquipment(this.player.getEntityId(), packetEnumItems);
        this.sendToPlayers(entityEquipment);
    }

}
