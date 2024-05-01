package br.com.sigvet.sigvetapi.feature.photo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhotoResponseDTO {
    private String fileName;
    private String contentType;
    private String link;
    private Long size;

}
