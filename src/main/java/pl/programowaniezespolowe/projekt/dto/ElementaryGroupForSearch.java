package pl.programowaniezespolowe.projekt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementaryGroupForSearch {

    private String code;
    private String name;
    private String synthesis;
    private String tasks;
}
