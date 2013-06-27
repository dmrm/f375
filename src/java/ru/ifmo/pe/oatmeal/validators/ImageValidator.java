/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 *
 * @author D
 */
@FacesValidator("imageValidator")
public class ImageValidator extends FileValidator{
    
    private List<String> imageTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        super.validate(context, component, value);
        UploadedFile file = (UploadedFile)value;
        String type = file.getContentType();
        if(!isImage(type)){
            throw new ValidatorException(new FacesMessage("Invalid file type"));
        }
    }
    
    private boolean isImage(String type){
        return imageTypes.contains(type);
    }
    
}
