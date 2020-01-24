

java -jar sablecc.jar -d src grammar/postfix.sablecc
javac -cp src -d bin src/postfix/Main.java
java -cp bin postfix.Main
