import java.util.stream.Stream;

public class core {
    public static void main(String[] args) {
        System.out.println("Hello world");
        Stream<String> namesStream = Stream.of("John", "Marry", "George", "Paul", "Alice", "Ann");

        namesStream
                .filter(e -> e.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }
}
