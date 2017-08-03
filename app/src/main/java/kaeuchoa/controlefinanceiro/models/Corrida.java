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
    private GregorianCalendar data;
    private double valor;

    public Corrida() {
    }

    public Corrida(String origem, String destino, double valor) {
        this.origem = origem;
        this.destino = destino;
        this.data = new GregorianCalendar();
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
        Date currentDate = data.getTime();

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDateString = formatter.format(currentDate);

        return formattedDateString;
    }

    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
