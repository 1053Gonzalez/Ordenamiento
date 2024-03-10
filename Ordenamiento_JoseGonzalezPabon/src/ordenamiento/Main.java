  //{21, 40, 4, 9, 10, 35}
package ordenamiento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class Main extends JFrame {
    
    //definimos las variables
    private int arraysize;
    private int[] array = {};
    private Burbuja burbuja;
    private int indicePasos = 0;
    private int totalPasos = 0;
    private int totalPasadas = 0;
    private GraficarBurbuja graficoBurbuja;
    private JTextArea areaDeTexto;
    private JButton botonRegresar;
    private JButton botonSiguiente;

    public Main() {
        mostrarMenu();
    }
    
    //mostramos menu inicial 
    private void mostrarMenu() {

        String inputSize = JOptionPane.showInputDialog(null, "Bienbenido al Software de ordenamiento de JoseGP.\n\nPor favor ingrese el tamaño del arreglo:\n");
        if (inputSize != null && !inputSize.isEmpty()) {
            try {
                arraysize = Integer.parseInt(inputSize);
                array = new int[arraysize];
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            return;  // Salimos del método si se presiona Cancelar o se cierra la ventana de entrada
        }

        // Ingresarmos los elementos del arreglo mediante JOptionPane
        for (int i = 0; i < arraysize; i++) {
            String inputValue = JOptionPane.showInputDialog(null, "Ingrese el elemento " + (i + 1) + ":");
            try {
                array[i] = Integer.parseInt(inputValue);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Configuracion de la ventana principal
        setTitle("Menú de Ordenamiento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); //para poder cambiar la ventana de dimensiones

        // Panel principal con BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Etiqueta con el título
        JLabel titleLabel = new JLabel();
        
        //titulo para la ventana del menu, donde de momento solo se encuentra el metodo burbuja
        titleLabel.setText("<html>Selecciona un método de ordenamiento para el arreglo:<br> ==>" + Arrays.toString(array) + "</html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Unispace", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        panel.add(titleLabel, BorderLayout.NORTH);

        // Panel con botones
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 100));

        // Botón Burbuja
        JButton burbujaButton = crearBoton("Ordenamiento Burbuja");
        burbujaButton = new JButton("Ordenamiento Burbuja");
        burbujaButton.setFont(new Font("Unispace", Font.BOLD, 18));
        burbujaButton.setForeground(Color.WHITE);
        burbujaButton.setBackground(Color.BLUE);
        burbujaButton.setOpaque(true);
        burbujaButton.setBorderPainted(false);
        burbujaButton.addActionListener(e -> realizarSiguientePaso());

        burbujaButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                dispose();  // Cierra la ventana actual
                inicializarVentanaBurbuja();
            }
        }
        );
        buttonPanel.add(burbujaButton);

        // Botón Salir
        JButton botonSalir = crearBoton("Salir");
        botonSalir.setFont(new Font("Unispace", Font.BOLD, 18));
        botonSalir.setForeground(Color.WHITE);
        botonSalir.setBackground(Color.BLUE);
        botonSalir.setOpaque(true);
        botonSalir.setBorderPainted(false);
        botonSalir.addActionListener(e -> realizarSiguientePaso());
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        );
        buttonPanel.add(botonSalir);
        panel.add(buttonPanel, BorderLayout.CENTER);

        
        // Configuracion de la ventana
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //metodo para crear botones
    private JButton crearBoton(String texto) {
        JButton button = new JButton(texto);
        button.setFont(new Font("Unispace", Font.PLAIN, 18));
        return button;
    }
    
    //metodo para mostrtar la ventana de ordenamiento burbuja 
    private void inicializarVentanaBurbuja() {
        
        //titulo de la ventana
        setTitle("Ordenamiento Burbuja Grafica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        
        //obtenemos el arreglo
        graficoBurbuja = new GraficarBurbuja(array);
        
        //botones
        botonSiguiente = new JButton("Siguiente");
        botonSiguiente.setFont(new Font("Unispace", Font.BOLD, 18));
        botonSiguiente.setForeground(Color.WHITE);
        botonSiguiente.setBackground(Color.BLUE);
        botonSiguiente.setOpaque(true);
        botonSiguiente.setBorderPainted(false);
        botonSiguiente.addActionListener(e -> realizarSiguientePaso());

        botonRegresar = new JButton("Regresar al Menú");  // Mueve esta línea aquí
        botonRegresar.setFont(new Font("Unispace", Font.BOLD, 18));
        botonRegresar.setForeground(Color.WHITE);
        botonRegresar.setBackground(Color.BLUE);
        botonRegresar.setOpaque(true);
        botonRegresar.setBorderPainted(false);
        botonRegresar.addActionListener(e -> regresaMenu());
        
        //area de texto
        areaDeTexto = new JTextArea(10, 40);
        areaDeTexto.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        areaDeTexto.setEditable(false);
        areaDeTexto.setLineWrap(true);
        areaDeTexto.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(areaDeTexto);

        JPanel panelDeContenido = new JPanel();
        panelDeContenido.setLayout(new BorderLayout());
        panelDeContenido.add(graficoBurbuja, BorderLayout.CENTER);
        panelDeContenido.add(botonSiguiente, BorderLayout.SOUTH);
        panelDeContenido.add(botonRegresar, BorderLayout.NORTH);  // Agrega el botón de regreso aquí
        panelDeContenido.add(scrollPane, BorderLayout.EAST);
        
        //dimenciones de la ventana
        setContentPane(panelDeContenido);
        pack();
        setSize(1000, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        burbuja = new Burbuja(array);
    }
    
    //metotdo para regresar al menu
    private void regresaMenu() {
        dispose();  // Cierra la ventana actual
        mostrarMenu();  // Muestra el menú
    }

    //Metodo que realiza validaciones y conteo de los pasos y pasadas del algoritmo de ordenamiento
    private void realizarSiguientePaso() {
        if (estaOrdenado()) {
            areaDeTexto.append("\n¡Arreglo ordenado!\n");
            areaDeTexto.append("Número total de pasos: " + totalPasos + "\n");
            areaDeTexto.append("Número total de pasadas: " + totalPasadas + "\n");
            return;
        }

        int i = indicePasos;
        int j = indicePasos + 1;

        areaDeTexto.append("Paso " + (indicePasos + 1) + ": ");
        areaDeTexto.append("Comparando " + array[i] + " con " + array[j]);

        if (array[i] > array[j]) {
            areaDeTexto.append(", " + array[i] + " es mayor que " + array[j] + "\n");
            areaDeTexto.append("Intercambiando " + array[i] + " con " + array[j] + "\n");

            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        } else {
            areaDeTexto.append(", " + array[i] + " es menor o igual a " + array[j] + "\n");
            areaDeTexto.append("No se realiza intercambio\n");
        }

        System.out.println(Arrays.toString(array));
        areaDeTexto.append("" + Arrays.toString(array) + "\n\n");

        indicePasos++;
        totalPasos++;

        if (indicePasos >= arraysize - totalPasadas - 1) {
            areaDeTexto.append("Finalizada pasada " + (totalPasadas + 1) + "\n");
            indicePasos = 0;
            totalPasadas++;
        }

        graficoBurbuja.actualizarArray(array);  // Update the graphical representation
    }
    
    //mettodo que valida si el arreglo esta ordenado
    private boolean estaOrdenado() {
        for (int i = 0; i < arraysize - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
