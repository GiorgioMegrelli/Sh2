# Sh2

<b>Sh2 | Shori - "Universal" Shell</b>

The project is for fun and to learn Java annotations better

### How to run

To compile the project, run:
```shell
./gradlew clean build
```

The Sh2 jar is available in the build directory: `build/libs/`. To run it, use:

```shell
java -jar build/libs/Sh2-<version>.jar
```

You should see the output:

```

       .--.--.     ,---,          ,----,
      /  /    '. ,--.' |        .'   .' \
     |  :  /`. / |  |  :      ,----,'    |
     ;  |  |--`  :  :  :      |    :  .  ;
     |  :  ;_    :  |  |,--.  ;    |.'  /
      \  \    `. |  :  '   |  `----'/  ;
       `----.   \|  |   /' :    /  ;  /
       __ \  \  |'  :  | | |   ;  /  /-,
      /  /`--'  /|  |  ' | :  /  /  /.`|
     '--'.     / |  :  :_:,'./__;      :
       `--'---'  |  | ,'    |   :    .'
                 `--''      ;   | .'
                            `---'

      * Sh2 | Shori - Universal Shell *

```

To see all available commands run:
```shell
java -jar build/libs/Sh2-<version>.jar help -all
```

### How to register a command

To register a command you should annotate your command class with `@Command` and implement the interface `CommandInvokable`.

For example:

```java
@Command
public class YourCommand implements CommandInvokable {
    @Override
    public void invoke() throws Exception {
        // This method is invoked when a command is called
    }
}
```

If you want to use arguments and parameters in your command class, you muust add a field of your parameters class and annotate it. See an example:

```java
public class YourCommandParameters {
    private String option;

    @ParameterField(name = "optionName")
    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}

@Command
public class YourCommand implements CommandInvokable {
    @Parameters
    private YourCommandParameters parameters;

    @Override
    public void invoke() throws Exception {
        String option = parameters.getOption();
        // Use Sh2Context.IO().out() to print the data you want
        Sh2Context.IO().out().println(option);
    }
}
```
Sh2 will insert parsed parameters object before `invoke()` method is called, so you will be able to use it within your command.
If you want to use arguments in you command class add this getter and setter: `public String[] getArguments()` and `public void setArguments(String[])`
methods in parameters' class. You can also do it simplier by this 'shortcut':
```java
class YourCommandParameters extends ParametersWithArguments {
    // ...
}
```

<i>Check `src/main/java/ge/sh2/command/custom/` to see examples and additional details.</i>

### TODO List

- [ ] Update algorithm of finding setters and getters
- [ ] Add custom command arguments parser
- [ ] Add new commands
- [ ] Improve code quality (should be always always here...:D)
- [ ] Add CLI mode

## Project is WELCOME for modifications
