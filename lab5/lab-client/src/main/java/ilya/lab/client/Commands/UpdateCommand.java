package ilya.lab.client.Commands;

import ilya.lab.client.Exceptions.CtrlDException;
import ilya.lab.client.Exceptions.WrongFileFormatException;
import ilya.lab.client.IO.IOManager;
import ilya.lab.client.Utility.CollectionManager;

/**
 * update command
 */
public class UpdateCommand extends Command {
    public UpdateCommand(IOManager io, CollectionManager manager) {
        super(1, io, manager);
    }

    @Override
    public void execute(String[] args) throws WrongFileFormatException, CtrlDException {
        try {
            getManager().updateRouteByID(Long.valueOf(args[0]), getIOManager());
        } catch (NumberFormatException e) {
            getIOManager().printWarning("Invalid command's arguments!");
            if (getIOManager().getIsFile()) {
                throw new WrongFileFormatException();
            }
        }
    }
}
