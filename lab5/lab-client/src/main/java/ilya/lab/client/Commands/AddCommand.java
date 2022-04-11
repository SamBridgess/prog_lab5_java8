package ilya.lab.client.Commands;

import ilya.lab.client.Exceptions.CtrlDException;
import ilya.lab.client.Exceptions.WrongFileFormatException;
import ilya.lab.client.IO.IOManager;
import ilya.lab.client.Utility.CollectionManager;

/**
 * add command
 */
public class AddCommand extends Command {
    private final CollectionManager manager;
    public AddCommand(IOManager io, CollectionManager manager) {
        super(0, io);
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) throws WrongFileFormatException, CtrlDException {
        manager.addNewElement(getIOManager());
    }
}
