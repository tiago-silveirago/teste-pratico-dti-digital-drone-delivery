package com.tiago_silveirago.testepraticodtidigitaldronedelivery.utils;

public class DistanciaUtil {

    public static double calcularDistanciaEntrePontos(double[] ponto1, double[] ponto2) {
        double dx = ponto2[0] - ponto1[0];
        double dy = ponto2[1] - ponto1[1];
        return Math.sqrt(dx * dx + dy * dy);
    }
}