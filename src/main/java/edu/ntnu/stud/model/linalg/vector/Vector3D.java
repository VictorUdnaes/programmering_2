package edu.ntnu.stud.model.linalg.vector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class Vector3D implements Vector {
    private double x;
    private double y;
    private double z;

    // Constructor
    @JsonCreator
    public Vector3D(@JsonProperty("x") double x, @JsonProperty("y") double y, @JsonProperty("z") double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Method to add two vectors
    public static Vector3D add(Vector3D v1, Vector3D v2) {
        double x = v1.getX() + v2.getX();
        double y = v1.getY() + v2.getY();
        double z = v1.getZ() + v2.getZ();
        return new Vector3D(x, y, z);
    }

    // Method to subtract one vector from another
    public static Vector3D subtract(Vector3D v1, Vector3D v2) {
        double x = v1.getX() - v2.getX();
        double y = v1.getY() - v2.getY();
        double z = v1.getZ() - v2.getZ();
        return new Vector3D(x, y, z);
    }

    // Method to calculate dot product of two vectors
    public static double dotProduct(Vector3D v1, Vector3D v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
    }

    // Method to calculate cross product of two vectors
    public static Vector3D crossProduct(Vector3D v1, Vector3D v2) {
        double x = v1.getY() * v2.getZ() - v1.getZ() * v2.getY();
        double y = v1.getZ() * v2.getX() - v1.getX() * v2.getZ();
        double z = v1.getX() * v2.getY() - v1.getY() * v2.getX();
        return new Vector3D(x, y, z);
    }

    // Method to calculate magnitude (length) of the vector
    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    // Method to normalize the vector (make its magnitude 1)
    public void normalize() {
        float mag = magnitude();
        if (mag != 0) {
            x /= mag;
            y /= mag;
            z /= mag;
        }
    }

    // Getters and setters
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3D vector3D = (Vector3D) o;
        return Double.compare(vector3D.x, x) == 0 &&
            Double.compare(vector3D.y, y) == 0 &&
            Double.compare(vector3D.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}


