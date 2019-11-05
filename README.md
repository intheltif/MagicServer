# Program 2: Where the *Magic* Happens.

## Description

Author(s): Evert Ball and Garrett Starkey

Date: 01 November 2019

An application that implements the client-side and the server side of a 
new protocol the ‘magic’ protocol (RFC 12-mg). The 'magic' protocol
is a protocol that implements sending Magic the Gathering cards across
the internet. Both UDP and TCP implementations of this program have 
been implemented.


## Usage

To run this program, first compile it:

```bash
javac */*.java
```

After compiling, start the server-side implementation:

```bash
java server/MagicServerDriver <protocol> [<port_number>]
```

Next, start the client-side implementation:
```bash
java server/MagicServerDriver <protocol> <hostname/IP> [<port_number>] [<flag>]
```

Please see RFC 12-mg for a list of appropriate flags and a overview of 
how the 'magic' protocol works.

## Known Issues

The client-side implementation of the UDP version of this project only
prints the first card that it was sent and then continually prints that
card 37 times. After printing the card 37 times, the program hangs.
