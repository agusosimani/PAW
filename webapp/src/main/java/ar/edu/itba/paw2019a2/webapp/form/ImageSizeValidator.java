package ar.edu.itba.paw2019a2.webapp.form;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageSizeValidator implements ConstraintValidator<ImageSize, MultipartFile> {
    public void initialize(ImageSize constraint) {
    }

    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        return multipartFile.getSize() < 1024 * 1024;
    }
}