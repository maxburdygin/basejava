package com.bjava.storage;

import com.bjava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int indexToInsertTo = getIndex(r.getUuid());
        if (indexToInsertTo < 0)
            indexToInsertTo = Math.abs(indexToInsertTo + 1);
        for (int i = size; i >= indexToInsertTo ; i--) {
            storage[i + 1] = storage[i];
        }
        storage[indexToInsertTo] = r;
        size++;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = null;
            if (index < size - 1 && size > 1)
                System.arraycopy(storage, index + 1, storage, index, storage.length - index - 1);
            storage[size] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, getDummy(uuid));
    }

    private Resume getDummy(String uuid) {
        Resume dummy = new Resume();
        dummy.setUuid(uuid);
        return dummy;
    }

}
