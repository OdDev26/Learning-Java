package streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        List<Movie> movies= List.of(
                new Movie(18,"a",Genre.ACTION),
                new Movie(9,"b",Genre.COMEDY),
                new Movie(6,"c",Genre.ACTION)
        );

        long result = movies.stream().filter(movie -> movie.getLikes() > 11)
                .count();


        // Creating streams
        // 1. all collections have the stream() method
        // 2. We can also stream an array like this:
//        int [] numbers = {1,2,3};
//        Arrays.stream(numbers).forEach(n-> System.out.println(n));
        //3.  Stream.of(1,2,3);

        //To create an infinite stream of objects, we do:
        // 1. Stream<Double> doubleStream = Stream.generate(() -> Math.random()); // Infinte stream of random values
        //To limit the output of iterating through this stream
//        doubleStream.limit(20)
//                .forEach(n-> System.out.println(n)); // In streams we can have one or more transformations and a terminal operation (in this case is forEach)

        // 2. Using stream iterate method
//        Stream<Integer> stream = Stream.iterate(1, n -> n + 1); // An infinite stream of increments of 1
//        stream.limit(20)
//                .forEach(n-> System.out.println(n));


        // Mapping: The map and flatMap method are used for transformation of an object

//        movies.stream().map(movie -> movie.getTitle())
//                .forEach(m-> System.out.println(m));

        //There are variations of the map function like mapToInt( takes an integer and returns an integer)
//        movies.stream().mapToInt(movie-> 5)// map to int converts each movie to an int
//                .forEach(n-> System.out.println(n));

        // The flatMap is used to flatten a Stream<List<Object>> to a Stream<Object> e.g Stream<List<Integer>> to Stream<Integer>
        // We could also flatten a Stream<Set<Object>>, Stream<Array<Object>>, etc.
//        Stream.of(List.of(1,2,3), List.of(5,6,7))
//                .flatMap(list->list.stream()) // with this we flatten the list to just its objects with no list
//                .forEach(n-> System.out.println(n));

        // NOTE: STREAM methods that don't return anything e.g forEach are called terminal operations, while those that return a stream
        // e.g map, filter are called intermediate operations meaning we can chain other operations to them
        // Without the terminal operations nothing is done because this is when the stream output is consumed and

        // Slicing streams
        // a. limit() method
//        movies.stream().limit(2).forEach(m-> System.out.println(m.getTitle()));

        // b. skip() method
        // movies.stream().skip(2).forEach(m-> System.out.println(m.getTitle())); // This would skip the first 2 items and print the last only

        // c. takeWhile() method -> this would stop taking items from a collection when the condition is no longer satisfied
//        movies.stream().takeWhile(m-> m.getLikes() > 11).forEach(m-> System.out.println(m.getTitle()));

//        d. dropWhile() method -> this would skip all items that meet a condition and take the rest (not advisable)
//        movies.stream().dropWhile(m-> m.getLikes() >= 18).forEach(m-> System.out.println(m.getTitle()));


        // SORTING STREAMS
//        movies.stream().sorted((a,b)->a.getTitle().compareTo(b.getTitle())) // We sort using the comparable interface which is a functional interface
//                .forEach(movie -> System.out.println(movie.getTitle()));

        // line 74 - 75 can be rewritten as
//        movies.stream().sorted(Comparator.comparing(movie -> movie.getTitle())).
//                forEach(movie -> System.out.println(movie.getTitle()));

//        movies.stream().sorted(Comparator.comparing(Movie::getLikes)). // Sorts movies by likes in ascending order
//                forEach(movie -> System.out.println(movie.getTitle()));

        // To sort a collection in a descending order
//        movies.stream().sorted(Comparator.comparing(Movie::getLikes).reversed()) // This would sort the items in the collection in a descending order by likes
//                .forEach(movie -> System.out.println(movie.getTitle()));

        // GETTING UNIQUE ELEMENTS
//        movies.stream().map(movie -> movie.getTitle())
//                .distinct() // We can only return distinct values from a list of integers, strings but not objects
//                .forEach(title -> System.out.println(title));

        // THE PEEK OPERATION: USED TO GET THE OUTPUT OF EACH STAGE OF THE STREAM OPERATION (Useful for debugging)
//        movies.stream().map(m->m.getTitle()).peek(t-> System.out.println(t)) // with this we can see the outputs of the map method per time
//                .forEach(m-> System.out.println(m));

        // REDUCERS (Used to reduce a stream to a desired output) => They are all terminal operations
        // 1. count() e.g
