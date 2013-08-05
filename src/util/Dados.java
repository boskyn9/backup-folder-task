/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author boskyn9
 */
public class Dados {
    
    private String origem;
    private String destino;
    private Long time;
    private Long tempoVida;
    private static Dados dados;
    
    public Dados(){}
    
    public static Dados getDados(){
        if(dados==null) {
            dados = new Dados();
        }
        return dados;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
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

    public Long getTempoVida() {
        return tempoVida;
    }

    public void setTempoVida(Long tempoVida) {
        this.tempoVida = tempoVida;
    } 

    @Override
    public String toString() {
        return "Dados{" + "origem=" + origem + ", destino=" + destino + ", time=" + time + ", tempoVida=" + tempoVida + '}';
    }   
    
}
