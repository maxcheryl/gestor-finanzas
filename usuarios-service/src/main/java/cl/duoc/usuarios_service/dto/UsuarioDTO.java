package cl.duoc.usuarios_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Integer id;

    @NotBlank(message = "Nombre no puede estar vacio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "Apellido paterno no puede estar vacio")
    @Size(max = 100, message = "El apellido paterno no puede superar los 100 caracteres")
    private String apellidoPaterno;

    @NotBlank(message = "Apellido materno no puede estar vacio")
    @Size(max = 100, message = "El apellido materno no puede superar los 100 caracteres")
    private String apellidoMaterno;
}