package Requests;

import Actions.CommandInvoker;
import Collections.MapCommands;
import Other.ReadCommand;

import java.util.concurrent.Callable;

public class ExecuteCommand implements Callable<String> {
        private MapCommands mc;
        private ReadCommand com;

        public ExecuteCommand(MapCommands mc, ReadCommand com) {
            this.mc=mc;
            this.com=com;
        }

        @Override
        public String call() throws Exception {
            return CommandInvoker.getInstance().executeCom(mc, com);
        }
    }

