package com.transtour.backend.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.transtour.backend.models.builder.ResponsableReservaBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "responsable_reserva")
@NoArgsConstructor @Getter @Setter
public class ResponsableReserva implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30)
	private String nombre;
	
	@Column(nullable = false, length = 30)
	private String apellido;
	
	@Column(nullable = false, length = 45)
	private String email;
	
	@Column(nullable = false, length = 15)
	private String telefono;
	
	
	public ResponsableReserva(ResponsableReservaBuilder builder) {
		this.id = builder.getId();
		this.nombre = builder.getNombre();
		this.apellido = builder.getApellido();
		this.email = builder.getEmail();
		this.telefono = builder.getTelefono();
	}
	
	private static final long serialVersionUID = 1L;
}