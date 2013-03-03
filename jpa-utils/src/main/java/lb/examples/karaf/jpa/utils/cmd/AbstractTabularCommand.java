package lb.examples.karaf.jpa.utils.cmd;

/**
 *
 */
public abstract class AbstractTabularCommand extends AbstractCommand {
    private ShellTable table;

    /**
     * c-tor
     *
     * @param columns
     */
    public AbstractTabularCommand(String... columns) {
        table = new ShellTable(columns);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Object doExecute() throws Exception {
        table.clear();
        doExecuteCommand();
        table.print();

        return null;
    }

    /**
     *
     * @param args
     */
    protected void addRow(Object... args) {
        table.addRow(args);
    }

}
