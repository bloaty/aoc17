Building
--------

From the root of the project, run `./gradlew shadowJar`. This will download Gradle, build the source, and deposit a JAR into `./build/libs`.

Running
-------

After building the JAR, you can run it with Java (version 8 or higher):

```bash
java -jar ./build/libs/aoc17-sources.jar --help 
```

IDE Support
-----------

To import the project easily into an IDE, generate the necessary project files: `./gradlew eclipse` or `./gradlew idea`, depending on your religion.
