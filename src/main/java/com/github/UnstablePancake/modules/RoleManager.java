package com.github.UnstablePancake.modules;

import com.github.UnstablePancake.modules.Roles.RolePermissions;
import com.github.UnstablePancake.modules.Roles.Roles;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.UserJoinEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.*;

import java.awt.*;
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

    public void welcomeMessage(UserJoinEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
        event.getGuild().getChannels().get(0).sendMessage(null, new EmbedBuilder()
                .withColor(new Color(153, 170, 181))
                .withTitle("Welcome to the server " + event.getUser().getName()).build(), false);
    }

    public void setRole(UserJoinEvent event, IRole role) throws RateLimitException, DiscordException, MissingPermissionsException {
        event.getGuild().getUserByID(event.getUser().getID()).addRole(role);
        System.out.println(event.getUser().getName() + "_" + role);
    }
}
