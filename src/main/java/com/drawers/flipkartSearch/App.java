package com.drawers.flipkartSearch;

import org.drawers.bot.DrawersClient;
import org.drawers.bot.dao.MqttChatMessage;
import org.drawers.bot.dto.DrawersMessage;
import org.drawers.bot.lib.DrawersBotString;
import org.drawers.bot.lib.DrawersBotStringHelp;
import org.drawers.bot.lib.Response;

/**
 * Initializing the bot.
 */
public class App extends DrawersClient {

    public App(String clientId, String password) {
        super(clientId, password);
    }

    static private FlipkartSearch flipkartSearch = new FlipkartSearch();

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(DrawersBotStringHelp.getDrawersBotStringHelp().toJsonString());

        if(args.length != 2) {
            System.out.println("Usage: java DictionaryBot <clientId> <password>");
        } else {
            String clientId = args[0];
            String password = args[1];
            App client = new App(clientId, password);
            client.startBot();
        }
    }

    @Override
    public DrawersMessage processMessageAndReply(DrawersMessage message) {
        try {
            DrawersBotString drawersBotString = DrawersBotString.fromString(message.getMessage());
            Response response = flipkartSearch.operate(drawersBotString);
            System.out.println(response);
            return new DrawersMessage(
                    message.getSender(),
                    response.toString(),
                    MqttChatMessage.ChatConstant.ChatType.QAR);
        } catch (Exception ex) {
            return new DrawersMessage(message.getSender(), "Something went wrong: " + ex.getLocalizedMessage());
        }
    }
}