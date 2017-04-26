package com.github.UnstablePancake.modules;

import com.github.UnstablePancake.modules.Utility.Ansi;
import com.github.UnstablePancake.modules.roles.Roles;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.util.*;
import java.util.List;

public class Role {

    private RoleBuilder roleBuilder;
    private List<IRole> guildRoles;

    public Role(IGuild guild) throws RateLimitException, DiscordException, MissingPermissionsException {
        roleBuilder = new RoleBuilder(guild);
        guildRoles = guild.getRoles();
        createRoles();
    }

    private void createRoles() throws RateLimitException, DiscordException, MissingPermissionsException {
        for (Roles r : Roles.values()){
            if (!exists(r)){
                roleBuilder.withName(r.getName()).withColor(r.getColor()).withPermissions(r.getPermission()).build();
                System.out.println(Ansi.color("[Role] + " + r.getName() + " was created.", Ansi.CYAN));
            } else {
                System.out.println(Ansi.color("[Role] " + r.getName() + " already exists. Role was not created", Ansi.CYAN));
            }
        }
    }

    private boolean exists(Roles r){
        for(IRole gr : guildRoles){
            if(gr.getName().equals(r.getName()))
                return true;
        }
        return false;
    }
}
