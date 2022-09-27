package com.compilador;

import java.io.IOException;

import com.compilador.view.View;
import com.compilador.view.View2;

public class Main {

    public static void main(String[] args) throws IOException {
        View v = new View();
        View2 v2 = new View2();
        v.criaJanela();
        v2.criaJanela();
     }
}
