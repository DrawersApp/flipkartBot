package com.drawers.flipkartSearch;

import org.drawers.bot.lib.*;
import retrofit.RestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nishant.pathak on 01/05/16.
 */
public class FlipkartSearch implements Operation {

    private static IFlipkart flipkart;
    private static List<BotStringElement> botStringElements = new ArrayList<>();
    private static DrawersBotString search;

    @Override
    public Response operateInternal(DrawersBotString drawersBotString) {
        return flipkart.search(searchKey, 10);
    }

    private String searchKey;


    @Override
    public boolean validateAndParse(DrawersBotString drawersBotString) {
        if (drawersBotString.getBotStringElements() == null ||
                drawersBotString.getBotStringElements().isEmpty()
                || drawersBotString.getBotStringElements().size() != search.getBotStringElements().size()) {
            return false;
        }
        if (drawersBotString.getBotStringElements().size() != 2) return false;
        searchKey = drawersBotString.getBotStringElements().get(1).getText();

        return !(this.searchKey == null);
    }

    private enum OperationFlipkart {
        SEARCH
    }

    static {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://affiliate-api.flipkart.net")
                .build();
        flipkart = restAdapter.create(IFlipkart.class);
        botStringElements.add(new BotStringElement(BotStringType.U, "Find"));
        botStringElements.add(new BotStringElement(BotStringType.S, "Hats", null));
        search = new DrawersBotString(botStringElements, OperationFlipkart.SEARCH.name());
        DrawersBotStringHelp.getDrawersBotStringHelp().getDrawersBotStrings().add(search);
    }
}
