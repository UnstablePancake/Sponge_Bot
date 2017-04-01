package com.github.UnstablePancake.modules.Roles;

import java.awt.*;

public enum Roles {

    // Main Roles
    admin("Administrator", new Color(78, 185, 110)),
    mod("Moderator", new Color(229, 76, 59)),
    user("Regular", new Color(59, 151, 210)),
    guest("Guest", new Color(153, 170, 181)),

    // Other Roles
    csgo("CSGO", new Color(153, 170, 181));

    private final String NAME;
    private final Color COLOR;

    Roles(String n, Color c){
        NAME = n;
        COLOR = c;
    }

    public String getName(){
        return NAME;
    }

    public Color getColor(){
        return COLOR;
    }
}
