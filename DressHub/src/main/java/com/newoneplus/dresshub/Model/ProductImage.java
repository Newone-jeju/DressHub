package com.newoneplus.dresshub.Model;

import com.sun.xml.internal.ws.api.FeatureListValidatorAnnotation;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long productId;
    String image;
    String imageSize;
}
