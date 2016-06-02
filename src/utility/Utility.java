package utility;

import java.util.Calendar;
import java.util.List;

import dao.interfaces.I_Pedido;
import daofactory.DAOFactory;

public class Utility {


	public String generarClave(String dni, String nom,String apePat){
		
		String nuevaClave = "";
		int tam = nom.length();
		String primerComp = "";
		
		if(tam>3){
			primerComp = nom.substring(0,3);
		}else{
			primerComp=nom.substring(0);
		}
		
		char segundoComp =dni.charAt(0);
		char tercerComp=dni.charAt(5);
		
		String dummy = apePat.substring(0,1);
		byte [] array_bytes = dummy.getBytes();
		String cuartoComp="";
		
		for(int i = 0;i<array_bytes.length;i++){
			cuartoComp+=""+array_bytes[i];
		}
		
		int ultimoComp = generarAleatorio(10,99);
		
		nuevaClave=""+primerComp+segundoComp+tercerComp+cuartoComp+ultimoComp;
		return nuevaClave;
	}
	
	public int generarAleatorio(int min ,int max){
		return (int)((max-min+1)*Math.random()+min);
	}
	
	public String generarCodigoPedido(){
		
		String nuevoCodigo="";
		
		I_Pedido pedidoDao=DAOFactory.getDAOFactory(DAOFactory.MYSQL).getPedidoDAO();
		String ultimoCodigo=pedidoDao.obtenerUltimoCodigo();
		
		List<String>datosMesyAnio=pedidoDao.obtenerMesyAnio();
		
		String year=datosMesyAnio.get(0);
		String month=datosMesyAnio.get(1);
		
		if(Integer.parseInt(month)<10){
			month="0"+month;
		}
		
		String parteIncremental=ultimoCodigo.substring(4);
		int parteIncrement = Integer.parseInt(parteIncremental);
		
		if(parteIncrement<=9998){
			
			parteIncrement=parteIncrement+1;
			String ultimoElemento="";
			if(parteIncrement<10){
				ultimoElemento ="000"+parteIncrement;
			}else if(parteIncrement<100){
				ultimoElemento="00"+parteIncrement;
			}else if(parteIncrement<1000){
				 ultimoElemento="0"+parteIncrement;
			}else{
				ultimoElemento=""+parteIncrement;
			}
			
			
			nuevoCodigo=year+month+ultimoElemento;
		}else{
			nuevoCodigo="Se sobrepaso el limite de Pedido por mes";
		}
		
		return nuevoCodigo;
	}
	
}
