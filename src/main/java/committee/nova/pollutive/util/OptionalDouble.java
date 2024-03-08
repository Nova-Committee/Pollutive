package committee.nova.pollutive.util;

import committee.nova.pollutive.api.IHasVanillaImpl;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public final class OptionalDouble implements IOptionalNumeric<Double>, IHasVanillaImpl<java.util.OptionalDouble> {
    private static final OptionalDouble EMPTY = new OptionalDouble();

    private final boolean isPresent;
    private final double value;

    private OptionalDouble() {
        this.isPresent = false;
        this.value = Double.NaN;
    }

    public static OptionalDouble empty() {
        return EMPTY;
    }

    private OptionalDouble(double value) {
        this.isPresent = true;
        this.value = value;
    }

    public static OptionalDouble of(double value) {
        return new OptionalDouble(value);
    }

    @Override
    public Optional<Double> boxed() {
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
        return (float) value;
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

    public void ifPresent(DoubleConsumer consumer) {
        if (isPresent)
            consumer.accept(value);
    }

    public double orElse(double other) {
        return isPresent ? value : other;
    }

    public double orElseGet(DoubleSupplier other) {
        return isPresent ? value : other.getAsDouble();
    }

    public <X extends Throwable> double orElseThrow(Supplier<X> exceptionSupplier) throws X {
        if (isPresent) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    public <U> Optional<U> map(DoubleFunction<U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(mapper.apply(value));
        }
    }

    public <U> Optional<U> flatMap(DoubleFunction<? extends Optional<? extends U>> mapper) {
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

        if (!(obj instanceof OptionalDouble)) {
            return false;
        }

        OptionalDouble other = (OptionalDouble) obj;
        return (isPresent && other.isPresent)
                ? Double.compare(value, other.value) == 0
                : isPresent == other.isPresent;
    }

    @Override
    public int hashCode() {
        return isPresent ? Double.hashCode(value) : 0;
    }

    @Override
    public String toString() {
        return isPresent
                ? String.format("OptionalDouble[%s]", value)
                : "OptionalDouble.empty";
    }

    @Override
    public java.util.OptionalDouble toVanilla() {
        return isPresent ? java.util.OptionalDouble.of(value) : java.util.OptionalDouble.empty();
    }
}