/*
 * =========================================================================
 *
 *   Copyright (c) 2009-2025 Arxila OSS (https://arxila.io)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *   implied. See the License for the specific language governing
 *   permissions and limitations under the License.
 *
 * =========================================================================
 */
package io.arxila.javaruntype.testtypes;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class BoundedTypes {

    public static class NoGenerics {}

    public static class One<E> {}

    public static class Two<E,T> {}

    // T extends E
    public static class Bounded1<E,T extends E> {}

    // T extends Serializable
    public static class Bounded2<E,T extends Serializable> {}

    // E extends Serializable, and T extends E
    public static class Bounded3<E extends Serializable, T extends E> {}

    // X extends Collection<T> (avoid multiple bounds after a type variable)
    public static class Bounded4<E,T,X extends Collection<T>> {}

    // X extends Collection<? extends T> (avoid multiple bounds after a type variable)
    public static class Bounded5<E,T,X extends Collection<? extends T>> {}

    public static class GenericBound1<E, T extends List<E>> {}

    public static class GenericBound2<T extends List<? extends Number>> {}

    public static class ArrayInBound1<T extends List<String[]>> {}

    public static class ArrayInBound2<T extends Collection<int[][]>> {}

}
