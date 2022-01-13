package com.moubiehideequipment.moubiehideequipment.manager;

import com.moubieapi.moubieapi.manager.ThreadManagerAbstract;
import com.moubiehideequipment.api.manager.EquipmentThreadManager;
import com.moubiehideequipment.moubiehideequipment.thread.EquipmentPacketThread;

import java.util.UUID;

/**
 * 用於管理玩家隱藏裝備數據包線程管理器
 * @author MouBieCat
 */
public final class EquipmentPacketThreadManager
        extends ThreadManagerAbstract<UUID, EquipmentPacketThread>
        implements EquipmentThreadManager {
}
