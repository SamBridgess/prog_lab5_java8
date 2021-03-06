package ilya.lab.client.Commands;

import ilya.lab.client.IO.IOManager;
import ilya.lab.client.Utility.CollectionManager;
import ilya.lab.client.Utility.XmlParser;

import java.io.IOException;

/**
 * save command
 */
public class SaveCommand extends Command {
    private final CollectionManager manager;
    private final String path;
    public SaveCommand(IOManager io, CollectionManager manager, String path) {
        super(0, io);
        this.manager = manager;
        this.path = path;
    }

    /**
     * executes command with arguments
     *
     * @param args      arguments
     * @throws IOException
     */
    @Override
    public void execute(String[] args) throws IOException {
        XmlParser.convertCollectionToXml(manager, path);
        getIOManager().printConfirmation("Collection was saved successfully");
    }
}
