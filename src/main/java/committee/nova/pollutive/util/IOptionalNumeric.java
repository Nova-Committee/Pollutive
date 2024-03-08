package committee.nova.pollutive.util;

import java.util.Optional;

public interface IOptionalNumeric<T extends Number> {
    Optional<T> boxed();

    byte getAsByte();

    short getAsShort();

    int getAsInt();

    long getAsLong();

    float getAsFloat();

    double getAsDouble();

    Number getAsNumber();
}
