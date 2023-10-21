javac -cp Pokemon.jar -d . *.java */*.java */*/*.java
jar cfm Lab7.jar META-INF/MANIFEST.mf -C . Lab2/ -C . ../Lab2/data

java -jar Lab7.jar