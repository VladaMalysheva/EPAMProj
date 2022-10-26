package com.example.epamproj.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static Logger log = LogManager.getLogger(CommandContainer.class.getName());

    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("register", new RegisterCommand());
        commands.put("login", new LoginCommand());
        commands.put("delete_user", new DeleteCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("getProducts", new GetProducts());
        commands.put("goCalculate", new GoCalculateCommand());
        commands.put("calculate", new CalculateCommand());
        commands.put("goOrder", new GoOrderCommand());
        commands.put("order", new OrderCommand());
    }

    private CommandContainer() {
    }

    public static Command getCommand(String commandName){
        Command res = commands.get(commandName);
        log.info("getCommand(): name of the found command => " + res.getClass());
        return res;
    }
}
