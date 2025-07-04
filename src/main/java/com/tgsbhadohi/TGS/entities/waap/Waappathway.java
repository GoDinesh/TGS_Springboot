package com.tgsbhadohi.TGS.entities.waap;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Waappathway {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String authkey;
	private String route;
	private String url;
}
