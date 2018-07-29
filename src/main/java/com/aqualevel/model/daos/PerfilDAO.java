package com.aqualevel.model.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.aqualevel.model.DaoUtil;
import com.aqualevel.model.Perfil;
import com.aqualevel.model.daos.interfaces.CrudPi;

@Component	
public class PerfilDAO extends DaoUtil implements CrudPi<Perfil> {

	@Override
	public ArrayList<Perfil> getAll() {
		String sql = "select * from tb_perfil";
		ArrayList<Perfil> perfis = new ArrayList<>();
		try {
			ResultSet rs = super.getStatement().executeQuery(sql);
			while(rs.next()) {
				Perfil perfil = new Perfil();
				perfil.setId(rs.getLong("id"));
				perfil.setNomePerfil(rs.getString("perfil"));
				perfis.add(perfil);
			}
			rs.close();			
		}catch(SQLException e) {
			System.out.println("Erro SQL" + e.getMessage());
		}finally {
			super.destroiConnection();
		}
		
		return perfis;
	}

	@Override
	public Perfil getOneId(Long id) {
		String sql = "select * from tb_perfil where id = ?";
		Perfil perfil = new Perfil();
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				perfil.setId(rs.getLong("id"));
				perfil.setNomePerfil(rs.getString("perfil"));
			}
			pst.close();
			rs.close();
						
		} catch (SQLException e) {
			System.out.println("Erro SQL ->>" + e.getMessage());
		}finally {
			super.destroiConnection();
		}
		return perfil;
	}

	@Override
	public Perfil getOneName(String name) {
		String sql = "select * from tb_perfil where LOWER(perfil) = ?";
		Perfil perfil = new Perfil();
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setString(1, name.toLowerCase());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				perfil.setId(rs.getLong("id"));
				perfil.setNomePerfil(rs.getString("perfil"));
			}
			pst.close();
			rs.close();
			super.destroiConnection();
		} catch (SQLException e) {
			System.out.println("Erro SQL ->>" + e.getMessage());
		}
		return perfil;
	}

	@Override
	public boolean setOne(Perfil element) {
		String sql = "insert into tb_perfil (perfil) values (?)";
		int retorno = 0;
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setString(1, element.getNomePerfil());
			retorno = pst.executeUpdate();
			pst.close();
			super.destroiConnection();
		} catch (SQLException e) {
			System.out.println("Erro SQL >>" + e.getMessage());
		}
		return retorno > 0;
	}

	@Override
	public boolean setList(ArrayList<Perfil> lista) {
		int retorno = 0;
		if(!lista.isEmpty()) {
			String sql = "insert into tb_perfil (perfil) values (?)";
			try {
				PreparedStatement pst = super.getPreparedStatement(sql);
				for (Perfil perfil : lista) {
					pst.setString(1, perfil.getNomePerfil());
				}
				retorno = pst.executeUpdate();
				pst.close();
				super.destroiConnection();
			} catch (SQLException e) {
				System.out.println("Erro SQL >>" + e.getMessage());
			}
		}
		return retorno > 0;
	}

	@Override
	public Perfil updateOne(Perfil element) {
		String sql = "UPDATE tb_perfil SET perfil=? WHERE id = ?";
		int retorno = 0;
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setString(1, element.getNomePerfil());
			pst.setLong(2, element.getId());
			retorno = pst.executeUpdate();
			pst.executeQuery();
			super.destroiConnection();
			
		} catch (SQLException e) {
			System.out.println("Erro SQL >>" + e.getMessage());
		}
		if(retorno > 0) {
			return getOneId(element.getId());
		}
		
		return null;
	}

	@Override
	public boolean deleteOne(Perfil element) {
		String sql = "DELETE FROM tb_perfil WHERE id = ?";
		
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
		String sql = "DELETE FROM tb_perfil";
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
