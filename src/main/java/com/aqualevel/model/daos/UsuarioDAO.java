package com.aqualevel.model.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aqualevel.model.DaoUtil;
import com.aqualevel.model.Usuario;
import com.aqualevel.model.daos.interfaces.CrudPi;

@Component
public class UsuarioDAO extends DaoUtil implements CrudPi<Usuario> {
	

	
	@Autowired
	PerfilDAO perfilDao;

	@Override
	public ArrayList<Usuario> getAll() {
		String sql = "SELECT * FROM tb_usuario";
		ArrayList<Usuario> usuarios = new ArrayList<>();
		try {
			ResultSet rs = super.getStatement().executeQuery(sql);
			
			while (rs.next()) {
				Usuario user = new Usuario();
				
				user.setId(rs.getLong("id"));
				user.setCPF(rs.getString("cpf"));
				user.setDataNasc(rs.getDate("data_nasc"));
				user.setEmail(rs.getString("email"));
				user.setSenha(rs.getString("senha"));
				user.setPerfil(perfilDao.getOneId(rs.getLong("id_perfil")));
				user.setNome(rs.getString("nome"));
				user.setSobreNome(rs.getString("sobre_nome"));
				
				usuarios.add(user);
			}
			rs.close();
			//super.destroiConnection();
		} catch (SQLException e) {
			System.out.println("Erro na Consulta -->" + e.getMessage());
		}finally {
			super.destroiConnection();
		}
		
		return usuarios;
	}

	@Override
	public Usuario getOneId(Long id) {
		
		String sql = "SELECT * FROM tb_usuario WHERE id = ?";
		Usuario  user = new Usuario();
		
		System.out.println(id);
		
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				user.setId(rs.getLong("id"));
				user.setCPF(rs.getString("cpf"));
				user.setDataNasc(rs.getDate("data_nasc"));
				user.setEmail(rs.getString("email"));
				user.setPerfil(perfilDao.getOneId(rs.getLong("id_perfil")));
				user.setNome(rs.getString("nome"));
				user.setSobreNome(rs.getString("sobre_nome"));
			}
			pst.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Erro na Consulta -->" + e.getMessage());
		}finally {
			super.destroiConnection();
		}
		
		return user;
	}
	
	public Usuario getOneCpf(String cpf) {
		System.out.println("CPF? " + cpf);
		
		String sql = "SELECT * FROM tb_usuario WHERE cpf = ?";
		Usuario  user = new Usuario();
		
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setString(1, cpf);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				user.setId(rs.getLong("id"));
				user.setCPF(rs.getString("cpf"));
				user.setDataNasc(rs.getDate("data_nasc"));
				user.setEmail(rs.getString("email"));
				user.setPerfil(perfilDao.getOneId(rs.getLong("id_perfil")));
				user.setNome(rs.getString("nome"));
				user.setSobreNome(rs.getString("sobre_nome"));
			}
			pst.close();
			rs.close();
			super.destroiConnection();			
		} catch (SQLException e) {
			System.out.println("Erro na Consulta -->" + e.getMessage());
		}
		
		return user;
	}
	
