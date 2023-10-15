package ge.sh2.core.command.store;

import ge.sh2.core.object.CommandObject;
import ge.sh2.utils.exception.CommandNameDuplicateException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CommandsStore implements ICommandsStore {

    private final Map<String, CommandObject> entries = new HashMap<>();

    @Override
    public void store(String name, CommandObject commandObject) {
        name = validateName(name);
        if(entries.containsKey(name)) {
            throw new CommandNameDuplicateException(name);
        }
        entries.put(name, commandObject);
    }

    @Override
    public void store(CommandObject commandObject) {
        store(commandObject.getName(), commandObject);
    }

    @Override
    public CommandObject get(String name) {
        name = validateName(name);
        return entries.get(name);
    }

    @Override
    public List<CommandObject> getAll() {
        Stream<String> keys = entries.keySet().stream().sorted();
        return keys.map(entries::get).toList();
    }

    @Override
    public void merge(CommandsStore other) {
        for(CommandObject entry: other) {
            store(entry);
        }
    }

    @Override
    public Iterator<CommandObject> iterator() {
        return getAll().iterator();
    }

    @Override
    public void forEach(Consumer<? super CommandObject> action) {
        getAll().forEach(action);
    }

    @Override
    public Spliterator<CommandObject> spliterator() {
        return getAll().spliterator();
    }

    @Override
    public String toString() {
        return getAll().toString();
    }

    private String validateName(String name) {
        Objects.requireNonNull(name);
        return name.toLowerCase();
    }

    public static CommandsStore merge(CommandsStore ...stores) {
        CommandsStore merged = new CommandsStore();
        for(CommandsStore store: stores) {
            merged.merge(store);
        }
        return merged;
    }

}
