package committee.nova.pollutive.util;

import committee.nova.pollutive.util.function.ShortConsumer;
import committee.nova.pollutive.util.function.ShortFunction;
import committee.nova.pollutive.util.function.ShortSupplier;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public final class OptionalShort implements IOptionalNumeric<Short> {
    private static final OptionalShort EMPTY = new OptionalShort();

    private final boolean isPresent;
    private final short value;

    private OptionalShort() {
        this.isPresent = false;
        this.value = 0;
    }

    public static OptionalShort empty() {
        return EMPTY;
    }

    private OptionalShort(short value) {
        this.isPresent = true;
        this.value = value;
    }

    public static OptionalShort of(short value) {
        return new OptionalShort(value);
    }

    @Override
    public Optional<Short> boxed() {
        return isPresent ? Optional.of(value) : Optional.empty();
    }

    @Override
    public byte getAsByte() {
        if (!isPresent) throw new NoSuchElementException("No value present");
        return (byte) value;
    }

    @Override
    public short getAsShort() {
        if (!isPresent) throw new NoSuchElementException("No value present");
        return value;
    }

    @Override
    public int getAsInt() {
        if (!isPresent) throw new NoSuchElementException("No value present");
        return value;
    }

    @Override
    public long getAsLong() {
        if (!isPresent) throw new NoSuchElementException("No value present");
        return value;
    }

    @Override
    public float getAsFloat() {
        if (!isPresent) throw new NoSuchElementException("No value present");
        return value;
    }

    @Override
    public double getAsDouble() {
        if (!isPresent) throw new NoSuchElementException("No value present");
        return value;
    }

    @Override
    public Number getAsNumber() {
        if (!isPresent) throw new NoSuchElementException("No value present");
        return value;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void ifPresent(ShortConsumer consumer) {
        if (isPresent) consumer.accept(value);
    }

    public short orElse(short other) {
        return isPresent ? value : other;
    }

    public short orElseGet(ShortSupplier other) {
        return isPresent ? value : other.getAsShort();
    }

    public <X extends Throwable> short orElseThrow(Supplier<X> exceptionSupplier) throws X {
        if (isPresent) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    public <U> Optional<U> map(ShortFunction<U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(mapper.apply(value));
        }
    }

    public <U> Optional<U> flatMap(ShortFunction<? extends Optional<? extends U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return Optional.empty();
        } else {
            @SuppressWarnings("unchecked")
            Optional<U> r = (Optional<U>) mapper.apply(value);
            return Objects.requireNonNull(r);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OptionalShort)) {
            return false;
        }

        OptionalShort other = (OptionalShort) obj;
        return (isPresent && other.isPresent)
                ? value == other.value
                : isPresent == other.isPresent;
    }

    @Override
    public int hashCode() {
        return isPresent ? Integer.hashCode(value) : 0;
    }

    @Override
    public String toString() {
        return isPresent
                ? String.format("OptionalShort[%s]", value)
                : "OptionalShort.empty";
    }
}