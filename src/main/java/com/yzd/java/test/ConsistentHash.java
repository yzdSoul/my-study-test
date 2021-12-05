package com.yzd.java.test;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

public class ConsistentHash<T> {
    private final static HashFunction md5 = Hashing.md5();

    private final TreeMap<Long, T> hashRing = new TreeMap<Long, T>();
    private final int numberOfReplicas;
    private int count = 0;

    public ConsistentHash(int numberOfReplicas, Collection<T> nodes) {
        this.numberOfReplicas = numberOfReplicas;

        for (T node : nodes) {
            addNode(node);
        }
    }

    private long getHash(T node, int replicaId) {
        return getUInt32MD5(node.toString() + "#" + replicaId);
    }

    /**
     * @return first four bytes (unsigned 32bits integer) of md5 digest
     */
    public static long getUInt32MD5(Object object) {
        return md5.hashBytes(object.toString().getBytes()).asInt() & 0xffffffffL;
    }

    public void addNode(T node) {
        count++;
        for (int i = 0; i < numberOfReplicas; i++) {
            hashRing.put(getHash(node, i), node);
        }
    }

    public void removeNode(T node) {
        count--;
        for (int i = 0; i < numberOfReplicas; i++) {
            hashRing.remove(getHash(node, i));
        }
    }

    public T getNode(Object object) {
        if (hashRing.isEmpty()) {
            return null;
        }

        long hash = getUInt32MD5(object);
        Map.Entry<Long, T> entry = hashRing.ceilingEntry(hash);
        if (entry == null) {
            entry = hashRing.firstEntry();
        }

        return entry.getValue();
    }

    public int size() {
        return count;
    }


    public static void main(String[] args) {
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add("192.168.1.1");
        arrayList.add("192.168.1.2");
        arrayList.add("192.168.1.3");
        arrayList.add("192.168.1.4");
        ConsistentHash<Object> consistentHash = new ConsistentHash<>(5, arrayList);
        System.out.println(consistentHash.size());

    }
}
