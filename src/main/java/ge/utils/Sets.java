package ge.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sets {

    @SafeVarargs
    public static <E> Set<E> toSet(E... values) {
        if(values == null || values.length == 0) {
            return new HashSet<>();
        }
        return new HashSet<>(Arrays.asList(values));
    }

}
