package bloodbowl;

import java.util.Hashtable;

public class Varios {

	public static int precioSubidas(String subidasJuntas,String mejoras) throws Exception {
		Hashtable<String,String> habilidades=Parser.parseHabilidades();
		int precio=0;
		if(subidasJuntas.trim().length()>0) {
			String[] subidas=subidasJuntas.trim().split(" ");
			for(String subida:subidas)
				if(subida.equals("+1Mo"))
					precio+=30;
				else if(subida.equals("+1Fu"))
					precio+=50;
				else if(subida.equals("+1Ag"))
					precio+=40;
				else if(subida.equals("+1Ar"))
					precio+=30;
				else if(subida.matches("[1-6][1-6]"))
					precio=precio;
				else {
					Hashtable<String,Integer> categorias=new Hashtable<String,Integer>();
					categorias.put("gn",0);
					categorias.put("ag",1);
					categorias.put("st",2);
					categorias.put("ps",3);
					categorias.put("mt",4);
					if(mejoras.charAt(categorias.get(habilidades.get(subida)))=='1')
						precio+=20;
					else
						precio+=30;
				}
		}
		return precio;
	}

	public static int[] ajustesAtributos(String mejorasYLesiones) {
		int[] ajustes={0,0,0,0};
		if(mejorasYLesiones.trim().length()>0) {
			String[] myls=mejorasYLesiones.trim().split(" ");
			for(String myl:myls)
				if(myl.equals("+1Mo"))
					ajustes[0]++;
				else if(myl.equals("+1Fu"))
					ajustes[1]++;
				else if(myl.equals("+1Ag"))
					ajustes[2]++;
				else if(myl.equals("+1Ar"))
					ajustes[3]++;
				else if(myl.equals("-1Mo"))
					ajustes[0]--;
				else if(myl.equals("-1Fu"))
					ajustes[1]--;
				else if(myl.equals("-1Ag"))
					ajustes[2]--;
				else if(myl.equals("-1Ar"))
					ajustes[3]--;
		}
		return ajustes;
	}




}


