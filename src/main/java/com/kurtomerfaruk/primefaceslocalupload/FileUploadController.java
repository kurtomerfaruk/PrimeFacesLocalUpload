package com.kurtomerfaruk.primefaceslocalupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Omer Faruk Kurt
 * @Created on date 23/11/2017 14:55:47
 * @blog http://kurtomerfaruk.com
 * @email kurtomerfaruk@gmail.com
 */
@ManagedBean
@ApplicationScoped
public class FileUploadController implements java.io.Serializable {
    
    private static final long serialVersionUID = -563611737955708822L;
    
    public void handleFileUpload(FileUploadEvent event) {
        try {
            String fileName = "C:/temp/" + event.getFile().getFileName();
            
            File result = new File(fileName);
            
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            
            byte[] buffer = new byte[8192];
            
            int bulk;
            
            InputStream inputStream = event.getFile().getInputstream();
            
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                
                fileOutputStream.flush();
            }
            
            fileOutputStream.close();
            inputStream.close();
            
            FacesMessage msg = new FacesMessage("Success", event.getFile().getFileName() + " is uploaded");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        } catch (IOException e) {
            e.printStackTrace();
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "The files were not uploaded");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }
    
}
