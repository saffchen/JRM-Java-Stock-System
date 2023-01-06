package saffchen.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDto implements Serializable {
    private String email;
    private String nickname;
}
