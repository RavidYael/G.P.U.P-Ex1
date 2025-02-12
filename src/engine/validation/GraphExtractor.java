package engine.validation;

import engine.jaxb.generated.GPUPDescriptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;

public class GraphExtractor {
    private final static String JAXB_XML_PACKAGE_NAME = "engine.jaxb.generated";


    public GPUPDescriptor getGeneratedGraph() {
        return generatedGraph;
    }

    private GPUPDescriptor generatedGraph;
    private GraphValidator graphValidator;
    private  boolean valid = true;


    public GraphExtractor(Path directory) throws Exception {
        if (!directory.endsWith("xml")) {
            throw (new Exception("File doesn't end with .xml"));

        }
        else
            generatedGraph = extractGraphFromFile(directory);


    }

    public  GPUPDescriptor extractGraphFromFile (Path directory) {
        try {
            InputStream inputStream = new FileInputStream(directory.toString());
            JAXBContext jc = JAXBContext.newInstance(JAXB_XML_PACKAGE_NAME);
            Unmarshaller u = jc.createUnmarshaller();
            return (GPUPDescriptor) u.unmarshal(inputStream);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        valid = false;
        return null;
    }

    }