package com.bjava.storage;

import com.bjava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(getAll(), null);
        size = 0;
    }

    public void update(Resume r) {
        if (indexOfMatchUuid(r.uuid) > -1)
            storage[indexOfMatchUuid(r.uuid)] = r;
        System.out.println("ERROR - no Resume has been found for updating");
    }

    public void save(Resume r) {
        if (r != null && get(r.uuid) == null) {
            storage[size()] = r;
            ++size;
        }
    }

    public Resume get(String uuid) {
        if (indexOfMatchUuid(uuid) > -1) {
            return storage[indexOfMatchUuid(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        if (indexOfMatchUuid(uuid) > -1) {
            storage[indexOfMatchUuid(uuid)] = storage[size() - 1];
            storage[size() - 1] = null;
            --size;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    public int size() {
        return size;
    }

    private int indexOfMatchUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return -1;
    }
}
