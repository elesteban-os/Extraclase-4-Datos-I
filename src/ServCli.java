public class ServCli {
    private int clients;
    private boolean server = false;

    // Excepción cuándo el servidor ya está abierto.
    public void serverAlreadyOpen() throws Exception {
        throw new Exception("El servidor ya está abierto");
    }

    // Excepción cuándo el servidor está lleno.
    public void serverIsFull() throws Exception {
        throw new Exception("El servidor ya está lleno");   
    }

    // Abrir servidor y comprobar si ya está abierto.
    public void openServer() throws Exception {
        if (!this.server){
            this.server = true;
            System.out.println("Server abierto");
        } else {
            serverAlreadyOpen();
        }
    }

    // Unir al cliente y comprobar si ya existen dos clientes conectados.
    public void joinServer() throws Exception {
        if (this.clients < 2){
            this.clients++;
            System.out.println("Nuevo cliente conectado");
        } else{
            serverIsFull();
        }
    }
    
    public static void main(String[] args){
        // Instancia de la clase. El servidor tendrá que abrirse y solo acepta dos clientes.
        ServCli serverClient = new ServCli();

        // Esta parte del código abre un servidor. Al no haber uno ya abierto, no ejecuta excepción.
        try {
            serverClient.openServer();  
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Esta parte del código abre un servidor. Al haber uno ya abierto, ejecuta excepción.
        try {
            serverClient.openServer();  
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Esta parte del código abre un cliente. Hay ya 1/2 clientes abiertos, por lo tanto el cliente puede
        // conectarse sin problemas. Cuándo el cliente abandona, se ejecula el final.
        try {
            serverClient.joinServer();  
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Intento de cliente (Cliente cerrado)");
        }

        // Esta parte del código abre un cliente. Hay ya 2/2 clientes abiertos, por lo tanto el cliente puede
        // conectarse sin problemas. Cuándo el cliente abandona, se ejecula el final.
        try {
            serverClient.joinServer();  
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Intento de cliente (Cliente cerrado)");
        }

        // Esta parte del código abre un cliente. El servidor ya está lleno, por lo tanto no se podrá unir y 
        // mostrará una excepción.
        try {
            serverClient.joinServer();  
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Intento de cliente (Cliente cerrado)");
        }
    }
}
