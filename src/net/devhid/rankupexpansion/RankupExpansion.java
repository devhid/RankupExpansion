package net.devhid.rankupexpansion;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.devhid.pexrankup.RankupPlugin;
import net.devhid.pexrankup.api.RankupAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class RankupExpansion extends PlaceholderExpansion {

    private final PluginManager pluginManager;

    private PermissionsEx pex;
    private RankupPlugin rankup;

    public RankupExpansion() {
        this.pluginManager = Bukkit.getPluginManager();
    }

    @Override
    public boolean canRegister() {
        return pluginManager.getPlugin(getPlugin()) != null && pluginManager.getPlugin("PEX-Rankup") != null;
    }

    @Override
    public boolean register() {

        if (!canRegister()) {
            return false;
        }

        pex = (PermissionsEx) pluginManager.getPlugin(getPlugin());
        rankup = (RankupPlugin) pluginManager.getPlugin("PEX-Rankup");

        if (pex == null || rankup == null) {
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
        return "1.0.2";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }

        RankupAPI rankupAPI = rankup.getRankupAPI();
        PermissionUser user = pex.getPermissionsManager().getUser(player);

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
