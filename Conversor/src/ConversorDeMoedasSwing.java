import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversorDeMoedasSwing extends JFrame {
	
	 private JComboBox<String> comboMoedasDestino;
	    private JTextField textFieldValorOrigem;
	    private JTextField textFieldValorDestino;
	    private JButton btnConverter;

	    
	    private static final double TAXA_DOLAR = 5.30;
	    private static final double TAXA_EURO = 6.20;
	    private static final double TAXA_PESO_ARGENTINO = 0.054;
	    private static final double TAXA_PESO_CHILENO = 0.0070;

	    public ConversorDeMoedasSwing() {
	       
	        setTitle("Conversor de Moedas");
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setLayout(new GridLayout(4, 2, 10, 10));
	        getContentPane().setBackground(Color.LIGHT_GRAY);

	        
	        String[] moedas = {"Dólar (USD)", "Euro (EUR)", "Peso Argentino (ARS)", "Peso Chileno (CLP)"};
	        comboMoedasDestino = new JComboBox<>(moedas);
	        comboMoedasDestino.setBackground(Color.GRAY);

	        JLabel labelMoedaDestino = new JLabel("Moeda de Destino:");
	        JLabel labelValorOrigem = new JLabel("Valor em Reais:");
	        JLabel labelValorDestino = new JLabel("Valor Convertido:");

	        textFieldValorOrigem = new JTextField(10);
	        textFieldValorDestino = new JTextField(10);
	        textFieldValorDestino.setEditable(false);
	        textFieldValorOrigem.setBackground(Color.WHITE);
	        textFieldValorDestino.setBackground(Color.WHITE);

	        btnConverter = new JButton("Converter");
	        btnConverter.addActionListener(new ActionListener() {
	           
	            public void actionPerformed(ActionEvent e) {
	                converterMoeda();
	            }
	        });
	        btnConverter.setBackground(Color.RED);
	        
	        add(labelMoedaDestino);
	        add(comboMoedasDestino);
	        add(labelValorOrigem);
	        add(textFieldValorOrigem);
	        add(labelValorDestino);
	        add(textFieldValorDestino);

	        add(btnConverter);

	        pack();
	        setLocationRelativeTo(null); 
	        setVisible(true);
	    }

	    private void converterMoeda() {
	        String moedaDestino = (String) comboMoedasDestino.getSelectedItem();
	        double valorOrigem;

	        try {
	            valorOrigem = Double.parseDouble(textFieldValorOrigem.getText());
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        double taxaDeCambio = getTaxaDeCambio(moedaDestino);

	        if (taxaDeCambio != -1) {
	            double valorDestino = valorOrigem / taxaDeCambio;
	            textFieldValorDestino.setText(String.format("%.2f", valorDestino));
	        } else {
	            JOptionPane.showMessageDialog(this, "Taxa de câmbio não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
	        }
	    }

	    private double getTaxaDeCambio(String moedaDestino) {
	        switch (moedaDestino) {
	            case "Dólar (USD)":
	                return TAXA_DOLAR;
	            case "Euro (EUR)":
	                return TAXA_EURO;
	            case "Peso Argentino (ARS)":
	                return TAXA_PESO_ARGENTINO;
	            case "Peso Chileno (CLP)":
	                return TAXA_PESO_CHILENO;
	            default:
	                return -1;
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new ConversorDeMoedasSwing();
	            }
	        });
	    }
	    
}
