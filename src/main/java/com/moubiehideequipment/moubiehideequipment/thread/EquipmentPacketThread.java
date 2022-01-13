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

package com.moubiehideequipment.moubiehideequipment.thread;

import com.mojang.datafixers.util.Pair;
import com.moubieapi.moubieapi.itemstack.ItemStackBuilder;
import com.moubiehideequipment.MouBieCat;
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
        this.runTaskTimer(MouBieCat.getInstance(), 0L, 3L * 20L);
    }

    @Override
    public void run() {
        this.startHide();
    }

    @Override
    public synchronized void cancel()
            throws IllegalStateException {
        this.cancelHide();
        super.cancel();
    }

    /**
     * 開始隱藏裝備
     */
    private void startHide() {
        // 建造隱藏物品項目
        final ItemStack nmsItemStack = ItemStackBuilder.Helper.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.AIR));

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
     * 取消隱藏裝備
     */
    private void cancelHide() {
        // 發送的裝備插槽
        final List<Pair<EnumItemSlot, ItemStack>> packetEnumItems = new ArrayList<>();

        // 獲取玩家身上裝備
        final PlayerInventory inventory = player.getInventory();

        final org.bukkit.inventory.ItemStack helmet =
                inventory.getHelmet() != null ? inventory.getHelmet() : new org.bukkit.inventory.ItemStack(Material.AIR);

        final org.bukkit.inventory.ItemStack chest =
                inventory.getChestplate() != null ? inventory.getChestplate() : new org.bukkit.inventory.ItemStack(Material.AIR);

        final org.bukkit.inventory.ItemStack leggings =
                inventory.getLeggings() != null ? inventory.getLeggings() : new org.bukkit.inventory.ItemStack(Material.AIR);

        final org.bukkit.inventory.ItemStack boots =
                inventory.getBoots() != null ? inventory.getBoots() : new org.bukkit.inventory.ItemStack(Material.AIR);

        packetEnumItems.add(new Pair<>(EnumItemSlot.f, ItemStackBuilder.Helper.asNMSCopy(helmet)));
        packetEnumItems.add(new Pair<>(EnumItemSlot.e, ItemStackBuilder.Helper.asNMSCopy(chest)));
        packetEnumItems.add(new Pair<>(EnumItemSlot.d, ItemStackBuilder.Helper.asNMSCopy(leggings)));
        packetEnumItems.add(new Pair<>(EnumItemSlot.c, ItemStackBuilder.Helper.asNMSCopy(boots)));

        // 實例數據包
        final PacketPlayOutEntityEquipment entityEquipment =
                new PacketPlayOutEntityEquipment(this.player.getEntityId(), packetEnumItems);
        this.sendToPlayers(entityEquipment);
    }

}
