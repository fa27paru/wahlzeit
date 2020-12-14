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
        assertClassInvariants();

        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);
        CartesianCoordinate result = new CartesianCoordinate(x, y, z);

        result.assertClassInvariants();
        return result;
    }

    @Override
    public SphericalCoordinate asSphericalCoordinate() {
        assertClassInvariants();

        return this;
    }

    @Override
    public double getCentralAngle(Coordinate other) {
        assertClassInvariants();
        assertNotNullArgument(other);

        SphericalCoordinate otherSpherical = other.asSphericalCoordinate();
        double phiDiff = Math.abs(phi - otherSpherical.phi);
        double c = Math.sin(theta) * Math.sin(otherSpherical.theta) + Math.cos(theta) * Math.cos(otherSpherical.theta) * Math.cos(phiDiff);
        double result = Math.acos(c);

        assertResultNotNan(result);
        return result;
    }

    @Override
    protected void assertClassInvariants() {
        if(Double.isNaN(phi) || Double.isNaN(theta)) throw new IllegalStateException("Spherical angles can not be Nan");
        if(Double.isNaN(radius)) throw new IllegalStateException("Spherical radius can not be Nan");
        if(radius < 0) throw new IllegalStateException("Spherical radius can not be negative");
        super.assertClassInvariants();
    }
}
