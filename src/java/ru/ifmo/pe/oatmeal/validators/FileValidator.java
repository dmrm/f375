/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 *
 * @author D
 */
@FacesValidator("fileValidator")
public class FileValidator implements Validator{
    
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UploadedFile file = (UploadedFile)value;
        if(file == null){
            throw new ValidatorException(new FacesMessage("Please, specify file path"));
        }
         if (file.getSize() > MAX_FILE_SIZE) {
            throw new ValidatorException(new FacesMessage("Upoaded file exceeds size limit"));
        }
    }
}