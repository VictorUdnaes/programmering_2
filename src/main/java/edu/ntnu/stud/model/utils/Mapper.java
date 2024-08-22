package edu.ntnu.stud.model.utils;

public class Mapper {
    private Mapper() {
    }
    /**
     * Maps a value from one range to another.
     *
     * @param value  The value to map.
     * @param start1 The start of the original range.
     * @param stop1  The end of the original range.
     * @param start2 The start of the new range.
     * @param stop2  The end of the new range.
     * @return The mapped value.
     */
    public static float map(float value, float start1, float stop1, float start2, float stop2) {
        return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
    }
}