package ilya.lab.client.Commands;

import ilya.lab.client.Exceptions.CtrlDException;
import ilya.lab.client.Exceptions.WrongFileFormatException;
import ilya.lab.client.IO.IOManager;
import ilya.lab.client.Utility.LineExecuter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * execute_script command
 */
public class ExecuteScriptCommand extends Command {
    private HashMap<String, Command> commands;

    public ExecuteScriptCommand(IOManager io, HashMap<String, Command> commands) {
        super(1, io);
        this.commands = commands;
    }

    @Override
    public void execute(String[] args) throws IOException, CtrlDException, WrongFileFormatException {
        File file = new File(args[0]);
        getIOManager().setIsFile(true);
        try {
            if(!getIOManager().addFileToStack(file)) {
                getIOManager().printWarning("Recursion detected!");
                getIOManager().popFileStack();
                throw new WrongFileFormatException();
            }
            getIOManager().fillExecutionStack(file);
            getIOManager().printConfirmation("Starting to execute " + file.getName());
            while (!getIOManager().isLastFileExecuted()) {
                getIOManager().setIsFile(true);
                String command = getIOManager().getNextLine();
                LineExecuter.executeLine(command, commands, getIOManager());
            }
            getIOManager().popFileStack();
        } catch (IOException e) {
            getIOManager().printWarning("File not found!");
            return;
        } finally {
            getIOManager().setIsFile(false);
        }
        getIOManager().printConfirmation(file.getName() + " executed successfully");
    }
}

