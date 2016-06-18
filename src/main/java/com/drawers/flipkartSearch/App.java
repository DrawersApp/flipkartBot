package com.drawers.flipkartSearch;

import com.drawers.dao.packets.MqttProviderManager;
import org.drawers.bot.lib.DrawersBotStringHelp;
import org.drawers.bot.listener.DrawersMessageListener;
import org.drawers.bot.mqtt.DrawersBot;
import org.drawers.bot.util.SendMail;

/**
 * Initializing the bot.
 */
public class App implements DrawersMessageListener {

    private static DrawersBot bot;
    private static App client;
    private static MqttProviderManager mqttProviderManager;
    private String clientId;


    public App(String clientId, String password) {
        bot = new DrawersBot(clientId, password, this);
        mqttProviderManager = MqttProviderManager.getInstanceFor(bot);
        mqttProviderManager.setClientIdAndName(clientId, "Dictionary");
    }

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        System.out.println(DrawersBotStringHelp.getDrawersBotStringHelp().toJsonString());

        if(args.length != 3) {
            System.out.println("Usage: java DrawersClientCli <clientId> <password> <admin-email-id>");
        } else {
            String clientId = args[0];
            String password = args[1];
            String adminEmail = args[2];
            SendMail.getInstance().setAdminEmail(adminEmail);
            SendMail.getInstance().sendMail("Welcome to Drawers Bot", "Your bot is up and running now.");
            client = new App(clientId, password);
            client.clientId = clientId;
            client.startBot();
        }
    }

    protected void startBot() throws InterruptedException {
        FlipkartMessageListener flipkartMessageListener = new FlipkartMessageListener(bot, clientId);
        mqttProviderManager.addMessageListener(flipkartMessageListener);
        mqttProviderManager.addGroupMessageListener(flipkartMessageListener);
        bot.start();
        while (true) {
            Thread.sleep(10000000000000000L);
        }

    }


    @Override
    public void onConnected() {

    }
}