/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import ru.ifmo.pe.oatmeal.business.Affair;

/**
 *
 * @author D
 */
@ManagedBean
public class EvidenceMB {
    
    @EJB
    private Affair affair;
    
    private static final String eviPath = "files/evis/";
    private static final String refPath = "/evis/";
    
    private String path;
    private String description;
    private UploadedFile file;

    public String getPath() {
        return path;
    }

    public void setPath(String Path) {
        this.path = Path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void loadEvi(long affairId) throws IOException{
        String fileName = file.getName();
        String userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        byte[] byteFile = file.getBytes();
        path = saveFile(userName, fileName, byteFile);
        affair.createEvidence(affairId, description, path);
    }
    
    private String saveFile(String userName, String fileName, byte[] byteImg) throws IOException{
        path = userName + "_" + fileName;
        File f = new File(eviPath);
        f.mkdirs();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(eviPath + path));
        bos.write(byteImg);
        bos.flush();
        bos.close();
        return refPath + path;
    }
    
}
