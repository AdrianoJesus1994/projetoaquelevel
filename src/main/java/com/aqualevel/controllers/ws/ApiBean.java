package com.aqualevel.controllers.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aqualevel.model.Sensor;
import com.aqualevel.model.Volume;
import com.aqualevel.model.services.SensorService;
import com.aqualevel.model.services.VolumeService;

@Component
public class ApiBean {
	
	@Autowired
	private SensorService sensorService;
	
	@Autowired
	private VolumeService volumeService;

	public void setVolume(long codSensor, float valor) {
		if(getBuscaSensor(codSensor) != null) {
			Sensor sensor = getBuscaSensor(codSensor);
			System.out.println("Sensor: " + sensor.toString());
			
			switch (sensor.getIdReservatorio().getTipo().getTipo()) {
			case "Cubico":
					setVolumeReservatorioCubico(sensor, valor);
				break;
			case "Tronco de Cone":
				setVolumeReservatorioTroncoDeCone(sensor, valor);
				break;
			case "Cilindrico":
				setVolumeReservatorioCilindrico(sensor, valor);
				break;
			default:
				break;
			}
		}
	}
	
	private Sensor getBuscaSensor(long codSensor) {
		List<Sensor> sensores = getSensorService().getAll();
		for (Sensor sensor : sensores) {
			if(sensor.getCodigo() == codSensor){
				return sensor;
			}
		}
		return null;
	}
	
	
	private void setVolumeReservatorioCubico(Sensor sensor, float valor) {
		float altura = sensor.getIdReservatorio().getAltura();
		float largura = sensor.getIdReservatorio().getLargura();
		float profundidade = sensor.getIdReservatorio().getProfundidade();
		
		System.out.println("Altura:" + altura + "- Lagura: " + largura + "- Profundidade: " + profundidade + "- Valor: " + valor);
				
		float volume =((altura - valor)*largura*profundidade);
		
		Volume vol = new Volume();
		vol.setVolume(volume/1000);
		vol.setReserv(sensor.getIdReservatorio());
		getVolumeService().setVolume(vol);
	}
	
	private void setVolumeReservatorioCilindrico(Sensor sensor, float valor) {
		//PI*Raio*Altura
		
		float altura = sensor.getIdReservatorio().getAltura();
		float raio = sensor.getIdReservatorio().getRaio();
		
		float volume = (float) (Math.PI*(Math.pow(raio, 2))*(altura-valor));
		Volume vol = new Volume();
		vol.setVolume(volume/1000);
		vol.setReserv(sensor.getIdReservatorio());
		getVolumeService().setVolume(vol);
	}
	
	private void setVolumeReservatorioTroncoDeCone(Sensor sensor, float valor) {
		float altura = sensor.getIdReservatorio().getAltura();
		float raio = sensor.getIdReservatorio().getRaio();
		float raioMenor = sensor.getIdReservatorio().getRaioMenor();
		
		float volumeAtual = (float) (( (Math.PI*(altura - valor) / 3 ) * (Math.pow(raio, 2)+(raio*raioMenor)+( Math.pow(raioMenor, 2) ) ) ) );
	
		Volume vol = new Volume();
		vol.setVolume(volumeAtual/1000);
		vol.setReserv(sensor.getIdReservatorio());
		getVolumeService().setVolume(vol);
	}

	private SensorService getSensorService() {
		return sensorService;
	}

	public VolumeService getVolumeService() {
		return volumeService;
	}
	
	
	
}
