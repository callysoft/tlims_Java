package com.tlimskech.marketplace.core.data;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class CodeValue implements Serializable {

    String code;
    String name;
}
