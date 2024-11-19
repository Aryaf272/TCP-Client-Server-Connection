
import java.io.*;
import java.net.*;
public class TCPServer {
    public static void main(String[] args) {
         try (ServerSocket serverSocket = new ServerSocket(12346)) {
             System.out.println("Server is running...");
             
             while (true) {
                 try (Socket clientSocket = serverSocket.accept();
                      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                     
                     // Read client's request
                     String request = in.readLine();
                     
                     // Process client's request and send response
                     String response = processRequest(request);
                     out.println(response);
                    
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
     
     private static String processRequest(String request) {
         // Split request into parts
         String[] parts = request.split(" ");
         if (parts.length != 2) {
             return "500 empty request";
         }
         
         // Extract conversion method and number
         String letter = parts[0];
         int number;
         try {
             number = Integer.parseInt(parts[1]);
         } catch (NumberFormatException e) {
             return "400 The number is not valid";
         }
         
         // Perform conversion and generate response
         String conversionResult = convertNumber(letter, number);
         if (conversionResult.equals("Invalid conversion method")) {
             return "300 Bad request";
         } else {
             return "200 oK , the number is :" + conversionResult;
         }
     }
     
     private static String convertNumber(String letter, int number) {
         if (letter.equalsIgnoreCase("B")) {
             // Convert the number to binary
             return Integer.toBinaryString(number);
         } else if (letter.equalsIgnoreCase("H")) {
             // Convert the number to hexadecimal
             return Integer.toHexString(number);
         } else {
             // Invalid conversion method
             return "Invalid conversion method";
         }
     }
}
