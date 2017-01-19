package bloodbowl;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.util.Hashtable;
import java.util.Vector;
import bdatos.ResultSet2;


public class Parser {


    private static Document getDocument(String file) throws Exception {
        Document doc=null;
        //try {
            doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(Parser.class.getResourceAsStream(file));
        //} catch(Exception e) {
            //System.out.println(e);
        //}
        return doc;
    }


    public static Hashtable<String,String> parseHabilidades() throws Exception {
        Hashtable<String,String> habilidades=new Hashtable<String,String>();
        Element el;
        NodeList nl=getDocument("../../../xml/habilidades.xml").getDocumentElement().getElementsByTagName("habilidad");
        for(int i=0;i<nl.getLength();i++) {
            el=(Element)nl.item(i);
            habilidades.put(el.getAttribute("name"),el.getAttribute("category"));
        }
        return habilidades;
    }


    public static ResultSet2 parseJugadores(String raza) throws Exception {
        Element el,el2;
        NodeList nl,nl2;
        ResultSet2 rset=new ResultSet2(new String[]{"posicion","mov","fue","agi","arm","habilidades","coste","mejoras","cantidad"});
        String[] row;
        nl=getDocument("../../../xml/razas.xml").getDocumentElement().getElementsByTagName("raza");
        for(int i=0;i<nl.getLength();i++) {
            el=(Element)nl.item(i);
            if(el.getAttribute("nombre").equals(raza)) {
                nl2=el.getElementsByTagName("posicion");
                for(int i2=0;i2<nl2.getLength();i2++) {
                    row=new String[9];
                    el2=(Element)nl2.item(i2);
                    row[0]=el2.getAttribute("nombre");
                    row[1]=el2.getAttribute("mov");
                    row[2]=el2.getAttribute("fue");
                    row[3]=el2.getAttribute("agi");
                    row[4]=el2.getAttribute("arm");
                    row[5]=el2.getAttribute("habilidades");
                    row[6]=el2.getAttribute("coste");
                    row[7]=el2.getAttribute("mejoras");
                    row[8]=el2.getAttribute("cantidad");
                    rset.addRow(row);
                }
            }
            //el2=(Element)el.getElementsByTagName("otros").item(0);
            //System.out.print(" "+el2.getAttribute("rerolls"));
            //System.out.println(" "+el2.getAttribute("medico"));
        }
        return rset;
    }


    public static Vector<String> listaRazas() throws Exception {
        Vector<String> lista=new Vector<String>();
        NodeList nl=getDocument("../../../xml/razas.xml").getDocumentElement().getElementsByTagName("raza");
        for(int i=0;i<nl.getLength();i++)
            lista.addElement(((Element)nl.item(i)).getAttribute("nombre"));
        return lista;
    }


    public static String parseOtros(String raza) throws Exception {
        String otros=null;
        Element el,el2;
        NodeList nl=getDocument("../../../xml/razas.xml").getDocumentElement().getElementsByTagName("raza");
        for(int i=0;i<nl.getLength();i++) {
            el=(Element)nl.item(i);
            if(el.getAttribute("nombre").equals(raza)) {
                el2=(Element)el.getElementsByTagName("otros").item(0);
                otros=el2.getAttribute("rerolls")+"#"+el2.getAttribute("medico");
            }
        }
        return otros;
    }

    public static String getValue(String raza,String posicion,String atributo) throws Exception {
        Element el,el2;
        NodeList nl,nl2;
        String valor="";
        ResultSet2 rset=new ResultSet2(new String[]{"posicion","mov","fue","agi","arm","habilidades","coste","mejoras","cantidad"});
        nl=getDocument("../../../xml/razas.xml").getDocumentElement().getElementsByTagName("raza");
        for(int i=0;i<nl.getLength();i++) {
            el=(Element)nl.item(i);
            if(el.getAttribute("nombre").equals(raza)) {
                nl2=el.getElementsByTagName("posicion");
                for(int i2=0;i2<nl2.getLength();i2++) {
                    el2=(Element)nl2.item(i2);
                    if(el2.getAttribute("nombre").equals(posicion))
                        valor=el2.getAttribute(atributo);
                }
            }
        }
        return valor;
    }

    public static Hashtable<String,String> getValuesRaza(String raza,String atributo) throws Exception {
        Element el,el2;
        NodeList nl,nl2;
		Hashtable<String,String> ht=new Hashtable<String,String>();
        nl=getDocument("../../../xml/razas.xml").getDocumentElement().getElementsByTagName("raza");
        for(int i=0;i<nl.getLength();i++) {
            el=(Element)nl.item(i);
            if(el.getAttribute("nombre").equals(raza)) {
                nl2=el.getElementsByTagName("posicion");
                for(int i2=0;i2<nl2.getLength();i2++) {
                    el2=(Element)nl2.item(i2);
                    ht.put(el2.getAttribute("nombre"),el2.getAttribute(atributo));
                }
            }
        }
        return ht;
    }
    
    public static Vector<String> getStars() throws Exception {
        Vector<String> stars=new Vector<String>();
        NodeList nl=bloodbowl.Parser.getDocument("../../../xml/stars.xml").getDocumentElement().getElementsByTagName("star");
        for(int i=0;i<nl.getLength();i++)
          stars.addElement(nl.item(i).getTextContent());
        return stars;
    }


}


