package segundaPractica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class CrearArchivo {

	public static void main(String[] arg) {

		Scanner sc = new Scanner(System.in);
		Scanner texto = null;
		String archivoCreado, rutaArchCreado, palabra, ruta, renglon;
		int contador = 0;
		ArrayList<String> renglones = new ArrayList();

		// Seleccionar la ruta del archivo a abrir
		JFileChooser archivoSelecccionado = new JFileChooser();
		archivoSelecccionado.showOpenDialog(archivoSelecccionado);

		// Crear archivo donde se va a escribir la informacion
		System.out.println("Nombre del archivo a crear:");
		archivoCreado = sc.nextLine();

		// Ruta donde se guarda el archivo
		System.out.println("Ruta para guardar el archivo: ");
		rutaArchCreado = sc.nextLine();

		// Palabra que se va a buscar
		System.out.println("Palabra a buscar: ");
		palabra = sc.nextLine();

		try {
			// Ruta del archivo seleccionado
			ruta = archivoSelecccionado.getSelectedFile().getAbsolutePath();
			File archivo = new File(ruta);
			texto = new Scanner(archivo);

			System.out.println("Palabra que se busca: " + palabra);

			try {
				FileWriter escribir = new FileWriter(rutaArchCreado + "\\" + archivoCreado);

				while (texto.hasNext()) { // recorrido del archivo
					renglon = texto.nextLine();
					if (renglon.contains(palabra)) {
						contador++;
						renglones.add(renglon);
					}
				}
				// Escritura en el archivo
				escribir.write("La palabra " + palabra + " se encontro: " + contador + " veces. \n\n");
				renglones.forEach((r) -> {
					try {
						escribir.write(r + "\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				escribir.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("La palabra " + palabra + " se encontro " + contador + " veces");
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (NullPointerException e) {
			System.out.println(e.toString() + " No se a seleccionado un archivo");
		} finally {
			if (texto != null) {
				texto.close();
			}
		}

		sc.close();
	}

}
