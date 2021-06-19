package com.lucastieni.WordExtractor.api.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TUsuarioDTO {
	private String email;
	private String name;
	private String password;
}
