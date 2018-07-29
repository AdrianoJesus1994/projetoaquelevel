package com.aqualevel.model.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aqualevel.model.DaoUtil;
import com.aqualevel.model.Reservatorio;
import com.aqualevel.model.Volume;
import com.aqualevel.model.daos.interfaces.VolumePi;

@Component
public class VolumeDAO extends DaoUtil implements VolumePi {
	
	@Autowired
	ReservatorioDAO reservatorioDao;

	@Override
	public Volume getVolume(Reservatorio reserv) {
		String sql = "select id, data, id_reservatorio, volume from tb_volume where id = (select max(id) from tb_volume where id_reservatorio = ?);";
		Volume vol = new Volume();
		try {
			
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setLong(1, reserv.getId());
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				vol.setId(rs.getLong("id"));
				vol.setData(rs.getDate("data"));
				vol.setReserv(reservatorioDao.getOneId(rs.getLong("id")));
				vol.setVolume(rs.getFloat("volume"));
				System.out.println("VOL: " + vol.toString());
			}
			
			pst.close();
			rs.close();
			super.destroiConnection();			
			
		} catch (SQLException e) {
			System.out.println("Erro SQL >>" + e.getMessage());
		}
		return vol;
	}

	@Override
	public boolean setVolume(Volume vol) {
		String sql = "insert into tb_volume ( data, id_reservatorio, volume) values (?,?,?)";
		int retorno = 0;
		
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setDate(1, (Date) vol.getData());
			pst.setLong(2, vol.getReserv().getId());
			pst.setFloat(3, vol.getVolume());
			
			retorno = pst.executeUpdate();
			pst.close();
			super.destroiConnection();
			
		} catch (SQLException e) {
			System.out.println("Erro SQL >>" + e.getMessage());
		}
		
		return retorno > 0;
	}

	@Override
	public ArrayList<Volume> getHistoricoVolume(Reservatorio reserv, int mes, int mesfim) {
		String sql = "select * from tb_volume where id_reservatorio = ? and extract(MONTH FROM data) BETWEEN ? AND ?";
		ArrayList<Volume> volumes = new ArrayList<>();
		
		try {
			
			PreparedStatement pst = super.getPreparedStatement(sql);
			
			pst.setLong(1, reserv.getId());
			pst.setInt(2, mes);
			pst.setInt(3, mesfim);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Volume vol = new Volume();
				vol.setId(rs.getLong("id"));
				vol.setData(rs.getDate("data"));
				vol.setReserv(reservatorioDao.getOneId(rs.getLong("id")));
				vol.setVolume(rs.getFloat("volume"));
				System.out.println("VOL: " + vol.toString());
				volumes.add(vol);
			}
			
			pst.close();
			rs.close();
			super.destroiConnection();			
			
		} catch (SQLException e) {
			System.out.println("Erro SQL >>" + e.getMessage());
		}
		return volumes;
	}


}
