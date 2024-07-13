package ar.com.codoacodo.db;

import java.util.Date;

public class Pelicula {

	private int id;
	private String titulo;
	private String genero;
	private int duracion;
	private String director;
	private String reparto;
	private String sinopsis;
	private String imagen;
	
	
	
	public Pelicula() {
		
	}
	public Pelicula(int id, String titulo, String genero, int duracion, String director,
			String reparto, String sinopsis, String imagen) {
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = duracion;
		this.director = director;
		this.reparto = reparto;
		this.sinopsis = sinopsis;
		this.imagen = imagen;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getReparto() {
		return reparto;
	}
	public void setReparto(String reparto) {
		this.reparto = reparto;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
}
