package ar.com.codoacodo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaService {
	private Conexion conexion;
	
	public PeliculaService() {
		this.conexion=new Conexion();
	}
	
	public List<Pelicula> getAllPeliculas() throws SQLException, ClassNotFoundException{
		List<Pelicula> peliculas = new ArrayList<>();
		Connection con = conexion.getConnection();
		String sql="select * from pelicula";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Pelicula p = new Pelicula(
					rs.getInt("id"),
					rs.getString("titulo"),
					rs.getString("genero"),
					rs.getInt("duracion"),
					rs.getString("director"),
					rs.getString("reparto"),
					rs.getString("sinopsis"),
					rs.getString("imagen")
					);
			
			peliculas.add(p);
		}
		rs.close();
		ps.close();
		con.close();
		return peliculas;
	}
	
	public Pelicula getPeliculaById(int id) throws SQLException, ClassNotFoundException{
		Pelicula p = null;
		Connection con = conexion.getConnection();
		String sql="select * from pelicula where id = ? ";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			p = new Pelicula(
						rs.getInt("id"),
						rs.getString("titulo"),
						rs.getString("genero"),
						rs.getInt("duracion"),
						rs.getString("director"),
						rs.getString("reparto"),
						rs.getString("sinopsis"),
						rs.getString("imagen")
						);	
		}
		rs.close();
		ps.close();
		con.close();
		return p;
		
	}
	
	public void addPelicula(Pelicula p) throws SQLException, ClassNotFoundException {
		
		Connection con = conexion.getConnection();
		String sql="insert into pelicula (titulo, genero, duracion, director, reparto, sinopsis, imagen) "
				+ " values(?,?,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, p.getTitulo());
		ps.setString(2, p.getGenero());
		ps.setInt(3, p.getDuracion());
		ps.setString(4, p.getDirector());
		ps.setString(5, p.getReparto());
		ps.setString(6, p.getSinopsis());
		ps.setString(7, p.getImagen());
		
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	public void updatePelicula(Pelicula p) throws SQLException, ClassNotFoundException {
		
		Connection con = conexion.getConnection();
		String sql="update pelicula set titulo=?, genero=?, duracion=?, director=?, reparto=?, sinopsis=?, imagen=? "
				+ "where id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, p.getTitulo());
		ps.setString(2, p.getGenero());
		ps.setInt(3, p.getDuracion());
		ps.setString(4, p.getDirector());
		ps.setString(5, p.getReparto());
		ps.setString(6, p.getSinopsis());
		ps.setString(7, p.getImagen());
		ps.setInt(8, p.getId());
		
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	public void deletePelicula(int id) throws SQLException, ClassNotFoundException {
			
		Connection con = conexion.getConnection();
		String sql="delete from pelicula where id = ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
		con.close();
		
	}

}
