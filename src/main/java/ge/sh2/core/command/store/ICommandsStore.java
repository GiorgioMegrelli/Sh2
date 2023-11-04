package ge.sh2.core.command.store;

import ge.sh2.core.object.command.CommandObject;

import java.util.List;
import java.util.function.Predicate;

public interface ICommandsStore extends Iterable<CommandObject> {

    void store(String name, CommandObject commandObject);
    void store(CommandObject commandObject);

    CommandObject get(String name);
    List<CommandObject> getAll();

    List<CommandObject> filter(Predicate<CommandObject> filterFunction);

    default List<CommandObject> getInternals() {
        return filter(commandObject -> !commandObject.isCustomCommand());
    }

    default List<CommandObject> getCustoms() {
        return filter(CommandObject::isCustomCommand);
    }

    void merge(CommandsStore other);

}
