package bdacoes;

import bdacoes.PkgJanelas.Controllers.C_JanelaPrincipal;

public class BdAcoes {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.d3d","false");
        new C_JanelaPrincipal();
    }
}