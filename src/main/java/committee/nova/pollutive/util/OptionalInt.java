package committee.nova.pollutive.util;

import committee.nova.pollutive.api.IHasVanillaImpl;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public final class OptionalInt implements IOptionalNumeric<Integer>, IHasVanillaImpl<java.util.OptionalInt> {
    private static final OptionalInt EMPTY = new OptionalInt();

    private final boolean isPresent;
    private final int value;

    private OptionalInt() {
        this.isPresent = false;
        this.value = 0;
    }

    public static OptionalInt empty() {
        return EMPTY;
    }

    private OptionalInt(int value) {
        this.isPresent = true;
        this.value = value;
    }

    public static OptionalInt of(int value) {
        return new OptionalInt(value);
    }

    @Override
    public Optional<Integer> boxed() {
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
        return (float) value;
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

    public void ifPresent(IntConsumer consumer) {
        if (isPresent)
            consumer.accept(value);
    }

    public int orElse(int other) {
        return isPresent ? value : other;
    }

    public int orElseGet(IntSupplier other) {
        return isPresent ? value : other.getAsInt();
    }

    public <X extends Throwable> int orElseThrow(Supplier<X> exceptionSupplier) throws X {
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

        if (!(obj instanceof OptionalInt)) {
            return false;
        }

        OptionalInt other = (OptionalInt) obj;
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
                ? String.format("OptionalInt[%s]", value)
                : "OptionalInt.empty";
    }

    @Override
    public java.util.OptionalInt toVanilla() {
        return isPresent ? java.util.OptionalInt.of(value) : java.util.OptionalInt.empty();
    }
}