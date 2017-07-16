package main.java;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/*
 * @Author Rupendra Singh
 */

public class Export extends Thread{
	List<Element> list;
	String fileType;


	Export(List<Element> list, String fileType){
		this.list= list;
		this.fileType= fileType;
	}

	public void run(){  

		long threadId = Thread.currentThread().getId();
		Parse.log.info("Thread # " + threadId+"----run-----");
		try  {

			// Log list Java objects at INFO level
			if(fileType.equalsIgnoreCase("JAVA")){

				for(int i =0;i<list.size();i++){
					Parse.log.info("Thread # " + threadId +"  ------------------");
					Parse.log.info("Thread # " + threadId +"  Atomic Number :"+Integer.toString(list.get(i).getAtomicNumber()));
					Parse.log.info("Thread # " + threadId +"  Element :"+list.get(i).getElement());
					Parse.log.info("Thread # " + threadId +"  Symbol :"+list.get(i).getSymbol());
					Parse.log.info("Thread # " + threadId +"  Period :"+Integer.toString(list.get(i).getPeriod()));
					Parse.log.info("Thread # " + threadId +"  Group :"+Integer.toString(list.get(i).getGroup()));
					Parse.log.info("Thread # " + threadId +"  Phase :"+list.get(i).getPhase());
					Parse.log.info("Thread # " + threadId +"  Most Sable Crystal :"+list.get(i).getMostStableCrystal());
					Parse.log.info("Thread # " + threadId +"  Type :"+list.get(i).getType());
					Parse.log.info("Thread # " + threadId +"  Ionic Radius :"+Float.toString(list.get(i).getIonicradius()));
					Parse.log.info("Thread # " + threadId +"  Atomic Radius :"+Float.toString(list.get(i).getAtomicRadius()));
					Parse.log.info("Thread # " + threadId +"  Electronegativity :"+Float.toString(list.get(i).getElectroNegativity()));
					Parse.log.info("Thread # " + threadId +"  First Ionization Potential :"+Float.toString(list.get(i).getFirstIonizationPotential()));
					Parse.log.info("Thread # " + threadId +"  Density :"+Float.toString(list.get(i).getDensity()));
					Parse.log.info("Thread # " + threadId +"  Melting Point(K) :"+Float.toString(list.get(i).getMeltingPoint()));
					Parse.log.info("Thread # " + threadId +"  Boiling Point(K) :"+Float.toString(list.get(i).getBoilingPoint()));
					Parse.log.info("Thread # " + threadId +"  Isotopes :"+Integer.toString(list.get(i).getIsotopes()));
					Parse.log.info("Thread # " + threadId +"  Discoverer :"+list.get(i).getDiscoverer());
					Parse.log.info("Thread # " + threadId +"  Year of Discovery :"+Integer.toString(list.get(i).getYearOfDiscovery()));
					Parse.log.info("Thread # " + threadId +"  Specific Heat Capacity :"+Float.toString(list.get(i).getSpecificHeatCapacity()));
					Parse.log.info("Thread # " + threadId +"  Electron configuration :"+list.get(i).getElectronConfiguration());
					Parse.log.info("Thread # " + threadId +"  Display Row :"+Integer.toString(list.get(i).getDisplayRow()));
					Parse.log.info("Thread # " + threadId +"  Display Column :"+Integer.toString(list.get(i).getDisplayColumn()));

				}

			}

			// Export those Java Objects in a JSON file, under src/main/resources
			if(fileType.equalsIgnoreCase("JSON")){
				Parse.log.info("Thread # " + threadId + "  Exporting JSON file");
				ObjectMapper mapper = new ObjectMapper();
				mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
				FileWriter fileOut = new FileWriter("src/main/resources/Periodic Table of Elements.json");
				mapper.writeValue(fileOut, list);

			}

			// Export those Java Objects in a XML file, under src/main/resources
			if(fileType.equalsIgnoreCase("XML")){
				Parse.log.info("Thread # " + threadId + "  Exporting XML file");
				XMLEncoder encoder=null;
				encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("src/main/resources/Periodic Table of Elements.xml")));
				encoder.writeObject(list);
				encoder.close();
			}

		}catch(Exception e){
			System.out.println(e);
		}
	} 
}
