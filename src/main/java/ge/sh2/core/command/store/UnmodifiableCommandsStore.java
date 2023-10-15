package ge.sh2.core.command.store;

import ge.sh2.core.object.command.CommandObject;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class UnmodifiableCommandsStore implements ICommandsStore {

    private final ICommandsStore commandsStore;

    public UnmodifiableCommandsStore(ICommandsStore commandsStore) {
        this.commandsStore = commandsStore;
    }

    @Override
    public void store(String name, CommandObject commandObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void store(CommandObject commandObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CommandObject get(String name) {
        return commandsStore.get(name);
    }

    @Override
    public List<CommandObject> getAll() {
        return commandsStore.getAll();
    }

    @Override
    public void merge(CommandsStore other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<CommandObject> iterator() {
        return commandsStore.iterator();
    }

    @Override
    public void forEach(Consumer<? super CommandObject> action) {
        commandsStore.forEach(action);
    }

    @Override
    public Spliterator<CommandObject> spliterator() {
        return commandsStore.spliterator();
    }

    @Override
    public String toString() {
        return commandsStore.toString();
    }

}
