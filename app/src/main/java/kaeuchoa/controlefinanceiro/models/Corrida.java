package kaeuchoa.controlefinanceiro.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by kaeuc on 20/04/2017.
 *
 *  TODO: DATE PICKER : https://www.codeproject.com/Articles/1009239/Learn-Date-and-Time-in-Android
 */

public class Corrida {

    private String origem;
    private String destino;
    private String data;
    private double valor;

    public Corrida() {
    }

    public Corrida(String origem, String destino, String data, double valor) {
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.valor = valor;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getData() {
        return  data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
