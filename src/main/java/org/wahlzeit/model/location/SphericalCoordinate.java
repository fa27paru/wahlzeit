package org.wahlzeit.model.location;

import org.wahlzeit.utils.conditions.Preconditions;
import org.wahlzeit.utils.conditions.Postconditions;

public class SphericalCoordinate extends AbstractCoordinate {
    protected double phi;
    protected double theta;
    protected double radius;

    public SphericalCoordinate(double phi, double theta, double radius) {
        Preconditions.assertArgumentNotNan(phi);
        Preconditions.assertArgumentNotNan(theta);
        Preconditions.assertArgumentNotNan(radius);
        Preconditions.assertArgumentWithinRange(phi, 0, Math.PI, "phi must be greater or equal 0 and smaller than pi");
        Preconditions.assertArgumentWithinRange(theta, 0, Math.PI, "theta must be greater or equal 0 and smaller than pi");
        Preconditions.assertNonNegativeArgument(radius, "the radius must not be negative");

        this.phi = phi;
        this.theta = theta;
        this.radius = radius;

        assertClassInvariants();
    }

    public SphericalCoordinate() {
        this(0, 0, 0);

        assertClassInvariants();
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();

        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);
        CartesianCoordinate result = new CartesianCoordinate(x, y, z);

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
        Preconditions.assertNotNullArgument(other);

        SphericalCoordinate otherSpherical = other.asSphericalCoordinate();
        double phiDiff = Math.abs(phi - otherSpherical.phi);
        double c = Math.sin(theta) * Math.sin(otherSpherical.theta)
                + Math.cos(theta) * Math.cos(otherSpherical.theta) * Math.cos(phiDiff);
        double result = Math.acos(c);

        Postconditions.assertResultNotNan(result);
        Postconditions.assertResultWithinRange(result, 0, Math.PI, "the resulting angle must be greater or equal 0 and smaller than pi");
        return result;
    }

    @Override
    protected void assertClassInvariants() {
        if (Double.isNaN(phi) || Double.isNaN(theta))
            throw new IllegalStateException("spherical angles can not be Nan");
        if (Double.isNaN(radius))
            throw new IllegalStateException("spherical radius can not be Nan");
        if (phi < 0 || phi >= Math.PI)
            throw new IllegalStateException("phi must be greater or equal 0 and smaller than pi");
        if (theta < 0 || theta >= Math.PI)
            throw new IllegalStateException("theta must be greater or equal 0 and smaller than pi");
        if (radius < 0)
            throw new IllegalStateException("spherical radius can not be negative");
        super.assertClassInvariants();
    }

    public double getPhi() {
        assertClassInvariants();

        double result = phi;

        Postconditions.assertResultNotNan(result);
        Postconditions.assertResultWithinRange(phi, 0, Math.PI, "phi must be greater or equal 0 and smaller than pi");
        return result;
    }

    public void setPhi(double phi) {
        Preconditions.assertArgumentNotNan(phi);
        Preconditions.assertArgumentWithinRange(phi, 0, Math.PI, "phi must be greater or equal 0 and smaller than pi");
        
        this.phi = phi;
        
        assertClassInvariants();
    }
    
    public double getTheta() {
        assertClassInvariants();
        
        double result = theta;
        
        Postconditions.assertResultNotNan(result);
        Postconditions.assertResultWithinRange(theta, 0, Math.PI, "theta must be greater or equal 0 and smaller than pi");
        return result;
    }

    public void setTheta(double theta) {
        Preconditions.assertArgumentNotNan(theta);
        Preconditions.assertArgumentWithinRange(theta, 0, Math.PI, "theta must be greater or equal 0 and smaller than pi");

        this.theta = theta;

        assertClassInvariants();
    }

    public double getRadius() {
        assertClassInvariants();

        double result = radius;

        Postconditions.assertResultNotNan(result);
        Postconditions.assertNonNegativeResult(result, "the radius must not be negative");
        return result;
    }

    public void setRadius(double radius) {
        Preconditions.assertArgumentNotNan(radius);
        Preconditions.assertNonNegativeArgument(radius, "the radius must not be negative");

        this.radius = radius;

        assertClassInvariants();
    }
}
