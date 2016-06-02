package daofactory;
import java.sql.Connection;
import java.sql.DriverManager;

import dao.interfaces.*;
import dao.mysql.*;

public class MySQLDaoFactory extends DAOFactory{
		
	public static Connection obtenerConexion(){
		
		Connection con=null;
		
		try {
			/*
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://us-cdbr-azure-central-a.cloudapp.net/acsm_4fdf7587bc34acf";
			String user = "baa3cfbe4cfdbc";
			String password ="f10adb85";
			con = DriverManager.getConnection(url,user,password);
			 */
		
			/**
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://mysql5692-sistemafarmacia.jelasticireland.com/bd_farmacia";
			String user = "root";
			String password ="GOTcgn06962";
			con = DriverManager.getConnection(url,user,password);
			**/
			/*
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/bd_farmacia";
			String user = "root";
			String password ="root";
			con = DriverManager.getConnection(url,user,password);
			*/
		
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/acsm_4fdf7587bc34acf";
			String user = "root";
			String password ="";
			con = DriverManager.getConnection(url,user,password);
			
		} catch (Exception e) {
		// TODO: handle exception
		
			System.out.print(e.getMessage());
			System.out.print("Connection Failed!");
			e.printStackTrace();		
		}
		return con;
	
	}
	
	public I_Pedido getPedidoDAO(){
		return new MySql_PedidoDao();
	}

	@Override
	public I_Usuario getUsuarioDao() {
		// TODO Auto-generated method stub
		return new MySql_UsuarioDao();
	}
	
	public I_Comprobante getComprobanteDAO(){
		return new MySql_ComprobanteDao();
	}

	@Override
	public I_Distrito getDistritoDao() {
		// TODO Auto-generated method stub
		return new MySql_DistritoDao();
	}

	@Override
	public I_Rol getRolDao() {
		// TODO Auto-generated method stub
		return new MySql_RolDao();
	}
	
	@Override
	public I_Medicamento getMedicamentoDao() {
		// TODO Auto-generated method stub
		return new MySql_MedicamentoDao();
	}

	@Override
	public I_SintomasComunes getSintomasComunesDao() {
		// TODO Auto-generated method stub
		return new MySql_SintomasComunesDao();
	}
}
