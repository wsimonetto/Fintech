package br.com.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.fintech.DBException.DBException;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.entities.UsuarioCadastro;
import br.com.fintech.entities.UsuarioExclusao;
import br.com.fintech.entities.UsuarioLogin;
import br.com.fintech.singleton.ConnectionManager;
import br.com.fintech.util.Criptografia;

public class OracleUsuarioDAO implements UsuarioDAO {

	private Connection conexao;

	@Override
	public void cadastrar(UsuarioCadastro usuario) throws DBException {

		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_FIN_USUARIO (cod_usuario, nr_cpf, nm_completo, nm_email, nr_telefone, nm_senha, dt_cadastro) VALUES (SEQ_USUARIO.NEXTVAL, ?, ?, ?, ?, ?, ?) ";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getCpfUsuario());
			stmt.setString(2, usuario.getNomeUsuario());
			stmt.setString(3, usuario.getEmailUsuario());
			stmt.setString(4, usuario.getTelefoneUsuario());
			stmt.setString(5, usuario.getSenhaUsuario());
			Calendar dataAtual = Calendar.getInstance();
			java.sql.Date data = new java.sql.Date(dataAtual.getTimeInMillis());
			stmt.setDate(6, data);

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao Cadastrar Usuário!");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean verificarUsuarioExistentePorCPF(String cpf) {
		try (Connection conexao = ConnectionManager.getInstance().getConnection();
				PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM TB_FIN_USUARIO WHERE nr_cpf = ?")) {
			stmt.setString(1, cpf);

			try (ResultSet rs = stmt.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean verificarUsuarioExistentePorEmail(String email) {
		try (Connection conexao = ConnectionManager.getInstance().getConnection();
				PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM TB_FIN_USUARIO WHERE nm_email = ?")) {
			stmt.setString(1, email);

			try (ResultSet rs = stmt.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean validarUsuario(UsuarioLogin usuarioLogin) {
		
	    try (Connection conexao = ConnectionManager.getInstance().getConnection();
	         PreparedStatement stmt = conexao.prepareStatement(
	                 "SELECT cod_usuario FROM TB_FIN_USUARIO WHERE nm_email = ? AND nm_senha = ?")) {
	        stmt.setString(1, usuarioLogin.getEmail());
	        stmt.setString(2, usuarioLogin.getSenha());

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                int codigoUsuario = rs.getInt("cod_usuario");
	                usuarioLogin.setCodigoUsuario(codigoUsuario);
	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Apenas para depuração, substitua por um sistema de log adequado.
	    }
	    return false;
	}
	
	@Override
	public boolean validarSenhaUsuario(UsuarioExclusao usuarioExclusao) {
		try (Connection conexao = ConnectionManager.getInstance().getConnection();
				PreparedStatement stmt = conexao
						.prepareStatement("SELECT * FROM TB_FIN_USUARIO WHERE cod_usuario = ? AND nm_senha = ?")) {
			stmt.setInt(1, usuarioExclusao.getCodUsuario());
			stmt.setString(2, usuarioExclusao.getSenhaUsuario());

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int codigoUsuario = rs.getInt("cod_usuario");
					usuarioExclusao.setCodUsuario(codigoUsuario);
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public void atualizarSenha(int codigoUsuario, String novaSenha) {
		try (Connection conexao = ConnectionManager.getInstance().getConnection();
				PreparedStatement stmt = conexao
						.prepareStatement("UPDATE TB_FIN_USUARIO SET nm_senha = ? WHERE cod_usuario = ?")) {
			stmt.setString(1, Criptografia.criptografar(novaSenha));
			stmt.setInt(2, codigoUsuario);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remover(int codigoUsuario) throws DBException {
		String sql = "DELETE FROM TB_FIN_USUARIO WHERE cod_usuario = ?";
		try (Connection conexao = ConnectionManager.getInstance().getConnection();
				PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codigoUsuario);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("Erro ao remover usuário", e);
		}
	}

} // FIM
