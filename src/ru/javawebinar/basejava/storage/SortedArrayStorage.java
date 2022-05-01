package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    protected void insert(Resume r, int indexToInsertTo) {
        indexToInsertTo = Math.abs(indexToInsertTo + 1);
        for (int i = size; i >= indexToInsertTo; i--) {
            storage[i + 1] = storage[i];
        }
        storage[indexToInsertTo] = r;
    }

    @Override
    protected void remove(int index) {
        storage[index] = null;
        if (index < size - 1 && size > 1)
            System.arraycopy(storage, index + 1, storage, index, storage.length - index - 1);
        storage[size] = null;
    }
}
