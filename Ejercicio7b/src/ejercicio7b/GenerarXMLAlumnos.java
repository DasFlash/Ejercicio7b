package ejercicio7b;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class GenerarXMLAlumnos {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        Alumno[] alumnos = new Alumno[5];

        // Ingresar los datos de los alumnos
        for (int i = 0; i < 5; i++) {
            System.out.println("Ingrese los datos del alumno " + (i + 1) + ":");
            System.out.print("NIA: ");
            int nia = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Apellidos: ");
            String apellidos = sc.nextLine();
            System.out.print("Genero (M/F): ");
            char genero = sc.next().charAt(0);
            sc.nextLine(); // Limpiar el buffer
            System.out.print("Fecha de Nacimiento (yyyy-mm-dd): ");
            String fechaNacimiento = sc.nextLine();
            System.out.print("Ciclo: ");
            String ciclo = sc.nextLine();
            System.out.print("Curso: ");
            String curso = sc.nextLine();
            System.out.print("Grupo: ");
            String grupo = sc.nextLine();

            alumnos[i] = new Alumno(nia, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, grupo);
        }

        // Crear el documento XML
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Elemento raíz
            Element rootElement = doc.createElement("alumnos");
            doc.appendChild(rootElement);

            // Añadir los alumnos al documento
            for (Alumno alumno : alumnos) {
                Element alumnoElement = doc.createElement("alumno");
                rootElement.appendChild(alumnoElement);

                // Añadir atributos al alumno
                alumnoElement.setAttribute("NIA", Integer.toString(alumno.getNia()));
                alumnoElement.setAttribute("Nombre", alumno.getNombre());
                alumnoElement.setAttribute("Apellidos", alumno.getApellidos());
                alumnoElement.setAttribute("Genero", Character.toString(alumno.getGenero()));
                alumnoElement.setAttribute("FechaNacimiento", alumno.getFechaNacimiento());
                alumnoElement.setAttribute("Ciclo", alumno.getCiclo());
                alumnoElement.setAttribute("Curso", alumno.getCurso());
                alumnoElement.setAttribute("Grupo", alumno.getGrupo());
            }

            // Guardar el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("alumnos.xml"));

            transformer.transform(source, result);

            System.out.println("Archivo XML generado correctamente: alumnos.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	

}
