package com.drawers.flipkartSearch;

import com.drawers.dao.ChatConstant;
import org.drawers.bot.DrawersClient;
import org.drawers.bot.dto.DrawersMessage;
import org.drawers.bot.lib.DrawersBotString;
import org.drawers.bot.lib.DrawersBotStringHelp;
import org.drawers.bot.lib.Response;
import org.drawers.bot.util.SendMail;

/**
 * Initializing the bot.
 */
public class App extends DrawersClient {

    public App(String clientId, String password) {
        super(clientId, password);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(DrawersBotStringHelp.getDrawersBotStringHelp().toJsonString());

        if(args.length != 3) {
            System.out.println("Usage: java DrawersClientCli <clientId> <password> <admin-email-id>");
        } else {
            String clientId = args[0];
            String password = args[1];
            String adminEmail = args[2];
            SendMail.getInstance().setAdminEmail(adminEmail);
            SendMail.getInstance().sendMail("Welcome to Drawers Bot", "Your bot is up and running now.");
            App client = new App(clientId, password);
            client.startBot();
        }
    }

    @Override
    public DrawersMessage processMessageAndReply(DrawersMessage message) {
        try {
            DrawersBotString drawersBotString = DrawersBotString.fromString(message.getMessage());
            Response response = (new FlipkartSearch()).operate(drawersBotString);
            return new DrawersMessage(
                    message.getSender(),
                    response.toString(),
                    ChatConstant.ChatType.QAR);
        } catch (Exception ex) {
            return new DrawersMessage(message.getSender(), "Something went wrong: " + ex.getLocalizedMessage());
        }
    }
}