package com.github.caay2000.metropolis;

import com.github.caay2000.metropolis.model.Position;

public class DistanceCalculator {

    public enum Algorithm {
        HAVERSINE,
        VINCENTY
    }

    private static final double EARTH_RADIUS = 6371d;
    private static final Algorithm DEFAULT_ALGORITHM = Algorithm.HAVERSINE;

    public static double distanceBetween(Position origin, Position destination) {
        return distanceBetween(origin, destination, DEFAULT_ALGORITHM);
    }

    public static double distanceBetween(Position origin, Position destination, Algorithm algorithm) {
        if (algorithm == Algorithm.HAVERSINE) {
            return haversine(origin, destination);
        } else {
            return vincenty(origin, destination);
        }
    }

    public static Position getPositionOnRoute(Position origin, Position destination, double maxDistance) {

        double dLat = Math.toRadians(destination.getLat() - origin.getLat());
        double dLon = Math.toRadians(destination.getLng() - origin.getLng());

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(Math.toRadians(origin.getLat())) *
                        Math.cos(Math.toRadians(destination.getLat()));
        double c = 2 * Math.asin(Math.sqrt(a));
        double distance = EARTH_RADIUS * c * 1000;

        double newLat = origin.getLat() + Math.toDegrees(dLat / distance * maxDistance);
        double newLng = origin.getLng() + Math.toDegrees(dLon / distance * maxDistance);

        return new Position(newLat, newLng);
    }

    private static double haversine(Position origin, Position destination) {

        double dLat = Math.toRadians(destination.getLat() - origin.getLat());
        double dLon = Math.toRadians(destination.getLng() - origin.getLng());

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(Math.toRadians(origin.getLat())) *
                        Math.cos(Math.toRadians(destination.getLat()));
        double c = 2 * Math.asin(Math.sqrt(a));
        return EARTH_RADIUS * c * 1000;
    }

    private static double vincenty(Position origin, Position destination) {
        double a = 6378137, b = 6356752.314245, f = 1 / 298.257223563;
        double L = Math.toRadians(destination.getLng() - origin.getLng());
        double U1 = Math.atan((1 - f) * Math.tan(Math.toRadians(origin.getLat())));
        double U2 = Math.atan((1 - f) * Math.tan(Math.toRadians(destination.getLat())));
        double sinU1 = Math.sin(U1), cosU1 = Math.cos(U1);
        double sinU2 = Math.sin(U2), cosU2 = Math.cos(U2);
        double cosSqAlpha;
        double sinSigma;
        double cos2SigmaM;
        double cosSigma;
        double sigma;

        double lambda = L, lambdaP, iterLimit = 100;
        do {
            double sinLambda = Math.sin(lambda), cosLambda = Math.cos(lambda);
            sinSigma = Math.sqrt((cosU2 * sinLambda)
                    * (cosU2 * sinLambda)
                    + (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda)
                    * (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda)
            );
            if (sinSigma == 0) {
                return 0;
            }

            cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * cosLambda;
            sigma = Math.atan2(sinSigma, cosSigma);
            double sinAlpha = cosU1 * cosU2 * sinLambda / sinSigma;
            cosSqAlpha = 1 - sinAlpha * sinAlpha;
            cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;

            double C = f / 16 * cosSqAlpha * (4 + f * (4 - 3 * cosSqAlpha));
            lambdaP = lambda;
            lambda = L + (1 - C) * f * sinAlpha
                    * (sigma + C * sinSigma
                    * (cos2SigmaM + C * cosSigma
                    * (-1 + 2 * cos2SigmaM * cos2SigmaM)
            )
            );

        } while (Math.abs(lambda - lambdaP) > 1e-12 && --iterLimit > 0);

        if (iterLimit == 0) {
            return 0;
        }

        double uSq = cosSqAlpha * (a * a - b * b) / (b * b);
        double A = 1 + uSq / 16384
                * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
        double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));
        double deltaSigma =
                B * sinSigma
                        * (cos2SigmaM + B / 4
                        * (cosSigma
                        * (-1 + 2 * cos2SigmaM * cos2SigmaM) - B / 6 * cos2SigmaM
                        * (-3 + 4 * sinSigma * sinSigma)
                        * (-3 + 4 * cos2SigmaM * cos2SigmaM)));

        return b * A * (sigma - deltaSigma);
    }

}
