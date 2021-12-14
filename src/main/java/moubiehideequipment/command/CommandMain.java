package moubiehideequipment.command;

import moubiehideequipment.main.MouBieCat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表該插件的命令主類
 * @author MouBieCat
 */
public final class CommandMain
        implements TabExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull Command command,
                             final @NotNull String label, final @NotNull String[] args) {
        if (sender instanceof final Player player && args.length == 1) {
            final YamlConfiguration message = MouBieCat.getInstance().getMessage();

            if (args[0].equalsIgnoreCase("on")) {
                MouBieCat.getInstance().getPacketHelper().injectPlayerPacket(player);
                player.sendMessage(MouBieCat.PLUGIN_TITLE + message.getString("Messages.on"));
                return true;
            }

            else if (args[0].equalsIgnoreCase("off")) {
                MouBieCat.getInstance().getPacketHelper().uninjectPlayerPacket(player);
                player.sendMessage(MouBieCat.PLUGIN_TITLE + message.getString("Messages.off"));
                return true;
            }

            player.sendMessage(MouBieCat.PLUGIN_TITLE + message.getString("Messages.help"));
        }

        return false;
    }

    @Override
    @NotNull
    public List<String> onTabComplete(final @NotNull CommandSender sender, final @NotNull Command command,
                                               final @NotNull String alias, final @NotNull String[] args) {
        final List<String> list = new ArrayList<>();

        if (args.length == 1) {
            list.add("on"); list.add("off");
        }

        return list;
    }

}
