/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author boskyn9
 */
public class FileZipBackup implements FileFilter{

    @Override
    public boolean accept(File pathname) {
        if(pathname.getName().toLowerCase().endsWith(".zip")){
            return true;
        }
        return false;
    }
    
}
