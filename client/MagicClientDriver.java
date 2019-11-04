package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This class contains an application that can drive both the TCP and UDP
 * implementations of a <code>MagicClient</code>.
 *
 * @author Evert Ball
 * @version 01 November 2019
 */
public class MagicClientDriver {


    private static final int FAILURE = 1;
    private static final int SUCCESS = 0;

    /**
     * Provides the entry point of the program.
     *
     * @param args Command line arguemnts to the program. There must be exactly
     *             2 or 3 arguments. The first argument must be either "tcp" or
     *             "udp". The second argument must be the hostname or IP
     *             address of a remote host running a magic server for the
     *             specified protocol. The third parameter, if present, is 
     *             either the port number or the flag. The four argument, if
     *             present, must be the flag (in which case the third argument
     *             must be the port number).
     */
    public static void main(String[] args) {

        // Check for correct # of args
        if(args.length < 2 || args.length > 4) {
            System.out.println("Usage: MagicClientDriver <PROTOCOL> " + 
                "<IP/HOST> [<PORT> [<FLAG>]]");
        } // end usage check

        if(args[0].toLowerCase().equals("tcp")) {
            if(args.length == 2) {
                try {
                    AbstractMagicClient tcp = new MagicTcpClient(InetAddress.getByName(args[1]));
                    tcp.printToStream(System.out);
                } catch (UnknownHostException uhe) {
                    System.err.println("Unknown host, cannot connect.");
                    System.exit(FAILURE);
                } catch (IOException ioe) {
                    System.err.println("Encountered IO error. Cannot continue. ");
                    System.exit(FAILURE);
                }
            } else if(args.length == 3) {
                try {
                    AbstractMagicClient tcp = new MagicTcpClient(InetAddress.getByName(args[1]), Integer.parseInt(args[2]));
                    tcp.printToStream(System.out);
                } catch(NumberFormatException nfe) {
                    try {
                        AbstractMagicClient tcp = new MagicTcpClient(InetAddress.getByName(args[1]), args[2]);
                        tcp.printToStream(System.out);
                    } catch(UnknownHostException uhe) {
                        System.err.println("Unknown host, cannot connect.");
                        System.exit(FAILURE);
                    } catch(IOException ioe) {
                        System.err.println("Encountered IO error. Cannot continue. ");
                        System.exit(FAILURE);
                    } // end try-catch
                } catch (UnknownHostException uhe) {
                    System.err.println("Unknown host, cannot connect.");
                    System.exit(FAILURE);
                } catch (IOException ioe) {
                    System.err.println("Encountered IO error. Cannot continue. ");
                    System.exit(FAILURE);
                } // end try-catch
            } else if(args.length == 4) {
                try {
                    AbstractMagicClient tcp = new MagicTcpClient(InetAddress.getByName(args[1]), Integer.parseInt(args[2]), args[3]);
                    tcp.printToStream(System.out);
                } catch(NumberFormatException nfe) {
                    System.err.println("Third argument must be a port number. Exiting...");
                    System.exit(FAILURE);
                } catch(UnknownHostException uhe) {
                    System.err.println("Unknown host, cannot connect.");
                    System.exit(FAILURE);
                } catch(IOException ioe) {
                    System.err.println("Encountered IO error. Cannot continue. ");
                    System.exit(FAILURE);
                } // end try-catch
            } // end if statement
        } else if(args[0].toLowerCase().equals("udp")) {
            System.out.println("Still under construction, please be patient");
            System.exit(2);
        }

        System.exit(SUCCESS);

    } // end main method

} // end MagicClientDriver class
