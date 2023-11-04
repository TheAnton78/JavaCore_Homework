import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.json.simple.JSONArray;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);
        String json = listToJson(list);
        writeString(json, "data.json");

        List<Employee> listFromXML = parseXML("data.xml");
        String jsonFromXML = listToJson(listFromXML);
        writeString(jsonFromXML, "data2.json");

        String json3 = readString("new_data.json");
        List<Employee> list3 = jsonToList(json3);
        list3.forEach(System.out::println);
    }

    public static List<Employee> parseCSV(String[] columnMapping, String fileName){
        List<Employee> list = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))){
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();
            list = csv.parse();
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return list;
    }

    public static String listToJson(List<Employee> list){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Type listType = new TypeToken<List<Employee>>() {}.getType();
        return gson.toJson(list, listType);
    }

    public static void writeString(String json, String fileName){
        try(FileWriter writer = new FileWriter(fileName)){
            writer.write(json);
            writer.flush();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


    public static List<Employee> parseXML(String fileName){
        List<Employee> list = new ArrayList<>();
        try {
            var factory = DocumentBuilderFactory.newInstance();
            var builder = factory.newDocumentBuilder();
            var file = new File(fileName);
            Document doc = builder.parse(file);
            Node root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node_ = nodeList.item(i);
                if (Node.ELEMENT_NODE == node_.getNodeType()) {
                    Element element = (Element) node_;
                    Employee employee = new Employee(element);
                    NodeList nodeList_ = node_.getChildNodes();
                    for( int j = 0; j < nodeList_.getLength(); j++){
                        Node node__ = nodeList_.item(j);
                        if (Node.ELEMENT_NODE == node__.getNodeType()) {
                            if (node__.getNodeName().equals("id")) {
                                employee.id = Long.parseLong(node__.getTextContent());
                            }
                            if (node__.getNodeName().equals("firstName")) {
                                employee.firstName = node__.getTextContent();
                            }
                            if (node__.getNodeName().equals("lastName")) {
                                employee.lastName = node__.getTextContent();
                            }
                            if (node__.getNodeName().equals("country")) {
                                employee.country = node__.getTextContent();
                            }
                            if (node__.getNodeName().equals("age")) {
                                employee.age = Integer.parseInt(node__.getTextContent());
                            }
                        }
                    }

                    list.add(employee);
                }
            }


        } catch (IOException | ParserConfigurationException | SAXException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static String readString(String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        String s;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((s = br.readLine()) != null) {
                stringBuilder.append(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return stringBuilder.toString();
    }

    public static List<Employee> jsonToList(String json) {
        List<Employee> list = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(json);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            for (Object jsonObject : jsonArray) {
                list.add(gson.fromJson(String.valueOf( jsonObject), Employee.class));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;

    }
}

