package committee.nova.pollutive.util.function;

@FunctionalInterface
public interface ByteFunction<R> {
    R apply(byte value);
}
