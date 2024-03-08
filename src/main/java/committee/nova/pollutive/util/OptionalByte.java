package committee.nova.pollutive.util;

import committee.nova.pollutive.util.function.ByteConsumer;
import committee.nova.pollutive.util.function.ByteFunction;
import committee.nova.pollutive.util.function.ByteSupplier;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public final class OptionalByte implements IOptionalNumeric<Byte> {
    private static final OptionalByte EMPTY = new OptionalByte();

    private final boolean isPresent;
    private final byte value;

    private OptionalByte() {
        this.isPresent = false;
        this.value = 0;
    }

    public static OptionalByte empty() {
        return EMPTY;
    }

    private OptionalByte(byte value) {
        this.isPresent = true;
        this.value = value;
    }

    public static OptionalByte of(byte value) {
        return new OptionalByte(value);
    }

    @Override
    public Optional<Byte> boxed() {
        return isPresent ? Optional.of(value) : Optional.empty();
    }

    @Override
    public byte getAsByte() {
        if (!isPresent) throw new NoSuchElementException("No value present");
        return value;
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

    public void ifPresent(ByteConsumer consumer) {
        if (isPresent) consumer.accept(value);
    }

    public byte orElse(byte other) {
        return isPresent ? value : other;
    }

    public byte orElseGet(ByteSupplier other) {
        return isPresent ? value : other.getAsByte();
    }

    public <X extends Throwable> byte orElseThrow(Supplier<X> exceptionSupplier) throws X {
        if (isPresent) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    public <U> Optional<U> map(ByteFunction<U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(mapper.apply(value));
        }
    }

    public <U> Optional<U> flatMap(ByteFunction<? extends Optional<? extends U>> mapper) {
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

        if (!(obj instanceof OptionalByte)) {
            return false;
        }

        OptionalByte other = (OptionalByte) obj;
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
                ? String.format("OptionalByte[%s]", value)
                : "OptionalByte.empty";
    }
}