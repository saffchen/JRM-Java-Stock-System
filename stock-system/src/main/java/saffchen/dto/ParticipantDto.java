package saffchen.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParticipantDto implements Serializable {
    private String email;
    private String nickName;
}
