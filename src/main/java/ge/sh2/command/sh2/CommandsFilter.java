package ge.sh2.command.sh2;

import ge.sh2.core.Sh2Context;
import ge.sh2.core.object.command.CommandObject;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class CommandsFilter {

    public static List<CommandObject> filterCommands(Predicate<CommandObject> filter) {
        return Sh2Context.getCommands()
                .getAll()
                .stream()
                .filter(filter)
                .sorted(Comparator.comparing(CommandObject::getName))
                .toList();
    }

    public static List<CommandObject> getCustomCommands() {
        return filterCommands(CommandObject::isCustomCommand);
    }

    public static List<CommandObject> getInternalCommands() {
        return filterCommands(commandObject -> !commandObject.isCustomCommand());
    }

}
