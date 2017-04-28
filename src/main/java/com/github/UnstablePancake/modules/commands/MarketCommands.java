package com.github.UnstablePancake.modules.commands;

import co.kaioru.distort.d4j.command.D4JCommandBuilder;
import com.github.UnstablePancake.bot.UserData;
import com.github.UnstablePancake.modules.games.points.Market;
import com.github.UnstablePancake.modules.games.points.Points;
import sx.blah.discord.api.IDiscordClient;

public class MarketCommands extends Commands{

    public MarketCommands(IDiscordClient client) {
        super(client);
        D4JModule.getListener().setPrefix("$buy ");
        //buyTrophy();
    }

    private void buyTrophy(){
        reg.registerCommand(new D4JCommandBuilder("trophy")
                .build((args, msg) -> {
                    final int PRICE = 50000000;
                    if(Points.hasEnoughMoney(msg.getAuthor(), PRICE)){
                        Market.purchaseTrophy(msg.getAuthor());
                        msg.getChannel().sendMessage(msg.getAuthor().mention()
                                + " You bought a :trophy: for **" + PRICE + "**");
                    } else {
                        msg.reply("You do not have enough money to buy a :trophy:");
                    }
                    System.out.println(UserData.trophies);
        }));
    }
}
