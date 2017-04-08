package com.github.UnstablePancake.modules;

import com.github.UnstablePancake.modules.Roles.Roles;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.util.*;
import java.util.List;

public class RoleManager {

    private RoleBuilder roleBuilder;
    private List<IRole> guildRoles;

    public RoleManager(IGuild guild) throws RateLimitException, DiscordException, MissingPermissionsException {
        roleBuilder = new RoleBuilder(guild);
        guildRoles = guild.getRoles();
        createRoles();
    }

    public void createRoles() throws RateLimitException, DiscordException, MissingPermissionsException {
        for (Roles r : Roles.values()){
            if (!exists(r)){
                roleBuilder.withName(r.getName()).withColor(r.getColor()).withPermissions(r.getPermission()).build();
                System.out.println("$" + r.getName() + " was created.");
            } else {
                System.out.println("$" + r.getName() + " already exists. Role was not created");
            }
        }
    }

    public boolean exists(Roles r){
        for(IRole gr : guildRoles){
            if(gr.getName().equals(r.getName()))
                return true;
        }
        return false;
    }
}
