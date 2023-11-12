# Sh2

<b>Sh2 | Shori</b>

<b>"Universal" Shell</b>

The project for a fun and to learn java annotations better

### How to run

Compile the project:
```shell
./gradlew clean build
```

The Sh2 jar is available in dir `build/libs/`. To run the jar use:

```shell
java -jar build/libs/Sh2-<version>-all.jar
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
java -jar build/libs/Sh2-<version>-all.jar help -all
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

If you want to use arguments and parameters in your command and a field of your parameters class and annotated it as following:

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
        // Use Sh2Context.getIO() to print the data you want
        Sh2Context.getIO().println(option);
    }
}
```
Sh2 will insert parsed parameters class before `invoke()` method is run so you can use it in this method.
If you want to use arguments in you command class add `public String[] getArguments()` and `public void setArguments(String[])`
methods in parameters' class or simply do it:
```java
class YourCommandParameters extends ParametersWithArguments {
    // ...
}
```

<i>Check `src/main/java/ge/sh2/command/custom/` to see example and more details.</i>
