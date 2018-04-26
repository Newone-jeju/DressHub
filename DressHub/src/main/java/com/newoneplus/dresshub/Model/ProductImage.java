package com.newoneplus.dresshub.Model;

import com.sun.xml.internal.ws.api.FeatureListValidatorAnnotation;
import lombok.Data;

@Data
public class ProductImage {
    Integer id;
    Integer productId;
    String image;
    String imageSize;
}
