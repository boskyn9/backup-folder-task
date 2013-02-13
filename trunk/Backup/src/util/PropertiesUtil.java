/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author boskyn9
 */
public class PropertiesUtil {
    
    static Properties prop = new Properties(); 
    
    public static void setDados(Dados dados){
        prop.setProperty("origem", dados.getOrigem());
        prop.setProperty("destino", dados.getDestino());
        
        if(dados.getTime()!=null){
            prop.setProperty("tempo", dados.getTime()+"");
        }
        if(dados.getTempoVida()!=null){
            prop.setProperty("tempoVida", dados.getTempoVida()+"");
        }
        try {
            prop.store(new FileOutputStream("config.properties"), null);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public static void getDados(){
        Dados dados = Dados.getDados();
        try {
            prop.load(new FileInputStream("config.properties"));
            dados.setOrigem(prop.getProperty("origem"));
            dados.setDestino(prop.getProperty("destino"));
            
            String tempo = prop.getProperty("tempo");
            if(tempo!=null && !tempo.isEmpty()){
                dados.setTime(new Long(tempo));
            }
            
            String tempoVida = prop.getProperty("tempoVida");
            if(tempoVida!=null && !tempoVida.isEmpty()){
                dados.setTempoVida(new Long(tempoVida));
            }
            
        } catch (IOException ex) {
            System.out.println("Arquivo de proprieadades (./config.properties) não encontrado.");
        }
    }
}
