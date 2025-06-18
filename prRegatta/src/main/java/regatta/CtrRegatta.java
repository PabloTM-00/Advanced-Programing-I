package regatta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrRegatta implements ActionListener {

    private Regatta model;
    private ViewRegatta view;

    public CtrRegatta(ViewRegatta view) {
        this.view = view;
        this.model = new Regatta();  // Inicializa regata vacía

        // Registra el controlador en la vista (botones)
        view.controller(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case ViewRegatta.READ:
                readAction();
                break;
            case ViewRegatta.MOVE:
                moveAction();
                break;
            case ViewRegatta.SAVE:
                saveAction();
                break;
            default:
                // Comando no reconocido, opcional mostrar mensaje o ignorar
                break;
        }
    }

    private void readAction() {
        try {
            // Crear nueva regata
            model = new Regatta();

            // Leer barcos del archivo
            String inputFile = view.getInputFile();
            model.readFile(inputFile);

            // Limpiar texto en la vista
            view.clear();

            // Mostrar cada barco en una línea
            for (Ship ship : model.getParticipants()) {
                view.addLine(ship.toString());
            }

            view.setMesssage("Ships loaded successfully from " + inputFile);
        } catch (RegattaException ex) {
            view.setMesssage("Error reading file: " + ex.getMessage());
        } catch (Exception ex) {
            view.setMesssage("Unexpected error: " + ex.getMessage());
        }
    }

    private void moveAction() {
        try {
            model.move(10);  // Mover barcos 10 minutos
            view.clear();

            for (Ship ship : model.getParticipants()) {
                view.addLine(ship.toString());
            }

            view.setMesssage("Ships moved 10 minutes successfully.");
        } catch (Exception ex) {
            view.setMesssage("Error moving ships: " + ex.getMessage());
        }
    }

    private void saveAction() {
        try {
            String outputFile = view.getOutputFile();
            model.writeToFile(outputFile);
            view.setMesssage("Regatta saved successfully to " + outputFile);
        } catch (RegattaException ex) {
            view.setMesssage("Error saving file: " + ex.getMessage());
        } catch (Exception ex) {
            view.setMesssage("Unexpected error: " + ex.getMessage());
        }
    }
}
