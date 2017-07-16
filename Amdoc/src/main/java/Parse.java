package main.java;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*
 * @Author Rupendra Singh
 */

public class Parse {


	static private FileHandler fileTxt;
	static Logger log = Logger.getLogger(Parse.class.getName());

	public static void main(String arg[]){

		Parse obj = new Parse();
		List<Element> list = obj.parse("Periodic Table of Elements.csv");
		Thread objJava = new Export(list, "JAVA");
		Thread objJson = new Export(list, "JSON");
		Thread objXml = new Export(list, "XML");
		objJava.start();
		objJson.start();
		objXml.start();
	}

	// Returns list of Elements from CSV file
	public List<Element> parse(String fileName){

		List<Element> list = new ArrayList<>();
		try  {
			
			fileTxt = new FileHandler("src/main/resources/log.txt");
			log.addHandler(fileTxt);
			SimpleFormatter formatter = new SimpleFormatter();  
			fileTxt.setFormatter(formatter); 
			log.info("----Inside Parse-----");
			File file = new File((new URL(getClass().getClassLoader().getResource(fileName).toString().replaceAll("%20", " "))).getFile());
			System.out.println("Hello");
			InputStream in = new FileInputStream(file);

			CSV csv = new CSV(true, ',', in);
			if(csv.hasNext())csv.next();
			while (csv.hasNext()) {

				List<String> x = csv.next();
				Element e = new Element();
				e.setAtomicNumber( Integer.parseInt(x.get(0)));
				e.setElement(x.get(1));
				e.setSymbol(x.get(2));
				if(!"".equals(x.get(3)))
					e.setAtomicWeight(Float.parseFloat(x.get(3)));
				if(!"".equals(x.get(4)))
					e.setPeriod(Integer.parseInt(x.get(4)));
				if(!"".equals(x.get(5)))
					e.setGroup(Integer.parseInt(x.get(5)));
				e.setPhase(x.get(6));
				if(!"".equals(x.get(7)))
					e.setMostStableCrystal(x.get(7));
				e.setType(x.get(8));
				if(!"".equals(x.get(9)))
					e.setIonicradius(Float.parseFloat(x.get(9)));
				if(!"".equals(x.get(10)))
					e.setAtomicRadius(Float.parseFloat(x.get(10)));
				if(!"".equals(x.get(11)))
					e.setElectroNegativity(Float.parseFloat(x.get(11)));
				if(!"".equals(x.get(12)))
					e.setFirstIonizationPotential(Float.parseFloat(x.get(12)));
				if(!"".equals(x.get(13)))
					e.setDensity(Float.parseFloat(x.get(13)));
				if(!"".equals(x.get(14)))
					e.setMeltingPoint(Float.parseFloat(x.get(14)));
				if(!"".equals(x.get(15)))
					e.setBoilingPoint(Float.parseFloat(x.get(15)));
				if(!"".equals(x.get(16)))
					e.setIsotopes(Integer.parseInt(x.get(16)));
				e.setDiscoverer(x.get(17));
				if(!"".equals(x.get(18)))
					e.setYearOfDiscovery(Integer.parseInt(x.get(18)));
				if(!"".equals(x.get(19)))
					e.setSpecificHeatCapacity(Float.parseFloat(x.get(19)));
				if(!"".equals(x.get(20)))
					e.setElectronConfiguration(x.get(20));
				if(!"".equals(x.get(21)))
					e.setDisplayRow(Integer.parseInt(x.get(21)));
				if(!"".equals(x.get(22)))
					e.setDisplayColumn(Integer.parseInt(x.get(22)));

				list.add(e);

			}

		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}


}

