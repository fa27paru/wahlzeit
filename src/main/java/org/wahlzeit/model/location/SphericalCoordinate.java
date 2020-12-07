package org.wahlzeit.model.location;

public class SphericalCoordinate extends AbstractCoordinate {
    public double phi;
    public double theta;
    public double radius;

    public SphericalCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    public SphericalCoordinate() {
        this(0, 0, 0);
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public SphericalCoordinate asSphericalCoordinate() {
        return this;
    }

    @Override
    public double getCentralAngle(Coordinate other) {
        SphericalCoordinate otherSpherical = other.asSphericalCoordinate();
        double phiDiff = Math.abs(phi - otherSpherical.phi);
        double c = Math.sin(theta) * Math.sin(otherSpherical.theta) + Math.cos(theta) * Math.cos(otherSpherical.theta) * Math.cos(phiDiff);
        return Math.acos(c);
    }
}
