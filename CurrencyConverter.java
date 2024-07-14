import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class CurrencyConverter extends javax.swing.JFrame {

    // API URL and key (replace with your actual API key)
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/3241c35ea171275a7a5555ee/latest/USD";
    private HashMap<String, Double> exchangeRates = new HashMap<>();

    String[] currencyUnits = {"Units", "USD", "INR", "BRL", "CAD", "KES", "IDR", "NGN", "PHP", "PKR"};

    public CurrencyConverter() {
        initComponents();
        fetchExchangeRates();
    }

    private void fetchExchangeRates() {
        try {
            URI uri = new URI(API_URL);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response manually
            String json = response.toString();
            int startIndex = json.indexOf("\"conversion_rates\":{") + 19;
            int endIndex = json.indexOf("}", startIndex);
            String ratesString = json.substring(startIndex, endIndex);

            String[] ratesArray = ratesString.split(",");
            for (String rate : ratesArray) {
                String[] keyValue = rate.split(":");
                String key = keyValue[0].replace("\"", "").trim();
                double value = Double.parseDouble(keyValue[1].trim());
                exchangeRates.put(key, value);
            }

            // Output the rates to verify (optional)
            for (String key : exchangeRates.keySet()) {
                System.out.println(key + ": " + exchangeRates.get(key));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        firstCountry = new javax.swing.JComboBox<>();
        secondCountry = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        t2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton1.setBackground(Color.blue);
        jButton1.setForeground(Color.white);
        jButton2 = new javax.swing.JButton();
        jButton2.setBackground(Color.green);
        jButton2.setForeground(Color.black);
        jButton3 = new javax.swing.JButton();
        jButton3.setBackground(Color.red);
        jButton3.setForeground(Color.white);
        secondCurrencyUnit = new javax.swing.JLabel();
        firstCurrencyUnit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(245,167,46));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setText("CURRENCY CONVERTER");

        firstCountry.setFont(new java.awt.Font("Tahoma", 1, 18));
        firstCountry.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Choose One...", "USD", "INR", "BRL", "CAD", "KES", "IDR", "NGN", "PHP", "PKR"}));
        firstCountry.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                firstCountryItemStateChanged(evt);
            }
        });

        secondCountry.setFont(new java.awt.Font("Tahoma", 1, 18));
        secondCountry.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Choose One...", "USD", "INR", "BRL", "CAD", "KES", "IDR", "NGN", "PHP", "PKR"}));
        secondCountry.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                secondCountryItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15));
        jLabel2.setText("From Currency");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 15));
        jLabel3.setText("To Currency");
        t1.setFont(new java.awt.Font("Tahoma", 1, 18));
        t2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jButton1.setText("Convert Currency");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18));
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        secondCurrencyUnit.setFont(new java.awt.Font("Tahoma", 1, 14));
        secondCurrencyUnit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        secondCurrencyUnit.setText("units");

        firstCurrencyUnit.setFont(new java.awt.Font("Tahoma", 1, 14));
        firstCurrencyUnit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstCurrencyUnit.setText("units");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(290, 290, 290))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(firstCountry, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(t1)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(firstCurrencyUnit, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(81, 81, 81)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jLabel3)
                                                                .addComponent(secondCountry, 0, 272, Short.MAX_VALUE)
                                                                .addComponent(t2))
                                                        .addComponent(secondCurrencyUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(jButton2)
                                                .addGap(94, 94, 94)
                                                .addComponent(jButton3)))
                                .addContainerGap(114, Short.MAX_VALUE))
        );

        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(firstCountry, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                        .addComponent(secondCountry))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(t1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                        .addComponent(t2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(secondCurrencyUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(23, 23, 23))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(firstCurrencyUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2)
                                        .addComponent(jButton3))
                                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        firstCountry.setSelectedIndex(0);
        secondCountry.setSelectedIndex(0);
        t1.setText("");
        t2.setText("");
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (firstCountry.getSelectedItem().equals("Choose One...") || secondCountry.getSelectedItem().equals("Choose One...") || t1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return;
        }

        double amountToChange = Double.parseDouble(t1.getText());
        double amountChanged = 0.0;
        double amountInBase = 0.0;

        String fromCurrency = firstCountry.getSelectedItem().toString();
        String toCurrency = secondCountry.getSelectedItem().toString();

        if (exchangeRates.containsKey(fromCurrency) && exchangeRates.containsKey(toCurrency)) {
            amountInBase = amountToChange / exchangeRates.get(fromCurrency);
            amountChanged = amountInBase * exchangeRates.get(toCurrency);
        } else {
            JOptionPane.showMessageDialog(null, "Currency not supported");
            return;
        }

        String value = String.format("%.3f", amountChanged);
        t2.setText(value);
    }

    private void firstCountryItemStateChanged(java.awt.event.ItemEvent evt) {
        firstCurrencyUnit.setText(currencyUnits[firstCountry.getSelectedIndex()]);
    }

    private void secondCountryItemStateChanged(java.awt.event.ItemEvent evt) {
        secondCurrencyUnit.setText(currencyUnits[secondCountry.getSelectedIndex()]);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConverter().setVisible(true);
            }
        });
    }

    private javax.swing.JComboBox<String> firstCountry;
    private javax.swing.JLabel firstCurrencyUnit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> secondCountry;
    private javax.swing.JLabel secondCurrencyUnit;
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
}
