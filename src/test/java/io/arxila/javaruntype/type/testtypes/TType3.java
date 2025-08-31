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
package io.arxila.javaruntype.type.testtypes;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class TType3<Z,Y extends Number,X extends Comparable<? super Integer>&Serializable> 
        implements Map<Integer[],Y>, Serializable {

    private static final long serialVersionUID = -5810998990292352043L;

    public void clear() {
        // nothing
    }

    public boolean containsKey(Object key) {
        return false;
    }

    public boolean containsValue(Object value) {
        return false;
    }

    public Set<java.util.Map.Entry<Integer[], Y>> entrySet() {
        return null;
    }

    public Y get(Object key) {
        return null;
    }

    public boolean isEmpty() {
        return false;
    }

    public Set<Integer[]> keySet() {
        return null;
    }

    public Y put(Integer[] key, Y value) {
        return null;
    }

    public void putAll(Map<? extends Integer[], ? extends Y> m) {
        // nothing
    }

    public Y remove(Object key) {
        return null;
    }

    public int size() {
        return 0;
    }

    public Collection<Y> values() {
        return null;
    }

    
    
}
