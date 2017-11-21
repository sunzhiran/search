package edu.bjut.search.index;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 跳跃表
 */
public class SkipList<V extends Comparable<? super V>> {
    private static Logger logger = LoggerFactory.getLogger(SkipList.class);

    private SkipListNode[] root;
    private int maxLevel;


    class SkipListNode implements Comparable<V> {
        V value;
        SkipListNode next;
        SkipListNode down;
        public SkipListNode(V value) {
            value = this.value;
        }

        @Override
        public int compareTo(V o) {
            return value.compareTo(o);
        }
    }






}