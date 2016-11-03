package net.devhid.rankupexpansion;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.devhid.pexrankup.RankupAPI;
import net.devhid.pexrankup.RankupPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class RankupExpansion extends PlaceholderExpansion {

    private PermissionsEx plugin;

    @Override
    public boolean canRegister() {
        return Bukkit.getPluginManager().getPlugin(getPlugin()) != null;
    }

    @Override
    public boolean register() {

        if (!canRegister()) {
            return false;
        }

        plugin = (PermissionsEx) Bukkit.getPluginManager().getPlugin(getPlugin());


        if (plugin == null) {
            return false;
        }

        return PlaceholderAPI.registerPlaceholderHook(getIdentifier(), this);
    }

    @Override
    public String getIdentifier() {
        return "pexrankup";
    }

    @Override
    public String getPlugin() {
        return "PermissionsEx";
    }

    @Override
    public String getAuthor() {
        return "devhid";
    }

    @Override
    public String getVersion() {
        return "1.0.1";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }

        RankupAPI rankupAPI = RankupPlugin.getRankupAPI();
        PermissionUser user = plugin.getPermissionsManager().getUser(player);

        if(identifier.equals("current_group")) {
            return rankupAPI.getCurrentGroup(user);
        }

        if(identifier.equals("next_rank")) {
            return rankupAPI.getNextRank(user);
        }

        if(identifier.equals("cost_of_next_rank")) {
            return rankupAPI.getCostOfNextRankString(user);
        }

        if(identifier.equals("cost_of_next_rank_formatted")) {
            return rankupAPI.getCostOfNextRankFormatted(user);
        }

        return null;
    }
}
