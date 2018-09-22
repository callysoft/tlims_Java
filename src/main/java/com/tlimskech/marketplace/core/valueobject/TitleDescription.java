package com.tlimskech.marketplace.core.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TitleDescription implements Serializable {

    @NotBlank(message = "Title is required *")
    @Column(nullable = false)
    private String title;
    @Lob
    private String description;
}
