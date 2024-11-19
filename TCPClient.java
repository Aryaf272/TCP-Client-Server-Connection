import java.io.*;
import java.net.*;
public class TCPClient {
 public static void main(String[] args) {
 String serverAddress = "127.0.0.1";
 int serverPort = 12346;
 try (Socket socket = new Socket(serverAddress, serverPort);
 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
 BufferedReader in = new BufferedReader(new
InputStreamReader(socket.getInputStream()));
 BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
 System.out.println("Connected to server. Input your command:");
 String userInput;
 while ((userInput = stdIn.readLine()) != null) {
 out.println(userInput);
 String response = in.readLine();
 System.out.println("Server response: " + response);
 }}
 /*} catch (UnknownHostException e) {
 System.err.println("Unknown host: " + serverAddress);
 } */ catch (IOException e) {
 System.err.println("Error connecting to the server: " + e.getMessage());
 }}}
