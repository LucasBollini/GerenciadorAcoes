package bdacoes.PkgGlobais;

import java.time.LocalDate;

public class ClasseAcao {
    private String nome;
    private LocalDate data;
    private float valor, corret, emol, pMedio;
    private int qtd;
    private String tipo;
    
    public ClasseAcao(String nome, String data, String qtd, String valor, String corret, String emol, String pMedio, String tipo){
        this.nome = nome;
        this.qtd = Integer.parseInt(qtd);
        this.data = LocalDate.parse(data);
        this.pMedio = Float.parseFloat(pMedio.replace(',', '.'));
        this.valor = Float.parseFloat(valor.replace(',', '.'));
        this.corret = Float.parseFloat(corret.replace(',', '.'));
        this.emol = Float.parseFloat(emol.replace(',', '.'));
        this.tipo = tipo;
    }
    
    public String getNome(){
        return nome;
    }
    
    public int getQtd() {
        return qtd;
    }
    
    public LocalDate getData() {
        return data;
    }

    public float getValor() {
        return valor;
    }

    public float getpMedio() {
        return pMedio;
    }
    
    public void setpMedio(float novoValor) {
        pMedio = novoValor;
    }
    
    public float getCorret() {
        return corret;
    }

    public float getEmol() {
        return emol;
    }
    
    public String getTipo(){
        return tipo;
    }
}