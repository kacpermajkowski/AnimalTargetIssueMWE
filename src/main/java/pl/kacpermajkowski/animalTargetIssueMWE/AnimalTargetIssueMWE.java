package pl.kacpermajkowski.animalTargetIssueMWE;

import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnimalTargetIssueMWE extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AnimalTargetIssueMWE instance(){
        return getPlugin(AnimalTargetIssueMWE.class);
    }

    @EventHandler
    public void onAnimalTarget(EntityTargetLivingEntityEvent event){
        if(event.getReason().equals(EntityTargetLivingEntityEvent.TargetReason.TEMPT) &&
                event.getTarget() instanceof Player tempter
        ){
            tempter.sendMessage("following entity has targeted you: " + event.getEntity().getType());
        }
    }

    /**
     * Sends message to closest player with information of whether Animal is targeting a player
     *
     */
    @EventHandler
    public void onEntityMove(EntityMoveEvent event){
        if(event.getEntity() instanceof Cow cow){
            if(cow.getTarget() instanceof Player){
                Bukkit.broadcastMessage("cow targeting player");
            } else {
                Bukkit.broadcastMessage("cow target is not a player");
            }
        }
    }
}
