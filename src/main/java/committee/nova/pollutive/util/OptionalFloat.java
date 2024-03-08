package committee.nova.pollutive.util;

import committee.nova.pollutive.util.function.FloatConsumer;
import committee.nova.pollutive.util.function.FloatSupplier;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

public final class OptionalFloat implements IOptionalNumeric<Float> {
    private static final OptionalFloat EMPTY = new OptionalFloat();

    private final boolean isPresent;
    private final float value;

    private OptionalFloat() {
        this.isPresent = false;
        this.value = Float.NaN;
    }

    public static OptionalFloat empty() {
        return EMPTY;
    }

    private OptionalFloat(float value) {
        this.isPresent = true;
        this.value = value;
    }

    public static OptionalFloat of(float value) {
        return new OptionalFloat(value);
    }

    @Override
    public Optional<Float> boxed() {
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
        return (short) value;
    }

    @Override
    public int getAsInt() {
        if (!isPresent) throw new NoSuchElementException("No value present");
        return (int) value;
    }

    @Override
    public long getAsLong() {
        if (!isPresent) throw new NoSuchElementException("No value present");
        return (long) value;
    }

    @Override
    public float getAsFloat() {
        if (!isPresent) throw new NoSuchElementException("No value present");
        return value;
    }

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

    public void ifPresent(FloatConsumer consumer) {
        if (isPresent)
            consumer.accept(value);
    }

    public float orElse(float other) {
        return isPresent ? value : other;
    }

    public float orElseGet(FloatSupplier other) {
        return isPresent ? value : other.getAsFloat();
    }

    public <X extends Throwable> float orElseThrow(Supplier<X> exceptionSupplier) throws X {
        if (isPresent) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OptionalFloat)) {
            return false;
        }

        OptionalFloat other = (OptionalFloat) obj;
        return (isPresent && other.isPresent)
                ? Float.compare(value, other.value) == 0
                : isPresent == other.isPresent;
    }

    @Override
    public int hashCode() {
        return isPresent ? Float.hashCode(value) : 0;
    }

    @Override
    public String toString() {
        return isPresent
                ? String.format("OptionalFloat[%s]", value)
                : "OptionalFloat.empty";
    }
}