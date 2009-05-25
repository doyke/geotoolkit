/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2008-2009, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2009, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotoolkit.referencing.operation.projection;

import org.junit.*;
import org.geotoolkit.test.Depend;

import static java.lang.Math.*;
import static java.lang.Double.*;
import static org.geotoolkit.referencing.operation.projection.UnitaryProjection.*;


/**
 * Tests the {@link UnitaryProjection} class. This class uses {@link Mercator}
 * for testing purpose, because it is the simpliest non-trivial projection.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @version 3.00
 *
 * @since 3.00
 */
@Depend(ProjectionParametersTest.class)
public final class UnitaryProjectionTest extends ProjectionTestCase {
    /**
     * Tolerance level for comparing floating point numbers.
     */
    private static final double TOLERANCE = 1E-12;

    /**
     * Natural logarithm of the pseudo-infinity as returned by Mercator formulas in the spherical
     * case, truncated to nearest integer. This is not a real infinity because there is no exact
     * representation of π/2 in base 2, so tan(π/2) is not positive infinity.
     */
    static final int LN_INFINITY = 37;

    /**
     * Creates a default test suite.
     */
    public UnitaryProjectionTest() {
        super(UnitaryProjection.class, null);
    }

    /**
     * Tests the value documented in the javadoc. Those value may be freely changed; those
     * tests exist only in increase the change that the documented values are right.
     */
    @Test
    public void testDocumentation() {
        double minutes = toDegrees(ANGLE_TOLERANCE) * 60;
        assertEquals("Documentation said 0.2\" precision.", 0.2, minutes*60, 0.1);
        assertEquals("Documentation said 6 km precision.", 6, minutes*1852, 0.5);

        minutes = toDegrees(ITERATION_TOLERANCE) * 60;
        assertEquals("Documentation said 1 mm precision.", 0.001, minutes*1852, 0.0005);
    }

    /**
     * Tests a few formulas used by the Mercator projection in the spherical case. This is a
     * little bit more a Java test than a Geotoolkit test (or to be more accurate, a test of our
     * understanding of the {@code java.lang.Math} library).
     *
     * {@preformat math
     *   Forward:  y = log(tan(π/4 + φ/2))
     *   Inverse:  φ = π/2 - 2*atan(exp(-y))
     * }
     */
    @Test
    public void testMath() {
        assertEquals("Forward 0°N",      0, log(tan(PI/4)),                   TOLERANCE);
        assertEquals("Inverse 0 m",      0, PI/2 - 2*atan(exp(0)),            TOLERANCE);
        assertEquals("Forward 90°S",     NEGATIVE_INFINITY, log(tan(0)),      TOLERANCE);
        assertEquals("Forward (90+ε)°S", NaN,  log(tan(-nextUp(0))),          TOLERANCE);
        assertEquals("Inverse -∞",       PI/2, atan(exp(-NEGATIVE_INFINITY)), TOLERANCE);
        assertEquals("Inverse -∞ appr.", PI/2, atan(exp(LN_INFINITY + 1)),    TOLERANCE);
        /*
         * tan(PI/2) do not produces positive infinity as we would expect, because there is no
         * exact representation of PI in base 2.  Experiments show that we get some high value
         * instead (1.633E+16 on my machine, having a logarithm of 37.332).
         */
        assertTrue  ("Forward 90°N",     1E+16 < tan(PI/2));
        assertTrue  ("Forward 90°N",     LN_INFINITY < log(tan(PI/2)));
        assertEquals("Forward (90+ε)°N", NaN, log(tan(nextUp(PI/2))),      TOLERANCE);
        assertEquals("Inverse +∞",       0, atan(exp(NEGATIVE_INFINITY)),  TOLERANCE);
        assertEquals("Inverse +∞ appr.", 0, atan(exp(-(LN_INFINITY + 1))), TOLERANCE);
    }

