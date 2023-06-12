import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class mainForm extends JFrame {

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextField textIngresoNombre;
    private JButton ingresarPlatoButton;
    private JTextArea textAIngresoPlatos;
    private JButton QuemarDatosButton;
    private JTextField textIngresoPrecio;
    private JTextField textIngresoCalorias;
    private JTextField textIngresoPreparacion;
    private JButton buscarModifButton;
    private JButton modificarModifButton;
    private JTextField textoModifNombre;
    private JTextField textoModifPrecio;
    private JTextField textoModifCalorias;
    private JTextField textoModifPreparacion;
    private JTextArea textAModif;
    private JButton ButtonBuscarEliminar;
    private JTextField textNombreEliminar;
    private JTextArea textAEliminar;
    private JButton eliminarButton;
    private JComboBox<String> comboBoxOrder;
    private JButton mostrarButton;
    private JTextArea textAMostrar;
    private JButton buscarButton;
    private JTextField textBuscarPlatoOrden;

    private Menu menu;

    public mainForm() {
        menu = new Menu();

        ingresarPlatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textIngresoNombre.getText();
                double precio = obtenerDoubleValido(textIngresoPrecio.getText());
                int calorias = obtenerEnteroValido(textIngresoCalorias.getText());
                int tiempoPreparacion = obtenerEnteroValido(textIngresoPreparacion.getText());

                if (nombre.isEmpty() || precio == -1 || calorias == -1 || tiempoPreparacion == -1) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, ingrese todos los datos correctamente.");
                } else {
                    Plato plato = new Plato(nombre, precio, calorias, tiempoPreparacion);
                    menu.agregarPlato(plato);

                    actualizarTextAIngresoPlatos();
                    limpiarCamposIngreso();
                }
            }
        });

        modificarModifButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textoModifNombre.getText();
                double precio = Double.parseDouble(textoModifPrecio.getText());
                int calorias = Integer.parseInt(textoModifCalorias.getText());
                int tiempoPreparacion = Integer.parseInt(textoModifPreparacion.getText());

                if (nombre.isEmpty() || textoModifPrecio.getText().isEmpty() || textoModifCalorias.getText().isEmpty() || textoModifPreparacion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, ingrese todos los datos del plato.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Plato platoSeleccionado = obtenerPlatoSeleccionado();
                if (platoSeleccionado != null) {
                    platoSeleccionado.setNombre(nombre);
                    platoSeleccionado.setPrecio(precio);
                    platoSeleccionado.setCalorias(calorias);
                    platoSeleccionado.setTiempoPreparacion(tiempoPreparacion);
                    actualizarTextAreaModif();
                    limpiarCamposModif();
                }
            }
        });

        QuemarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.quemarDatosPredeterminados();
                actualizarTextAIngresoPlatos();
            }
        });

        buscarModifButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePlato = textoModifNombre.getText();
                Plato plato = menu.buscarPlato(nombrePlato);
                if (plato != null) {
                    textoModifPrecio.setText(String.valueOf(plato.getPrecio()));
                    textoModifCalorias.setText(String.valueOf(plato.getCalorias()));
                    textoModifPreparacion.setText(String.valueOf(plato.getTiempoPreparacion()));
                    textoModifPrecio.setEditable(true);
                    textoModifCalorias.setEditable(true);
                    textoModifPreparacion.setEditable(true);

                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Plato no encontrado.");
                }
            }
        });

        modificarModifButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePlato = textoModifNombre.getText();
                double precio = obtenerDoubleValido(textoModifPrecio.getText());
                int calorias = obtenerEnteroValido(textoModifCalorias.getText());
                int tiempoPreparacion = obtenerEnteroValido(textoModifPreparacion.getText());

                if (nombrePlato.isEmpty() || precio == -1 || calorias == -1 || tiempoPreparacion == -1) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, ingrese todos los datos correctamente.");
                } else {
                    Plato plato = new Plato(nombrePlato, precio, calorias, tiempoPreparacion);
                    boolean exito = menu.modificarPlato(nombrePlato, plato);
                    if (exito) {
                        JOptionPane.showMessageDialog(mainPanel, "Plato modificado correctamente.");
                        actualizarTextAModif();
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "Plato no encontrado.");
                    }
                }
            }
        });

        ButtonBuscarEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePlato = textNombreEliminar.getText();
                Plato plato = menu.buscarPlato(nombrePlato);
                if (plato != null) {
                    textAEliminar.setText(plato.toString());
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Plato no encontrado.");
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePlato = textNombreEliminar.getText();
                boolean exito = menu.eliminarPlato(nombrePlato);
                if (exito) {
                    JOptionPane.showMessageDialog(mainPanel, "Plato eliminado correctamente.");
                    textAEliminar.setText("");
                    actualizarTextAIngresoPlatos();
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Plato no encontrado.");
                }
            }
        });

        comboBoxOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ordenSeleccionado = (String) comboBoxOrder.getSelectedItem();
                if (ordenSeleccionado != null) {
                    switch (ordenSeleccionado) {
                        case "Nombre":
                            menu.ordenarPorNombre();
                            break;
                        case "Precio":
                            menu.ordenarPorPrecio();
                            break;
                        case "Calorias":
                            menu.ordenarPorCalorias();
                            break;
                        case "Tiempo de Preparacion":
                            menu.ordenarPorTiempoPreparacion();
                            break;
                    }
                    actualizarTextAMostrar();
                }
            }
        });

        // Crear un modelo para el JComboBox
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();


        comboBoxModel.addElement("Nombre");
        comboBoxModel.addElement("Precio");
        comboBoxModel.addElement("Calorias");
        comboBoxModel.addElement("Tiempo de Preparacion");


        comboBoxOrder.setModel(comboBoxModel);


        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTextAMostrar();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePlato = textBuscarPlatoOrden.getText();
                int indice = menu.buscarPlatoOrden(nombrePlato);
                if (indice != -1) {
                    JOptionPane.showMessageDialog(mainPanel, "El plato se encuentra en el índice: " + indice);
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Plato no encontrado.");
                }
            }
        });
    }

 
    private double obtenerDoubleValido(String texto) {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    private Plato obtenerPlatoSeleccionado() {
        int inicioSeleccion = textAModif.getSelectionStart();
        int finSeleccion = textAModif.getSelectionEnd();

        if (inicioSeleccion != finSeleccion) {
            String textoSeleccionado = textAModif.getText().substring(inicioSeleccion, finSeleccion);
            List<Plato> platos = menu.getPlatos();
            for (Plato plato : platos) {
                if (plato.toString().equals(textoSeleccionado)) {
                    return plato;
                }
            }
        }

        return null;
    }


    private void actualizarTextAreaModif() {
        textAModif.setText("");
        List<Plato> platos = menu.getPlatos();
        for (Plato plato : platos) {
            textAModif.append(plato.toString() + "\n");
        }
    }

    private void limpiarCamposModif() {
        textoModifNombre.setText("");
        textoModifPrecio.setText("");
        textoModifCalorias.setText("");
        textoModifPreparacion.setText("");
    }


    
    private int obtenerEnteroValido(String texto) {
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

 
    private void actualizarTextAIngresoPlatos() {
        textAIngresoPlatos.setText("");
        for (Plato plato : menu.getPlatos()) {
            textAIngresoPlatos.append(plato.toString() + "\n");
        }
    }

  
    private void limpiarCamposIngreso() {
        textIngresoNombre.setText("");
        textIngresoPrecio.setText("");
        textIngresoCalorias.setText("");
        textIngresoPreparacion.setText("");
    }


    private void actualizarTextAModif() {
        textAModif.setText("");
        String nombrePlato = textoModifNombre.getText();
        Plato plato = menu.buscarPlato(nombrePlato);
        if (plato != null) {
            textAModif.setText(plato.toString());
        }
    }

    // Actualizar el campo de texto textAMostrar con los platos ordenados
    private void actualizarTextAMostrar() {
        textAMostrar.setText("");
        ArrayList<Plato> platos = menu.getPlatos();
        for (Plato plato : platos) {
            textAMostrar.append(plato.toString() + "\n");
        }
    }

 
    public static void main(String[] args) {
        JFrame frame = new JFrame("Restaurante App");
        frame.setContentPane(new mainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
