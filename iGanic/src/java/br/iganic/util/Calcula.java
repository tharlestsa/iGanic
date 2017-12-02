
package br.iganic.util;

/**
 *
 * @author tharles
 */
public class Calcula {

    public static double distanciaEntreDoisPontos(double lat1, double lng1, double lat2, double lng2, String unidade) {
        double theta = lng1 - lng2;
        double distancia = Math.sin(convertDegressToRadians(lat1)) * Math.sin(convertDegressToRadians(lat2)) + Math.cos(convertDegressToRadians(lat1)) * Math.cos(convertDegressToRadians(lat2)) * Math.cos(convertDegressToRadians(theta));
        distancia = Math.acos(distancia);
        distancia = convertRadianosToDegress(distancia);
        distancia = distancia * 60 * 1.1515;
        distancia = distancia * 1.609344;

        if (unidade == "M") {
            distancia = distancia * 1000.0;
        }

        return (distancia);
    }

    private static double convertDegressToRadians(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double convertRadianosToDegress(double rad) {
        return (rad * 180 / Math.PI);
    }
}