    /**
     * Tests the {@link UnitaryProjection#tsfn} method. This is also a tests of the forward
     * Mercator projection in the ellipsoidal case.
     *
     * {@preformat math
     *   Forward:  y = -log(tsfn(φ))
     *   Inverse:  φ = cphi2(exp(-y))
     * }
     */
    @Test
    public void testTsfn() {
        boolean ellipse = true;
        do {
            transform = MercatorTest.create(ellipse);
            assertEquals("Function contract",  NaN, tsfn(NaN),               TOLERANCE);
            assertEquals("Function contract",  NaN, tsfn(POSITIVE_INFINITY), TOLERANCE);
            assertEquals("Function contract",  NaN, tsfn(NEGATIVE_INFINITY), TOLERANCE);
            assertEquals("Function contract",    1, tsfn(0),                 TOLERANCE);
            assertEquals("Function contract",    0, tsfn(+PI/2),             TOLERANCE);
            assertTrue  ("Function contract",       tsfn(-PI/2)            > 1E+16);
            assertTrue  ("Out of bounds",           tsfn(+PI/2 + 0.1)      < 0);
            assertTrue  ("Out of bounds",           tsfn(-PI/2 - 0.1)      < 0);
            assertEquals("Out of bounds",       -1, tsfn(PI),                TOLERANCE);
            assertTrue  ("Out of bounds",           tsfn(PI*3/2)           < -1E+16);
            assertEquals("Function periodicity", 1, tsfn(2*PI),              TOLERANCE);
            assertEquals("Function periodicity", 0, tsfn(PI*5/2),            TOLERANCE);

            assertEquals("Forward 0°N",  0,                 -log(tsfn(0)),     TOLERANCE);
            assertEquals("Forward 90°N", POSITIVE_INFINITY, -log(tsfn(+PI/2)), TOLERANCE);
            assertTrue  ("Forward 90°S", -LN_INFINITY >     -log(tsfn(-PI/2)));
        } while ((ellipse = !ellipse) == false);
    }

    /**
     * Tests the {@link UnitaryProjection#cphi2} method. We expect it to be the converse
     * of the {@link #tsfn} function.  In theory only the range [-90° ... +90°] needs to
     * be tested. However the function still consistent in the range [-90° ... +270°] so
     * we test that range for tracking this fact.
     *
     * @throws ProjectionException Should never happen.
     */
    @Test
    public void testCphi2() throws ProjectionException {
        boolean ellipse = true;
        do {
            transform = MercatorTest.create(ellipse);
            tolerance = ellipse ? ITERATION_TOLERANCE : TOLERANCE;
            assertEquals("Function contract",  NaN,  cphi2(NaN),               tolerance);
            assertEquals("Function contract",  PI/2, cphi2(0),                 tolerance);
            assertEquals("Function contract",  PI/2, cphi2(MIN_VALUE),         tolerance);
            assertEquals("Function contract",  0,    cphi2(1),                 tolerance);
            assertEquals("Function contract", -PI/2, cphi2(MAX_VALUE),         tolerance);
            assertEquals("Function contract", -PI/2, cphi2(POSITIVE_INFINITY), tolerance);
            assertEquals("Out of bounds",   PI+PI/2, cphi2(NEGATIVE_INFINITY), tolerance);
            assertEquals("Out of bounds",   PI+PI/2, cphi2(-MAX_VALUE),        tolerance);
            assertEquals("Out of bounds",   PI,      cphi2(-1),                tolerance);
            assertEquals("Almost f. contract", PI/2, cphi2(-MIN_VALUE),        tolerance);
            /*
             * Using tsfn as a reference.
             */
            for (int i=-90; i<=270; i+=5) {
                final double phi  = toRadians(i);
                final double ts   = tsfn(phi);
                final double back = toDegrees(cphi2(ts));
                if (i <= 90) {
                    assertTrue("tsfn in valid range should be positive.", ts >= 0);
                } else {
                    assertTrue("tsfn in invalid range should be negative.", ts < 0);
                }
                assertEquals("Inverse function doesn't match.", i, back, tolerance);
            }
        } while ((ellipse = !ellipse) == false);
    }

    /**
     * Tests the {@link UnitaryProjection#qsfn} method.
     */
    @Test
    public void testQsfn() {
        boolean ellipse = true;
        do {
            transform = MercatorTest.create(ellipse);
            tolerance = TOLERANCE;
            for (int i=-100; i<=100; i++) {
                final double sinphi = i/100.0;
                final double q = qsfn(sinphi);
                assertEquals("Expected qsfn(-sinphi) == -qsfn(sinphi)", q, -qsfn(-sinphi), tolerance);
                assertEquals("Expected sinphi and qsfn(sinphi) to have same sign.", signum(sinphi), signum(q), 0);
            }
        } while ((ellipse = !ellipse) == false);
    }
}
