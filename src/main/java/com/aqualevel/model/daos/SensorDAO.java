package com.aqualevel.model.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aqualevel.model.DaoUtil;
import com.aqualevel.model.Sensor;
import com.aqualevel.model.daos.interfaces.CrudPi;

@Component
public class SensorDAO extends DaoUtil implements CrudPi<Sensor> {
	
	@Autowired
	ReservatorioDAO reservatorioService;

	@Override
	public ArrayList<Sensor> getAll() {
		String sql = "select * from tb_sensor";
		
		ArrayList<Sensor> sensores = new ArrayList<>();
		try {
			ResultSet rs = super.getStatement().executeQuery(sql);
			while(rs.next()) {
				Sensor sensor = new Sensor();
				sensor.setId(rs.getLong("id"));
				sensor.setCodigo(rs.getLong("codigo"));
				sensor.setNome(rs.getString("nome"));
				sensor.setIdReservatorio(reservatorioService.getOneId(rs.getLong("id_reservatorio")));								
				sensores.add(sensor);
			}
			rs.close();
			super.destroiConnection();
		} catch (SQLException e) {
			System.out.println("Erro sql >>" + e.getMessage());
		}
		
		return sensores;
	}

	@Override
	public Sensor getOneId(Long id) {
		String sql = "select * from tb_sensor where id = ?";
		Sensor sensor = new Sensor();
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				sensor.setId(rs.getLong("id"));
				sensor.setCodigo(rs.getLong("codigo"));
				sensor.setNome(rs.getString("nome"));
				sensor.setIdReservatorio(reservatorioService.getOneId(rs.getLong("id_reservatorio")));
			}
		} catch (SQLException e) {
			System.out.println("Erro SQL >>" + e.getMessage());
		}
		
		return sensor;
	}

	@Override
	public Sensor getOneName(String name) {
		String sql = "select * from tb_sensor where nome = ?";
		Sensor sensor = new Sensor();
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				sensor.setId(rs.getLong("id"));
				sensor.setCodigo(rs.getLong("codigo"));
				sensor.setNome(rs.getString("nome"));
				sensor.setIdReservatorio(reservatorioService.getOneId(rs.getLong("id_reservatorio")));
				pst.close();
				rs.close();
				super.destroiConnection();
			}
		} catch (SQLException e) {
			System.out.println("Erro SQL >>" + e.getMessage());
		}
		
		return sensor;
	}

	@Override
	public boolean setOne(Sensor element) {
		String sql = "insert into tb_sensor (codigo, nome, id_reservatorio) values (?,?,?)";
		int retorno = 0;
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setLong(1, element.getCodigo());
			pst.setString(2, element.getNome());
			pst.setLong(3, element.getIdReservatorio().getId());
			
			retorno = pst.executeUpdate();
			pst.close();
			super.destroiConnection();
			
		} catch (SQLException e) {
			System.out.println("Erro sql" + e.getMessage());
		}
		
		return retorno > 0;
	}

	@Override
	public boolean setList(ArrayList<Sensor> lista) {
		String sql = "insert into tb_sensor (codigo, nome, id_reservatorio) values (?,?,?)";
		int retorno = 0;
		
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			
			for (Sensor sensor : lista) {
				pst.setLong(1, sensor.getCodigo());
				pst.setString(2, sensor.getNome());
				pst.setLong(3, sensor.getIdReservatorio().getId());
			}
			
			retorno = pst.executeUpdate();
			pst.close();
			super.destroiConnection();
		} catch (SQLException e) {
			System.out.println("Erro SQL >>" + e.getMessage());
		}
		
		return retorno > 0;
	}

	@Override
	public Sensor updateOne(Sensor element) {
		String sql = "update tb_sensor set codigo=?, nome=?, id_reservatorio=? where id = ?";
		int retorno = 0;
		
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setLong(1, element.getCodigo());
			pst.setString(2, element.getNome());
			pst.setLong(3, element.getIdReservatorio().getId());
			pst.setLong(4, element.getId());
			
			retorno = pst.executeUpdate();
			pst.close();
			super.destroiConnection();
			
		} catch (SQLException e) {
			System.out.println("Erro SQL >>" + e.getMessage() ); 
		}
		
		if (retorno > 0) {
			return getOneId(element.getId());
		}		
		return null;
	}

	@Override
	public boolean deleteOne(Sensor element) {
		String sql = "DELETE FROM tb_sensor WHERE id = ?";
		
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
		String sql = "DELETE FROM tb_sensor";
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
