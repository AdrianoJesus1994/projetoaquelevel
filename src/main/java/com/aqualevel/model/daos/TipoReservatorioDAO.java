package com.aqualevel.model.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.aqualevel.model.DaoUtil;
import com.aqualevel.model.TipoReservatorio;
import com.aqualevel.model.daos.interfaces.CrudPi;


@Component	
public class TipoReservatorioDAO extends DaoUtil implements CrudPi<TipoReservatorio> {

	@Override
	public ArrayList<TipoReservatorio> getAll() {
		String sql = "select * from tb_tipo_reservatorio";
		ArrayList<TipoReservatorio> tipos = new ArrayList<>();
		try {
			ResultSet rs = super.getStatement().executeQuery(sql);
			while(rs.next()) {
				TipoReservatorio type = new TipoReservatorio();
				type.setId(rs.getLong("id"));
				type.setTipo(rs.getString("tipo"));
				tipos.add(type);
			}
			
			rs.close();
			super.destroiConnection();
			
		}catch(SQLException e) {
			System.out.println("Erro SQL" + e.getMessage());
		}
		
		return tipos;
	}

	@Override
	public TipoReservatorio getOneId(Long id) {
		String sql = "select * from tb_tipo_reservatorio where id = ?";
		TipoReservatorio tipo = new TipoReservatorio();
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				tipo.setId(rs.getLong("id"));
				tipo.setTipo(rs.getString("tipo"));
			}
			pst.close();
			rs.close();
			super.destroiConnection();
			
		} catch (SQLException e) {
			System.out.println("Erro SQL ->>" + e.getMessage());
		}
		return tipo;

	}

	@Override
	public TipoReservatorio getOneName(String name) {
		String sql = "select * from tb_tipo_reservatorio where tipo = ?";
		TipoReservatorio tipo = new TipoReservatorio();
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				tipo.setId(rs.getLong("id"));
				tipo.setTipo(rs.getString("perfil"));
			}
			pst.close();
			rs.close();
			super.destroiConnection();
		} catch (SQLException e) {
			System.out.println("Erro SQL ->>" + e.getMessage());
		}
		return tipo;
	}

	@Override
	public boolean setOne(TipoReservatorio element) {
		String sql = "insert into tb_tipo_reservatorio (tipo) values (?)";
		int retorno = 0;
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setString(1, element.getTipo());
			retorno = pst.executeUpdate();
			pst.close();
			super.destroiConnection();
		} catch (SQLException e) {
			System.out.println("Erro SQL >>" + e.getMessage());
		}
		return retorno > 0;
	}

	@Override
	public boolean setList(ArrayList<TipoReservatorio> lista) {
		int retorno = 0;
		if(!lista.isEmpty()) {
			String sql = "insert into tb_tipo_reservatorio (tipo) values (?)";
			try {
				PreparedStatement pst = super.getPreparedStatement(sql);
				for (TipoReservatorio tipo : lista) {
					pst.setString(1, tipo.getTipo());
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
	public TipoReservatorio updateOne(TipoReservatorio element) {
		String sql = "UPDATE tb_tipo_reservatorio SET tipo=? WHERE id = ?";
		int retorno = 0;
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setString(1, element.getTipo());
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
	public boolean deleteOne(TipoReservatorio element) {
		String sql = "DELETE FROM tb_tipo_reservatorio WHERE id = ?";
		
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
		String sql = "DELETE FROM tb_tipo_reservatorio";
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
