package io.github.colindj1120.enhancedfx.utils.consumers;

/**
 * Represents a functional interface that accepts three arguments and performs an operation without returning any result. This is a specialized form of {@link java.util.function.Consumer} designed for
 * situations where an operation involves three input parameters. The {@code TriConsumer} interface facilitates operations on a triplet of data, enhancing the ability to perform complex data manipulation,
 * actions, or side effects involving three distinct pieces of information.
 *
 * <p>
 * The use of {@code TriConsumer} is particularly beneficial in scenarios requiring the concurrent handling of three variables, such as aggregating data from multiple sources, applying a set of operations to a
 * trio of objects, or executing side-effect based logic that does not produce a return value. This functional interface promotes clearer, more modular programming by abstracting multi-parameter operations into
 * succinct, functionally described units of logic.
 * </p>
 *
 * <p>
 * While {@code TriConsumer} does not inherently support method chaining due to its void return type, it plays a crucial role in scenarios where operations need to be performed that affect external state or
 * require the manipulation of multiple objects without the necessity of a result. It is ideally suited for procedural operations, such as logging, applying configurations, or other similar tasks.
 * </p>
 *
 * <p>
 * Example usage might involve combining information from three sources to perform an action, like updating a user profile, logging multi-dimensional data points, or complex event handling that requires three
 * distinct pieces of data.
 * </p>
 *
 * <p>
 * <em>Example:</em>
 * <pre>{@code
 * TriConsumer<String, Integer, Double> processRecord = (name, age, balance) -> {
 *     System.out.println("Processing record for: " + name + " Age: " + age + " Balance: " + balance);
 *     // Additional processing logic here
 * };
 * processRecord.accept("Alice", 42, 1000.00);
 * }</pre>
 * </p>
 *
 * @param <T>
 *         the type of the first argument to the operation
 * @param <U>
 *         the type of the second argument to the operation
 * @param <V>
 *         the type of the third argument to the operation
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
@FunctionalInterface
public interface TriConsumer<T, U, V> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t
     *         the first input argument
     * @param u
     *         the second input argument
     * @param v
     *         the third input argument
     */
    void accept(T t, U u, V v);
}
