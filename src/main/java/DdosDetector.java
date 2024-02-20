//import java.util.HashMap;
//
//public class DdosDetector {
//
//
//    private static final double THRESHOLD = 2.0; // Adjust the threshold based on your environment
//
//    public static double calculateEntropy(String data) {
//        HashMap<Character, Integer> frequencyMap = new HashMap<>();
//
//        for (char c : data.toCharArray()) {
//            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
//        }
//
//        double entropy = 0.0;
//        int dataSize = data.length();
//
//        for (int frequency : frequencyMap.values()) {
//            double probability = (double) frequency / dataSize;
//            entropy -= probability * (Math.log(probability) / Math.log(2));
//        }
//
//        return entropy;
//    }
//
//    public static void main(String[] args) {
//      // this url is used for smaller data set items
////        String originalNormalTraffic = "GET /home\nPOST /submit\nGET /images/logo.png\n";
////        String originalDdosTraffic = "GET /index.html\nGET /index.html\nGET /index.html\n";
//
//
//        String originalNormalTraffic = "GET /home\nPOST /submit\nGET /images/logo.png\n"
//                + "GET /page1\nPOST /data\nGET /images/header.png\n"
//                + "GET /about\nPOST /login\nGET /styles/main.css\n"
//                + "GET /contact\nPOST /submitForm\nGET /images/background.jpg\n";
//
//        String originalDdosTraffic = "GET /index.html\nGET /index.html\nGET /index.html\n"
//                + "GET /page1\nGET /page1\nGET /page1\n"
//                + "GET /about\nGET /about\nGET /about\n"
//                + "GET /contact\nGET /contact\nGET /contact\n";
//
//
//
//        double originalNormalEntropy = calculateEntropy(originalNormalTraffic);
//        double originalDdosEntropy = calculateEntropy(originalDdosTraffic);
//
//        System.out.println("Original Normal Traffic Entropy: " + originalNormalEntropy);
//        System.out.println("Original DDoS Traffic Entropy: " + originalDdosEntropy);
//
//        if (originalDdosEntropy > THRESHOLD) {
//            System.out.println("Original High entropy detected. Possible DDoS attack!");
//        } else {
//            System.out.println("Original Normal entropy. No apparent DDoS attack.");
//        }
//
//
//        String providedNormalTraffic = "GET /index.html\nPOST /submit\nGET /images/logo.png\n";
//        String providedDdosTraffic = "GET /index.html\nGET /index.html\nGET /index.html\n";
//
//        double providedNormalEntropy = calculateEntropy(providedNormalTraffic);
//        double providedDdosEntropy = calculateEntropy(providedDdosTraffic);
//
//        System.out.println("\nProvided Normal Traffic Entropy: " + providedNormalEntropy);
//        System.out.println("Provided DDoS Traffic Entropy: " + providedDdosEntropy);
//
//        if (providedDdosEntropy > THRESHOLD) {
//            System.out.println("Provided High entropy detected. Possible DDoS attack!");
//        } else {
//            System.out.println("Provided Normal entropy. No apparent DDoS attack.");
//        }
//    }
//}


import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class DdosDetector {
    private static final double THRESHOLD;
    private static final String DDOS_PATHS;
    private static final int MAX_CONNECTIONS;
    private static final int TIMEOUT_SECONDS;

    static {
        Properties properties = new Properties();
        try (InputStream input = DdosDetector.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        THRESHOLD = Double.parseDouble(properties.getProperty("threshold", "2.0"));
        DDOS_PATHS = properties.getProperty("ddosPaths", "");
        MAX_CONNECTIONS = Integer.parseInt(properties.getProperty("maxConnections", "1000"));
        TIMEOUT_SECONDS = Integer.parseInt(properties.getProperty("timeoutSeconds", "10"));
    }




    public static double calculateEntropy(String data) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : data.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        double entropy = 0.0;
        int dataSize = data.length();

        for (int frequency : frequencyMap.values()) {
            double probability = (double) frequency / dataSize;
            entropy -= probability * (Math.log(probability) / Math.log(2));
        }

        return entropy;
    }

    public static void main(String[] args) {
        // Original dataset for smaller data set items
        String originalNormalTraffic = "GET /home\nPOST /submit\nGET /images/logo.png\n";
        String originalDdosTraffic = "GET /index.html\nGET /index.html\nGET /index.html\n";

        double originalNormalEntropy = calculateEntropy(originalNormalTraffic);
        double originalDdosEntropy = calculateEntropy(originalDdosTraffic);

        System.out.println("Original Normal Traffic Entropy: " + originalNormalEntropy);
        System.out.println("Original DDoS Traffic Entropy: " + originalDdosEntropy);


        if (originalDdosEntropy > THRESHOLD) {
            System.out.println("Original High entropy detected. Possible DDoS attack!");
        } else {
            System.out.println("Original Normal entropy. No apparent DDoS attack.");
        }

        // Provided dataset for testing purpose
        String providedNormalTraffic = "GET /index.html\nPOST /submit\nGET /images/logo.png\n";
        String providedDdosTraffic = "GET /index.html\nGET /index.html\nGET /index.html\n";

        double providedNormalEntropy = calculateEntropy(providedNormalTraffic);
        double providedDdosEntropy = calculateEntropy(providedDdosTraffic);

        System.out.println("\nProvided Normal Traffic Entropy: " + providedNormalEntropy);
        System.out.println("Provided DDoS Traffic Entropy: " + providedDdosEntropy);
        System.out.println("DDoS Paths: " + DDOS_PATHS);

        if (providedDdosEntropy > THRESHOLD) {
            System.out.println("Provided High entropy detected. Possible DDoS attack!");
        } else {
            System.out.println("Provided Normal entropy. No apparent DDoS attack.");
        }
    }
}

