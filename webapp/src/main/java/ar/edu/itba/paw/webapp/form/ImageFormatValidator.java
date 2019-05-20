package ar.edu.itba.paw.webapp.form;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.ByteArrayInputStream;

public class ImageFormatValidator implements ConstraintValidator<ImageFormat, MultipartFile> {
    public void initialize(ImageFormat constraint) {
    }

    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        try {
            return ImageIO.read(new ByteArrayInputStream(multipartFile.getBytes())) != null;
        } catch (Exception e){
            return true;
        }
    }
}
