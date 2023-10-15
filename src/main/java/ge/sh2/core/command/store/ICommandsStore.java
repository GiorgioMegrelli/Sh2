package ge.sh2.core.command.store;

import ge.sh2.core.object.CommandObject;

public interface ICommandsStore extends Iterable<CommandObject> {

    void store(String name, CommandObject commandObject);
    void store(CommandObject commandObject);

    CommandObject get(String name);

    void merge(CommandsStore other);

}
