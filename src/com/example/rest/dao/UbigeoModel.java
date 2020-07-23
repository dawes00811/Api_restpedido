package com.example.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.rest.entidades.Ubigeo;
import com.example.rest.util.MySqlDBConexion;


public class UbigeoModel {

	public List<String> listarDepartamentos() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<String> lista = new ArrayList<String>();
		try {
			String sql = "select distinct (departamento) from ubigeo";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				lista.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return lista;
	}

	public List<String> listarProvincia(Ubigeo obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<String> lista = new ArrayList<String>();
		try {
			String sql = "select distinct (provincia) from ubigeo where departamento=?";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getDepartamento());
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				lista.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return lista;
	}

	public List<Ubigeo> listarDistrito(Ubigeo obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<Ubigeo> lista = new ArrayList<Ubigeo>();
		try {
			String sql = "select idubigeo, distrito from ubigeo where departamento=? and provincia=?";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getDepartamento());
			pstm.setString(2, obj.getProvincia());
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			Ubigeo aux = null;
			while (rs.next()) {
				aux = new Ubigeo();
				aux.setIdUbigeo(rs.getInt(1));
				aux.setDistrito(rs.getString(2));
				lista.add(aux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return lista;
	}

	public int buscaIdUbigeo(Ubigeo obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int idUbigeo = -1;
		try {
			String sql = "select idubigeo from ubigeo where departamento=? and provincia=?and distrito=?";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getDepartamento());
			pstm.setString(2, obj.getProvincia());
			pstm.setString(3, obj.getDistrito());
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			if (rs.next()) {
				idUbigeo = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return idUbigeo;
	}
}
