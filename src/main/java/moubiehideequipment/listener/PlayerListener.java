package moubiehideequipment.listener;

import moubiehideequipment.main.MouBieCat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * 有關玩家事件觸發操作
 * @author MouBieCat
 */
public class PlayerListener
        implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        MouBieCat.getInstance().getPacketHelper().injectPlayerPacket(player);
    }

    /**
     * 玩家離開伺服器事件
     * @param event 事件
     */
    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        MouBieCat.getInstance().getPacketHelper().uninjectPlayerPacket(player);
        MouBieCat.getInstance().getPacketThreadManager().remove(player);
    }

}
