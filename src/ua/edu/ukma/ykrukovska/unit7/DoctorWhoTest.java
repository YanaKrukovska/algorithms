package ua.edu.ukma.ykrukovska.unit7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoctorWhoTest {

    @Test
    void oneElement() {
        Assertions.assertFalse(DoctorWho.findPairs("1488"));

    }

    @Test
    void twoSoulmates() {
        Assertions.assertTrue(DoctorWho.findPairs("1 1"));

    }

    @Test
    void twoButTooDifferent() {
        Assertions.assertFalse(DoctorWho.findPairs("1 1488"));

    }


    @Test
    void twoSocial() {
        Assertions.assertFalse(DoctorWho.findPairs("5 1 1 1 1"));

    }

    @Test
    void yanasWorstNightmare() {
        Assertions.assertTrue(DoctorWho.findPairs("4 4 3 3 2 2 2"));

    }

    @Test
    void yanasWorstNightmare1() {
        Assertions.assertFalse(DoctorWho.findPairs("3 3 1 1"));

    }
}