package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        TorpedoStore store = new TorpedoStore(1);

        boolean result = store.fire(1);

        assertEquals(true, result);
    }

    @Test
    void fire_Success_DecreasesCount() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        boolean result = store.fire(1);

        assertTrue(result);
        assertEquals(4, store.getTorpedoCount());
    }

    @Test
    void fire_MoreTorpedoes_DecreasesCountByThatNumber() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        store.fire(3);

        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_Failure_CountUnchanged() {
        TorpedoStore store = new TorpedoStore(5, 1.0);

        boolean result = store.fire(1);

        assertFalse(result);
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_MoreThanAvailable_Throws() {
        TorpedoStore store = new TorpedoStore(1, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(2));
    }

    @Test
    void fire_Zero_Throws() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void isEmpty_NewStore_False() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertFalse(store.isEmpty());
    }

    @Test
    void isEmpty_AfterFiringLastTorpedo_True() {
        TorpedoStore store = new TorpedoStore(1, 0.0);

        store.fire(1);

        assertTrue(store.isEmpty());
    }
}