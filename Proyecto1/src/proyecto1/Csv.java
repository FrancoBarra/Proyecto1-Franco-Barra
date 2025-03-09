/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;

/**
 *
 * @author frank
 */
public class Csv {
    /**
     * Crea y guarda un archivo con los datos del grafo de la partida en el lugar especificado a traves del metodo "seleccionarArchivo"
     * @param nombreArchivo es el nombre con que se guardara el archivo csv
     * @param g es el grafo que guarda los cambios realizados en la partida
     */
    public void guardarGrafoCSV(String nombreArchivo, Grafo g) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Escribir encabezados
            writer.write("Filas,Columnas,MaxNodos,Banderas,Minas");
            writer.newLine();
            // Escribir información del grafo
            writer.write(g.getFilas() + "," + g.getColumnas() + "," + g.getMaxNodos() + "," + g.getBanderas() + "," + g.getMinas());
            writer.newLine();

            // Escribir encabezados para las casillas
            writer.write("ID,Fila,Columna,TieneMina,Revelada,Marcada,MinasAdyacentes");
            writer.newLine();

            // Escribir información de cada casilla
            for (Casilla casilla : g.getCasillas()) {
                writer.write(casilla.getId() + ","
                        + casilla.getFila() + ","
                        + casilla.getColumna() + ","
                        + casilla.getTieneMina() + ","
                        + casilla.getRevelada() + ","
                        + casilla.getMarcada() + ","
                        + casilla.getMinasAdyacentes());
                writer.newLine();
            }

            // Escribir la lista de adyacencia
            writer.write("ListaAdyacencia");
            writer.newLine();
            for (int i = 0; i < g.getListaAdy().length; i++) {
                NodoLista<Integer> nodo = g.getListaAdy()[i].getpFirst();
                StringBuilder adyacentes = new StringBuilder();
                while (nodo != null) {
                    adyacentes.append(nodo.getValor()).append(" ");
                    nodo = nodo.getpNext();
                }
                writer.write(i + ": " + adyacentes.toString().trim());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
 * Busca en la dirrección de memoria obtenida del metodo "seleccionarArchivo" para leerlo y devolver el grafo de la partida guardada
 * @param nombreArchivo Es el nombre del archivo que va a abrirse
 * @return Grafo
 */
 public Grafo leerGrafoCSV(String nombreArchivo) {
     //Se crea un grafo vacio que adoptara las caracteristicas del grafo de la partida guardada
    Grafo g = new Grafo(0, 0, 0); 

    try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
        String line;

        // Leer la primera línea (información del grafo)
        line = reader.readLine(); // Leer encabezados
        line = reader.readLine(); // Leer la línea con los valores

        String[] grafoInfo = line.split(",");
        g.setFilas(Integer.parseInt(grafoInfo[0]));
        g.setColumnas(Integer.parseInt(grafoInfo[1]));
        g.setMaxNodos(Integer.parseInt(grafoInfo[2]));
        g.setBanderas(Integer.parseInt(grafoInfo[3]));
        g.setMinas(Integer.parseInt(grafoInfo[4]));

        // Inicializar las casillas y la lista de adyacencia
        g.setCasillas(new Casilla[g.getMaxNodos()]);
        g.setListaAdy(new List[g.getMaxNodos()]);
        for (int i = 0; i < g.getMaxNodos(); i++) {
            g.getListaAdy()[i] = new List();
        }

        // Leer la segunda línea (encabezados de las casillas)
        reader.readLine(); // Leer "ID,Fila,Columna,TieneMina,Revelada,Marcada,MinasAdyacentes"

        // Leer cada línea de las casillas
        while ((line = reader.readLine()) != null) {
            String[] datos = line.split(",");

            // Asegurarse de que hay suficientes datos
            if (datos.length != 7) {
                continue; // O manejar el error de alguna manera
            }

            String id = datos[0];
            int fila = Integer.parseInt(datos[1]);
            int columna = Integer.parseInt(datos[2]);
            boolean tieneMina = Boolean.parseBoolean(datos[3]);
            boolean revelada = Boolean.parseBoolean(datos[4]);
            boolean marcada = Boolean.parseBoolean(datos[5]);
            int minasAdyacentes = Integer.parseInt(datos[6]);

            // Encontrar el índice de la casilla en el arreglo
            int indice = fila * g.getColumnas() + columna;

            // Actualizar la casilla
            g.getCasillas()[indice] = new Casilla(id, fila, columna);
            g.getCasillas()[indice].setTieneMina(tieneMina);
            g.getCasillas()[indice].setRevelada(revelada);
            g.getCasillas()[indice].setMarcada(marcada);
            g.getCasillas()[indice].setMinasAdyacentes(minasAdyacentes);
        }

        // Leer la lista de adyacencia
        line = reader.readLine(); // Leer "ListaAdyacencia"
        while ((line = reader.readLine()) != null) {
            String[] partes = line.split(":");
            int index = Integer.parseInt(partes[0].trim());
            String[] adyacentes = partes[1].trim().split(" ");
            for (String adyacente : adyacentes) {
                int vecinoIndex = Integer.parseInt(adyacente);
                if (!g.getListaAdy()[index].busqueda(vecinoIndex)) {
                    g.getListaAdy()[index].addFirst(vecinoIndex);
                }
                if (!g.getListaAdy()[vecinoIndex].busqueda(index)) {
                    g.getListaAdy()[vecinoIndex].addFirst(index);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (NumberFormatException e) {
        System.err.println("Error al convertir un número: " + e.getMessage());
    }
    return g;
}
    /**
     * Devuelve la dirección de memoria donde se guardara o leera la partida
     * @param modo parametro que define la acción a realizar ya sea "guardar" o "leer"
     * @return 
     */
    public String seleccionarArchivo(String modo) {
        JFileChooser fileChooser = new JFileChooser();
        String rutaArchivo = null;

        
        if (modo.equalsIgnoreCase("guardar")) {
            fileChooser.setDialogTitle("Guardar archivo CSV");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV Files", "csv"));
            int resultado = fileChooser.showSaveDialog(null);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
               
                if (!archivoSeleccionado.getName().endsWith(".csv")) {
                    archivoSeleccionado = new File(archivoSeleccionado.getAbsolutePath() + ".csv");
                }
                rutaArchivo = archivoSeleccionado.getAbsolutePath();
            }
        } else if (modo.equalsIgnoreCase("leer")) {
            fileChooser.setDialogTitle("Seleccionar archivo CSV para leer");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV Files", "csv"));
            int resultado = fileChooser.showOpenDialog(null);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
                rutaArchivo = archivoSeleccionado.getAbsolutePath();
            }
        }

        return rutaArchivo;
    }
}

