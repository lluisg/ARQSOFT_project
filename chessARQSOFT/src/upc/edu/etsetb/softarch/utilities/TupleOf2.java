/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.utilities;

import java.util.Objects;

/**
 *
 * @author Lluis
 * @param <T1>
 * @param <T2>
 */
public class TupleOf2<T1, T2> {
    private T1 first;
    private T2 second;

    public TupleOf2(T1 first, T2 second){
        this.first = first;
        this.second = second;
    }
    
    public void setFirst(T1 first) {
        this.first = first;
    }
    
    public void setSecond(T2 second) {
        this.second = second;
    }
    
    public T1 getFirst() {
        return this.first;
    }
    
    public T2 getSecond() {
        return this.second;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.first);
        hash = 71 * hash + Objects.hashCode(this.second);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TupleOf2 other = (TupleOf2) obj;
        if (!Objects.equals(this.first, other.first)) {
            return false;
        }
        if (!Objects.equals(this.second, other.second)) {
            return false;
        }
        return true;
    }
}