public Usuario getOneEmail(String email) {
		
		String sql = "SELECT * FROM tb_usuario WHERE email = ?";
		Usuario  user = new Usuario();
		
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				user.setId(rs.getLong("id"));
				user.setCPF(rs.getString("cpf"));
				user.setDataNasc(rs.getDate("data_nasc"));
				user.setEmail(rs.getString("email"));
				user.setPerfil(perfilDao.getOneId(rs.getLong("id_perfil")));
				user.setNome(rs.getString("nome"));
				user.setSobreNome(rs.getString("sobre_nome"));
			}
			pst.close();
			rs.close();
			super.destroiConnection();			
		} catch (SQLException e) {
			System.out.println("Erro na Consulta -->" + e.getMessage());
		}
		
		return user;
	}
	

	@Override
	public Usuario getOneName(String name) {
		String sql = "SELECT * FROM tb_usuario WHERE nome = ?";
		Usuario  user = new Usuario();
		
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				user.setId(rs.getLong("id"));
				user.setCPF(rs.getString("cpf"));
				user.setDataNasc(rs.getDate("data_nasc"));
				user.setEmail(rs.getString("email"));
				user.setPerfil(perfilDao.getOneId(rs.getLong("id_perfil")));
				user.setNome(rs.getString("nome"));
				user.setSobreNome(rs.getString("sobre_nome"));
			}
			
			pst.close();
			rs.close();
			super.destroiConnection();
		} catch (SQLException e) {
			System.out.println("Erro na Consulta -->" + e.getMessage());
		}
		
		return user;
	}

	@Override
	public boolean setOne(Usuario element) {
		String sql = "INSERT INTO tb_usuario (cpf, data_nasc, email, id_perfil, nome, sobre_nome, senha) VALUES (?,?,?,?,?,?,?)";
		
		int retorno = 0;
		
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setString(1, element.getCPF());
			pst.setDate(2, new Date(element.getDataNasc().getTime()));
			pst.setString(3, element.getEmail());
			pst.setLong(4, element.getPerfil().getId());
			pst.setString(5, element.getNome());
			pst.setString(6, element.getSobreNome());
			pst.setString(7, element.getSenha());
			
			retorno = pst.executeUpdate();
			pst.close();
			super.destroiConnection();
						
		} catch (SQLException e) {
			System.out.println("Erro na Insersção -->" + e.getMessage());
		}
		return retorno > 0;
	}

	@Override
	public boolean setList(ArrayList<Usuario> lista) {
		 int retorno = 0;
		
		if(!lista.isEmpty()) {
			
			String sql = "INSERT INTO tb_usuario (cpf, data_nasc, email, id_perfil, nome, sobre_nome, senha) VALUES (?,?,?,?,?,?,?)";
			
			try {
				PreparedStatement pst = super.getPreparedStatement(sql);
				for (Usuario usuario : lista) {
					pst.setString(1, usuario.getCPF());
					pst.setDate(2, (Date) usuario.getDataNasc());
					pst.setString(3, usuario.getEmail());
					pst.setLong(4, usuario.getPerfil().getId());
					pst.setString(5, usuario.getNome());
					pst.setString(6, usuario.getSobreNome());
					pst.setString(7, usuario.getSenha());
				}
				retorno = pst.executeUpdate();
				pst.close();
				super.destroiConnection();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return retorno > 0;
	}

	@Override
	public Usuario updateOne(Usuario element) {
		String sql = "UPDATE tb_usuario SET cpf=?, data_nasc=?, email=?, perfil=?, nome=?, sobre_nome=?, senha=? WHERE id = ?";
		int ret = 0;
		
		System.out.println("Usuario update: " + element.toString());
			
		try {
			PreparedStatement pst = super.getPreparedStatement(sql);
			pst.setString(1, element.getCPF());
			pst.setDate(2, new Date(element.getDataNasc().getTime()));
			pst.setString(3, element.getEmail());
			pst.setLong(4, element.getPerfil().getId());
			pst.setString(5, element.getNome());
			pst.setString(6, element.getSobreNome());
			pst.setString(7, element.getSenha());
			pst.setLong(8, element.getId());
			
			ret = pst.executeUpdate();
			
			pst.close();
			super.destroiConnection();
								
		} catch (SQLException e) {
			System.out.println("Erro na Insersção -->" + e.getMessage());
		}
		
		if(ret > 0) {
			return getOneId(element.getId());
		}
		
		return null;
	}

	@Override
	public boolean deleteOne(Usuario element) {
		String sql = "DELETE FROM tb_usuario WHERE id = ?";
		
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
		String sql = "DELETE FROM tb_usuario";
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
