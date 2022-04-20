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
        Arrays.fill(storage, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) {
            System.out.println("Update resume with id " + r.getUuid());
            storage[index] = r;
        } else {
            System.out.println("ERROR - no Resume has been found for updating with id " + r.getUuid());
        }
    }

    public void save(Resume r) {
        if (r != null && get(r.getUuid()) == null) {
            if (size >= 10000) {
                System.out.println("Error - there is no space in storage to save resume with id " + r.getUuid());
            } else {
                System.out.println("Save resume with id " + r.getUuid());
                storage[size()] = r;
                ++size;
            }
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            System.out.println("Get resume with id " + uuid);
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            System.out.println("Delete resume with id " + uuid);
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
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

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
