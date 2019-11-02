package server;

import java.io.FileNotFoundException;

/**
 * This class contains an application that can drive both the TCP and UDP
 * implementations of a <code>MagicServer</code>.
 *
 * @author Evert Ball
 * @version 01 November 2019
 */
public class MagicServerDriver {

    private static final int FAILURE = 1;
    private static final int SUCCESS = 0;
    private static final String FILE_NOT_FOUND_ERR =
            "Card file could not be found. Please " +
            "check that file exists and has the correct " +
            "permissions";

    
    /**
     * This method serves as the entry point of the program.
     *
     * @param args Command line arguments to the program. There must be 
     *             exactly 0 or 1 arguments. The second parameter, if present, 
     *             must be the port number on which the server will listen for 
     *             requests.
     */
    public static void main(String[] args) {

        // Check correct number of arguments
        if(args.length > 2) {
            System.out.println("Usage: java MagicServerDriver " +
                    "<transport_protocol> [<port number>]");
            System.exit(FAILURE);
        } else if(args.length < 1) {
            System.out.println("Usage: java MagicServerDriver " +
                    "<transport_protocol> [<port number>]");
            System.exit(FAILURE);
        }

        String transportProtocol = args[0];

        // Check transport protocol provided
        if(transportProtocol.toLowerCase().equals("tcp")) {
            if(args.length == 2) {
                try {
                    int port = Integer.parseInt(args[1]);
                    AbstractMagicServer tcp = new TcpMagicServer(port);
                } catch(FileNotFoundException fnfe) {
                    System.err.println(FILE_NOT_FOUND_ERR);
                }
            } else {
                try {
                    AbstractMagicServer tcp = new TcpMagicServer();
                } catch(FileNotFoundException fnfe) {
                    System.err.println(FILE_NOT_FOUND_ERR);
                }
            }
        } else if(transportProtocol.toLowerCase().equals("udp")) {
            if(args.length == 2) {
                try {
                    int port = Integer.parseInt(args[1]);
                    AbstractMagicServer udp = new UdpMagicServer(port);
                } catch(FileNotFoundException fnfe) {
                    System.err.println(FILE_NOT_FOUND_ERR);
                }
            } else {
                try {
                    AbstractMagicServer udp = new UdpMagicServer();
                } catch(FileNotFoundException fnfe) {
                    System.err.println(FILE_NOT_FOUND_ERR);
                }
            }
        }
        System.exit(SUCCESS);

    } // end main method

} // end MagicServerDriver class
