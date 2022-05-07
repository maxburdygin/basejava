package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.awt.image.VolatileImage;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume r = new Resume("123");
        storage.save(r);
        //todo change some fields
        storage.update(r);
        assertEquals(r, storage.get("123"));
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(3, storage.getAll().length);
    }

    @Test
    public void save() throws Exception {
        Resume resume = new Resume("new");
        storage.save(resume);
        assertEquals("new", storage.get("new").getUuid());
    }

    @Test
    public void delete() throws Exception {
        storage.delete("uuid1");
        assertEquals(2, storage.size());
    }

    @Test
    public void get() throws Exception {
        assertEquals("uuid1", storage.get("uuid1").getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(new Resume("uuid1"));
    }

    @Test(expected = StorageException.class)
    public void saveStorageOverflow() {
        int length = 10000 - storage.size();
        for (int i = 0; i < length ; i++) {
            storage.save(new Resume(String.valueOf(i)));
        }
        System.out.println(storage.size());
        storage.save(new Resume("ahahah"));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("ahaha");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("ahaha"));
    }
}