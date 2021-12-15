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

package moubiehideequipment.packet;

import com.mojang.datafixers.util.Pair;
import moubiehideequipment.main.MouBieCat;
import net.minecraft.network.protocol.game.PacketPlayOutEntityEquipment;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.ItemStack;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_18_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
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
    public EquipmentPacketThread(@NotNull Player player) {
        super(player);
    }

    /**
     * 運行該線程做的事情
     */
    @Override
    public void run() {
        this.hide();
        if (!this.player.isOnline())
            this.cancel();
    }

    /**
     * 取消該線程
     * @throws IllegalStateException 異常
     */
    @Override
    public synchronized void cancel() throws IllegalStateException {
        MouBieCat.getInstance().getPacketHelper().uninjectPlayerPacket(this.player);
        this.notHide();
    }

    /**
     * 發送隱藏裝備數據包
     */
    private void hide() {
        // 建造物品項目
        final org.bukkit.inventory.ItemStack orgItemStack = new org.bukkit.inventory.ItemStack(Material.AIR);
        // 轉成 NMS 型式
        final ItemStack nmsItemStack = CraftItemStack.asNMSCopy(orgItemStack);

        // 發送的裝備插槽
        final List<Pair<EnumItemSlot, ItemStack>> packetEnumItems = Arrays.asList(
                new Pair<>(EnumItemSlot.a, nmsItemStack),
                new Pair<>(EnumItemSlot.a, nmsItemStack),
                new Pair<>(EnumItemSlot.a, nmsItemStack),
                new Pair<>(EnumItemSlot.a, nmsItemStack)
        );

        // 實例數據包
        final PacketPlayOutEntityEquipment entityEquipment =
                new PacketPlayOutEntityEquipment(this.player.getEntityId(), packetEnumItems);

        this.sendToPlayers(entityEquipment);
    }

    /**
     * 發送不再隱藏裝備數據包
     */
    private void notHide() {
        // 獲取玩家真正的穿著
        final PlayerInventory inventory = this.player.getInventory();
        final org.bukkit.inventory.ItemStack orgHelmet = inventory.getHelmet();
        final org.bukkit.inventory.ItemStack orgChestplate = inventory.getChestplate();
        final org.bukkit.inventory.ItemStack orgLeggings = inventory.getLeggings();
        final org.bukkit.inventory.ItemStack orgBoots = inventory.getBoots();
        // 轉成 NMS 型式
        final ItemStack nmsHelmet = CraftItemStack.asNMSCopy(orgHelmet);
        final ItemStack nmsChestplate = CraftItemStack.asNMSCopy(orgChestplate);
        final ItemStack nmsLeggings = CraftItemStack.asNMSCopy(orgLeggings);
        final ItemStack nmsBoots = CraftItemStack.asNMSCopy(orgBoots);

        // 發送的裝備插槽
        final List<Pair<EnumItemSlot, ItemStack>> packetEnumItems = Arrays.asList(
                new Pair<>(EnumItemSlot.a, nmsHelmet),
                new Pair<>(EnumItemSlot.a, nmsChestplate),
                new Pair<>(EnumItemSlot.a, nmsLeggings),
                new Pair<>(EnumItemSlot.a, nmsBoots)
        );

        // 實例數據包
        final PacketPlayOutEntityEquipment entityEquipment =
                new PacketPlayOutEntityEquipment(this.player.getEntityId(), packetEnumItems);

        this.sendToPlayers(entityEquipment);
    }

}
