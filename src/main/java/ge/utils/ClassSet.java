package ge.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ClassSet extends HashSet<Class<?>> {
    public static class ClassSetCollector implements Collector<Class<?>, ClassSet, ClassSet> {
        @Override
        public Supplier<ClassSet> supplier() {
            return ClassSet::new;
        }

        @Override
        public BiConsumer<ClassSet, Class<?>> accumulator() {
            return ClassSet::add;
        }

        @Override
        public BinaryOperator<ClassSet> combiner() {
            return (set0, set1) -> {
                set0.addAll(set1);
                return set0;
            };
        }

        @Override
        public Function<ClassSet, ClassSet> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.emptySet();
        }
    }

    public static ClassSetCollector collector() {
        return new ClassSetCollector();
    }
}
