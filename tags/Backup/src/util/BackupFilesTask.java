/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author boskyn9
 */
public class BackupFilesTask extends TimerTask {

    String origem = null;
    String destino = null;
    Long tempoVida = null;
    JTextArea logArea;

    public BackupFilesTask(JTextArea logArea) throws Exception {
        this.logArea = logArea;
        Dados dados = Dados.getDados();
        destino = dados.getDestino();
        origem = dados.getOrigem();
        tempoVida = dados.getTempoVida();
        
        if (origem == null || origem.isEmpty()) {
            throw new Exception("Origem não definida. ("+origem+")");
        }
        if (destino == null || destino.isEmpty()) {
            throw new Exception("Destino não definido.("+destino+")");
        }
    }

    @Override
    public void run() {
        try {
            Date data = new Date();
            String filename = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(data);
            String logDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data);

            addLog(String.format("%s - iniciando backup!", logDate));

            File tempFile = new File(System.getProperty("java.io.tmpdir")+File.separator+new File(origem).getName());            
            Arquivo.copyFolder(new File(origem), tempFile);            
            FolderZiper.zipFolder(tempFile.getAbsolutePath(), destino + File.separator + filename + ".zip");
            addLog(String.format("%s - realizado com sucesso!", logDate));            
            tempFile.delete();
            
            if(tempoVida!=null && tempoVida > 0){
                String deletes = Arquivo.apagarArquivosZipDaPasta(destino, tempoVida);
                if(deletes!=null && !deletes.isEmpty()){
                    addLog("\n");
                addLog("Aqruivos apagados");
                addLog("-------------------------");
                addLog(deletes);
                addLog("-------------------------");
                }
                
            }            

        } catch (Exception ex) {
            addLog("Erro ao gerar o arquivo de backup!");
            addLog(ex.toString());
            Logger.getLogger(BackupFilesTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addLog(String log) {
        if (logArea != null) {
            logArea.setText(logArea.getText() + log + "\n");
        }
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }
}