//        long count =movies.stream().count();
        // 2. anyMatch, returns a boolean if an item in a collection match a condition
//        boolean anyMatch = movies.stream().anyMatch(movie -> movie.getLikes() > 20);
        // 3. allMatch returns a boolean if all items in a collection match a condition
//        boolean allMatch = movies.stream().allMatch(movie -> movie.getLikes() > 20);
//         4. noneMatch returns a boolean if no item in a collection match a condition
//        boolean noneMatch = movies.stream().noneMatch(movie -> movie.getLikes() > 20);
        // 5. findFirst returns an optional of the first item in a movie collection
//        Movie movie = movies.stream().findFirst().get();
        // 6. max returns the largest element in a collection based on a comparator
//        Movie max = movies.stream().max(Comparator.comparing(Movie::getLikes)).get();

        //  The reduce() operator: used to sum the elements in different objects in a collection, e.g to sum all the likes in the movies collection
//        Integer sum = movies.stream().map(movie -> movie.getLikes())
//                .reduce((a, b) -> a + b) // We can rewrite this as reduce(Integer::sum)
//                .get();
//        System.out.println(sum);

        // To ensure we don't get an exception if the stream is empty, we use orElse() after reduce()
//        Integer sum1 = movies.stream().map(movie -> movie.getLikes())
//                .reduce((a, b) -> a + b).orElse(0);
//
//        System.out.println(sum1);

      // We can also use the reduce() method like this:
//        Integer sum3= movies.stream().map(movie -> movie.getLikes())
//                .reduce(0,(a,b)->a+b);// where 0 is the initial sum
//        System.out.println(sum);

        // Collectors: This are used to collect(convert) a stream into a data structure (e.g list, set, map, String, integer, etc.)
//        List<Integer> integerList = movies.stream().map(movie -> movie.getLikes())
//                .collect(Collectors.toList());
//         movies.stream().map(movie -> movie.getLikes())
//                .collect(Collectors.toSet());

//        Map<String, Integer> map = movies.stream()
//                .collect(Collectors.toMap(m -> m.getTitle(), m -> m.getLikes()));
//        System.out.println(map);

        // using the Collectors to sum fields e.g the total of likes in a collection
//        Integer sum = movies.stream().collect(Collectors.summingInt(movie -> movie.getLikes())); // sums all the likes and return the result as an int
//        System.out.println(sum);

//        Double sum = movies.stream().collect(Collectors.summingDouble(movie -> movie.getLikes())); // sums all the likes and return the result as a double
//        System.out.println(sum);

//        IntSummaryStatistics collect = movies.stream().filter(movie -> movie.getLikes() > 10)
//                .collect(Collectors.summarizingInt(m -> m.getLikes()));// returns the count of movies with likes greater than 10, returns the sum of the likes greater than 20, their average, etc in integer values,
        // we also have summarizingLong( which returns the values as  long), etc.

        // We can also use collectors to join strings with a delimiter (optional) and return a concatenated string as the result
//        String output = movies.stream().filter(movie -> movie.getLikes() > 5)
//                .map(movie -> movie.getTitle())
//                .collect(Collectors.joining("-"));
//        System.out.println(output);

        // Grouping: We can group a genre to a list of movies by doing
        Map<Genre, List<Movie>> result2 = movies.stream().collect(Collectors.groupingBy(Movie::getGenre)); // groups movies by genre
//        System.out.println(result2);
        // We can also get the count of movies in each category by doing:
        Map<Genre, Long> result3 = movies.stream().collect(Collectors.groupingBy(Movie::getGenre, Collectors.counting()));
        // We can also join the movie titles in each group by a delimiter
        Map<Genre, String> result4 = movies.stream().collect(Collectors.groupingBy(Movie::getGenre,
                Collectors.mapping(Movie::getTitle, Collectors.joining(",")))); // This would join the movies in each group by , e.g {ACTION=a,c, COMEDY=b}


        // Partitioning => We use this to group objects that satisfy a condition or not into true or false
        Map<Boolean, String> result5 = movies.stream().collect(Collectors.partitioningBy(movie -> movie.getLikes() > 10, Collectors.mapping(Movie::getTitle,
                Collectors.joining("-"))));

        // To work with primitive values, it's more efficient to work with one of the specialized types e.g IntStream, DoubleStream and LongStream
//        IntStream.of(1,2); // To generate a finite stream of integers

        IntStream.rangeClosed(0,5)  // This creates a stream of nos 0 to 5
                .forEach(System.out::println);

//        IntStream.range(0,5)  // This creates a stream of nos 0 to 4
//                .forEach(System.out::println);


    }
}
