package com.github.caay2000.metropolis;

/**
 * This is the implementation Haversine Distance Algorithm between two places
 *
 * @author ananth
 * R = earth’s radius (mean radius = 6,371km)
 * Δlat = lat2− lat1
 * Δlong = long2− long1
 * a = sin²(Δlat/2) + cos(lat1).cos(lat2).sin²(Δlong/2)
 * c = 2.atan2(√a, √(1−a))
 * d = R.c
 */

public class HaversineDistance {

    /**
     * @param args            arg 1- latitude 1
     *                        arg 2 — latitude 2
     *                        arg 3 — longitude 1
     *                        arg 4 — longitude 2
     * @param originLat
     * @param destinationLat
     * @param originLong
     * @param destinationLong
     */
    public static void main(Double originLat, Double destinationLat, Double originLong, Double destinationLong) {
        final int R = 6371; // Radious of the earth
        Double latDistance = toRad(destinationLat - originLat);
        Double lonDistance = toRad(destinationLong - originLong);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(originLat)) * Math.cos(toRad(destinationLat)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distance = R * c;

        System.out.println("The distance between two lat and long is::" + distance);

    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }

}
