package com.example.springbootvalidation.validation;

import com.example.springbootvalidation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Component
public class ProductIdExistingValidator implements ConstraintValidator<ProductIdExisting, Long> {

    @Autowired
    private ProductService productService;

    @Override
    public boolean isValid(Long productId, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(productId) || !productService.findById(productId).isPresent();
    }
}
