package com.aqualevel.model.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aqualevel.controllers.beans.ApplicationUtil;
import com.aqualevel.model.DaoUtil;
import com.aqualevel.model.Reservatorio;
import com.aqualevel.model.daos.interfaces.CrudPi;

@Component
public class ReservatorioDAO extends DaoUtil implements CrudPi<Reservatorio> {
	
	@Autowired
	TipoReservatorioDAO tipoReservatorio;
	
	@Autowired
	UsuarioDAO usuarioService;

	@Override
	public ArrayList<Reservatorio> getAll() {
		String sql = "select * from tb_reservatorio where id_user = ?";
		ArrayList<Reservatorio> reserv = new ArrayList<>();
		
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setLong(1, ApplicationUtil.getInstancia().getUsuarioLogado().getId());
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {				
				Reservatorio reservatorio = new Reservatorio();
				reservatorio.setId(rs.getLong("id"));
				reservatorio.setNome(rs.getString("nome"));
				reservatorio.setAltura(rs.getFloat("altura"));
				reservatorio.setRaioMenor(rs.getFloat("raio_menor"));
				reservatorio.setLargura(rs.getFloat("largura"));
				reservatorio.setRaio(rs.getFloat("raio"));
				reservatorio.setProfundidade(rs.getFloat("profundidade"));
				reservatorio.setTipo(tipoReservatorio.getOneId(rs.getLong("id_tipo")));
				reservatorio.setUsuario(usuarioService.getOneId(rs.getLong("id_user")));				
				reserv.add(reservatorio);
			}
			
			rs.close();
			super.destroiConnection();
			
		}catch(SQLException e) {
			System.out.println("Erro SQL" + e.getMessage());
		}
		
		return reserv;
	}

	@Override
	public Reservatorio getOneId(Long id) {
		String sql = "select * from tb_reservatorio where id = ?";
		Reservatorio reservatorio = new Reservatorio();
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {							
				reservatorio.setId(rs.getLong("id"));
				reservatorio.setNome(rs.getString("nome"));
				reservatorio.setAltura(rs.getFloat("altura"));
				reservatorio.setRaioMenor(rs.getFloat("raio_menor"));
				reservatorio.setProfundidade(rs.getFloat("profundidade"));
				reservatorio.setLargura(rs.getFloat("largura"));
				reservatorio.setRaio(rs.getFloat("raio"));
				reservatorio.setTipo(tipoReservatorio.getOneId(rs.getLong("id_tipo")));
				reservatorio.setUsuario(usuarioService.getOneId(rs.getLong("id_user")));				
			}
			
			rs.close();
			super.destroiConnection();
			
		}catch(SQLException e) {
			System.out.println("Erro SQL" + e.getMessage());
		}
		
		return reservatorio;
	}

	@Override
	public Reservatorio getOneName(String name) {
		String sql = "select * from tb_reservatorio where nome = ?";
		Reservatorio reservatorio = new Reservatorio();
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				
				
				reservatorio.setId(rs.getLong("id"));
				reservatorio.setNome(rs.getString("nome"));
				reservatorio.setAltura(rs.getFloat("altura"));
				reservatorio.setRaioMenor(rs.getFloat("raio_menor"));
				reservatorio.setProfundidade(rs.getFloat("profundidade"));
				reservatorio.setLargura(rs.getFloat("largura"));
				reservatorio.setRaio(rs.getFloat("raio"));
				reservatorio.setTipo(tipoReservatorio.getOneId(rs.getLong("id_tipo")));
				reservatorio.setUsuario(usuarioService.getOneId(rs.getLong("id_user")));
				
			}
			
			rs.close();
			super.destroiConnection();
			
		}catch(SQLException e) {
			System.out.println("Erro SQL" + e.getMessage());
		}
		
		return reservatorio;
	}

	@Override
	public boolean setOne(Reservatorio element) {
		String sql = "insert into tb_reservatorio (nome, altura, raio_menor, largura, raio, id_tipo, id_user, profundidade) values (?,?,?,?,?,?,?,?)";
		int retorno = 0;
		
		System.out.println(element.toString());
		
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			
			pst.setString(1, element.getNome());
			pst.setFloat(2, element.getAltura());
			pst.setFloat(3, element.getRaioMenor());
			pst.setFloat(4, element.getLargura());
			pst.setFloat(5, element.getRaio());
			pst.setLong(6, element.getTipo().getId());
			pst.setLong(7, element.getUsuario().getId());
			pst.setFloat(8, element.getProfundidade());
			
			
			retorno = pst.executeUpdate();
			pst.close();
			super.destroiConnection();
			
		} catch (SQLException e) {
			System.out.println("Erro SQL:" + e.getMessage());
		}
		
		return retorno > 0;
	}

	@Override
	public boolean setList(ArrayList<Reservatorio> lista) {
		String sql = "insert into tb_reservatorio (nome, altura, raio_menor, largura, raio, id_tipo, id_user, profundidade) values (?,?,?,?,?,?,?)";
		int retorno = 0;
		
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			
			for (Reservatorio element : lista) {
				
				pst.setString(1, element.getNome());
				pst.setFloat(2, element.getAltura());
				pst.setFloat(3, element.getRaioMenor());
				pst.setFloat(4, element.getLargura());
				pst.setFloat(5, element.getRaio());
				pst.setLong(6, element.getTipo().getId());
				pst.setFloat(7, element.getProfundidade());
				pst.setLong(8, element.getUsuario().getId());
				
			}		
			
			retorno = pst.executeUpdate();
			pst.close();
			super.destroiConnection();
			
		} catch (SQLException e) {
			System.out.println("Erro SQL:" + e.getMessage());
		}
		
		return retorno > 0;
	}

	@Override
	public Reservatorio updateOne(Reservatorio element) {
		String sql = "update tb_reservatorio set nome=?, altura=?, raio_menor=?, largura=?, raio=?, id_tipo=?, id_user=?, profundidade=? where id = ?";
		
		int retorno = 0;
		
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			
			pst.setString(1, element.getNome());
			pst.setFloat(2, element.getAltura());
			pst.setFloat(3, element.getRaioMenor());
			pst.setFloat(4, element.getLargura());
			pst.setFloat(5, element.getRaio());
			pst.setLong(6, element.getTipo().getId());
			pst.setLong(7, element.getUsuario().getId());
			pst.setFloat(8, element.getProfundidade());
			pst.setLong(9, element.getId());
			
			retorno = pst.executeUpdate();
			pst.close();
			super.destroiConnection();
			
		} catch (SQLException e) {
			System.out.println("Erro SQL:" + e.getMessage());
		}
		
		if (retorno > 0) {
			return getOneId(element.getId());
		}
		
		return null;
	}

	@Override
	public boolean deleteOne(Reservatorio element) {
		String sql = "DELETE FROM tb_reservatorio WHERE id = ?";
		
		int retorno = 0;
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setLong(1, element.getId());
			retorno = pst.executeUpdate();
			pst.close();
			super.destroiConnection();
		} catch (SQLException e) {
			System.out.println("Erro sql -->" + e.getMessage());
		}
		return retorno > 0;
	}

	@Override
	public boolean deleteAll() {
		String sql = "DELETE FROM tb_reservatorio";
		int retorno = 0;
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			retorno = pst.executeUpdate();
			pst.close();
			super.destroiConnection();
		} catch (SQLException e) {
			System.out.println("Erro sql -->" + e.getMessage());
		}
		return retorno > 0;
	}

}
